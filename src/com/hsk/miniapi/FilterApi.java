package com.hsk.miniapi;

import com.hsk.miniapi.xframe.jwt.TokenUtil;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.supper.until.json.JsonUtil;
import com.hsk.xframe.api.persistence.SysUserInfo;
import org.hibernate.Session;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * author: yangfeng
 * time: 2020/4/9 9:33
 */
public class FilterApi extends OncePerRequestFilter {

    final String NO_FILTER_URL = "/shoppingLoginApi.htm,/weChatLoginApi.htm,/doNoFilterApi.htm";
    private String WECHAT_FILTER_STR = "/wechat/";

    final String TOKEN_HEAD = "Breazer ";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String uri = httpServletRequest.getRequestURI();
        String url = httpServletRequest.getRequestURL().toString();
        Boolean needFilter = true;
        Boolean nofilterBaseUrl = false;

        String[] filters = NO_FILTER_URL.split(",");
        for (String filterName : filters) {
            if (url.contains(filterName)) {
                needFilter = false;
                break;
            }
        }
        if (uri.indexOf("doApi.htm") < 0 && needFilter) { // pc端接口需要特殊处理
            nofilterBaseUrl = true;
        }
        //过滤微信端接口
//        if(url.contains(WECHAT_FILTER_STR)){
//            needFilter=false;
//            String openId = httpServletRequest.getHeader("openId");
//            if(openId==null || openId.equals("")){
//                SysRetrunMessage srm = new SysRetrunMessage(0L);
//                srm.setMeg("请传入openId!");
//                httpServletResponse.setCharacterEncoding("utf-8");
//                httpServletResponse.setContentType("application/json;charset=utf-8");
//                PrintWriter out = null;
//                out = httpServletResponse.getWriter();
//                out.write(JsonUtil.getjsonstr(srm));
//            }else{
//                filterChain.doFilter(httpServletRequest, httpServletResponse);
//            }
//        }
        if (uri.indexOf("doApi.htm") >= 0 && needFilter) { //api端需要进行验证
            String token = httpServletRequest.getHeader("token");
            if (token != null && token.startsWith(TOKEN_HEAD) && !token.trim().equals("")) {
                token = token.substring(TOKEN_HEAD.length());
                if (TokenUtil.TOKEN_MAP.containsKey(token)) {//如果存在，更新登录时间
                    Map<String, Object> tokenData = TokenUtil.TOKEN_MAP.get(token);
                    tokenData.put("createTime", new Date());
                    SysUserInfo account = (SysUserInfo) tokenData.get("account");
                    TokenUtil.TOKEN_MAP.put(token, tokenData);
                    String sessionId = httpServletRequest.getHeader("sessionId");
//                    String sessionId = httpServletRequest.getSession().getId();
//                    HttpSession session = SessionUtil.getSession(account.getAccount());
                    String accountName = SessionUtil.getLocalSessionId(sessionId);
//                    if (checkSessionTime(sessionId, account)) {
//                        SysRetrunMessage srm = new SysRetrunMessage(0L);
//                        srm.setMeg("已下线，请重新登录!");
//                        httpServletResponse.setCharacterEncoding("utf-8");
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = null;
//                        out = httpServletResponse.getWriter();
//                        out.write(JsonUtil.getjsonstr(srm));
//                        return;
//                    }
                    if (accountName == null || accountName.equals("") || !accountName.equals(account.getAccount())) {
                        SysRetrunMessage srm = new SysRetrunMessage(0L);
                        srm.setMeg("已下线，请重新登录!");
                        httpServletResponse.setCharacterEncoding("utf-8");
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = null;
                        out = httpServletResponse.getWriter();
                        out.write(JsonUtil.getjsonstr(srm));
                        return;
                    }
//                    SessionUtil.setLink(sessionId, account.getAccount());
                    if (account == null) {
                        SysRetrunMessage srm = new SysRetrunMessage(0L);
                        srm.setMeg("已下线，请重新登录!");
                        httpServletResponse.setCharacterEncoding("utf-8");
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = null;
                        out = httpServletResponse.getWriter();
                        out.write(JsonUtil.getjsonstr(srm));
                    } else {
                        httpServletRequest.getSession().setAttribute("shoppingApiAccount", tokenData.get("account"));
//                        SessionUtil.setLink(httpServletRequest.getSession().getId(), accountName);
                        httpServletRequest.getSession().setMaxInactiveInterval(60);
                        filterChain.doFilter(httpServletRequest, httpServletResponse);
                    }
                } else {
                    SysRetrunMessage srm = new SysRetrunMessage(0L);
                    srm.setMeg("token已注销,请重新获取!");
                    httpServletResponse.setCharacterEncoding("utf-8");
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = null;
                    out = httpServletResponse.getWriter();
                    out.write(JsonUtil.getjsonstr(srm));
                }
            } else {
                httpServletResponse.addHeader("auhorization", "false");
                SysRetrunMessage srm = new SysRetrunMessage(0L);
                srm.setMeg("请先登录获取token!");
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = null;
                out = httpServletResponse.getWriter();
                out.write(JsonUtil.getjsonstr(srm));
            }
        } else { //pc端不需要
//            if (nofilterBaseUrl && url.indexOf("www.cnyyk.cn:8082") < 0 && (url.indexOf("www.cnyyk.cn/") >= 0 || url.indexOf("www.cnyyk.cn:") >= 0 || url.indexOf("www.cnyyk.cn") >= 0)) {
////            if (nofilterBaseUrl && url.indexOf("show.cnyyk.cn:8080") < 0 && (url.indexOf("show.cnyyk.cn/") >= 0 || url.indexOf("show.cnyyk.cn:") >= 0 || url.indexOf("show.cnyyk.cn") >= 0)) {
////            if (nofilterBaseUrl && url.indexOf("localhost:8080") < 0 && (url.indexOf("localhost/") >= 0 || url.indexOf("localhost:") >= 0 || url.indexOf("localhost") >= 0)) {
//                if ((url.indexOf("index.htm") < 0 && url.indexOf("xiangxi.htm") < 0 && url.indexOf(".htm") >= 0)) {
//                    String contextPath = httpServletRequest.getContextPath();
//                    StringBuffer requestUrl = httpServletRequest.getRequestURL();
//                    httpServletRequest.setCharacterEncoding("UTF-8");
//                    String name = httpServletRequest.getParameter("name");
//                    httpServletResponse.setContentType("text/html");
//                    httpServletRequest.setAttribute("name",name);
//                    httpServletResponse.sendRedirect(contextPath + "/index.htm");
//                    return;
//                }
//            }
//            if (url.endsWith("localhost:8080") || url.endsWith("localhost:8080/dentistmall") || url.endsWith("localhost:8080/dentistmall/") ||
//                    url.endsWith("cnyyk.cn") || url.endsWith("cnyyk.cn/dentistmall") || url.endsWith("cnyyk.cn/dentistmall/") ||
//                    url.endsWith("cnyyk.cn:8080") || url.endsWith("cnyyk.cn:8080/dentistmall") || url.endsWith("cnyyk.cn:8080/dentistmall/") ||
//                    url.endsWith("cnyyk.cn:8082") || url.endsWith("cnyyk.cn:8082/dentistmall") || url.endsWith("cnyyk.cn:8082/dentistmall/")) {
//                String contextPath = httpServletRequest.getContextPath();
//                StringBuffer requestUrl = httpServletRequest.getRequestURL();
//                httpServletRequest.setCharacterEncoding("UTF-8");
//                String name = httpServletRequest.getParameter("name");
//                httpServletResponse.setContentType("text/html");
//                httpServletRequest.setAttribute("name",name);
//                httpServletResponse.sendRedirect("/dentistmall/index.htm");
//                return;
//            }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private boolean checkSessionTime(String sessionId, SysUserInfo sysUserInfo) {
        String session = SessionUtil.getSession(sysUserInfo.getAccount());

        return true;
    }
}

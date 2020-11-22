package com.hsk.miniapi.xframe.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.supper.until.json.JsonUtil;


public class TokenFilter implements Filter{
	
 
 
	private String NOT_FILTER_STR="/add.htm,get11.htm,/system/test.htm,/testList.htm,/system/login.htm,/system/hospitalLogin.htm,/system/angetlogin.htm,/system/getQn.htm,/system/getPhoneCode.htm";
	private String WECHAT_FILTER_STR="/wechat/";
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
	     HttpServletResponse resp = (HttpServletResponse) response;
	     resp.setHeader("Access-Control-Allow-Origin", "*");  
	     resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
	     resp.setHeader("Access-Control-Max-Age", "3600");  
	     resp.setHeader("Access-Control-Allow-Headers", "*");  //x-requested-with
	     //过滤不需要检测token的接口
	     String requestUrl=req.getRequestURL().toString();
	     String[] notStrArray=NOT_FILTER_STR.split(",");
	     boolean isFilter=true;
	     for(String notStr : notStrArray){
	    	 if(requestUrl.contains(notStr)){
	    		 isFilter=false;
	    		 filterChain.doFilter(req, resp);
	    		 break;
	    	 }
	     }
	     //过滤微信端接口
	     if(requestUrl.contains(WECHAT_FILTER_STR)){
    		 isFilter=false;
    		 String openId = req.getHeader("openId");
    		 if(openId==null || openId.equals("")){
    			 SysRetrunMessage srm = new SysRetrunMessage(0L);
				 srm.setMeg("请传入openId!");
				 response.setCharacterEncoding("utf-8");
				 response.setContentType("application/json;charset=utf-8");
				 PrintWriter out = null;
				 out = response.getWriter();
				 out.write(JsonUtil.getjsonstr(srm)); 
    		 }else{
    			 filterChain.doFilter(req, resp);
    		 }
    	 }
	     if(isFilter){
	    	 String token = req.getHeader("token");
				if(token!=null && !token.trim().equals("")){
					if(TokenUtil.TOKEN_MAP.containsKey(token)){//如果存在，更新登录时间
						Map<String,Object> tokenData = TokenUtil.TOKEN_MAP.get(token);
						tokenData.put("createTime", new Date());
						TokenUtil.TOKEN_MAP.put(token, tokenData);
						filterChain.doFilter(req, resp);
					}else{
						SysRetrunMessage srm = new SysRetrunMessage(0L);
						srm.setMeg("token已注销,请重新获取!");
						response.setCharacterEncoding("utf-8");
						response.setContentType("application/json;charset=utf-8");
						PrintWriter out = null;
						out = response.getWriter();
						out.write(JsonUtil.getjsonstr(srm)); 
					}
				}else{
					SysRetrunMessage srm = new SysRetrunMessage(0L);
					srm.setMeg("请先登录获取token!");
					response.setCharacterEncoding("utf-8");
					response.setContentType("application/json;charset=utf-8");
					PrintWriter out = null;
					out = response.getWriter();
					out.write(JsonUtil.getjsonstr(srm)); 
				}
	     }
	     
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}

package com.hsk.miniapi;

import com.hsk.exception.HSKException;
import com.hsk.supper.controller.imp.SupperController;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.supper.dto.config.StrAction;
import com.hsk.supper.until.charset.Encode;
import com.hsk.supper.until.javaclass.ClassUtil;
import com.hsk.supper.until.json.JsonUtil;
import com.hsk.supper.until.log.logUtil;
import com.hsk.supper.until.web.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * author: yangfeng
 * time: 2020/4/9 9:47
 */
@Controller
public class ApiController extends SupperController {
    private SysRetrunMessage srm = new SysRetrunMessage(0L);
    public logUtil hsklog = logUtil.getLogUtil(this);

    /**
     * 新的统一入口方法
     * @param attr
     * @param models
     * @param request
     * @param response
     * @param jsonStr json字符串，有则处理，没有则忽略
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doNewService.htm", method = {RequestMethod.POST})
    public String doNewService(StrAction attr, ModelMap models, HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonStr) throws Exception {
        return this.backStr(attr, models, request, response, jsonStr);
    }
    @RequestMapping(
            value = {"/doNewService.htm"},
            method = {RequestMethod.GET}
    )
    public String doServiceGET(StrAction attr, ModelMap models, HttpServletRequest request, HttpServletResponse response) throws HSKException {
        return this.backStr(attr, models, request, response, null);
    }

    @RequestMapping(value = {"/doApi.htm"}, method = {RequestMethod.POST})
    public String doApiService(StrAction attr, ModelMap models, HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonStr) throws Exception {
        return this.backStr(attr, models, request, response, jsonStr);
    }
    @RequestMapping(
            value = {"/doNoFilterApi.htm"},
            method = {RequestMethod.POST}
    )
    public String doNoFilterApiService(StrAction attr, ModelMap models, HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonStr) throws Exception {
        return this.backStr(attr, models, request, response, jsonStr);
    }

    @RequestMapping(
            value = {"/shoppingLoginApi.htm"},
            method = {RequestMethod.POST}
    )
    public String doShoppingLoginApi(StrAction attr, ModelMap models, HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonStr) throws Exception {
        return this.backStr(attr, models, request, response, jsonStr);
    }

    @RequestMapping(
            value = {"/weChatLoginApi.htm"},
            method = {RequestMethod.POST}
    )
    public String doWeChatLoginApi(StrAction attr, ModelMap models, HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonStr) throws Exception {
        return this.backStr(attr, models, request, response, jsonStr);
    }

    @RequestMapping(
            value = {"/businessAgent.htm"},
            method = {RequestMethod.POST}
    )
    public String doBusinessAgentApi(StrAction attr, ModelMap models, HttpServletRequest request, HttpServletResponse response, @RequestBody String jsonStr) throws Exception {
        return this.backStr(attr, models, request, response, jsonStr);
    }
    
    public String backStr(StrAction attr, ModelMap models, HttpServletRequest request, HttpServletResponse response, String jsonStr) throws HSKException, SecurityException {
        BindingAwareModelMap modelMap = (BindingAwareModelMap)models;
        Object ls_back = null;
        String msg = null;
        attr = attr.Format();
        if (SystemContext.default_chip) {
            this.srm.setCode(0L);
            this.srm.setMeg("操作出错，错误信息：系统过期！");
            WebUtils.sendObject(response, Encode.StringForObject(this.srm));
            return null;
        } else {
            String paramContent = request.getParameter("paramContent");
            String key;
            if (paramContent != null && !paramContent.isEmpty()) {
                try {
                    new HashMap();
                    Map<String, Object> lb_map = JsonUtil.getMap4Json(paramContent);
                    Iterator var11 = lb_map.keySet().iterator();

                    while(var11.hasNext()) {
                        key = (String)var11.next();
                        if (lb_map.get(key) != null && !lb_map.get(key).toString().trim().equals("")) {
                            request.setAttribute(key, lb_map.get(key));
                        }

                        if (key.equals("limit") && lb_map.get(key) != null && !lb_map.get(key).toString().trim().equals("")) {
                            SystemContext.setPageSize(this.getPageSize(lb_map.get(key).toString()));
                        }

                        if (key.equals("page") && lb_map.get(key) != null && !lb_map.get(key).toString().trim().equals("")) {
                            SystemContext.setOffset(this.getOffset(lb_map.get(key).toString()));
                        }
                    }
                } catch (Exception var16) {
                    List<Map> list_map = JsonUtil.getListMapArrayJson(paramContent);
                    request.setAttribute("SystemParamArray", list_map);
                }
            }

            if (attr.getCommandindex() != null && !attr.getCommandindex().isEmpty()) {
                ls_back = this.getIO_do(attr, request);
                WebUtils.sendObject(response, Encode.StringForObject(ls_back));
                return null;
            } else {
                Enumeration e = request.getParameterNames();

                while(e.hasMoreElements()) {
                    key = (String)e.nextElement();
                    String[] prame_value = request.getParameterValues(key);
                    request.setAttribute(key, prame_value.length > 1 ? prame_value : (prame_value.length == 1 ? prame_value[0] : null));
                    modelMap.addAttribute(key, prame_value.length > 1 ? prame_value : (prame_value.length == 1 ? prame_value[0] : null));
                }
                request.setAttribute("jsonStr", jsonStr);

                if (ClassUtil.famtstr.size() < 1) {
                    ClassUtil.famtstr.add("com.hsk.dreamSnallTown.api.persistence");
                    ClassUtil.famtstr.add("com.cn.websupper.app.pojo");
                    ClassUtil.famtstr.add("com.hsk.supper.dto.config");
                }

                Object obj_url;
                if (attr.getService() != null && attr.getFunc() != null) {
                    try {
                        obj_url = SystemContext.getBean(attr.getService());
                        Object[] args = new Object[0];
                        args = ClassUtil.backjsonstr(obj_url, attr.getFunc(), request);
                        ClassUtil.invokeMethod(obj_url, "setModel", new Object[]{modelMap});
                        ClassUtil.invokeMethod(obj_url, "setRequest", new Object[]{request});
                        ClassUtil.invokeMethod(obj_url, "setResponse", new Object[]{response});
                        ls_back = ClassUtil.invokeMethod(obj_url, attr.getFunc(), args);
                    } catch (Exception var15) {
                        var15.printStackTrace();
                        msg = var15.getMessage();
                        this.srm.setCode(0L);
                        this.srm.setMeg(msg == null ? "操作出错" : "操作出错，错误信息：" + msg);
                        ls_back = this.srm;
                    }
                }

                if (ls_back == null) {
                    ls_back = this.srm;
                } else {
                    request.setAttribute(attr.getParamname(), ls_back);
                }

                obj_url = modelMap.get("url");
                attr.setUrl(obj_url == null ? attr.getUrl() : (String)obj_url);
                String url = attr.getSimplifyUrl();
                if (url == null) {
                    String contentType = (String)modelMap.get("contentType");
                    String charset = (String)modelMap.get("charset");
                    String filename = (String)modelMap.get("filename");
                    if (contentType != null && charset != null) {
                        WebUtils.sendFileToClient(response, contentType, charset, filename, Encode.StringForObject(ls_back));
                    } else {
                        WebUtils.sendObject(response, Encode.StringForObject(ls_back));
                    }
                }

                return url;
            }
        }
    }

    private String getIO_do(StrAction attr, HttpServletRequest request) {
        return "";
    }

    public int getOffset(String att_offset) {
        int offset = 0;

        try {
            offset = Integer.parseInt(att_offset);
        } catch (NumberFormatException var4) {
        }

        return offset;
    }

    public int getPageSize(String att_size) {
        int PageSize = 20;

        try {
            PageSize = Integer.parseInt(att_size);
        } catch (NumberFormatException var4) {
        }

        return PageSize;
    }
}

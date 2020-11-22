package com.hsk.xframe.web.sysFeedBack.service.impl;

import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.ISysFeedbackDao;
import com.hsk.xframe.api.persistence.SysFeedbackEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.sysFeedBack.service.ISysFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SysFeedBackService extends DSTService implements ISysFeedBackService {
    @Autowired
    private ISysFeedbackDao sysFeedbackDao;

    //查询反馈列表
    public PagerModel getSysFeedBackList(String input1,String selectType,Integer state,String questionType,String dateInput1,String dateInput2,Integer limit, Integer page) throws HSKException{
        PagerModel pm=new PagerModel();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<Map<String, Object>> mxList = sysFeedbackDao.getSysFeedBackList(input1, selectType, state, questionType, dateInput1, dateInput2, limit, page);
            if (mxList!=null&&mxList.size()>0){
            for (Map<String, Object> map : mxList) {
                Object createDate=map.get("createDate");
                if (createDate!=null&&createDate!=""){
                    String createDate1=createDate.toString();
                    map.put("createDate1",sdf.format(createDate));
                }else {
                    map.put("createDate1","");
                }
                Object questiontype=map.get("questiontype");
                if (questiontype!=null&&questiontype!=""){
                    map.put("questiontype","建议");
                }else {
                    map.put("questiontype","建议");
                }
                Object type=map.get("type");
                if (type!=null&&type!=""){
                    if (type.equals(1)){
                        map.put("type","后台");
                    }
                    if (type.equals(2)){
                        map.put("type","小程序商城");
                    }
                    if (type.equals(3)){
                        map.put("type","小程序代理商");
                    }

                }else {
                    map.put("type","");
                }
                Object state1=map.get("state");
                if (state1!=null&&state1!=""){
                    if (state1.equals(1)){
                        map.put("state","待处理");
                    }else if (state1.equals(2)){
                        map.put("state","已处理");
                    }
                }else {
                    map.put("state","");
                }
                Object fbValue=map.get("fbValue");
                if (fbValue!=null&&fbValue!=""){
                    map.put("fbValue",fbValue);
                }else {
                    map.put("fbValue","");
                }
                Object processingLog=map.get("processingLog");
                if (processingLog!=null&&processingLog!=""){
                    map.put("processingLog",processingLog);
                }else {
//                    if (state1.equals(1)){
//                        Object fbId1=map.get("fbId");
//                        Integer fbId=Integer.parseInt(fbId1.toString());
//                        SysFeedbackEntity sysFeedbackEntity=sysFeedbackDao.findSysFeedBackById(fbId);
//                        String ProcessingLogStr="待处理,"+"反馈人"+","+sdf.format(createDate);
//                        sysFeedbackEntity.setProcessingLog(ProcessingLogStr);
//                        sysFeedbackDao.saveUpdateFeedBack(sysFeedbackEntity);
//                        map.put("processingLog",ProcessingLogStr);
//                    }else {
//                        map.put("processingLog",processingLog);
//                    }
                }
            }
            }
            List<Map<String,Object>> mxList1 =sysFeedbackDao.getSysFeedBackList(input1, selectType, state, questionType, dateInput1, dateInput2, limit, page);
            Integer mxListCount1=mxList1.size();
            pm.setItems(mxList);
            pm.setRows(mxList);
            pm.setTotal(mxListCount1);
            pm.setTotalCount(mxList.size());
        }catch (HSKDBException e) {
            e.printStackTrace(); }
        return pm;
    }

    public SysRetrunMessage saveUpdateFeedBack(Integer fbId) throws HSKException{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        try {
            SysFeedbackEntity sysFeedbackEntity=sysFeedbackDao.findSysFeedBackById(fbId);
            sysFeedbackEntity.setState(2);
            Date date=new Date();
            String ProcessingLogStr="已处理,"+sysUserInfo.getUserName()+","+sdf.format(date);
            sysFeedbackEntity.setProcessingLog(ProcessingLogStr);
            sysFeedbackEntity.setEditDate(date);
            sysFeedbackEntity.setEditRen(sysUserInfo.getUserName());
            sysFeedbackDao.saveUpdateFeedBack(sysFeedbackEntity);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }
    //返回反馈次数
    public SysRetrunMessage countFeedBack() throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        Map<String, Integer> SeMap = new HashMap<String, Integer>();
        try {
            Integer countFeedBack=sysFeedbackDao.countFeedBack();
            SeMap.put("countFeedBack",countFeedBack);
            srm.setObj(SeMap);
         } catch (HSKDBException e) {
        e.printStackTrace();
    }
        return srm;
    }

    public SysRetrunMessage getSysFeedBackListMx(Integer fbId) throws HSKException{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> SeMap = new HashMap<String, Object>();
        try {
            SysFeedbackEntity sysFeedbackEntity=sysFeedbackDao.findSysFeedBackById(fbId);
            SeMap.put("feedDate",sdf.format(sysFeedbackEntity.getCreateDate())!=null ? sdf.format(sysFeedbackEntity.getCreateDate()):"");
            SeMap.put("feedId1",sysFeedbackEntity.getFbId());
            if (sysFeedbackEntity.getType()!=null&&sysFeedbackEntity.getType()!="null"){
                if (sysFeedbackEntity.getType().equals("0")){
                    SeMap.put("feedType","后台");
                }else if (sysFeedbackEntity.getType().equals("1")){
                    SeMap.put("feedType","小程序商城");
                }else if (sysFeedbackEntity.getType().equals("2")){
                    SeMap.put("feedType","小程序代理商");
                }
            }
            else {
                SeMap.put("feedType","");
            }
            if (sysFeedbackEntity.getSuiId()!=null){
                sysUserInfo.setSuiId(sysFeedbackEntity.getSuiId());
                SeMap.put("feedUserName",sysUserInfo.getUserName());
                SeMap.put("feedNodeName",sysUserInfo.getOrgGxId_Str());
                SeMap.put("feedPhone",sysUserInfo.getPhoneNumber());
            }else {
                SeMap.put("feedUserName","");
                SeMap.put("feedNodeName","");
                SeMap.put("feedPhone","");
            }
            SeMap.put("questionType1","建议");
            if (sysFeedbackEntity.getState()!=null){
                if (sysFeedbackEntity.getState()==1){
                    SeMap.put("feedState","待处理");
                }else if (sysFeedbackEntity.getState()==2){
                    SeMap.put("feedState","已处理");
                }else if (sysFeedbackEntity.getState()==3){
                    SeMap.put("feedState","待回复");
                }
            }
            else {
                SeMap.put("feedState","");
            }
            SeMap.put("fbValue",sysFeedbackEntity.getFbValue()!=null?sysFeedbackEntity.getFbValue():"");
            srm.setObj(SeMap);
        } catch (HSKDBException e) {
            e.printStackTrace();
        }
        return srm;
    }
}

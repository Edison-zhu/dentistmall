package com.hsk.xframe.web.sysFeedBack.service;

import com.hsk.exception.HSKException;
import com.hsk.supper.dto.comm.PagerModel;
import com.hsk.supper.dto.comm.SysRetrunMessage;

public interface ISysFeedBackService {
    //查询反馈列表
    public PagerModel getSysFeedBackList(String input1,String selectType,Integer state,String questionType,String dateInput1,String dateInput2,Integer limit, Integer page) throws HSKException;
    //处理反馈信息
    public SysRetrunMessage saveUpdateFeedBack(Integer fbId) throws HSKException;
    //返回反馈次数
    public SysRetrunMessage countFeedBack() throws HSKException;

    //查询反馈详情
    public SysRetrunMessage getSysFeedBackListMx(Integer fbId) throws HSKException;

}

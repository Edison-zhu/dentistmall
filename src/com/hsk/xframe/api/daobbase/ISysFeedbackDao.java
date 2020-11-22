package com.hsk.xframe.api.daobbase;

import com.hsk.exception.HSKDBException;
import com.hsk.xframe.api.persistence.SysFeedbackEntity;

import java.util.List;
import java.util.Map;

public interface ISysFeedbackDao {

    public List<Map<String,Object>> getSysFeedBackList(String input1,String selectType,Integer state,String questionType,String dateInput1,String dateInput2,Integer limit, Integer page) throws HSKDBException;
    public void  saveUpdateFeedBack(SysFeedbackEntity sysFeedbackEntity) throws HSKDBException;
    public SysFeedbackEntity findSysFeedBackById(Integer fbId) throws HSKDBException;

    //查询反馈次数
    public Integer countFeedBack() throws HSKDBException;

}

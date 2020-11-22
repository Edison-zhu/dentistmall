package com.hsk.xframe.api.daobbase.imp;

import com.hsk.exception.HSKDBException;
import com.hsk.supper.dao.imp.SupperDao;
import com.hsk.xframe.api.daobbase.ISysFeedbackDao;
import com.hsk.xframe.api.persistence.SysFeedbackEntity;
import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Repository
public class SysFeedbackDao extends SupperDao implements ISysFeedbackDao {

    public List<Map<String,Object>> getSysFeedBackList(String input1,String selectType,Integer state,String questionType,String dateInput1,String dateInput2,Integer limit, Integer page) throws HSKDBException{

        String sql="SELECT fb_id as fbId,create_date as createDate,question_type as questiontype,type as type,state,fb_value as fbValue,processing_log as processingLog FROM sys_feedback WHERE 1=1 ";
        if (input1!=null&&!input1.equals("")){
            sql+=" AND fb_value like '%"+input1+"%'";
        }
        if (selectType!=null&&!selectType.equals("")){
            if (selectType.equals("2")){
                sql+=" AND type =2";
            }
            if (selectType.equals("3")){
                sql+=" AND type =3";
            }
        }
        if (questionType!=null){
        }
        if (state!=null){
            if (state==1){
                sql+=" AND state =1";
            }else if (state==2){
                sql+=" AND state =2";
            }
        }
//        if (dateInput1!=null&&!dateInput1.equals("")&&dateInput2!=null&&dateInput2.equals("")){
//            sql+=" create_date>='"+dateInput1+" 00:00:00'";
//            sql+=" create_date<='"+dateInput2+" 23:59:59'";
//        }
        if (dateInput1!=null&&!dateInput1.equals("")){
            sql+=" AND create_date>='"+dateInput1+" 00:00:00'";
        }
        if (dateInput2!=null&&!dateInput2.equals("")){
            sql+=" AND create_date<='"+dateInput2+" 23:59:59'";
        }
        if(limit != null && page != null) {
            sql += " limit " + (page - 1) * limit + "," + limit;
        }
        return this.getJdbcDao().query(sql);
    }
    //根据ID查询方法
    public SysFeedbackEntity findSysFeedBackById(Integer fbId) throws HSKDBException{
        SysFeedbackEntity sysFeedbackEntity=new SysFeedbackEntity();
        if (fbId!=null){
            sysFeedbackEntity.setFbId(fbId);
            Object obj = this.getOne(sysFeedbackEntity);
            if (obj!=null)
                sysFeedbackEntity=(SysFeedbackEntity) obj;
        }
        return sysFeedbackEntity;
    }
    //处理日志
    public void  saveUpdateFeedBack(SysFeedbackEntity sysFeedbackEntity) throws HSKDBException {
        HibernateTemplate hibernateTemplate = this.getHibernateTemplate();
        hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
        hibernateTemplate.save(sysFeedbackEntity);
    }
    //查询反馈次数
    public Integer countFeedBack() throws HSKDBException{
        String sql="SELECT COUNT(fb_id) as countFeedBack FROM sys_feedback  WHERE 1 = 1 ";
        List<Map<Object,Object>> list = this.getJdbcDao().query(sql);
        if(list != null && list.size() > 0){
            return Integer.parseInt(list.get(0).get("countFeedBack").toString());
        }
        return 0;
    }


}

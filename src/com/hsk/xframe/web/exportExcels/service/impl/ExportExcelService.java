package com.hsk.xframe.web.exportExcels.service.impl;

import com.hsk.dentistmall.api.daobbase.IHomePageSupplierDao;
import com.hsk.dentistmall.api.daobbase.IMdOrderInfoDao;
import com.hsk.dentistmall.api.daobbase.IMdOrderMxDao;
import com.hsk.dentistmall.api.daobbase.imp.HomePageSupplierDao;
import com.hsk.dentistmall.api.persistence.MdOrderInfo;
import com.hsk.dentistmall.api.persistence.MdOrderMx;
import com.hsk.dentistmall.api.persistence.MdOutOrderMx;
import com.hsk.exception.HSKDBException;
import com.hsk.exception.HSKException;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IExpoetExcelDao;
import com.hsk.xframe.api.daobbase.ISysSalesmanDao;
import com.hsk.xframe.api.persistence.SysSalesManEntity;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.exportExcels.service.IExportExcelService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExportExcelService extends DSTService implements IExportExcelService {
    @Autowired
    ISysSalesmanDao sysSalesmanDao;
    @Autowired
    IHomePageSupplierDao homePageSupplierDao;
    //新版动态导出excel dao方法
    @Autowired
    IExpoetExcelDao expoetExcelDao;
    @Autowired
    IMdOrderMxDao mdOrderMxDao;
    public SysRetrunMessage exportSalesMan1(SysSalesManEntity sysSalesManEntity, String salesmanIds, String states1)throws Exception{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate=new Date();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",account.getUserName());
        Date date=new Date();
        dataMap.put("newDate",sdf.format(date));
        dataMap.put("nodeName",account.getOrgGxId_Str());

        //构造excel 工作薄
        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        //设置列宽
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 30 * 256);
        sheet.setColumnWidth(5, 30 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell=row.createCell(0);
        cell.setCellValue("业务代理发展统计表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 5);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle);
        //cel.setCellStyle(style);
        for (int j = 1; j <= 5; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行
        Row row1=sheet.createRow(1);
        //设置行高
        row1.setHeightInPoints(24);
        Cell cell1=row1.createCell(0);
        cell1.setCellValue("单位");
        cell1.setCellStyle(cellStyle);

        Cell cell2=row1.createCell(1);
        cell2.setCellValue("测试单位");
        cell2.setCellStyle(cellStyle);

        Cell cell3=row1.createCell(2);
        cell3.setCellValue("");
        cell3.setCellStyle(cellStyle);

        Cell cell4=row1.createCell(3);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle);

        Cell cell5=row1.createCell(4);
        cell5.setCellValue("制作日期");
        cell5.setCellStyle(cellStyle);

        Cell cell6=row1.createCell(5);
        cell6.setCellValue("测试日期");
        cell6.setCellStyle(cellStyle);

        //第二行
        //第一行
        Row row2=sheet.createRow(2);
        //设置行高
        row2.setHeightInPoints(22);
        Cell cell21=row2.createCell(0);
        cell21.setCellValue("制表人");
        cell21.setCellStyle(cellStyle);

        Cell cell22=row2.createCell(1);
        cell22.setCellValue("测试制表人");
        cell22.setCellStyle(cellStyle);

        Cell cell23=row2.createCell(2);
        cell23.setCellValue("");
        cell23.setCellStyle(cellStyle);

        Cell cell24=row2.createCell(3);
        cell24.setCellValue("");
        cell24.setCellStyle(cellStyle);

        Cell cell25=row2.createCell(4);
        cell25.setCellValue("");
        cell25.setCellStyle(cellStyle);

        Cell cell26=row2.createCell(5);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle);
        sysSalesManEntity.setSalesmanId(1);
        List<Map<String, Object>> list=sysSalesmanDao.exportSalesMan(sysSalesManEntity);
        Row row3=sheet.createRow(3);
        Cell cell31=null;
        int index=0;
        int indexRow=3;
        String testName="";
        int firstSize=3;
        if (list != null && list.size() > 0) {
            // String name1=null;
            for (Map<String, Object> map : list) {
                index++;
                firstSize++;
                row3 = sheet.createRow(indexRow++);
                cell31 = row3.createCell(0);
                Object agentCompany1 = map.get("agent_company");
                String agentCompany = String.valueOf(agentCompany1);
                cell31.setCellValue(agentCompany);
                if (agentCompany1!=testName){
                    Integer nameCount=homePageSupplierDao.saleManCount(agentCompany);
                    sheet.addMergedRegion(new CellRangeAddress(firstSize,(nameCount+firstSize), 0, 0));
                }
                testName = agentCompany;
            }
        }
        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String realPath = tmpPath + "测试测试1"+ ".xls";
        File outFile = new File(realPath);
        wb.write(new FileOutputStream(outFile));
        String rootUrl = request.getContextPath() + EXPORT_PATH + "测试测试1"+ ".xls";
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", rootUrl);
        map.put("fileName", "测试测试1" + ".xls");
        srm.setObj(map);
        return  srm;
    }


    public SysRetrunMessage exportSalesManAll(SysSalesManEntity sysSalesManEntity)throws Exception {
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate=new Date();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",account.getUserName());
        Date date=new Date();
        dataMap.put("newDate",sdf.format(date));
        dataMap.put("nodeName",account.getOrgGxId_Str());
        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle1 =wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle3 =wb.createCellStyle();
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font3=wb.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font3.setFontHeightInPoints((short)13);
        cellStyle3.setFont(font3);

        CellStyle cellStyle4 =wb.createCellStyle();
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font4=wb.createFont();
        font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font4.setFontHeightInPoints((short)13);
        cellStyle4.setFont(font4);
                //设置列宽
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 30 * 256);
        sheet.setColumnWidth(5, 30 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell=row.createCell(0);
        cell.setCellValue("业务代理发展统计表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 5);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle4);
        //cel.setCellStyle(style);
        for (int j = 1; j < 6; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle4); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行
        Row row1=sheet.createRow(1);
        //设置行高
        row1.setHeightInPoints(24);
        Cell cell1=row1.createCell(0);
        cell1.setCellValue("单位");
        cell1.setCellStyle(cellStyle1);

        Cell cell2=row1.createCell(1);
        cell2.setCellValue(account.getOrgGxId_Str());
        cell2.setCellStyle(cellStyle1);

        Cell cell3=row1.createCell(2);
        cell3.setCellValue("");
        cell3.setCellStyle(cellStyle1);

        Cell cell4=row1.createCell(3);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle1);

        Cell cell5=row1.createCell(4);
        cell5.setCellValue("制作日期");
        cell5.setCellStyle(cellStyle1);

        Cell cell6=row1.createCell(5);
        cell6.setCellValue("");
        cell6.setCellStyle(cellStyle1);

        Cell cell7=row1.createCell(6);
        cell6.setCellValue(sdf.format(date));
        cell6.setCellStyle(cellStyle1);


        //第二行
        //第一行
        Row row2=sheet.createRow(2);
        //设置行高
        row2.setHeightInPoints(22);
        Cell cell21=row2.createCell(0);
        cell21.setCellValue("制表人");
        cell21.setCellStyle(cellStyle1);

        Cell cell22=row2.createCell(1);
        cell22.setCellValue(account.getUserName());
        cell22.setCellStyle(cellStyle1);

        Cell cell23=row2.createCell(2);
        cell23.setCellValue("");
        cell23.setCellStyle(cellStyle1);

        Cell cell24=row2.createCell(3);
        cell24.setCellValue("");
        cell24.setCellStyle(cellStyle1);

        Cell cell25=row2.createCell(4);
        cell25.setCellValue("");
        cell25.setCellStyle(cellStyle1);

        Cell cell26=row2.createCell(5);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle1);
        Cell cell27=row2.createCell(6);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle1);

        Row row3=sheet.createRow(3);
        Cell cell32=null;
        //设置行高
        row3.setHeightInPoints(22);
        cell32=row3.createCell(0);
        cell32.setCellValue("序号");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(1);
        cell32.setCellValue("业务员");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(2);
        cell32.setCellValue("代理商");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(3);
        cell32.setCellValue("机构");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(4);
        cell32.setCellValue("发展日期");
        cell32.setCellStyle(cellStyle3);
        cell32=row3.createCell(5);
        cell32.setCellValue("经营状态");
        cell32.setCellStyle(cellStyle3);
        List<Map<String, Integer>> listAccount =sysSalesmanDao.selectSalesManId1(account.getOrgGxId());
        List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
        List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
        int index=0;
        int indexRow=4;
        int firstSize=2;
        int startSzie=3;
        int countSalesName=0;
        int countAgentCompany=0;

        int countSalman=0;
        int index1=0;
        int index2=0;
        int index3=0;
        int listSize=0;
        Row row4=sheet.createRow(4);

        int AgentCompanyCount=0;
        int rbaNameCount=0;
        if(listAccount != null && listAccount.size() > 0){
        for ( Map<String, Integer> map1 : listAccount){
            index1++;
            Integer salesmanIdAcc=map1.get("salesman_id");
            sysSalesManEntity.setSalesmanId(salesmanIdAcc);
            list = sysSalesmanDao.exportSalesManAdmin(sysSalesManEntity);
            listSize+=list.size();
            SysSalesManEntity  sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity);
            sysSalesManEntity1.setSalesmanId(sysSalesManEntity.getSalesmanId());
            String salesName = sysSalesManEntity1.getSalesName();
            Integer saleMan=sysSalesManEntity.getSalesmanId();
            Cell cell31=null;
            String testName="";
            String agentCompanyNext="";
            String agentCompanyTwoNext="";
            if (list != null && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    index++;
                    firstSize++;
                    startSzie++;
                    row4 = sheet.createRow(indexRow++);
                    //第0列
                    cell31 = row4.createCell(0);
                    cell31.setCellValue(index);
                    cell31.setCellStyle(cellStyle);
                    //第一列
                    cell31 = row4.createCell(1);
                    cell31.setCellValue(salesName);
                    if (salesName!=testName){
                        Integer nameCount=homePageSupplierDao.saleManCount2(saleMan);
                        Integer nameCountFirstSize=nameCount+startSzie-1;
//                        Integer nameCountFirstSize=nameCount+startSzie-1;
                        sheet.addMergedRegion(new CellRangeAddress(startSzie,nameCountFirstSize, 1, 1));
                    }
                    //添加样式
                    cell31.setCellStyle(cellStyle);
                    //第二列
                    cell31 = row4.createCell(2);
                    Object agentCompany1 = map.get("agent_company");
                    String agentCompany ="";

                    if (agentCompany1!=null&&agentCompany1!="null"&&!agentCompany1.equals("")){
                        agentCompany=String.valueOf(agentCompany1);
                    }
                    testName = salesName;
//                    if (salesName!=testName&&index>=2){
//////                        Integer nameCountFirstSize=
////                       System.out.println(startSzie-1);
//                      Integer  startSzies=startSzie-1;
//                        if (agentCompany.equals(agentCompanyNext)){
//                       countAgentCompany+=1;
//                          }
//                        if (!agentCompany.equals(agentCompanyNext)&&index>=2){
//                        int dangqianCount=startSzies;
//                        int start=dangqianCount-countAgentCompany;
//                        sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
//                        countAgentCompany=0;
//                        }
//                    }
//                   if (agentCompany.equals(agentCompanyNext)){
//                       countAgentCompany+=1;
//                    }
//                    if (!agentCompany.equals(agentCompanyNext)&&index>=2){
//                        int dangqianCount=firstSize;
//                        int start=dangqianCount-countAgentCompany;
//                        sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
//                        countAgentCompany=0;
//                    }
//                    if (index1==listAccount.size()&&agentCompany.equals(agentCompanyNext)){
//                        index2++;
//                        if (index2==list.size()){
//                            int dangQianCount=indexRow-1;
//                            int  start=dangQianCount-countAgentCompany;
//                            sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
//                        }
//
//                    }
                    String agentCompanyTwo=agentCompany+salesName;


                    if (agentCompanyTwo.equals(agentCompanyTwoNext)){
                       countAgentCompany+=1;
                    }
                    if (!agentCompanyTwo.equals(agentCompanyTwoNext)&&index>=2){
                        int dangqianCount=firstSize;
                        int start=dangqianCount-countAgentCompany;
                        sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
                        countAgentCompany=0;
                    }
                    if (index1==listAccount.size()&&agentCompanyTwo.equals(agentCompanyTwoNext)){
                        index2++;
                        if (index2==list.size()){
                            int dangQianCount=indexRow-1;
                            int  start=dangQianCount-countAgentCompany;
                            sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                        }

                    }
                    if (agentCompany!=null){

                    }
                    agentCompanyTwoNext=agentCompanyTwo;
                    agentCompanyNext = agentCompany;
                    cell31.setCellValue(agentCompany);
                    //添加样式
                    cell31.setCellStyle(cellStyle);
                    //第三列
                    cell31 = row4.createCell(3);
                    Object rbaName1 = map.get("rba_name");
                    String rbaName = String.valueOf(rbaName1);
                    Object rbsName1 = map.get("rbs_name");
                    String rbsName = String.valueOf(rbsName1);
                    Object rbbName1 = map.get("rbb_name");
                    String rbbName = String.valueOf(rbbName1);
                    String rbaNames=null;
                    if (rbaName!="null"){
                        //cell31.setCellValue(rbaName);
                        rbaNames=rbaName;
                    }else if (rbsName!="null"){
                        //cell31.setCellValue(rbsName);
                        rbaNames=rbsName;
                    }else if (rbbName!="null"){
                        rbaNames=rbbName;
                    }else{
                        rbaNames="";
                    }
                    cell31.setCellValue(rbaNames);
                    //添加样式
                    cell31.setCellStyle(cellStyle);
                    //第四列
                    cell31 = row4.createCell(4);
                    Object createDate1 = map.get("create_date");
                    String createDate = String.valueOf(createDate1);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date2 = sdf2.parse(createDate);
                    cell31.setCellValue(sdf.format(date2));
                    //添加样式
                    cell31.setCellStyle(cellStyle);
                    //第五列
                    cell31 = row4.createCell(5);
                    Object state1 = map.get("state");
                    String state= String.valueOf(state1);
                    Integer intState = Integer.valueOf(state);
                   // cell31.setCellValue(state);
                    //reMap.put("state",state);
                    if (intState == 1) {
                        cell31.setCellValue("正常");
                        cell31.setCellStyle(cellStyle);
                    } else if (intState == 2) {
                        cell31.setCellValue("异常");
                        cell31.setCellStyle(cellStyle);
                    }
                    if (agentCompany!=""){
                        AgentCompanyCount+=1;
                    }else {
                        AgentCompanyCount+=0;
                    }
                    if (rbaName!="null"||rbsName!="null"||rbbName!="null"){
                        rbaNameCount+=1;
                    }else{
                        rbaNameCount+=0;
                    }

                }
            }
            }
        }
        if (AgentCompanyCount!=0&&rbaNameCount!=0){
            Row row5=sheet.createRow(index+4);
            //设置行高
            row5.setHeightInPoints(24);
            Cell cell51=null;
            cell51=row5.createCell(0);
            cell51.setCellValue("汇总");
            cell51.setCellStyle(cellStyle);
            cell51=row5.createCell(1);
            cell51.setCellValue("");
            cell51.setCellStyle(cellStyle);
            cell51=row5.createCell(2);
            cell51.setCellValue(AgentCompanyCount+"家");
            cell51.setCellStyle(cellStyle);
            cell51=row5.createCell(3);
            cell51.setCellValue(rbaNameCount+"家");
            cell51.setCellStyle(cellStyle);
            cell51=row5.createCell(4);
            cell51.setCellValue("");
            cell51.setCellStyle(cellStyle);
            cell51=row5.createCell(5);
            cell51.setCellValue("");
            cell51.setCellStyle(cellStyle);
        }
        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        SimpleDateFormat sdf2=new SimpleDateFormat("MMddHHmmss");
        String fileName="YWDL"+sdf2.format(date);
        String realPath = tmpPath + fileName+ ".xlsx";
        File outFile = new File(realPath);
        wb.write(new FileOutputStream(outFile));
        String rootUrl = request.getContextPath() + EXPORT_PATH + fileName+ ".xlsx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", rootUrl);
        map.put("fileName", fileName + ".xlsx");
        srm.setObj(map);
        return srm;
    }

    public SysRetrunMessage exportCgOrderList(MdOrderInfo att_MdOrderInfo, MdOrderMx att_MdOrderMx, String moiIds) throws Exception{
            SysRetrunMessage srm=new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> dataMap = new HashMap<String, Object>();
            SysUserInfo account = this.GetOneSessionAccount();
            if (account.getOrganizaType().equals("20001")) {
                att_MdOrderInfo.setRbaId(account.getOldId());
            }
            if (account.getOrganizaType().equals("20002")) {
                att_MdOrderInfo.setRbsId(account.getOldId());
            }if (moiIds==null) {
                if (account.getOrganizaType().equals("100")) {
                    att_MdOrderInfo.setWzId(account.getOldId());
                }if (account.getOrganizaType().equals("20003")) {
                    att_MdOrderInfo.setRbbId(account.getOldId());
                }
            }
            dataMap.put("orgName", account.getOrgName());
            String timeArea = "";
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_start());
            if (att_MdOrderInfo.getPlaceOrderTime_start() != null || att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += "-";
            if (att_MdOrderInfo.getPlaceOrderTime_end() != null)
                timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_end());
            dataMap.put("timeArea", timeArea);
            dataMap.put("userName", account.getUserName());
            att_MdOrderInfo.setOrderState("1");
            Workbook wb=new XSSFWorkbook();
            Sheet sheet=wb.createSheet();
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle.setWrapText(true);
            //设置左对齐格式
            CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
//        Font font3=wb.createFont();
//        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
//        font3.setFontHeightInPoints((short)20);
//        cellStyle1.setFont(font3);
        CellStyle cellStyle11 = wb.createCellStyle();
        cellStyle11.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        Font font3=wb.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font3.setFontHeightInPoints((short)13);
        cellStyle11.setFont(font3);


        CellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        Font font=wb.createFont();
//        font.setColor((short) DC143C);
        font.setColor(HSSFFont.COLOR_RED);
        CellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle3.setFont(font);

        CellStyle cellStyle4 = wb.createCellStyle();
        Font font2=wb.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font2.setFontHeightInPoints((short)12);
        cellStyle4.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle4.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle4.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle4.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle4.setWrapText(true);
        cellStyle4.setFont(font2);


//        HSSFFont font = wb.createFont();
//        font.setFontName("黑体");
        //设置列宽
            sheet.setColumnWidth(0, 20 * 256);
            sheet.setColumnWidth(1, 16 * 256);
            sheet.setColumnWidth(2, 7 * 256);
            sheet.setColumnWidth(3, 10 * 256);
            sheet.setColumnWidth(4, 12 * 256);
             sheet.setColumnWidth(5, 10 * 256);
            sheet.setColumnWidth(6, 5 * 256);
            sheet.setColumnWidth(7, 40 * 256);
            sheet.setColumnWidth(8, 20 * 256);
            sheet.setColumnWidth(9, 20 * 256);
            sheet.setColumnWidth(10, 10 * 256);
            sheet.setColumnWidth(11, 8 * 256);
            sheet.setColumnWidth(12, 10 * 256);
            sheet.setColumnWidth(13, 8 * 256);
            sheet.setColumnWidth(14, 8 * 256);
            sheet.setColumnWidth(15, 20 * 256);
            sheet.setColumnWidth(16, 20 * 256);
            sheet.setColumnWidth(17, 10 * 256);
            sheet.setColumnWidth(18, 10 * 256);
            sheet.setColumnWidth(19, 10 * 256);
            sheet.setColumnWidth(20, 10 * 256);
            sheet.setColumnWidth(21, 8 * 256);
            sheet.setColumnWidth(22, 8 * 256);
            sheet.setColumnWidth(23, 10 * 256);
        sheet.setColumnWidth(25, 20 * 256);
            //标题
            Row row=sheet.createRow(0);
            //设置行高
//            row.setHeightInPoints(24);
            Cell cell=row.createCell(0);
            cell.setCellValue(account.getOrgName()+"订单交易明细");
            CellRangeAddress region = new CellRangeAddress(0,0 , 0, 25);
            sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
            cell.setCellStyle(cellStyle11);
            //cel.setCellStyle(style);
            for (int j = 1; j <= 25; j++) {
                cell = row.createCell(j);
//                cell.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
                cell.setCellValue("");
            }
            row.setHeightInPoints(24);
            //第一行数据
            Row row1=sheet.createRow(1);
            Cell cell1=null;
            cell1 =row1.createCell(0);
        cell1.setCellValue("");
        cell1 =row1.createCell(1);
        cell1.setCellValue("单位:");
        cell1.setCellStyle(cellStyle1);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));

        cell1=row1.createCell(2);
        cell1.setCellValue(account.getOrgName());
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 2, 4));

        cell1=row1.createCell(3);
        cell1.setCellValue("");
        cell1=row1.createCell(4);
        cell1.setCellValue("");
        cell1=row1.createCell(5);
        cell1.setCellValue("");
        cell1=row1.createCell(6);
        cell1.setCellValue("");
        cell1=row1.createCell(7);
        cell1.setCellValue("");

        cell1=row1.createCell(8);
        cell1.setCellValue("制表人");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue(account.getUserName());
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1=row1.createCell(11);
        cell1.setCellValue("日期区间");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(12);
        cell1.setCellValue("");
        cell1=row1.createCell(13);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
        cell1=row1.createCell(14);
        cell1.setCellValue("");
        cell1=row1.createCell(15);
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        if (att_MdOrderInfo.getPlaceOrderTime_start()!=null&&!att_MdOrderInfo.getPlaceOrderTime_start().equals("")&&att_MdOrderInfo.getPlaceOrderTime_end()!=null&&!att_MdOrderInfo.getPlaceOrderTime_end().equals("")) {
            cell1.setCellValue(sdf2.format(att_MdOrderInfo.getPlaceOrderTime_start()) + "-" + sdf2.format(att_MdOrderInfo.getPlaceOrderTime_end()));
        }else {
            cell1.setCellValue("");
        }
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(16);
        cell1.setCellValue("");
        cell1=row1.createCell(17);
        cell1.setCellValue("");
        cell1=row1.createCell(18);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 15, 18));
        row.setHeightInPoints(18);
//        cell1.setCellStyle(cellStyle);
//        cell1=row1.createCell(7);
//        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle);
//        cell1=row1.createCell(8);
//        cell1.setCellValue(timeArea);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 8, 10));
//        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(9);
//        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle);
//        cell1=row1.createCell(10);
//        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle);
//            for (int j = 11; j <= 22; j++) {
//                cell1 = row1.createCell(j);
//                cell1.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
//                cell1.setCellValue("");
//            }
        //第二行 汇总
        Integer sumNumbers=0;
        Double sumMoneys=0.0;
        Integer number2s=0;
        Integer shiShous=0;
        Double shiShouMoney=0.0;
        Double shiShouHuiZong=0.0;
        Row row4=sheet.createRow(2);
        Cell cell4=null;
        cell4 =row4.createCell(0);
        cell4.setCellValue("汇总");
        cell4.setCellStyle(cellStyle3);
        for (int j = 1; j <= 4; j++) {
            cell4 = row4.createCell(j);
            cell4.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
            cell4.setCellValue("");
        }
        for (int j = 6; j <= 9; j++) {
            cell4 = row4.createCell(j);
            cell4.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
            cell4.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 1, 4));
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 6, 9));
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 13, 18));
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 21, 22));
        Row row2=sheet.createRow(3);
        Cell cell2=null;
        cell2 =row2.createCell(6);
        cell2.setCellValue("序号");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(0);
        cell2.setCellValue("订单号");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(1);
        cell2.setCellValue("医院门诊");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(2);
        cell2.setCellValue("采购员");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(3);
        cell2.setCellValue("供应商");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(4);
        cell2.setCellValue("日期");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(5);
        cell2.setCellValue("订单金额");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(7);
        cell2.setCellValue("商品");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(8);
        cell2.setCellValue("规格");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(9);
        cell2.setCellValue("类型");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(10);
        cell2.setCellValue("单价");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(11);
        cell2.setCellValue("订单数量");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(12);
        cell2.setCellValue("金额");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(13);
        cell2.setCellValue("付款方式");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(14);
        cell2.setCellValue("状态");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(15);
        cell2.setCellValue("订单描述");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(16);
        cell2.setCellValue("收货物流信息");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(17);
        cell2.setCellValue("买家留言");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(18);
        cell2.setCellValue("卖家备注");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(19);
        cell2.setCellValue("发货数量");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(20);
        cell2.setCellValue("实收数量");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(21);
        cell2.setCellValue("退货数量");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(22);
        cell2.setCellValue("退货金额");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(23);
        cell2.setCellValue("实收金额");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(24);
        cell2.setCellValue("实收汇总金额");
        cell2.setCellStyle(cellStyle4);
        cell2 =row2.createCell(25);
        cell2.setCellValue("业务代理编号");
        cell2.setCellStyle(cellStyle4);
        //数据行开始
        int index=0;
        int indexRow=4;
        int Cell1firstSize=2;
//        int countIndex=1;
        int count1=0;
        int count2=0;
        int count3=0;
        int count4=0;
        int count5=0;
        int count6=0;
        int ddmxCount=0;
        int countenterpriseType=0;
        Row row3=sheet.createRow(4);
        List<Map<String, Object>> list=expoetExcelDao.getMxListbyOrderAndMx(att_MdOrderInfo, att_MdOrderMx,moiIds);
         Cell cell31=null;
//        cell31.set
        String orderCodeNext="";
        String rbasbNameNext="";
        String userNameNext="";
        String PlaceOrderTimeNext="";
        String ProcessNameNext="";
        String wlxxNext="";
        String ddmxNext="";
        String enterpriseTypeNext="";
        String orderCodees1="";
        Double TotalMoneySumOrder1=0.0;

        Double TotalMoneySumAll=0.0;
        Double TotalMoneySumAll1=0.0;
        int countIndex=1;
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                index++;
                Cell1firstSize++;
                row3 = sheet.createRow(indexRow++);
                cell31 = row3.createCell(6);
                String orderCodees=String.valueOf(map.get("orderCode"));
                if (orderCodees.equals(orderCodees1)){
                    countIndex++;
                    cell31.setCellValue(countIndex+"");
                }else {
                    countIndex=1;
                    cell31.setCellValue(countIndex+"");
                }
                cell31.setCellStyle(cellStyle);
                //Cell cell32 = row3.createCell(0);
                //订单号
                cell31 = row3.createCell(0);
                Object orderCode1 = map.get("orderCode");
                String orderCode="";
                if (orderCode1!=null&&orderCode1!="null"&&!orderCode1.equals("")){
                    orderCode=String.valueOf(orderCode1);
                }
                cell31.setCellValue(orderCode);
                if (orderCode.equals(orderCodeNext)){
                    count1+=1;
                }
                if(!orderCode.equals(orderCodeNext)&&index>=2){
                    int dangQianCount=Cell1firstSize;
                    int start =dangQianCount-count1;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 0, 0));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 4, 4));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 5, 5));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 13, 13));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 14, 14));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 15, 15));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 16, 16));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 17, 17));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 18, 18));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 23, 23));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 24, 24));
                    count1=0;
//                    countIndex=1;
                }
                if (index==list.size()&&orderCode.equals(orderCodeNext)){
                     int dangQianCount=indexRow-1;
                    int  start=dangQianCount-count1;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 0, 0));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 4, 4));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 5, 5));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 12, 12));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 13, 13));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 14, 14));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 15, 15));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 16, 16));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 17, 17));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 18, 18));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 23, 23));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 24, 24));
                }
                orderCodeNext=orderCode;
                cell31.setCellStyle(cellStyle);
                //医院门诊
                cell31 = row3.createCell(1);
                Object rbaName1 = map.get("rbaName");
                String rbaName="";
                if (rbaName1!=null&&rbaName1!="null"&&!rbaName1.equals("")){
                     rbaName=String.valueOf(rbaName1);
                }
                Object rbsName1 = map.get("rbsName");
                String rbsName="";
                if (rbsName1!=null&&rbsName1!="null"&&!rbsName1.equals("")){
                    rbsName= String.valueOf(rbsName1);
                }
                Object rbbName1 = map.get("rbbName");
                String rbbName="";
                if (rbbName1!=null&&rbbName1!="null"&&!rbbName1.equals("")){
                    rbbName=String.valueOf(rbbName1);
                }
                String rbasbName=rbaName+rbsName+rbbName;
                cell31.setCellValue(rbasbName);

//                if (rbasbName.equals(rbasbNameNext)){
//                    if (index<=1&&(rbasbName==null||rbasbName!="null"||!rbasbName.equals(""))){
//                        count2+=0;
//                    }else {
//                        count2 += 1;
//                    }
//                }
//
//                if(!rbasbName.equals(rbasbNameNext)&&index>=2){
//                    int dangQianCount=Cell1firstSize;
//                    int start =dangQianCount-count2;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
//                    count2=0;
//                }
//                //合并最后一次查询的单元格
//                if (index==list.size()&&rbasbName.equals(rbasbNameNext)){
//                    int dangQianCount=indexRow-1;
//                    int  start=dangQianCount-count2;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
//                }
//                rbasbNameNext=rbasbName;
                if (orderCodees.equals(orderCodees1)){
                    cell31.setCellStyle(cellStyle2);
                }else {
                    cell31.setCellStyle(cellStyle);
                }
                //采购员
                cell31 = row3.createCell(2);
                Object userName1 = map.get("userName");
                String userName="";
                if (userName1!=null&&userName1!="null"&&!userName1.equals("")){
                    userName= String.valueOf(userName1);
                }
                cell31.setCellValue(userName);
//                if (userName.equals(userNameNext)){
//                    count3+=1;
//                }
//                if(!userName.equals(userNameNext)&&index>=2){
//                    int dangQianCount=Cell1firstSize;
//                    int start =dangQianCount-count3;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
//                    count3=0;
//                }
//                //合并最后一次查询的单元格
//                if (index==list.size()&&userName.equals(userNameNext)){
//                    int dangQianCount=indexRow-1;
//                    int  start=dangQianCount-count3;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
//                }
//                userNameNext=userName;
                //
                cell31.setCellStyle(cellStyle);

                //供应商
                cell31 = row3.createCell(3);
                Object applicantName1 = map.get("applicantName");
                String applicantName="";
                if (applicantName1!=null&&applicantName1!="null"&&!applicantName1.equals("")){
                    applicantName= String.valueOf(applicantName1);
                }
                cell31.setCellValue(applicantName);
                if (orderCodees.equals(orderCodees1)){
                    cell31.setCellStyle(cellStyle2);
                }else {
                    cell31.setCellStyle(cellStyle);
                }

                //日期
                cell31 = row3.createCell(4);
                Object PlaceOrderTime1 = map.get("PlaceOrderTime");
                String PlaceOrderTime = String.valueOf(PlaceOrderTime1);
//                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date2 = sdf2.parse(PlaceOrderTime);
                cell31.setCellValue(PlaceOrderTime);

//                if (PlaceOrderTime.equals(PlaceOrderTimeNext)){
//                    count4+=1;
//                }
//                if(!PlaceOrderTime.equals(PlaceOrderTimeNext)&&index>=2){
//                    int dangQianCount=Cell1firstSize;
//                    int start =dangQianCount-count4;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 4, 4));
//                    count4=0;
//                }
//                //合并最后一次查询的单元格
//                if (index==list.size()&&PlaceOrderTime.equals(PlaceOrderTimeNext)){
//                    int dangQianCount=indexRow-1;
//                    int  start=dangQianCount-count4;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 4, 4));
//                }
//                PlaceOrderTimeNext=PlaceOrderTime;
                cell31.setCellStyle(cellStyle);

                //订单金额
                cell31 = row3.createCell(5);
                Object TotalMoneySum6 = map.get("TotalMoneyOrders");
                Double TotalMoneySum6s=Double.parseDouble(TotalMoneySum6.toString());
                cell31.setCellValue(df.format(TotalMoneySum6s));
                cell31.setCellStyle(cellStyle);
                //商品
                cell31 = row3.createCell(7);
                Object matName1 = map.get("matName");
                String matName=String.valueOf(matName1);
                cell31.setCellValue(matName);
                cell31.setCellStyle(cellStyle2);
                //规格
                cell31 = row3.createCell(8);
                Object NORM1 = map.get("NORM");
                String NORM=String.valueOf(NORM1);
                cell31.setCellValue(NORM);
                cell31.setCellStyle(cellStyle2);
                //类型
                cell31 = row3.createCell(9);
                Object matType1 = map.get("matType");
                String matType="";
                if (matType1!=null&&matType1!="null"&&!matType1.equals("")){
                    matType= String.valueOf(matType1);
                }
                cell31.setCellValue(matType);
                cell31.setCellStyle(cellStyle2);
                //单价
                cell31 = row3.createCell(10);
                Object UnitMoney1 = map.get("UnitMoney");
                Double UnitMoney=Double.parseDouble(UnitMoney1.toString());
                cell31.setCellValue(df.format(UnitMoney));
                cell31.setCellStyle(cellStyle);
                //订单数量
                cell31 = row3.createCell(11);
                Object matNumber1 = map.get("matNumber");
                Integer matNumber=Integer.parseInt(matNumber1.toString());
                sumNumbers+=matNumber;
                cell31.setCellValue(matNumber);
                cell31.setCellStyle(cellStyle);
                //金额
                cell31 = row3.createCell(12);
                Object TotalMoney1 = map.get("TotalMoney");
                Double TotalMoney=Double.parseDouble(TotalMoney1.toString());
                sumMoneys+=TotalMoney;
                cell31.setCellValue(df.format(TotalMoney));
                cell31.setCellStyle(cellStyle);
                //付款方式
                cell31 = row3.createCell(13);
                Object payName1 = map.get("payName");
                String payName="";
                if (payName1!=null&&payName1!="null"&&!payName1.equals("")){
                    payName=String.valueOf(payName1);
                }
                cell31.setCellValue(payName);
                cell31.setCellStyle(cellStyle);
                //状态
                cell31 = row3.createCell(14);
                Object ProcessName1 = map.get("ProcessName");
                String ProcessName=String.valueOf(ProcessName1);
                cell31.setCellValue(ProcessName);
//                cell31.setCellStyle(cellStyle);
//                if (ProcessName.equals(ProcessNameNext)){
//                    count5+=1;
//                }
//                if(!ProcessName.equals(ProcessNameNext)&&index>=2){
//                    int dangQianCount=Cell1firstSize;
//                    int start =dangQianCount-count5;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 12, 12));
//                    count5=0;
//                }
//                //合并最后一次查询的单元格
//                if (index==list.size()&&ProcessName.equals(ProcessNameNext)){
//                    int dangQianCount=indexRow-1;
//                    int  start=dangQianCount-count5;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 12, 12));
//                }
//                ProcessNameNext=ProcessName;
                cell31.setCellStyle(cellStyle);
                //订单描述
                cell31 = row3.createCell(15);
                Object ddmx1 = map.get("ddmx");
                String ddmx="";
                if (ddmx1!=null&&ddmx1!="null"&&!ddmx1.equals("")){
                    ddmx=String.valueOf(ddmx1);
                }else{
                    //总额4145.0000(含12元快递费)发票抬头:税号
                    ddmx="";
                }
//                if (ddmx.equals(ddmxNext)){
//                    ddmxCount+=1;
//                }
//                if(!ddmx.equals(ddmxNext)&&index>=2){
//                    int dangQianCount=Cell1firstSize;
//                    int start =dangQianCount-ddmxCount;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 13, 13));
//                    ddmxCount=0;
//                }
//                //合并最后一次查询的单元格
//                if (index==list.size()&&ddmx.equals(ddmxNext)){
//                    int dangQianCount=indexRow-1;
//                    int  start=dangQianCount-ddmxCount;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 13, 13));
//                }
//                ddmxNext=ddmx;
                cell31.setCellValue(ddmx);
                cell31.setCellStyle(cellStyle);

                //收货物流信息
                cell31 = row3.createCell(16);
                Object wlxx1 = map.get("wlxx");
                String wlxx="";
                if (wlxx1!=null&&wlxx1!="null"&&!wlxx1.equals("")){
                    wlxx=String.valueOf(wlxx1);
                }
                cell31.setCellValue(wlxx);

//                if (wlxx.equals(wlxxNext)){
//                    count6+=1;
//                }
//                if(!wlxx.equals(wlxxNext)&&index>=2){
//                    int dangQianCount=Cell1firstSize;
//                    int start =dangQianCount-count6;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 14, 14));
//                    //sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 15, 15));
//                    count6=0;
//                }
//                //合并最后一次查询的单元格
//                if (index==list.size()&&wlxx.equals(wlxxNext)){
//                    int dangQianCount=indexRow-1;
//                    int  start=dangQianCount-count6;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 14, 14));
//                }
//                wlxxNext=wlxx;
                cell31.setCellStyle(cellStyle);
                //买家留言
                cell31 = row3.createCell(17);
                Object enterpriseType1 = map.get("enterpriseType");
                String enterpriseType="";
                if (enterpriseType1!=null&&enterpriseType1!="null"&&!enterpriseType1.equals("")){
                    enterpriseType=String.valueOf(enterpriseType1);
                }
                cell31.setCellValue(enterpriseType);

//                if (enterpriseType.equals(enterpriseTypeNext)){
//                    countenterpriseType+=1;
//                }
//                if(!enterpriseType.equals(enterpriseTypeNext)&&index>=2&&!enterpriseType.equals("")){
//                    int dangQianCount=Cell1firstSize;
//                    int start =dangQianCount-countenterpriseType;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 15, 15));
//                    countenterpriseType=0;
//                }
//                if (index==list.size()&&enterpriseType.equals(enterpriseTypeNext)){
//                    int dangQianCount=indexRow;
//                    int  start=dangQianCount-countenterpriseType;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 15, 15));
//                }
//                enterpriseTypeNext=enterpriseType;


                cell31.setCellStyle(cellStyle);
                //卖家备注
                cell31 = row3.createCell(18);
                Object scopeBusiness1 = map.get("scopeBusiness");
                String scopeBusiness="";
                if (scopeBusiness1!=null&&scopeBusiness1!="null"&&!scopeBusiness1.equals("")){
                    scopeBusiness=String.valueOf(scopeBusiness1);
                }
                cell31.setCellValue(scopeBusiness);
                cell31.setCellStyle(cellStyle);
                //发货数量
                cell31 = row3.createCell(19);
                Object number21 = map.get("number2");
                Integer number2=null;
                if (number21!="null"&&number21!=null&&!number21.equals("")){
                    number2 =Integer.parseInt(number21.toString());
                    number2s+=number2;
                }else {
                     number2=0;
                    number2s+=0;
                }
                cell31.setCellValue(number2);
                cell31.setCellStyle(cellStyle);
                //实收数量
                cell31 = row3.createCell(20);
                Object shureNumber1 = map.get("shureNumber");
                Integer shureNumber=Integer.parseInt(shureNumber1.toString());
                shiShous+=shureNumber;
                cell31.setCellValue(shureNumber);
                cell31.setCellStyle(cellStyle);

                //退货数量
                cell31 = row3.createCell(21);
                Object backNumber1 = map.get("backNumber");
                Integer backNumber=Integer.parseInt(backNumber1.toString());
                cell31.setCellValue(backNumber);
                cell31.setCellStyle(cellStyle);
                //退货金额
                cell31 = row3.createCell(22);
                Object backMoney1 = map.get("backMoney");
                Double backMoney=Double.parseDouble(backMoney1.toString());
                cell31.setCellValue(df.format(backMoney));
                cell31.setCellStyle(cellStyle);
                //实收金额
                cell31 = row3.createCell(23);
                Object TotalMoney12 = map.get("TotalMoneySum1");
                if (!ProcessName1.toString().equals("交易成功")&&!ProcessName1.toString().equals("待发货")&&!ProcessName1.toString().equals("部分发货")&&!ProcessName1.toString().equals("待收货")){
                    cell31.setCellValue("");
                }else {
                    cell31.setCellValue(df.format(TotalMoney12));
                }
                cell31.setCellStyle(cellStyle);
                //实收汇总金额、
                cell31 = row3.createCell(24);
                Object TotalMoneySum1 = map.get("TotalMoneyOrders");
                Double TotalMoneySum=Double.parseDouble(TotalMoneySum1.toString());
                if (!ProcessName1.toString().equals("交易成功")&&!ProcessName1.toString().equals("待发货")&&!ProcessName1.toString().equals("部分发货")&&!ProcessName1.toString().equals("待收货")){
                    cell31.setCellValue("");
                }else {
                    cell31.setCellValue(df.format(TotalMoneySum));
                }
                cell31.setCellStyle(cellStyle);//

                //业务代理
                cell31 = row3.createCell(25);
                Object salesCode1 = map.get("sales_code");
                String salesCode="";
                if (salesCode1!=null&&salesCode1!="null"&&!salesCode1.equals("")){
                    salesCode= String.valueOf(salesCode1);
                }
                cell31.setCellValue(salesCode);
                cell31.setCellStyle(cellStyle);//

                Object TotalMoneySums = map.get("TotalMoney");
                Double TotalMoneySumD=Double.parseDouble(TotalMoneySums.toString());
                if (!ProcessName1.toString().equals("交易成功")&&!ProcessName1.toString().equals("待发货")&&!ProcessName1.toString().equals("部分发货")&&!ProcessName1.toString().equals("待收货")) {
                    shiShouMoney+=0;
                }else {
                    shiShouMoney+= TotalMoneySumD;
                }

                if (!ProcessName1.toString().equals("交易成功")&&!ProcessName1.toString().equals("待发货")&&!ProcessName1.toString().equals("部分发货")&&!ProcessName1.toString().equals("待收货")){
                    shiShouHuiZong+=0;
                }else {
                    if (TotalMoneySum.equals(TotalMoneySumOrder1)&&orderCodees.equals(orderCodees1)){
                        shiShouHuiZong+=0;
                    }else {
                        shiShouHuiZong+=TotalMoneySum;
                    }
                }
                if (TotalMoneySum.equals(TotalMoneySumOrder1)&&orderCodees.equals(orderCodees1)){
                        TotalMoneySumAll+=0;
                    }
                else {
                    TotalMoneySumAll+=TotalMoneySum;
                }
                TotalMoneySumAll1=TotalMoneySumAll;
                TotalMoneySumOrder1=TotalMoneySum;
                //把订单号值赋给上一个订单号值
                orderCodees1=orderCodees;
//                row3.setHeightInPoints(40);
            }
        }
        cell4 =row4.createCell(5);
        cell4.setCellValue(df.format(TotalMoneySumAll));
        cell4.setCellStyle(cellStyle3);
        cell4 =row4.createCell(9);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle3);
        cell4 =row4.createCell(10);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle3);
            cell4 =row4.createCell(11);
            cell4.setCellValue(sumNumbers);
            cell4.setCellStyle(cellStyle3);
            cell4 =row4.createCell(12);
            cell4.setCellValue(df.format(sumMoneys));
            cell4.setCellStyle(cellStyle3);
            for (int j = 13; j <18; j++) {
                cell4 = row4.createCell(j);
                cell4.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
                cell4.setCellValue("");
            }
            cell4 =row4.createCell(18);
            cell4.setCellValue("");
            cell4.setCellStyle(cellStyle);
            cell4 =row4.createCell(19);
            cell4.setCellValue(number2s);
            cell4.setCellStyle(cellStyle3);
            cell4 =row4.createCell(20);
            cell4.setCellValue(shiShous);
            cell4.setCellStyle(cellStyle3);
        cell4 =row4.createCell(21);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle);
        cell4 =row4.createCell(22);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle);
        cell4 =row4.createCell(23);
        cell4.setCellValue(df.format(shiShouMoney));
        cell4.setCellStyle(cellStyle3);
        cell4 =row4.createCell(24);
        cell4.setCellValue(df.format(shiShouHuiZong));
        cell4.setCellStyle(cellStyle3);
        cell4 =row4.createCell(25);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle3);


        Row row5=sheet.createRow(indexRow);
        Cell cell5=null;
        cell5 =row5.createCell(0);
        cell5.setCellValue("汇总");
        cell5.setCellStyle(cellStyle3);
        for (int j = 1; j <= 4; j++) {
            cell5 = row5.createCell(j);
            cell5.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell5.setCellValue("");
        }
        cell5 =row5.createCell(5);
        cell5.setCellValue(df.format(TotalMoneySumAll));
        cell5.setCellStyle(cellStyle3);
        for (int j = 6; j <= 9; j++) {
            cell5 = row5.createCell(j);
            cell5.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell5.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(indexRow,indexRow , 1, 4));
        sheet.addMergedRegion(new CellRangeAddress(indexRow,indexRow , 6, 9));
        sheet.addMergedRegion(new CellRangeAddress(indexRow,indexRow , 13, 18));
        sheet.addMergedRegion(new CellRangeAddress(indexRow,indexRow , 21, 22));
            String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
            File file = new File(tmpPath);
            if (!file.exists()) {
                file.mkdirs();
            }
        cell5 =row5.createCell(9);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle3);
        cell5 =row5.createCell(10);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle3);
        cell5 =row5.createCell(11);
        cell5.setCellValue(sumNumbers);
        cell5.setCellStyle(cellStyle3);
        cell5 =row5.createCell(12);
        cell5.setCellValue(df.format(sumMoneys));
        cell5.setCellStyle(cellStyle3);
        for (int j = 13; j <18; j++) {
            cell5 = row5.createCell(j);
            cell5.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
            cell5.setCellValue("");
        }
        cell5 =row5.createCell(18);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle);
        cell5 =row5.createCell(19);
        cell5.setCellValue(number2s);
        cell5.setCellStyle(cellStyle3);
        cell5 =row5.createCell(20);
        cell5.setCellValue(shiShous);
        cell5.setCellStyle(cellStyle3);
        cell5 =row5.createCell(21);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle);
        cell5 =row5.createCell(22);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle);
        cell5 =row5.createCell(23);
        cell5.setCellValue(df.format(shiShouMoney));
        cell5.setCellStyle(cellStyle3);
        cell4 =row5.createCell(24);
        cell4.setCellValue(df.format(shiShouHuiZong));
        cell4.setCellStyle(cellStyle3);
        cell4 =row5.createCell(25);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle3);
            //
//            Date newDate1=new Date();
//            SimpleDateFormat newDdf=new SimpleDateFormat("yyyy-MM-dd");
            //String excelNamefile=account.getOrgName()+"订单交易明细";
//            String excelName = null;
//             excelName = new String(excelNamefile.getBytes(), "UTF-8");
//            String realPath = tmpPath + excelName+ ".xlsx";
//            File outFile = new File(realPath);//wb.write(new FileOutputStream(outFile));
//            String rootUrl = request.getContextPath() + EXPORT_PATH + excelName+ ".xlsx";
//            response.reset();
//            response.setHeader("Content-disposition", "attachment; filename=" + rootUrl);
//            response.setContentType("Content-Type:application/vnd.ms-excel ");
//            wb.write(new FileOutputStream(outFile));
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("path", rootUrl);
//            map.put("filename", excelName + ".xlsx");
//            srm.setObj(map);
        //excel 名称
                String excelName = account.getOrgName() + "订单交易明细";
                String realPath = tmpPath + excelName + ".xlsx";
                File outFile = new File(realPath);
                wb.write(new FileOutputStream(outFile));
                String rootUrl = request.getContextPath() + EXPORT_PATH + excelName + ".xlsx";
                Map<String, String> map = new HashMap<String, String>();
                map.put("path", rootUrl);
                map.put("fileName", excelName + ".xlsx");
                srm.setObj(map);
            return srm;
    }
    //导出对账信息  未知是否合并
    @Override
    public SysRetrunMessage exportDzOrderList(MdOrderInfo att_MdOrderInfo,
                                              MdOrderMx att_MdOrderMx,String moiIds) throws Exception {
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        if (account.getOrganizaType().equals("20001")) {
            att_MdOrderInfo.setRbaId(account.getOldId());
        }
        if (account.getOrganizaType().equals("20002")) {
            att_MdOrderInfo.setRbsId(account.getOldId());
        }
        if (moiIds == null) {
            if (account.getOrganizaType().equals("100")) {
                att_MdOrderInfo.setWzId(account.getOldId());
            }
            if (account.getOrganizaType().equals("20003")) {
                att_MdOrderInfo.setRbbId(account.getOldId());
            }
        }
        /*att_MdOrderInfo.setWzId(account.getOldId());*/
        att_MdOrderInfo.setOrderState("1");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        int rowCount = 6;
        dataMap.put("orgName", account.getOrgName());
        String timeArea = "";
        if (att_MdOrderInfo.getPlaceOrderTime_start() != null)
            timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_start());
        if (att_MdOrderInfo.getPlaceOrderTime_start() != null || att_MdOrderInfo.getPlaceOrderTime_end() != null)
            timeArea += "-";
        if (att_MdOrderInfo.getPlaceOrderTime_end() != null)
            timeArea += sdf.format(att_MdOrderInfo.getPlaceOrderTime_end());

        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setWrapText(true);
        //设置左对齐格式
        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT); //左对齐
        //设置列宽
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 50 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 30 * 256);
        sheet.setColumnWidth(5, 60 * 256);
        sheet.setColumnWidth(6, 50 * 256);
        sheet.setColumnWidth(7, 50 * 256);
        sheet.setColumnWidth(10, 20 * 256);
        sheet.setColumnWidth(13, 40 * 256);
        sheet.setColumnWidth(14, 40 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell=row.createCell(0);
        cell.setCellValue(account.getOrgName()+"");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 21);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle);
        //cel.setCellStyle(style);
        for (int j = 1; j <= 21; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
       // List<Map<Object, Object>> list = mdOrderMxDao.getMxListByOrder(att_MdOrderInfo, att_MdOrderMx, moiIds);
        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //excel 名称
        String excelName = account.getOrgName() + "订单交易明细";
        String realPath = tmpPath + excelName + ".xlsx";
        File outFile = new File(realPath);
        wb.write(new FileOutputStream(outFile));
        String rootUrl = request.getContextPath() + EXPORT_PATH + excelName + ".xlsx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", rootUrl);
        map.put("fileName", excelName + ".xlsx");
        srm.setObj(map);
        return srm;
    }

    public SysRetrunMessage exportSalesManAgent(SysSalesManEntity sysSalesManEntity) throws Exception{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate=new Date();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",account.getUserName());
        Date date=new Date();
        dataMap.put("newDate",sdf.format(date));
        dataMap.put("nodeName",account.getOrgGxId_Str());
        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //设置列宽
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 30 * 256);
        sheet.setColumnWidth(5, 30 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell=row.createCell(0);
        cell.setCellValue("业务代理发展统计表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 6);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle);
        //cel.setCellStyle(style);
        for (int j = 1; j <= 6; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行
        Row row1=sheet.createRow(1);
        //设置行高
        row1.setHeightInPoints(24);
        Cell cell1=row1.createCell(0);
        cell1.setCellValue("单位");
        cell1.setCellStyle(cellStyle);

        Cell cell2=row1.createCell(1);
        cell2.setCellValue(account.getOrgGxId_Str());
        cell2.setCellStyle(cellStyle);

        Cell cell3=row1.createCell(2);
        cell3.setCellValue("");
        cell3.setCellStyle(cellStyle);

        Cell cell4=row1.createCell(3);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle);

        Cell cell5=row1.createCell(4);
        cell5.setCellValue("制作日期");
        cell5.setCellStyle(cellStyle);

        Cell cell6=row1.createCell(5);
        cell6.setCellValue("");
        cell6.setCellStyle(cellStyle);

        Cell cell7=row1.createCell(6);
        cell6.setCellValue(sdf.format(date));
        cell6.setCellStyle(cellStyle);

        //第二行
        //第一行
        Row row2=sheet.createRow(2);
        //设置行高
        row2.setHeightInPoints(22);
        Cell cell21=row2.createCell(0);
        cell21.setCellValue("制表人");
        cell21.setCellStyle(cellStyle);

        Cell cell22=row2.createCell(1);
        cell22.setCellValue(account.getUserName());
        cell22.setCellStyle(cellStyle);

        Cell cell23=row2.createCell(2);
        cell23.setCellValue("");
        cell23.setCellStyle(cellStyle);

        Cell cell24=row2.createCell(3);
        cell24.setCellValue("");
        cell24.setCellStyle(cellStyle);

        Cell cell25=row2.createCell(4);
        cell25.setCellValue("");
        cell25.setCellStyle(cellStyle);

        Cell cell26=row2.createCell(5);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle);
        Cell cell27=row2.createCell(6);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle);

        Row row3=sheet.createRow(3);
        Cell cell32=null;
        //设置行高
        row3.setHeightInPoints(22);
        cell32=row3.createCell(0);
        cell32.setCellValue("序号");
        cell32.setCellStyle(cellStyle);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(1);
        cell32.setCellValue("业务员");
        cell32.setCellStyle(cellStyle);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(2);
        cell32.setCellValue("代理商");
        cell32.setCellStyle(cellStyle);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(3);
        cell32.setCellValue("机构");
        cell32.setCellStyle(cellStyle);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(4);
        cell32.setCellValue("发展日期");
        cell32.setCellStyle(cellStyle);
        cell32=row3.createCell(5);
        cell32.setCellValue("经营状态");
        cell32.setCellStyle(cellStyle);



        return srm;
    }
    //管理员导出查看关联下的导出全部
    public SysRetrunMessage exportSalesManAdmin(SysSalesManEntity sysSalesManEntity) throws Exception{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate=new Date();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",account.getUserName());
        Date date=new Date();
        dataMap.put("newDate",sdf.format(date));
        dataMap.put("nodeName",account.getOrgGxId_Str());
        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);


        CellStyle cellStyle1 =wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle3 =wb.createCellStyle();
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font3=wb.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font3.setFontHeightInPoints((short)13);
        cellStyle3.setFont(font3);

        CellStyle cellStyle4 =wb.createCellStyle();
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font4=wb.createFont();
        font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font4.setFontHeightInPoints((short)13);
        cellStyle4.setFont(font4);
        //设置列宽
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 30 * 256);
        sheet.setColumnWidth(5, 30 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell=row.createCell(0);
        cell.setCellValue("业务代理发展统计表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 6);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle4);
        //cel.setCellStyle(style);
        for (int j = 1; j <= 6; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle4); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行
        Row row1=sheet.createRow(1);
        //设置行高
        row1.setHeightInPoints(24);
        Cell cell1=row1.createCell(0);
        cell1.setCellValue("单位");
        cell1.setCellStyle(cellStyle1);

        Cell cell2=row1.createCell(1);
        cell2.setCellValue(account.getOrgGxId_Str());
        cell2.setCellStyle(cellStyle1);

        Cell cell3=row1.createCell(2);
        cell3.setCellValue("");
        cell3.setCellStyle(cellStyle1);

        Cell cell4=row1.createCell(3);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle1);

        Cell cell5=row1.createCell(4);
        cell5.setCellValue("制作日期");
        cell5.setCellStyle(cellStyle1);

        Cell cell6=row1.createCell(5);
        cell6.setCellValue("");
        cell6.setCellStyle(cellStyle1);

        Cell cell7=row1.createCell(6);
        cell6.setCellValue(sdf.format(date));
        cell6.setCellStyle(cellStyle1);
        //第二行
        //第一行
        Row row2=sheet.createRow(2);
        //设置行高
        row2.setHeightInPoints(22);
        Cell cell21=row2.createCell(0);
        cell21.setCellValue("制表人");
        cell21.setCellStyle(cellStyle1);

        Cell cell22=row2.createCell(1);
        cell22.setCellValue(account.getUserName());
        cell22.setCellStyle(cellStyle1);

        Cell cell23=row2.createCell(2);
        cell23.setCellValue("");
        cell23.setCellStyle(cellStyle1);

        Cell cell24=row2.createCell(3);
        cell24.setCellValue("");
        cell24.setCellStyle(cellStyle1);

        Cell cell25=row2.createCell(4);
        cell25.setCellValue("");
        cell25.setCellStyle(cellStyle1);

        Cell cell26=row2.createCell(5);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle1);
        Cell cell27=row2.createCell(6);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle1);

        Row row3=sheet.createRow(3);
        Cell cell32=null;
        //设置行高
        row3.setHeightInPoints(22);
        cell32=row3.createCell(0);
        cell32.setCellValue("序号");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(1);
        cell32.setCellValue("业务员");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(2);
        cell32.setCellValue("代理商");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(3);
        cell32.setCellValue("机构");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(4);
        cell32.setCellValue("发展日期");
        cell32.setCellStyle(cellStyle3);
        cell32=row3.createCell(5);
        cell32.setCellValue("经营状态");
        cell32.setCellStyle(cellStyle3);
        Integer SalesmanId1=null;
        //判断显示多少条
        Integer Leaded = sysSalesmanDao.getPageModelInfoLeadedCount(sysSalesManEntity, 1);
        Integer Company = sysSalesmanDao.getPageModelInfoCompanyCount(sysSalesManEntity, 2);
        List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
        List<Map<String, String>> reList = new ArrayList<Map<String, String>>();
        list = sysSalesmanDao.exportSalesManAdmin(sysSalesManEntity);
        SysSalesManEntity  sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity);
        sysSalesManEntity1.setSalesmanId(sysSalesManEntity.getSalesmanId());
        String salesName = sysSalesManEntity1.getSalesName();
        Integer saleMan=sysSalesManEntity.getSalesmanId();
        int index=0;
        int indexRow=4;
        int firstSize=2;
        int countSalesName=0;
        int countAgentCompany=0;
        int index1=0;
        int index2=0;
        int listSize=0;
        Row row4=sheet.createRow(4);
        Cell cell31=null;
        String testName="";
        String agentCompanyNext="";
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                index++;
                firstSize++;
                row4 = sheet.createRow(indexRow++);
                //第0列
                cell31 = row4.createCell(0);
                cell31.setCellValue(index);
                cell31.setCellStyle(cellStyle);
                //第一列
                cell31 = row4.createCell(1);
                cell31.setCellValue(salesName);
                if (index==list.size()){
                    Integer nameCount=homePageSupplierDao.saleManCount2(saleMan);
                    //Integer nameCountFirstSize=indexRow-1;
                    int dangqianCount=indexRow-1;
                    int start=indexRow-nameCount;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
                }
                //添加样式
                cell31.setCellStyle(cellStyle);
                testName = salesName;
                //第二列
                cell31 = row4.createCell(2);
                Object agentCompany1 = map.get("agent_company");
                String agentCompany ="";
                if (agentCompany1!=null&&agentCompany1!="null"&&!agentCompany1.equals("")){
                    agentCompany=String.valueOf(agentCompany1);
                }
                if (agentCompany.equals(agentCompanyNext)){
                    if (index<=1&&(agentCompany1==null||agentCompany1!="null"||!agentCompany1.equals(""))){
                        countAgentCompany+=0;
                    }else {
                        countAgentCompany+=1;
                    }
                }
                if (!agentCompany.equals(agentCompanyNext)&&index>=2){
                    int dangqianCount=firstSize;
                    int start=dangqianCount-countAgentCompany;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
                    countAgentCompany=0;
                }
                if (index==list.size()&&agentCompany.equals(agentCompanyNext)){
                        int dangQianCount=indexRow-1;
                        int  start=dangQianCount-countAgentCompany;
                        sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                }
                agentCompanyNext = agentCompany;
                cell31.setCellValue(agentCompany);
                //添加样式
                cell31.setCellStyle(cellStyle);
                //第三列
                cell31 = row4.createCell(3);
                Object rbaName1 = map.get("rba_name");
                String rbaName = String.valueOf(rbaName1);
                Object rbsName1 = map.get("rbs_name");
                String rbsName = String.valueOf(rbsName1);
                Object rbbName1 = map.get("rbb_name");
                String rbbName = String.valueOf(rbbName1);
                String rbaNames=null;
                if (rbaName!="null"){
                    //cell31.setCellValue(rbaName);
                    rbaNames=rbaName;
                }else if (rbsName!="null"){
                    //cell31.setCellValue(rbsName);
                    rbaNames=rbsName;
                }else if (rbbName!="null"){
                    rbaNames=rbbName;
                }else{
                    rbaNames="";
                }
                cell31.setCellValue(rbaNames);
                //添加样式
                cell31.setCellStyle(cellStyle);
                //第四列
                cell31 = row4.createCell(4);
                Object createDate1 = map.get("create_date");
                String createDate = String.valueOf(createDate1);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date2 = sdf2.parse(createDate);
                cell31.setCellValue(sdf.format(date2));
                //添加样式
                cell31.setCellStyle(cellStyle);
                //第五列
                cell31 = row4.createCell(5);
                Object state1 = map.get("state");
                String state= String.valueOf(state1);
                Integer intState = Integer.valueOf(state);
                // cell31.setCellValue(state);
                //reMap.put("state",state);
                if (intState == 1) {
                    cell31.setCellValue("正常");
                    cell31.setCellStyle(cellStyle);
                } else if (intState == 2) {
                    cell31.setCellValue("异常");
                    cell31.setCellStyle(cellStyle);
                }
                //添加样式
            }
        }
        Row row5=sheet.createRow(index+4);
        //设置行高
        row5.setHeightInPoints(24);
        Cell cell51=null;
        cell51=row5.createCell(0);
        cell51.setCellValue("汇总");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(1);
        cell51.setCellValue("");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(2);
        cell51.setCellValue(Leaded+"家");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(3);
        cell51.setCellValue(Company+"家");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(4);
        cell51.setCellValue("");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(5);
        cell51.setCellValue("");
        cell51.setCellStyle(cellStyle);

        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        SimpleDateFormat sdf2=new SimpleDateFormat("MMddHHmmss");
        String fileName="YWDL"+sdf2.format(date);
        String realPath = tmpPath + fileName+ ".xlsx";
        File outFile = new File(realPath);
        wb.write(new FileOutputStream(outFile));
        String rootUrl = request.getContextPath() + EXPORT_PATH + fileName+ ".xlsx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", rootUrl);
        map.put("fileName", fileName + ".xlsx");
        srm.setObj(map);
        return srm;
    }
    public SysRetrunMessage exportSalesMan(SysSalesManEntity sysSalesManEntity) throws Exception{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate=new Date();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",account.getUserName());
        Date date=new Date();
        dataMap.put("newDate",sdf.format(date));
        dataMap.put("nodeName",account.getOrgGxId_Str());
        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle1 =wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle3 =wb.createCellStyle();
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font3=wb.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font3.setFontHeightInPoints((short)13);
        cellStyle3.setFont(font3);

        CellStyle cellStyle4 =wb.createCellStyle();
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font4=wb.createFont();
        font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font4.setFontHeightInPoints((short)13);
        cellStyle4.setFont(font4);
        //设置列宽
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 30 * 256);
        sheet.setColumnWidth(3, 30 * 256);
        sheet.setColumnWidth(4, 30 * 256);
        sheet.setColumnWidth(5, 30 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell=row.createCell(0);
        cell.setCellValue("业务代理发展统计表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 6);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle4);
        //cel.setCellStyle(style);
        for (int j = 1; j <= 6; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle4); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行
        Row row1=sheet.createRow(1);
        //设置行高
        row1.setHeightInPoints(24);
        Cell cell1=row1.createCell(0);
        cell1.setCellValue("单位");
        cell1.setCellStyle(cellStyle1);

        Cell cell2=row1.createCell(1);
        cell2.setCellValue(account.getOrgGxId_Str());
        cell2.setCellStyle(cellStyle1);

        Cell cell3=row1.createCell(2);
        cell3.setCellValue("");
        cell3.setCellStyle(cellStyle1);

        Cell cell4=row1.createCell(3);
        cell4.setCellValue("");
        cell4.setCellStyle(cellStyle1);

        Cell cell5=row1.createCell(4);
        cell5.setCellValue("制作日期");
        cell5.setCellStyle(cellStyle1);

        Cell cell6=row1.createCell(5);
        cell6.setCellValue("");
        cell6.setCellStyle(cellStyle1);

        Cell cell7=row1.createCell(6);
        cell6.setCellValue(sdf.format(date));
        cell6.setCellStyle(cellStyle1);
        //第二行
        //第一行
        Row row2=sheet.createRow(2);
        //设置行高
        row2.setHeightInPoints(22);
        Cell cell21=row2.createCell(0);
        cell21.setCellValue("制表人");
        cell21.setCellStyle(cellStyle1);

        Cell cell22=row2.createCell(1);
        cell22.setCellValue(account.getUserName());
        cell22.setCellStyle(cellStyle1);

        Cell cell23=row2.createCell(2);
        cell23.setCellValue("");
        cell23.setCellStyle(cellStyle1);

        Cell cell24=row2.createCell(3);
        cell24.setCellValue("");
        cell24.setCellStyle(cellStyle1);

        Cell cell25=row2.createCell(4);
        cell25.setCellValue("");
        cell25.setCellStyle(cellStyle1);

        Cell cell26=row2.createCell(5);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle1);
        Cell cell27=row2.createCell(6);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle1);

        Row row3=sheet.createRow(3);
        Cell cell32=null;
        //设置行高
        row3.setHeightInPoints(22);
        cell32=row3.createCell(0);
        cell32.setCellValue("序号");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(1);
        cell32.setCellValue("业务员");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(2);
        cell32.setCellValue("代理商");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(3);
        cell32.setCellValue("机构");
        cell32.setCellStyle(cellStyle3);
        row3.setHeightInPoints(22);
        cell32=row3.createCell(4);
        cell32.setCellValue("发展日期");
        cell32.setCellStyle(cellStyle3);
        cell32=row3.createCell(5);
        cell32.setCellValue("经营状态");
        cell32.setCellStyle(cellStyle3);
        Integer SalesmanId1=null;
        sysSalesManEntity = new SysSalesManEntity();
        sysSalesManEntity.setSalesAccount(account.getAccount());

        SysSalesManEntity sysSalesManEntity2 = sysSalesmanDao.getSalesManInfo(sysSalesManEntity);
        SalesmanId1 = sysSalesManEntity2.getSalesmanId();
        sysSalesManEntity.setSalesmanId(SalesmanId1);

        List<Map<String, Object>> list =new ArrayList<Map<String, Object>>();
        list = sysSalesmanDao.exportSalesMan(sysSalesManEntity);
        SysSalesManEntity  sysSalesManEntity1 = sysSalesmanDao.getObject(sysSalesManEntity);
        sysSalesManEntity1.setSalesmanId(sysSalesManEntity.getSalesmanId());
        String salesName = sysSalesManEntity1.getSalesName();
        Integer saleMan=sysSalesManEntity.getSalesmanId();
        int index=0;
        int indexRow=4;
        int firstSize=2;
        int countSalesName=0;
        int countAgentCompany=0;
        int index1=0;
        int index2=0;
        int listSize=0;
        Row row4=sheet.createRow(4);
        Cell cell31=null;
        String testName="";
        String agentCompanyNext="";

        int AgentCompanyCount=0;
        int rbaNameCount=0;
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                index++;
                firstSize++;
                row4 = sheet.createRow(indexRow++);
                //第0列
                cell31 = row4.createCell(0);
                cell31.setCellValue(index);
                cell31.setCellStyle(cellStyle);
                //第一列
                cell31 = row4.createCell(1);
                cell31.setCellValue(salesName);
                if (index==list.size()){
                    Integer nameCount=homePageSupplierDao.saleManCount2(saleMan);
                    //Integer nameCountFirstSize=indexRow-1;
                    int dangqianCount=indexRow-1;
                    int start=indexRow-nameCount;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
                }
                //添加样式
                cell31.setCellStyle(cellStyle);
                testName = salesName;
                //第二列
                cell31 = row4.createCell(2);
                Object agentCompany1 = map.get("agent_company");
                String agentCompany ="";
                if (agentCompany1!=null&&agentCompany1!="null"&&!agentCompany1.equals("")){
                    agentCompany=String.valueOf(agentCompany1);
                }
                if (agentCompany.equals(agentCompanyNext)){
                    if (index<=1&&(agentCompany1==null||agentCompany1!="null"||!agentCompany1.equals(""))){
                        countAgentCompany+=0;
                    }else {
                        countAgentCompany+=1;
                    }
                }
                if (!agentCompany.equals(agentCompanyNext)&&index>=2){
                    int dangqianCount=firstSize;
                    int start=dangqianCount-countAgentCompany;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
                    countAgentCompany=0;
                }
                if (index==list.size()&&agentCompany.equals(agentCompanyNext)){
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-countAgentCompany;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                }
                agentCompanyNext = agentCompany;
                cell31.setCellValue(agentCompany);
                //添加样式
                cell31.setCellStyle(cellStyle);
                //第三列
                cell31 = row4.createCell(3);
                Object rbaName1 = map.get("rba_name");
                String rbaName = String.valueOf(rbaName1);
                Object rbsName1 = map.get("rbs_name");
                String rbsName = String.valueOf(rbsName1);
                Object rbbName1 = map.get("rbb_name");
                String rbbName = String.valueOf(rbbName1);
                String rbaNames=null;
                if (rbaName!="null"){
                    //cell31.setCellValue(rbaName);
                    rbaNames=rbaName;
                }else if (rbsName!="null"){
                    //cell31.setCellValue(rbsName);
                    rbaNames=rbsName;
                }else if (rbbName!="null"){
                    rbaNames=rbbName;
                }else{
                    rbaNames="";
                }
                cell31.setCellValue(rbaNames);
                //添加样式
                cell31.setCellStyle(cellStyle);
                //第四列
                cell31 = row4.createCell(4);
                Object createDate1 = map.get("create_date");
                String createDate = String.valueOf(createDate1);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date2 = sdf2.parse(createDate);
                cell31.setCellValue(sdf.format(date2));
                //添加样式
                cell31.setCellStyle(cellStyle);
                //第五列
                cell31 = row4.createCell(5);
                Object state1 = map.get("state");
                String state= String.valueOf(state1);
                Integer intState = Integer.valueOf(state);
                // cell31.setCellValue(state);
                //reMap.put("state",state);
                if (intState == 1) {
                    cell31.setCellValue("正常");
                    cell31.setCellStyle(cellStyle);
                } else if (intState == 2) {
                    cell31.setCellValue("异常");
                    cell31.setCellStyle(cellStyle);
                }
                if (agentCompany!=""){
                    AgentCompanyCount+=1;
                }else {
                    AgentCompanyCount+=0;
                }
                if (rbaName!="null"||rbsName!="null"||rbbName!="null"){
                    rbaNameCount+=1;
                }else{
                    rbaNameCount+=0;
                }
                //添加样式
            }
        }
        Row row5=sheet.createRow(index+4);
        //设置行高
        row5.setHeightInPoints(24);
        Cell cell51=null;
        cell51=row5.createCell(0);
        cell51.setCellValue("汇总");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(1);
        cell51.setCellValue("");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(2);
        cell51.setCellValue(AgentCompanyCount+"家");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(3);
        cell51.setCellValue(rbaNameCount+"家");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(4);
        cell51.setCellValue("");
        cell51.setCellStyle(cellStyle);
        cell51=row5.createCell(5);
        cell51.setCellValue("");
        cell51.setCellStyle(cellStyle);

        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        SimpleDateFormat sdf2=new SimpleDateFormat("MMddHHmmss");
        String fileName="YWDL"+sdf2.format(date);
        String realPath = tmpPath + fileName+ ".xlsx";
        File outFile = new File(realPath);
        wb.write(new FileOutputStream(outFile));
        String rootUrl = request.getContextPath() + EXPORT_PATH + fileName+ ".xlsx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", rootUrl);
        map.put("fileName", fileName + ".xlsx");
        srm.setObj(map);


        return  srm;
    }
    public SysRetrunMessage exportSevenDayTwo() throws Exception{
        SysRetrunMessage srm = new SysRetrunMessage(1l);
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        Date StartDate=new Date();
        Date EndDate=new Date(StartDate.getTime()- 24*60*60*1000*7);
        Date sevenDayDate=new Date(EndDate.getTime()- 24*60*60*1000*7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        // SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Date NewDate=new Date();
        dataMap.put("newDate", sdf3.format(NewDate));
        //dataMap.put("newDate", sdf.format(NewDate));
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        dataMap.put("userName", sysUserInfo.getUserName());
        dataMap.put("OrgGxIdStr",sysUserInfo.getOrgGxId_Str());
        SysUserInfo account = this.GetOneSessionAccount();
        //开始日期
        dataMap.put("EndDate", sdf3.format(EndDate));
        //截止日期
        dataMap.put("StartDate", sdf3.format(StartDate));
        //7天日期
        dataMap.put("sevenDayDate", sdf3.format(sevenDayDate));

        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle2 = wb.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        Font font=wb.createFont();
        font.setFontHeightInPoints((short) 15);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        cellStyle2.setFont(font);
        CellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle4 = wb.createCellStyle();
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        Font font2=wb.createFont();
//        font2.setColor((short)5);//setColor((short)5)
        font2.setColor(HSSFFont.COLOR_RED);
        cellStyle4.setFont(font2);

        CellStyle cellStyle5 = wb.createCellStyle();
        cellStyle5.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle5.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle5.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle5.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle5.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle5.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle5.setFont(font2);

        CellStyle cellStyle6 = wb.createCellStyle();
        cellStyle6.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle6.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle6.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle6.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle6.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        Font font3=wb.createFont();
        font3.setFontHeightInPoints((short) 13);
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        cellStyle6.setFont(font3);

        //设置列宽
        sheet.setColumnWidth(0, 5 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);
        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(10, 20 * 256);
        sheet.setColumnWidth(11, 20 * 256);

        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell=row.createCell(0);
        cell.setCellValue("交易分析统计");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 10);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        //cel.setCellStyle(style);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=10; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行
        Row row1=sheet.createRow(1);
        //设置行高
        row1.setHeightInPoints(24);
        Cell cell1=row1.createCell(0);
        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle);

        Cell cell2=row1.createCell(1);
        cell2.setCellValue("");
//        cell2.setCellStyle(cellStyle);

        Cell cell3=row1.createCell(2);
        cell3.setCellValue("单位");
        cell3.setCellStyle(cellStyle3);

        Cell cell4=row1.createCell(3);
        cell4.setCellValue(account.getOrgGxId_Str());
        cell4.setCellStyle(cellStyle3);

        Cell cell41=row1.createCell(4);
        cell41.setCellValue("");
//        cell41.setCellStyle(cellStyle);
        Cell cell42=row1.createCell(5);
        cell42.setCellValue("");
//        cell42.setCellStyle(cellStyle);

        Cell cell43=row1.createCell(6);
        cell43.setCellValue("");
//        cell43.setCellStyle(cellStyle);
//        Cell cell44=row1.createCell(7);
//        cell44.setCellValue("");
//        cell44.setCellStyle(cellStyle);
        Cell cell45=row1.createCell(7);
        cell45.setCellValue("制作日期");
        cell45.setCellStyle(cellStyle3);

        Cell cell5=row1.createCell(8);
        cell5.setCellValue(sdf4.format(StartDate));
        cell5.setCellStyle(cellStyle3);

        Cell cell6=row1.createCell(9);
        cell6.setCellValue("");
        cell6.setCellStyle(cellStyle3);
        sheet.addMergedRegion(new CellRangeAddress(1,1,8,9));
        //第二行
        Row row21=sheet.createRow(2);
        //设置行高
        row21.setHeightInPoints(20);
        Cell cell21=row21.createCell(0);
        cell21.setCellValue("");
//        cell21.setCellStyle(cellStyle);

        Cell cell22=row21.createCell(1);
        cell22.setCellValue("");
//        cell22.setCellStyle(cellStyle);

        Cell cell23=row21.createCell(2);
        cell23.setCellValue("制表人");
        cell23.setCellStyle(cellStyle3);

        Cell cell24=row21.createCell(3);
        cell24.setCellValue(account.getUserName());
        cell24.setCellStyle(cellStyle3);

        Cell cell241=row21.createCell(4);
        cell241.setCellValue("");
//        cell241.setCellStyle(cellStyle);

        Cell cell242=row21.createCell(5);
        cell242.setCellValue("");
//        cell242.setCellStyle(cellStyle);

        Cell cell243=row21.createCell(6);
        cell243.setCellValue("");
//        cell243.setCellStyle(cellStyle);
//        Cell cell244=row21.createCell(7);
//        cell244.setCellValue("");
//        cell244.setCellStyle(cellStyle);
        Cell cell245=row21.createCell(7);
        cell245.setCellValue("日期");
        cell245.setCellStyle(cellStyle3);

        Cell cell25=row21.createCell(8);
        cell25.setCellValue(sdf3.format(EndDate)+"至"+sdf3.format(StartDate));
        cell25.setCellStyle(cellStyle3);

        Cell cell26=row21.createCell(9);
        cell26.setCellValue("");
        cell26.setCellStyle(cellStyle3);

        sheet.addMergedRegion(new CellRangeAddress(2,2,8,9));
        Row row3=sheet.createRow(3);
        //设置行高
        row3.setHeightInPoints(20);
        Cell cell31=null;
        cell31=row3.createCell(0);
        cell31.setCellValue("序号");
        cell31.setCellStyle(cellStyle6);
        cell31=row3.createCell(1);
        cell31.setCellValue("集团");
        cell31.setCellStyle(cellStyle6);
//
        cell31=row3.createCell(2);
        cell31.setCellValue("医院");
        cell31.setCellStyle(cellStyle6);

        cell31=row3.createCell(3);
        cell31.setCellValue("门诊");
        cell31.setCellStyle(cellStyle6);

        cell31=row3.createCell(4);
        cell31.setCellValue("日期");
        cell31.setCellStyle(cellStyle6);

        cell31=row3.createCell(5);
        cell31.setCellValue("购买数量");
        cell31.setCellStyle(cellStyle6);

        cell31=row3.createCell(6);
        cell31.setCellValue("交易数量");
        cell31.setCellStyle(cellStyle6);

        cell31=row3.createCell(7);
        cell31.setCellValue("产品分类");
        cell31.setCellStyle(cellStyle6);

        cell31=row3.createCell(8);
        cell31.setCellValue("平均单价");
        cell31.setCellStyle(cellStyle6);

        cell31=row3.createCell(9);
        cell31.setCellValue("总金额");
        cell31.setCellStyle(cellStyle6);
        cell31=row3.createCell(10);
        cell31.setCellValue("所属业务员");
        cell31.setCellStyle(cellStyle6);

        Row row4=sheet.createRow(4);
        //设置行高
        row4.setHeightInPoints(50);
        Cell cellRow4=null;
        int index=0;
        int indexRow=4;
        int indexRows=0;
//        int firstSize=2;
//        int countSalesName=0;
//        int countAgentCompany=0;
//        int index1=0;
//        int index2=0;
//        int listSize=0;

        int countrbasbName=0;
        int countrbaName=0;
        int countrbsName=0;
        int countrbbName=0;
        int counttime1=0;


        int Cell1firstSize=2;

        String rbasbNameNext="";
        String rbaNameNext="";
        String rbsNameNext="";
        String rbbNameNext="";
        String time1Next="";
//        String testName="";
//        String agentCompanyNext="";
//
//        int AgentCompanyCount=0;
//        int rbaNameCount=0;
//
        Double OrderNums=0.0;
        Double prices=0.0;
        Double TotalMoneys=0.0;
//        Double gouMai=0.0;

        Integer Typenum1=0;
        Integer TypenumCount0=0;
        Integer TypenumCount1=0;
        Integer TypenumCount2=0;
        Integer TypenumCount3=0;
        Integer TypenumCount4=0;
        Integer TypenumCount5=0;
        Integer TypenumCount6=0;
        Integer TypenumCount7=0;
        Integer TypenumCount8=0;


        List<Map<String, Object>> list = expoetExcelDao.getSevenListCountAll(EndDate, StartDate);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                index++;
                Cell1firstSize++;
                row4 = sheet.createRow(indexRow++);
                //序号
                cellRow4 = row4.createCell(0);
                cellRow4.setCellValue(index+"");
                cellRow4.setCellStyle(cellStyle);
                //医院门诊
                cellRow4 = row4.createCell(1);
                Object rbaName1 = map.get("rba_name");
                String rbaName="";
                if (rbaName1!=null&&rbaName1!="null"&&!rbaName1.equals("")){
                    rbaName=String.valueOf(rbaName1);
                }
                cellRow4.setCellValue(rbaName);
                if (rbaName.equals(rbaNameNext)){
                    if (index<=1&&(rbaName==null||rbaName!="null"||!rbaName.equals(""))){
                        countrbaName+=0;
                    }else {
                        countrbaName+=1;
                    }
                }
                if(!rbaName.equals(rbaNameNext)&&index>=2){
                    int dangQianCount=Cell1firstSize;
                    int start =dangQianCount-countrbaName;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    countrbaName=0;
                }
                //合并最后一次查询的单元格
                if (index==list.size()&&rbaName.equals(rbaNameNext)){
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-countrbaName;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                }
                rbaNameNext=rbaName;
                cellRow4.setCellStyle(cellStyle);
                cellRow4 = row4.createCell(2);
                Object rbsName1 = map.get("rbs_name");
                String rbsName="";
                if (rbsName1!=null&&rbsName1!="null"&&!rbsName1.equals("")){
                    rbsName= String.valueOf(rbsName1);
                }
                cellRow4.setCellValue(rbsName);
                if (rbsName.equals(rbsNameNext)){
                    if (index<=1&&(rbsName==null||rbsName!="null"||!rbsName.equals(""))){
                        countrbsName+=0;
                    }else {
                        countrbsName+=1;
                    }
                }
                if(!rbsName.equals(rbsNameNext)&&index>=2){
                    int dangQianCount=Cell1firstSize;
                    int start =dangQianCount-countrbsName;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                    countrbsName=0;
                }
                //合并最后一次查询的单元格
                if (index==list.size()&&rbsName.equals(rbsNameNext)){
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-countrbsName;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                }
                rbsNameNext=rbsName;
                cellRow4.setCellStyle(cellStyle);
                cellRow4 = row4.createCell(3);
                Object rbbName1 = map.get("rbb_name");
                String rbbName="";
                if (rbbName1!=null&&rbbName1!="null"&&!rbbName1.equals("")){
                    rbbName=String.valueOf(rbbName1);
                }
                cellRow4.setCellValue(rbbName);

                if (rbbName.equals(rbbNameNext)){
                    if (index<=1&&(rbbName==null||rbbName!="null"||!rbbName.equals(""))){
                        countrbbName+=0;
                    }else {
                        countrbbName+=1;
                    }
                }
                if(!rbbName.equals(rbbNameNext)&&index>=2){
                    int dangQianCount=Cell1firstSize;
                    int start =dangQianCount-countrbbName;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
                    countrbbName=0;
                }
                //合并最后一次查询的单元格
                if (index==list.size()&&rbbName.equals(rbbNameNext)){
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-countrbbName;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
                }
                rbbNameNext=rbbName;
                cellRow4.setCellStyle(cellStyle);

                //日期
                cellRow4 = row4.createCell(4);
                Object object1=map.get("time1");
                String time1=String.valueOf(object1);
                cellRow4.setCellValue(time1.toString());

                if (time1.equals(time1Next)){
                    if (index<=1&&(time1==null||time1!="null"||!time1.equals(""))){
                        counttime1+=0;
                    }else {
                        counttime1+=1;
                    }
                }
                if(!time1.equals(time1Next)&&index>=2){
                    int dangQianCount=Cell1firstSize;
                    int start =dangQianCount-counttime1;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 4, 4));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 5, 5));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 6, 6));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 8, 8));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 9, 9));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 10, 10));
                    counttime1=0;
                }
                //合并最后一次查询的单元格
                if (index==list.size()&&time1.equals(time1Next)){
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-counttime1;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 4, 4));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 5, 5));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 6, 6));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 8, 8));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 9, 9));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 10, 10));
                }
                cellRow4.setCellStyle(cellStyle);
                //购买数量
                cellRow4 = row4.createCell(5);
                cellRow4.setCellValue(df2.format(map.get("mat_number")!= null ?map.get("mat_number"):0d));
                cellRow4.setCellStyle(cellStyle);
                //交易数量
                cellRow4 = row4.createCell(6);
                cellRow4.setCellValue(df2.format(map.get("OrderNum")!= null ?map.get("OrderNum"):0d));
                Double  OrderNum1=Double.parseDouble(df2.format(map.get("OrderNum")));
                if (OrderNum1!=null&&!OrderNum1.equals("")){
                    if (!time1.equals(time1Next)){
                        OrderNums+=OrderNum1;
                    }else {
                        OrderNums+=0;
                    }
                }else {
                    OrderNums+=0;
                }
                cellRow4.setCellStyle(cellStyle);

//                Object OrderNumObj=map.get("OrderNum");
//                Double OrderNumObj1=Double.parseDouble(String.valueOf(OrderNumObj));
//                if (OrderNumObj1!=null){
//                    OrderNums+=OrderNumObj1;
//                }else {
//                    OrderNums+=0;
//                }
//                Object TotalMoneyObj=map.get("TotalMoney");
//                Double TotalMoneyObj1=Double.parseDouble(String.valueOf(TotalMoneyObj));
//                if (TotalMoneyObj1!=null){
//                    TotalMoneys+=TotalMoneyObj1;
//                }else {
//                    TotalMoneys+=0;
//                }
                //产品分类
                cellRow4 = row4.createCell(7);
                Object objectTypenum=map.get("matType");
                Integer countType=expoetExcelDao.getSevenListCountAll1Count(objectTypenum.toString(),time1);
                cellRow4.setCellValue(objectTypenum.toString()+"("+countType+")");
                cellRow4.setCellStyle(cellStyle);
//                Object matTypeCount=map.get("matTypeCount");
//
//                Integer IntTypenum=Integer.parseInt(objectTypenum.toString());
//                if (IntTypenum==2200){
//                    cellRow4.setCellValue("修复、充填类"+matTypeCount);
//                }
//                else if (IntTypenum==2201){
//                    cellRow4.setCellValue("车针/扩锉/磨头"+matTypeCount);
//                }
//                else if (IntTypenum==2202){
//                    cellRow4.setCellValue("口腔常用材料"+matTypeCount);
//                }
//                else if (IntTypenum==2203){
//                    cellRow4.setCellValue("医用耗材类"+matTypeCount);
//                }
//                else if (IntTypenum==2204){
//                    cellRow4.setCellValue("口腔科常用器械"+matTypeCount);
//                }
//                else if (IntTypenum==2205){
//                    cellRow4.setCellValue("正畸产品类"+matTypeCount);
//                }
//                else if (IntTypenum==2206){
//                    cellRow4.setCellValue("口腔护理"+matTypeCount);
//                }
//                else if (IntTypenum==2207){
//                    cellRow4.setCellValue("种植产品类"+matTypeCount);
//                }
//                else  if (IntTypenum==2208){
//                    cellRow4.setCellValue("口腔设备类"+matTypeCount);
//                }else {
//                    cellRow4.setCellValue("马尼金刚砂车针"+matTypeCount);
//                }

//                Integer countType=expoetExcelDao.getSevenListCountAll1Count(IntTypenum,time1.toString());
//                if (IntTypenum==2200){
//                    cellRow4.setCellValue("");
//                }
                //平均单价
                cellRow4 = row4.createCell(8);
                cellRow4.setCellValue(df.format(map.get("price")!= null ?map.get("price"):0d));
//                cellRow4.setCellValue("平均单价");
                cellRow4.setCellStyle(cellStyle);
                //总金额
                cellRow4 = row4.createCell(9);
                cellRow4.setCellValue(df.format(map.get("Total_money")!= null ?map.get("Total_money"):0d));
                cellRow4.setCellStyle(cellStyle5);

                Double  TotalMoney1=Double.parseDouble(df.format(map.get("Total_money")));
                if (TotalMoney1!=null&&!TotalMoney1.equals("")){
                    if (!time1.equals(time1Next)){
                        TotalMoneys+=TotalMoney1;
                    }else {
                        TotalMoneys+=0;
                    }
                }else {
                    TotalMoneys+=0;
                }
                //所属业务员
                cellRow4 = row4.createCell(10);
                cellRow4.setCellValue("");
                cellRow4.setCellStyle(cellStyle);

                time1Next=time1;
            }
        }
        Row row5=sheet.createRow(indexRow);
        Cell cellRow5=null;
        cellRow5 = row5.createCell(0);
        cellRow5.setCellValue("");
        cellRow5.setCellStyle(cellStyle);

        cellRow5 = row5.createCell(1);
        cellRow5.setCellValue("");
        cellRow5.setCellStyle(cellStyle);

        cellRow5 = row5.createCell(2);
        cellRow5.setCellValue("");
        cellRow5.setCellStyle(cellStyle);
        cellRow5 = row5.createCell(3);
        cellRow5.setCellValue("汇总");
        cellRow5.setCellStyle(cellStyle5);
        cellRow5 = row5.createCell(4);
        cellRow5.setCellValue("");
        cellRow5.setCellStyle(cellStyle);
        cellRow5 = row5.createCell(5);
        cellRow5.setCellValue("");
        cellRow5.setCellStyle(cellStyle);
        cellRow5 = row5.createCell(6);
        cellRow5.setCellValue(df2.format(OrderNums));
        cellRow5.setCellStyle(cellStyle);

        cellRow5 = row5.createCell(7);
        cellRow5.setCellValue("");
        cellRow5.setCellStyle(cellStyle);

        cellRow5 = row5.createCell(8);
        //总的平均单价
        if (OrderNums!=0&&TotalMoneys!=null&&OrderNums!=null) {
            prices=TotalMoneys/OrderNums;
        }
        cellRow5.setCellValue(df.format(prices));
        cellRow5.setCellStyle(cellStyle);
        cellRow5 = row5.createCell(9);
        cellRow5.setCellValue(df.format(TotalMoneys));
        cellRow5.setCellStyle(cellStyle5);
        cellRow5 = row5.createCell(10);
        cellRow5.setCellValue("");
        cellRow5.setCellStyle(cellStyle);


        Row row6=sheet.createRow(indexRow+1);
        Cell cellRow6=null;
        cellRow6 = row6.createCell(0);
        cellRow6.setCellValue("");
        cellRow6 = row6.createCell(1);
        cellRow6.setCellValue("");
        cellRow6 = row6.createCell(2);
        cellRow6.setCellValue("");
        cellRow6 = row6.createCell(3);
        cellRow6.setCellValue("对比7天数据");
        cellRow6.setCellStyle(cellStyle4);
        Double sevenOrderNums=0.0;
        Double sevenprices=0.0;
        Double sevenTotalMoneys=0.0;
        List<Map<String, Object>> listSeven = expoetExcelDao.getSevenListCountAll1(sevenDayDate, EndDate);
        if (listSeven != null && listSeven.size() > 0) {
            for (Map<String, Object> map : listSeven) {
                Object OrderNumObj=map.get("OrderNum");
                Double OrderNumObj1=Double.parseDouble(String.valueOf(OrderNumObj));
                if (OrderNumObj1!=null){
                    sevenOrderNums+=OrderNumObj1;
                }else {
                    sevenOrderNums+=0;
                }
                Object TotalMoneyObj=map.get("TotalMoney");
                Double TotalMoneyObj1=Double.parseDouble(String.valueOf(TotalMoneyObj));
                if (TotalMoneyObj1!=null){
                    sevenTotalMoneys+=TotalMoneyObj1;
                }else {
                    sevenTotalMoneys+=0;
                }
            }
        }
        //总的平均单价
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        if (sevenOrderNums!=0&&sevenTotalMoneys!=null&&sevenOrderNums!=null) {
            sevenprices=sevenTotalMoneys/sevenOrderNums;
        }
        cellRow6 = row6.createCell(4);
        cellRow6.setCellValue(sdf2.format(sevenDayDate)+"至"+sdf2.format(EndDate)+"(交易数量"+df2.format(sevenOrderNums)+"个,平均单价"+df.format(sevenprices)+"元,总金额"+df.format(sevenTotalMoneys)+"元)");
        cellRow6.setCellStyle(cellStyle4);
        for (int i = 5; i <=10 ; i++) {
            cellRow6 = row6.createCell(i);
            cellRow6.setCellStyle(cellStyle4); //style为带边框的样式 上面有定义
            cellRow6.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(indexRow+1,indexRow+1,4,10 ));
        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sdf2.format(EndDate)+"至"+sdf2.format(StartDate)+"交易分析表";
        String realPath = tmpPath + fileName+ ".xlsx";
        File outFile = new File(realPath);
        wb.write(new FileOutputStream(outFile));
        String rootUrl = request.getContextPath() + EXPORT_PATH + fileName+ ".xlsx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", rootUrl);
        map.put("fileName", fileName + ".xlsx");
        srm.setObj(map);
        return  srm;
    }

    //管理员业务放款导出放款明
    public SysRetrunMessage exportLoanMxList(String orderCodeGJ, String dateInput1, String dateInput2, Integer selectValue1, String moneyFw1,
                                             String moneyFw2, String gongYingS, String caiGou, String jiGouMenZhen, Integer selectValue,
                                             String dateInput7, String dateInput8, Integer moiId) throws Exception{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date StartDate=new Date();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",account.getUserName());
        Date date=new Date();
        dataMap.put("newDate",sdf.format(date));
        dataMap.put("nodeName",account.getOrgGxId_Str());
        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle1 =wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle3 =wb.createCellStyle();
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font3=wb.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font3.setFontHeightInPoints((short)13);
        cellStyle3.setFont(font3);

        CellStyle cellStyle4 =wb.createCellStyle();
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font4=wb.createFont();
        font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font4.setFontHeightInPoints((short)15);
        cellStyle4.setFont(font4);
        //设置列宽
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 15 * 256);
        sheet.setColumnWidth(9, 15 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 15 * 256);

//        sheet.setColumnWidth(3, 30 * 256);
//        sheet.setColumnWidth(4, 30 * 256);
//        sheet.setColumnWidth(5, 30 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell=row.createCell(0);
        cell.setCellValue("财务业务员代理放款统计表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 11);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle4);
        //cel.setCellStyle(style);
        for (int j = 1; j <= 11; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle4); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //制作人
        Row row1=sheet.createRow(1);
        //设置行高
        row1.setHeightInPoints(24);
        Cell cell1=row1.createCell(0);
        cell1.setCellValue("制作人");
        cell1.setCellStyle(cellStyle1);
        for (int j = 1; j <= 3; j++) {
            cell1 = row1.createCell(j);
            cell1.setCellStyle(cellStyle1); //style为带边框的样式 上面有定义
            cell1.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(1,1, 0, 3));
        cell1=row1.createCell(4);
        cell1.setCellValue(account.getUserName());
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(5);
        cell1.setCellValue("制作日期");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(6);
        Date date1=new Date();
        cell1.setCellValue(sdf2.format(date1));
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(7);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        sheet.addMergedRegion(new CellRangeAddress(1,1, 6, 7));
        cell1=row1.createCell(8);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue("统计范围");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(11);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        sheet.addMergedRegion(new CellRangeAddress(1,1, 10, 11));
        Row row2=sheet.createRow(2);
        //设置行高
        row.setHeightInPoints(24);
        Cell cell2=row2.createCell(0);
        cell2.setCellValue("序号");
        cell2.setCellStyle(cellStyle3);
        row.setHeightInPoints(24);
        cell2=row2.createCell(1);
        cell2.setCellValue("业务代理");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(2);
        cell2.setCellValue("服务类型");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(3);
        cell2.setCellValue("订单号");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(4);
        cell2.setCellValue("订单日期");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(5);
        cell2.setCellValue("支付方式");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(6);
        cell2.setCellValue("支付时间");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(7);
        cell2.setCellValue("支付单号");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(8);
        cell2.setCellValue("应收金额");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(9);
        cell2.setCellValue("实收金额");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(10);
        cell2.setCellValue("订单状态");
        cell2.setCellStyle(cellStyle3);
        cell2=row2.createCell(11);
        cell2.setCellValue("放款状态");
        cell2.setCellStyle(cellStyle3);

        Row row4=sheet.createRow(3);
        //设置行高
        Cell cellRow4=null;
        int index =0;
        int indexRows =3;
        Double shiShous=0.0;
        Double yingShou=0.0;
        List<Map<String, Object>> list =expoetExcelDao.getSalesManLoanMxList(orderCodeGJ, dateInput1, dateInput2, selectValue1, moneyFw1, moneyFw2, gongYingS, caiGou, jiGouMenZhen, selectValue, dateInput7, dateInput8, moiId);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                    index++;
                indexRows++;
                    cellRow4=row4.createCell(0);
                    cellRow4.setCellValue(index+"");
                    cellRow4.setCellStyle(cellStyle);
                    Object salesName=map.get("sales_name");
                    cellRow4=row4.createCell(1);
                    if (salesName!=null&& !salesName.equals("")){
                        cellRow4.setCellValue(salesName.toString());
                        cellRow4.setCellStyle(cellStyle);
                    }else {
                        cellRow4.setCellValue("");
                        cellRow4.setCellStyle(cellStyle);
                    }
                Object salesType=map.get("sales_type");
                cellRow4=row4.createCell(2);
                if (salesType!=null&& !salesType.equals("")){
                    if (salesType.equals("1")){
                        cellRow4.setCellValue("机构门诊业务");
                    }else if (salesType.equals("2")){
                        cellRow4.setCellValue("代理商");
                    }else{
                        cellRow4.setCellValue("供应商业务");
                    }
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                Object orderCode=map.get("order_code");
                cellRow4=row4.createCell(3);
                if (orderCode!=null&& !orderCode.equals("")){
                    cellRow4.setCellValue(orderCode.toString());
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Object placeOrderTime=map.get("place_order_time");
                cellRow4=row4.createCell(4);
                if (placeOrderTime!=null&& !placeOrderTime.equals("")){
                    cellRow4.setCellValue(sim.format(placeOrderTime));
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                Object payType=map.get("pay_type");
                cellRow4=row4.createCell(5);
                if (payType!=null&& !payType.equals("")){
                    if (payType.equals("1")){
                        cellRow4.setCellValue("支付宝");
                    }else if (payType.equals("2")){
                        cellRow4.setCellValue("微信");
                    }else if (payType.equals("3")){
                        cellRow4.setCellValue("月结");
                    }
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                Object payDate=map.get("pay_date");
                cellRow4=row4.createCell(6);
                if (payDate!=null&& !payDate.equals("")){
                    cellRow4.setCellValue(sim.format(payDate));
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                Object payCode=map.get("pay_code");
                cellRow4=row4.createCell(7);
                if (payCode!=null&& !payCode.equals("")){
                    cellRow4.setCellValue(sim.format(payDate));
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                Object placeOrderMoney=map.get("place_order_money");
                Double placeOrderMoney1=Double.parseDouble(placeOrderMoney.toString());
                cellRow4=row4.createCell(8);
                if (placeOrderMoney!=null&& !placeOrderMoney.equals("")){
                    yingShou=placeOrderMoney1;
                    cellRow4.setCellValue(df.format(placeOrderMoney));
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                Object actualMoney=map.get("actual_money");
                cellRow4=row4.createCell(9);
                Double actualMoney1=Double.parseDouble(actualMoney.toString());
                if (actualMoney!=null&& !actualMoney.equals("")){
                    shiShous=actualMoney1;
                    cellRow4.setCellValue(df.format(actualMoney));
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                Object processStatus=map.get("process_status");
                cellRow4=row4.createCell(10);
                if (processStatus!=null&& !processStatus.equals("")){
                    switch (processStatus.toString()){
                        case "1" :
                            cellRow4.setCellValue("待发货");
                            break;
                        case "2" :
                            cellRow4.setCellValue("待付款");
                            break;
                        case "3" :
                            cellRow4.setCellValue("待收货");
                            break;
                        case "4" :
                            cellRow4.setCellValue("部分发货");
                            break;
                        case "5" :
                            cellRow4.setCellValue("交易成功");
                            break;
                        case "6" :
                            cellRow4.setCellValue("交易失败");
                            break;
                    }
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
                Object loanState=map.get("loan_state");
                cellRow4=row4.createCell(11);
                if (loanState!=null&& !loanState.equals("")){
                    if (loanState.equals("1")){
                        cellRow4.setCellValue("已放款");
                    }else if (loanState.equals("2")){
                        cellRow4.setCellValue("待放款");
                    }else{
                        cellRow4.setCellValue("未放款");
                    }
                    cellRow4.setCellStyle(cellStyle);
                }else {
                    cellRow4.setCellValue("");
                    cellRow4.setCellStyle(cellStyle);
                }
            }
        }
        Row row5=sheet.createRow(indexRows);
        //设置行高
//        row5.setHeightInPoints(24);
        Cell cell5=row5.createCell(0);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle);

        cell5=row5.createCell(1);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle);
        cell5=row5.createCell(2);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle);
        cell5=row5.createCell(3);
        cell5.setCellValue("汇总");
        cell5.setCellStyle(cellStyle);
        for (int i=4 ; i <= 7; i++) {
            cell5=row5.createCell(i);
            cell5.setCellValue("");
            cell5.setCellStyle(cellStyle);
        }
        cell5=row5.createCell(8);
        cell5.setCellValue(df2.format(yingShou));
        cell5.setCellStyle(cellStyle);
        cell5=row5.createCell(9);
        cell5.setCellValue(df2.format(shiShous));
        cell5.setCellStyle(cellStyle);
        cell5=row5.createCell(10);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle);
        cell5=row5.createCell(11);
        cell5.setCellValue("");
        cell5.setCellStyle(cellStyle);
        sheet.addMergedRegion(new CellRangeAddress(indexRows,indexRows, 4, 7));
        sheet.addMergedRegion(new CellRangeAddress(indexRows,indexRows, 10, 11));

        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName="财务业务员代理放款统计表";
        String realPath = tmpPath + fileName+ ".xlsx";
        File outFile = new File(realPath);
        wb.write(new FileOutputStream(outFile));
        String rootUrl = request.getContextPath() + EXPORT_PATH + fileName+ ".xlsx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", rootUrl);
        map.put("fileName", fileName + ".xlsx");
        srm.setObj(map);
        return  srm;
    }

    //导出领料员申领管理批量导出物料清单和导出单个物料清单
    public SysRetrunMessage exportPick(String  moiIds) throws Exception{
        SysRetrunMessage srm=new SysRetrunMessage(1l);
        SysUserInfo account = this.GetOneSessionAccount();
        DecimalFormat df = new DecimalFormat("######0.00");
        DecimalFormat df2 = new DecimalFormat("######0");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
        Date StartDate=new Date();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("userName",account.getUserName());
        Date date=new Date();
        dataMap.put("newDate",sdf.format(date));
        dataMap.put("nodeName",account.getOrgGxId_Str());
        Workbook wb=new XSSFWorkbook();
        Sheet sheet=wb.createSheet();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle1 =wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle2 =wb.createCellStyle();
        cellStyle2.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 居中
        cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        CellStyle cellStyle3 =wb.createCellStyle();
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font3=wb.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font3.setFontHeightInPoints((short)13);
        cellStyle3.setFont(font3);

        CellStyle cellStyle4 =wb.createCellStyle();
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font font4=wb.createFont();
        font4.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font4.setFontHeightInPoints((short)13);
        cellStyle4.setFont(font4);
        //设置列宽
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
//        sheet.setColumnWidth(3, 20 * 256);
//        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(14, 20 * 256);
        sheet.setColumnWidth(16, 20 * 256);
        sheet.setColumnWidth(18, 20 * 256);
//        sheet.setColumnWidth(8, 30 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(25);
        Cell cell=row.createCell(0);
        cell.setCellValue(account.getOrgGxId_Str()+"-"+account.getUserName()+"领料表统计");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 19);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle4);
        //cel.setCellStyle(style);
        for (int j = 1; j <= 19; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle4); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        Row row1=sheet.createRow(1);
        //设置行高
        row1.setHeightInPoints(20);
        Cell cell1=null;
        cell1=row1.createCell(0);
        cell1.setCellValue("单位：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(1);
        cell1.setCellValue(account.getOrgGxId_Str());
        cell1.setCellStyle(cellStyle2);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 7));
        for (int j = 1; j <= 7; j++) {
            cell1 = row.createCell(j);
            cell1.setCellStyle(cellStyle1); //style为带边框的样式 上面有定义
            cell1.setCellValue("");
        }
        cell1=row1.createCell(8);
        cell1.setCellValue("制表日期：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue(sdf.format(date));
        cell1.setCellStyle(cellStyle2);
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(11);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(12);
        cell1.setCellValue("制表人:");
        cell1.setCellStyle(cellStyle1);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9, 10));
        cell1=row1.createCell(13);
        cell1.setCellValue(account.getUserName());
        cell1.setCellStyle(cellStyle2);
        cell1=row1.createCell(14);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 13, 14));
        cell1=row1.createCell(15);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(16);
        cell1.setCellValue("报表日期区间");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(17);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 16, 17));
        cell1=row1.createCell(19);
        cell1.setCellValue("");
        cell1.setCellStyle(cellStyle1);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 18, 19));

        Row row2=sheet.createRow(2);

        Row row3=sheet.createRow(3);
        //设置行高
        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("申领单号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("申领时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("状态");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("申领人");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
        cell3.setCellValue("物料编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
        cell3.setCellValue("物料名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
        cell3.setCellValue("物料规格");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
        cell3.setCellValue("包装方式");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
        cell3.setCellValue("申领数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
        cell3.setCellValue("出库数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(12);
        cell3.setCellValue("差数");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(13);
        cell3.setCellValue("申领留言");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(14);
        cell3.setCellValue("有效期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(15);
        cell3.setCellValue("批次");
        cell3.setCellStyle(cellStyle);

        cell3=row3.createCell(16);
        cell3.setCellValue("注册号/备案号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(17);
        cell3.setCellValue("出库人");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(18);
        cell3.setCellValue("出库时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(19);
        cell3.setCellValue("出库摘要");
        cell3.setCellStyle(cellStyle);
        Row row4=sheet.createRow(4);
    SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int index=0;
        int indexRow=4;
        int firstSize=2;
        int startSzie=3;
//        int countSalesName=0;
        int countAgentCompany=0;
//        int countSalman=0;
//        int index1=0;
        int index2=0;
        int index3=0;
//        int listSize=0;
        String[] moiIdArr= moiIds.split(",");
        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
//        Integer mxListSize=0;
//        Integer moiIdArrLenth=moiIdArr.length-1;

        Integer slz=0;
        Integer bfck=0;
        Integer ywc=0;
        Integer ycx=0;
        for (int i = 0; i < moiIdArr.length; ++i){
            Integer moiId=Integer.parseInt(moiIdArr[i]);
            List<Map<String,Object>> mxList =expoetExcelDao.exportPick(moiId);
            if (mxList!=null&&mxList.size()>0){
                for (Map<String, Object> map : mxList) {
                    index++;
                    firstSize++;
                    startSzie++;
                    row4 = sheet.createRow(indexRow++);
                    //第0列
                    cell31 = row4.createCell(0);
                    cell31.setCellValue(index);
                    cell31.setCellStyle(cellStyle);
                    //申领单号
                    cell31 = row4.createCell(1);
                    Object mooCode1=map.get("mooCode");
                    String mooCode="";
                    if (mooCode1!=null&&!mooCode1.equals("")){
                        mooCode=mooCode1.toString();
                    }
                    if (mooCode.equals(moodeNext)){
                        countAgentCompany+=1;
                    }
                    if (!mooCode.equals(moodeNext)&&index>=2){
                        int dangqianCount=firstSize;
                        int start=dangqianCount-countAgentCompany;
                        sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
                        countAgentCompany=0;
                    }
                    if ((i+1)==moiIdArr.length){
                        index2++;
//                        System.out.println(index2+"++"+mxList.size());
                        if (mxList.size()==index2){
                            int dangQianCount=indexRow-1;
                            int  start=dangQianCount-countAgentCompany;
                            sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                        }
                    }
                    cell31.setCellValue(mooCode);
                    cell31.setCellStyle(cellStyle);
                    moodeNext=mooCode;
                    //申领时间
                    cell31 = row4.createCell(2);
                    Object orderTime1=map.get("orderTime");
                    String orderTime="";
                    if (orderTime1!=null&&!orderTime1.equals("")){
                        orderTime=sdf1.format(orderTime1);
                    }
                    if (i==0){
                        endDate=orderTime;
                    }
                    if ((i+1)==moiIdArr.length){
                        index3++;
//                        System.out.println(index2+"++"+mxList.size());
                        if (mxList.size()==index3){
                            startDate=orderTime;
                        }
                    }
//                    if (map.size()==index){
//                        startDate=orderTime;
//                    }
//                    if (map.size()<=1){
//
//                    }
                    cell31.setCellValue(orderTime);
                    cell31.setCellStyle(cellStyle);
                    //申领状态
                    cell31 = row4.createCell(3);
                    Object flowState1=map.get("flowState");
                    String flowState="";
                    if (flowState1!=null&&!flowState1.equals("")){
                        if (flowState1.equals("2")){
                            flowState="申领中";
                            slz+=1;
                        }else if (flowState1.equals("3")){
                            flowState="部分出库";
                            bfck+=1;
                        }else if (flowState1.equals("4")){
                            flowState="已完成";
                            ywc+=1;
                        }else if (flowState1.equals("5")){
                            flowState="撤销";
                            ycx+=1;
                        }
                    }

                    cell31.setCellValue(flowState);
                    cell31.setCellStyle(cellStyle);

                    //申领人
                    cell31 = row4.createCell(4);
                    Object userName1=map.get("user_name");
                    String userName="";
                    if (userName1!=null&&!userName1.equals("")){
                        userName=userName1.toString();
                    }
                    cell31.setCellValue(userName);
                    cell31.setCellStyle(cellStyle);

                    //物料编号
                    cell31 = row4.createCell(5);
                    Object matCode1=map.get("mat_code");
                    String matCode="";
                    if (matCode1!=null&&!matCode1.equals("")){
                        matCode=matCode1.toString();
                    }
                    cell31.setCellValue(matCode);
                    cell31.setCellStyle(cellStyle);

                    //物料名称
                    cell31 = row4.createCell(6);
                    Object matName1=map.get("mat_name");
                    String matName="";
                    if (matName1!=null&&!matName1.equals("")){
                        matName=matName1.toString();
                    }
                    cell31.setCellValue(matName);
                    cell31.setCellStyle(cellStyle);

                    //物料规格
                    cell31 = row4.createCell(7);
                    Object NORM1=map.get("NORM");
                    String norm="";
                    if (NORM1!=null&&!NORM1.equals("")){
                        norm=NORM1.toString();
                    }
                    cell31.setCellValue(norm);
                    cell31.setCellStyle(cellStyle);

                    //品牌
                    cell31 = row4.createCell(8);
                    Object brand1=map.get("brand");
                    String brand="";
                    if (brand1!=null&&!brand1.equals("")){
                        brand=brand1.toString();
                    }
                    cell31.setCellValue(brand);
                    cell31.setCellStyle(cellStyle);
                    //包装方式
                    cell31 = row4.createCell(9);
                    Object productName1=map.get("productName");
                    String productName="";
                    if (productName1!=null&&!productName1.equals("")){
                        productName=productName1.toString();
                    }
                    cell31.setCellValue(productName);
                    cell31.setCellStyle(cellStyle);

//                    Basic_unit
                    //申请数量
                    cell31 = row4.createCell(10);
                    Object baseNumber1=map.get("base_number");
                    Object Basic_unit=map.get("Basic_unit");
                    Object split_quantity=map.get("split_quantity");
                    Object split_number1=map.get("split_number1");
                    Object spitChaNumber=map.get("spitChaNumber");
                    Object splitUnit=map.get("splitUnit");
                    String baseNumber="";
                    if (baseNumber1!=null&&!baseNumber1.equals("")){
                        if (Basic_unit!=null&&!Basic_unit.equals("")){
                            baseNumber+=df2.format(baseNumber1)+Basic_unit;
                        }else {
                            baseNumber+=df2.format(baseNumber1);
                        }
                    }
                    if (split_quantity!=null&&!split_quantity.equals("")){
//                            &&!df2.format(split_quantity).equals("0")
                        if (splitUnit!=null&&!splitUnit.equals("")){
                            baseNumber+=df2.format(split_quantity)+splitUnit.toString();
                        }else {
                            if (Basic_unit!=null&&!Basic_unit.equals("")){
                                baseNumber+=df2.format(split_quantity)+Basic_unit.toString();
                            }else {
                                baseNumber+=df2.format(split_quantity);
                            }
                        }
                    }
                    cell31.setCellValue(baseNumber);
                    cell31.setCellStyle(cellStyle);

                    //出库数量
                    cell31 = row4.createCell(11);
                    Object number11=map.get("number1");

                    String number1="";
                    if (number11!=null&&!number11.equals("")){
                        if (Basic_unit!=null&&!Basic_unit.equals("")){
                            number1+=df2.format(number11)+Basic_unit;
                        }else {
                            number1+=df2.format(number11);
                        }
                    }
                    if (split_number1!=null&&!split_number1.equals("")){
//                            &&!df2.format(split_quantity).equals("0")
                        if (splitUnit!=null&&!splitUnit.equals("")){
                            number1+=df2.format(split_number1)+splitUnit.toString();
                        }else {
                            if (Basic_unit!=null&&!Basic_unit.equals("")){
                                number1+=df2.format(split_number1)+Basic_unit.toString();
                            }else {
                                number1+=df2.format(split_number1);
                            }
                        }
                    }
                    cell31.setCellValue(number1);
                    cell31.setCellStyle(cellStyle);

                    //差数
                    cell31 = row4.createCell(12);
                    Object chaNumber1=map.get("chaNumber");
                    String chaNumber="";
                    if (chaNumber1!=null&&!chaNumber1.equals("")){
                        if (Basic_unit!=null&&!Basic_unit.equals("")){
                            chaNumber=df2.format(chaNumber1)+Basic_unit;
                        }else {
                            chaNumber=df2.format(chaNumber1);
                        }
                    }
                    if (spitChaNumber!=null&&!spitChaNumber.equals("")){
                        if (splitUnit!=null&&!splitUnit.equals("")){
                            chaNumber+=df2.format(spitChaNumber)+splitUnit.toString();
                        }else {
                            if (Basic_unit!=null&&!Basic_unit.equals("")){
                                chaNumber+=df2.format(spitChaNumber)+Basic_unit.toString();
                            }else {
                                chaNumber+=df2.format(spitChaNumber);
                            }
                        }
                    }
                    cell31.setCellValue(chaNumber);
                    cell31.setCellStyle(cellStyle);

                    //申领留言
                    cell31 = row4.createCell(13);
                    Object remarks1=map.get("remarks");
                    String remarks="";
                    if (remarks1!=null&&!remarks1.equals("")){
                        remarks=remarks1.toString();
                    }
                    cell31.setCellValue(remarks);
                    cell31.setCellStyle(cellStyle);

                    //有效期
                    cell31 = row4.createCell(14);
                    Object basicUnitAccuracy1=map.get("basicUnitAccuracy");
                    String basicUnitAccuracy="";
                    if (basicUnitAccuracy1!=null&&!basicUnitAccuracy1.equals("")){
                        basicUnitAccuracy=basicUnitAccuracy1.toString();
                    }
                    if (flowState1.equals("3")||flowState1.equals("4")){
                        cell31.setCellValue(basicUnitAccuracy);
                    }else {
                        cell31.setCellValue("");
                    }
                    cell31.setCellStyle(cellStyle);
                    //批次
                    cell31 = row4.createCell(15);
                    Object batchRule1=map.get("batchRule");
                    String batchRule="";
                    if (batchRule1!=null&&!batchRule1.equals("")){
                        batchRule=batchRule1.toString();
                    }
                    if (flowState1.equals("3")||flowState1.equals("4")){
                        cell31.setCellValue(batchRule);
                    }else {
                        cell31.setCellValue("");
                    }
                    cell31.setCellStyle(cellStyle);
                    //注册证号
                    cell31 = row4.createCell(16);
                    Object backPrinting1=map.get("backPrinting");
                    String backPrinting="";
                    if (backPrinting1!=null&&!backPrinting1.equals("")){
                        backPrinting=backPrinting1.toString();
                    }
                    if (flowState1.equals("3")||flowState1.equals("4")){
                        cell31.setCellValue(backPrinting);
                    }else {
                        cell31.setCellValue("");
                    }

                    cell31.setCellStyle(cellStyle);
                    //出库人
                    cell31 = row4.createCell(17);
                    Object outName1=map.get("outName");
                    String outName="";
                    if (outName1!=null&&!outName1.equals("")){
                        outName=outName1.toString();
                    }
                    cell31.setCellValue(outName);
                    cell31.setCellStyle(cellStyle);
                    //出库时间
                    cell31 = row4.createCell(18);
                    Object finshDate1=map.get("finshDate");
                    String finshDate="";
                    if (finshDate1!=null&&!finshDate1.equals("")){
                        finshDate=sdf1.format(finshDate1);
                    }
                    cell31.setCellValue(finshDate);
                    cell31.setCellStyle(cellStyle);
                    //出库摘要
                    cell31 = row4.createCell(19);
                    Object wowRemarks1=map.get("wow_remarks");
                    String wowRemarks="";
                    if (wowRemarks1!=null&&!wowRemarks1.equals("")){
                        wowRemarks=wowRemarks1.toString();
                    }
                    cell31.setCellValue(wowRemarks);
                    cell31.setCellStyle(cellStyle);
                }
            }
        }
        //将去除循环中的数据拿到时间里面
        cell1=row1.createCell(18);
        cell1.setCellValue(startDate+"-"+endDate);
        cell1.setCellStyle(cellStyle2);
        //第二行的汇总写到这个地方
        //设置行高
        row2.setHeightInPoints(20);
        Cell cell2=null;
        cell2=row2.createCell(0);
        cell2.setCellValue("汇总：");
        cell2.setCellStyle(cellStyle1);
        cell2=row2.createCell(1);
        String sumCount="共计"+index+"条数据，申领中"+slz+"条，部分出库"+bfck+"条，已完成"+ywc+"条，已撤销"+ycx+"条";
        cell2.setCellValue(sumCount);
        cell2.setCellStyle(cellStyle2);
        for (int j = 2; j <= 19; j++) {
            cell2 = row.createCell(j);
            cell2.setCellStyle(cellStyle2); //style为带边框的样式 上面有定义
            cell2.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 1, 19));


        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=account.getOrgGxId_Str()+"-"+account.getUserName()+"领料表统计";
        String realPath = tmpPath + fileName+ ".xlsx";
        File outFile = new File(realPath);
        wb.write(new FileOutputStream(outFile));
        String rootUrl = request.getContextPath() + EXPORT_PATH + fileName+ ".xlsx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("path", rootUrl);
        map.put("fileName", fileName + ".xlsx");
        srm.setObj(map);
        return  srm;
    }
}


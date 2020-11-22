package com.hsk.xframe.web.exportExcels.service.impl;

import com.hsk.dentistmall.api.persistence.MdCheckInventoryEntity;
import com.hsk.dentistmall.api.persistence.MdMaterielInfo;
import com.hsk.dentistmall.api.persistence.MdOutOrderMx;
import com.hsk.supper.dto.SystemContext;
import com.hsk.supper.dto.comm.SysRetrunMessage;
import com.hsk.xframe.api.daobbase.IExpoetExcelDao;
import com.hsk.xframe.api.daobbase.IorgDao;
import com.hsk.xframe.api.persistence.SysOrgGx;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.imp.DSTService;
import com.hsk.xframe.web.exportExcels.service.IEnterWarehouseExportService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EnterWarehouseExportService extends DSTService implements IEnterWarehouseExportService {
    @Autowired
    IExpoetExcelDao expoetExcelDao;
    @Autowired
    protected IorgDao orgDao;
//
    public SysRetrunMessage exportWarehousingList(String warehousCode, Integer select1, String remarks, String billCode, String operationDate, Integer desc, Integer limit, Integer page) throws Exception{
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
        SysUserInfo account = this.GetOneSessionAccount();
        Integer rba = null;
        Integer rbs = null;
        Integer rbb = null;
        Integer suiId = null;
        String purchaseType = null;
        if (account.getOrganizaType().equals("20001")) {
            rba = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "1";
        }
        if (account.getOrganizaType().equals("20002")) {
            rbs = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "2";
        }
        if (account.getOrganizaType().equals("20003")) {
            rbb = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "3";
        }

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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 18 * 256);
//        sheet.setColumnWidth(2, 15 * 256);
//        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 15 * 256);
//        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(20, 20 * 256);
//        sheet.setColumnWidth(11, 20 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue(sysUserInfo.getOrgName()+"入库报表统计");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 24);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=24; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行数据
        Row row1=sheet.createRow(1);
        Cell cell1=null;

        cell1 =row1.createCell(0);
        cell1.setCellValue("单位:");
        cell1.setCellStyle(cellStyle1);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));
        cell1 =row1.createCell(1);
        cell1.setCellValue(account.getOrgName());
        cell1=row1.createCell(2);
        cell1.setCellValue("");

        cell1=row1.createCell(3);
        cell1.setCellValue("");
        cell1=row1.createCell(4);
        cell1.setCellValue("");
        cell1=row1.createCell(5);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 5));
        cell1=row1.createCell(6);
        cell1.setCellValue("");
        cell1=row1.createCell(7);
        cell1.setCellValue("制表日期：");
        cell1=row1.createCell(8);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 7,8));
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue(sdf3.format(NewDate));
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1=row1.createCell(11);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9,11));
        cell1=row1.createCell(12);
        cell1.setCellValue("制表人：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(13);
        cell1.setCellValue(sysUserInfo.getUserName());
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
        cell1=row1.createCell(14);
        cell1.setCellValue("");
        cell1=row1.createCell(15);
        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(16);
        cell1.setCellValue("报表日期区间：");
        cell1=row1.createCell(17);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 16, 17));
        cell1.setCellValue("");
        cell1=row1.createCell(18);
        cell1.setCellValue("");
        cell1=row1.createCell(19);
        cell1.setCellValue("");
        cell1=row1.createCell(20);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 18, 20));
        cell1.setCellValue("");
//        row1.setHeightInPoints(18);
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        //标题
        Row row2=sheet.createRow(2);
        //设置行高
        Cell cell2=row2.createCell(0);
        cell2.setCellValue("汇总");
        for (int j = 1; j <=24; j++) {
            cell2 = row2.createCell(j);
            cell2.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 1, 24));
        Row row3=sheet.createRow(3);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("入库时间");
        cell3=row3.createCell(1);
        cell3.setCellValue("入库单号");
        cell3=row3.createCell(2);
        cell3.setCellValue("入库类型");
        cell3=row3.createCell(3);
        cell3.setCellValue("序列编号");
        cell3=row3.createCell(4);
        cell3.setCellValue("商品编号");
        cell3=row3.createCell(5);
        cell3.setCellValue("商品名称");
        cell3=row3.createCell(6);
        cell3.setCellValue("品牌");
        cell3=row3.createCell(7);
        cell3.setCellValue("规格型号");
        cell3=row3.createCell(8);
        cell3.setCellValue("型号编码");
        cell3=row3.createCell(9);
        cell3.setCellValue("单位");
        cell3=row3.createCell(10);
        cell3.setCellValue("订单数量");
        cell3=row3.createCell(11);
        cell3.setCellValue("发货数量");
        cell3=row3.createCell(12);
        cell3.setCellValue("入库数量");
        cell3=row3.createCell(13);
        cell3.setCellValue("拆分最小单位");
        cell3=row3.createCell(14);
        cell3.setCellValue("包装方式");
        cell3=row3.createCell(15);
        cell3.setCellValue("采购价(元)");
        cell3=row3.createCell(16);
        cell3.setCellValue("零售价(元)");
        cell3=row3.createCell(17);
        cell3.setCellValue("批号");
        cell3=row3.createCell(18);
        cell3.setCellValue("有效期");
        cell3=row3.createCell(19);
        cell3.setCellValue("注册号/备案号");
        cell3=row3.createCell(20);
        cell3.setCellValue("供应商");
        cell3=row3.createCell(21);
        cell3.setCellValue("制作人");
        cell3=row3.createCell(22);
        cell3.setCellValue("采购金额");
        cell3=row3.createCell(23);
        cell3.setCellValue("零售金额");
        cell3=row3.createCell(24);
        cell3.setCellValue("平台订单号");

//        cell3=row3.createCell(25);
//        cell3.setCellValue("产地");
//        cell3=row3.createCell(26);
//        cell3.setCellValue("主要成分");
//        cell3=row3.createCell(27);
//        cell3.setCellValue("产品用途");
//        cell3=row3.createCell(28);
//        cell3.setCellValue("保质期");
//        cell3=row3.createCell(29);
//        cell3.setCellValue("重量");
//        cell3=row3.createCell(30);
//        cell3.setCellValue("批号");
//        cell3=row3.createCell(31);
//        cell3.setCellValue("备注说明");

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
        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";

        List<Map<String,Object>> mxList =expoetExcelDao.exportWarehousingList(warehousCode, select1, remarks, billCode, operationDate, desc,
                rba, rbs, rbb, suiId, purchaseType);
        if (mxList!=null&&mxList.size()>0){
            for (Map<String, Object> map : mxList) {
                index++;
                firstSize++;
                startSzie++;
                row4 = sheet.createRow(indexRow++);
                //入库时间
                cell31 = row4.createCell(0);
                Object createDate=map.get("create5_87_");
                String createDate1="";
                if (createDate!=null&&!createDate.equals("")){
                    createDate1=sdf1.format(createDate);
                }
                cell31.setCellValue(createDate1);
                cell31.setCellStyle(cellStyle3);

                //入库单号relationBillCode
                cell31 = row4.createCell(1);
                Object orderCode=map.get("relationBillCode");
                String orderCode1="";
                if (orderCode!=null&&!orderCode.equals("")){
                    orderCode1=orderCode.toString();
                }
                cell31.setCellValue(orderCode1);
                cell31.setCellStyle(cellStyle3);


                cell31 = row4.createCell(2);
                cell31.setCellValue("订单入库");
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(3);
                cell31.setCellValue("");
                cell31.setCellStyle(cellStyle3);


                //入库单号relationBillCode
                cell31 = row4.createCell(4);
                Object matCode=map.get("mat12_87_");
                String matCode1="";
                if (matCode!=null&&!matCode.equals("")){
                    matCode1=matCode.toString();
                }
                cell31.setCellValue(matCode1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(5);
                Object matName=map.get("mat13_87_");
                String matName1="";
                if (matName!=null&&!matName.equals("")){
                    matName1=matName.toString();
                }
                cell31.setCellValue(matName1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(6);
                Object brand=map.get("formula124_");
                String brand1="";
                if (brand!=null&&!brand.equals("")){
                    brand1=brand.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(7);
                Object norm=map.get("NORM87_");
                String norm1="";
                if (norm!=null&&!norm.equals("")){
                    norm1=norm.toString();
                }
                cell31.setCellValue(norm1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(8);
                Object mmfCode=map.get("formula123_");
                String mmfCode1="";
                if (mmfCode!=null&&!mmfCode.equals("")){
                    mmfCode1=mmfCode.toString();
                }
                cell31.setCellValue(mmfCode1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(9);
                Object basic=map.get("Basic3_87_");
                String basic1="";
                if (basic!=null&&!basic.equals("")){
                    basic1=basic.toString();
                }
                cell31.setCellValue(basic1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(10);
                Object matNumber=map.get("mat14_87_");
                if (matNumber!=null&&!matNumber.equals("")){
                    cell31.setCellValue(df2.format(matNumber));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(11);
                Object number1=map.get("number28_87_");
                if (number1!=null&&!number1.equals("")){
                    cell31.setCellValue(df2.format(number1));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(12);
                Object enterNumber=map.get("enter11_87_");
                if (enterNumber!=null&&!enterNumber.equals("")){
                    cell31.setCellValue(df2.format(enterNumber));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(13);
                cell31.setCellValue(basic1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(14);
                Object praName=map.get("formula125_");
                String praName1="";
                if (praName!=null&&!praName.equals("")){
                    praName1=praName.toString();
                }
                cell31.setCellValue(praName1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(15);
                Object cgj=map.get("Unit36_87_");
                if (cgj!=null&&!cgj.equals("")){
                    cell31.setCellValue(df.format(cgj));
                }else {
                    cell31.setCellValue(df.format(""));
                }
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(16);
                Object lsj=map.get("money22_87_");
                if (lsj!=null&&!lsj.equals("")){
                    cell31.setCellValue(df.format(lsj));
                }else {
                    cell31.setCellValue(df.format(""));
                }
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(17);
//                Object batchCode=map.get("batch_code");
//                String batchCode1="";
//                if (batchCode!=null&&!batchCode.equals("")){
//                    batchCode1=batchCode.toString();
//                }
                cell31.setCellValue("");
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(18);
                Object valiedDate=map.get("valied_date");
                String valiedDate1="";
                if (valiedDate!=null&&!valiedDate.equals("")){
                    valiedDate1=sdf1.format(valiedDate);
                }
                cell31.setCellValue(valiedDate1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(19);
                Object backPrinting=map.get("back_Printing");
                String backPrinting1="";
                if (backPrinting!=null&&!backPrinting.equals("")){
                    backPrinting1=backPrinting.toString();
                }
                cell31.setCellValue(backPrinting1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(20);
                Object applicantName=map.get("applicant_Name");
                String applicantName1="";
                if (applicantName!=null&&!applicantName.equals("")){
                    applicantName1=applicantName.toString();
                }
                cell31.setCellValue(applicantName1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(21);
                Object createRen=map.get("create6_87_");
                String createRen1="";
                if (createRen!=null&&!createRen.equals("")){
                    createRen1=createRen.toString();
                }
                cell31.setCellValue(createRen1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(22);
                Object total=map.get("Total35_87_");
                if (total!=null&&!total.equals("")){
                    cell31.setCellValue(df.format(total));
                }else {
                    cell31.setCellValue(df.format(""));
                }
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(23);
                Object money2=map.get("money22_87_");
                if (money2!=null&&!money2.equals("")){
                    cell31.setCellValue(df.format(money2));
                }else {
                    cell31.setCellValue(df.format(""));
                }
                cell31.setCellStyle(cellStyle3);
            }
        }

        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"入库报表统计";
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

    public SysRetrunMessage exportWarehousingListMx(Integer wewId,String wmsMiIds) throws Exception{
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
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        MdMaterielInfo att_MdMaterielInfo=new MdMaterielInfo();
        //dataMap.put("newDate", sdf.format(NewDate));
        SysUserInfo account = this.GetOneSessionAccount();
        if (account.getOrganizaType().equals("100")) {//供应商增加
            att_MdMaterielInfo.setPurchaseType("1");
            att_MdMaterielInfo.setWzId(account.getOldId());
        } else if (account.getOrganizaType().equals("20001")) {//集团增加
            att_MdMaterielInfo.setPurchaseType("2");
            att_MdMaterielInfo.setWzId(account.getOldId());
        } else if (account.getOrganizaType().equals("20002")) {//医院增加
            att_MdMaterielInfo.setPurchaseType("3");
            att_MdMaterielInfo.setWzId(account.getOldId());
        } else if (account.getOrganizaType().equals("20003")) {//门诊增加
            att_MdMaterielInfo.setPurchaseType("4");
            att_MdMaterielInfo.setWzId(account.getOldId());
        } else//管理员查看所有供应商商品
            att_MdMaterielInfo.setPurchaseType("1");

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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 10 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 10 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(16, 15 * 256);
        sheet.setColumnWidth(17, 30 * 256);
//        sheet.setColumnWidth(9, 20 * 256);
//        sheet.setColumnWidth(10, 20 * 256);
//        sheet.setColumnWidth(11, 20 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue(sysUserInfo.getOrgName()+"物料统计报表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 24);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=24; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行数据
        Row row1=sheet.createRow(1);
        Cell cell1=null;

        cell1 =row1.createCell(0);
        cell1.setCellValue("单位:");
        cell1.setCellStyle(cellStyle1);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));
        cell1 =row1.createCell(1);
        cell1.setCellValue(account.getOrgName());
        cell1=row1.createCell(2);
        cell1.setCellValue("");

        cell1=row1.createCell(3);
        cell1.setCellValue("");
        cell1=row1.createCell(4);
        cell1.setCellValue("");
        cell1=row1.createCell(5);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 5));
        cell1=row1.createCell(6);
        cell1.setCellValue("");
        cell1=row1.createCell(7);
        cell1.setCellValue("制表日期：");
        cell1=row1.createCell(8);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 7,8));
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue(sdf3.format(NewDate));
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1=row1.createCell(11);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9,11));
        cell1=row1.createCell(12);
        cell1.setCellValue("制表人：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(13);
        cell1.setCellValue(sysUserInfo.getUserName());
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
        cell1=row1.createCell(14);
        cell1.setCellValue("");
        cell1=row1.createCell(15);
        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(16);
        cell1.setCellValue("报表日期区间：");
        cell1=row1.createCell(17);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 16, 17));
        cell1.setCellValue("");
        cell1=row1.createCell(18);
        cell1.setCellValue("");
        cell1=row1.createCell(19);
        cell1.setCellValue("");
        cell1=row1.createCell(20);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 18, 20));
        cell1.setCellValue("");
//        row1.setHeightInPoints(18);
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        //标题
        Row row2=sheet.createRow(2);
        //设置行高
        Cell cell2=row2.createCell(0);
        cell2.setCellValue("汇总");
        for (int j = 2; j <=24; j++) {
            cell2 = row2.createCell(j);
            cell2.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 1, 24));
        Row row3=sheet.createRow(3);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("物料号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("物料名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("物料规格");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("物料编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
        cell3.setCellValue("基本单位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
        cell3.setCellValue("系数");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
        cell3.setCellValue("最小拆分单位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
        cell3.setCellValue("库存数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
        cell3.setCellValue("采购均价");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
        cell3.setCellValue("零售均价");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
        cell3.setCellValue("库位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(12);
        cell3.setCellValue("别名");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(13);
        cell3.setCellValue("包装方式");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(14);
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(15);
        cell3.setCellValue("分类");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(16);
        cell3.setCellValue("创建时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(17);
        cell3.setCellValue("制作部门");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(18);
        cell3.setCellValue("有效期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(19);
        cell3.setCellValue("注册证有效期/备案日期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(20);
        cell3.setCellValue("货号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(21);
        cell3.setCellValue("产品标准");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(22);
        cell3.setCellValue("材质");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(23);
        cell3.setCellValue("注册号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(24);
        cell3.setCellValue("生产企业");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(25);
        cell3.setCellValue("产地");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(26);
        cell3.setCellValue("主要成分");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(27);
        cell3.setCellValue("产品用途");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(28);
        cell3.setCellValue("保质期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(29);
        cell3.setCellValue("重量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(30);
        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(31);
        cell3.setCellValue("备注说明");
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
        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";

        List<Map<String,Object>> mxList =expoetExcelDao.exportWarehousingListMx(wewId,wmsMiIds,att_MdMaterielInfo);
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

                //入库单号relationBillCode
                cell31 = row4.createCell(1);
                Object mat_code=map.get("mat33_50_");
                String mat_code1="";
                if (mat_code!=null&&!mat_code.equals("")){
                    mat_code1=mat_code.toString();
                }
                cell31.setCellValue(mat_code1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(2);
                Object mat_name=map.get("mat34_50_");
                String mat_name1="";
                if (mat_name!=null&&!mat_name.equals("")){
                    mat_name1=mat_name.toString();
                }
                cell31.setCellValue(mat_name1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(3);
                Object mmf=map.get("mmf44_50_");
                String mmf1="";
                if (mmf!=null&&!mmf.equals("")){
                    mmf1=mmf.toString();
                }
                cell31.setCellValue(mmf1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(4);
                Object mmf_code=map.get("mmf42_50_");
                String mmf_code1="";
                if (mmf_code!=null&&!mmf_code.equals("")){
                    mmf_code1=mmf_code.toString();
                }
                cell31.setCellValue(mmf_code1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(5);
                Object Basic_unit=map.get("Basic8_50_");
                String Basic_unit1="";
                if (Basic_unit!=null&&!Basic_unit.equals("")){
                    Basic_unit1=Basic_unit.toString();
                }
                cell31.setCellValue(Basic_unit1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(6);
                Object ratio=map.get("ratio");
//                String ratio1="";
                if (ratio!=null&&!ratio.equals("")){
                    cell31.setCellValue(df2.format(ratio));
                }
//                cell31.setCellValue(ratio1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(7);
                Object unit=map.get("unit");
                String unit1="";
                if (unit!=null&&!unit.equals("")){
                    unit1=unit.toString();
                }
                cell31.setCellValue(unit1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(8);
//                Object number1=map.get("number52_50_");
                Object number2=map.get("QUANTITY");
                Object number3=map.get("splitBaseNumber");
                String numbers="";
                if (number2!=null&&!number2.equals("")){
                    numbers+=df2.format(number2);
                    if (Basic_unit!=null&&!Basic_unit.equals("")){
                        numbers+=Basic_unit.toString();
                    }
                }
                if (number3!=null&&!number3.equals("")){
                    numbers+=df2.format(number3);
                    if (unit!=null&&!unit.equals("")){
                        numbers+=unit.toString();
                    }else {
                        numbers+=Basic_unit.toString();
                    }
                }
                cell31.setCellValue(numbers);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(9);
                Object price=map.get("price50_");
                if (price!=null&&!price.equals("")){
                    cell31.setCellValue(df.format(price));
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(10);
                Object retail_money=map.get("retail_money");
                if (retail_money!=null&&!retail_money.equals("")){
                    cell31.setCellValue(df.format(retail_money));
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(11);
                cell31.setCellValue("");
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(12);
                Object alias_name=map.get("alias_name");
                String alias_name1="";
                if (alias_name!=null&&!alias_name.equals("")){
                    alias_name1=alias_name.toString();
                }
            cell31.setCellValue(alias_name1);
            cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(13);
                Object product_name=map.get("product62_50_");
                String product_name1="";
                if (product_name!=null&&!product_name.equals("")){
                    product_name1=product_name.toString();
                }
                cell31.setCellValue(product_name1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(14);
                Object brand=map.get("brand50_");
                String brand1="";
                if (brand!=null&&!brand.equals("")){
                    brand1=brand.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(15);
                Object mat111=map.get("mat111_");
                Object formula91_=map.get("formula91_");
                String mattypes="";
                if (mat111!=null&&!mat111.equals("")){
                    mattypes+=mat111.toString();
                }
                if (formula91_!=null&&!formula91_.equals("")){
                    mattypes+="/"+formula91_.toString();
                }
//                Object mat_type=map.get("mat38_50_");
//                String mat_type1="";
//                if (mat_type!=null&&!mat_type.equals("")){
//                    mat_type1=mat_type.toString();
//                }
                cell31.setCellValue(mattypes);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(16);
                Object receipt_datetime=map.get("receipt68_50_");
                if (receipt_datetime!=null&&!receipt_datetime.equals("")){
                    cell31.setCellValue(sdf1.format(receipt_datetime));
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(17);
                Object Purchase_unit=map.get("Purchase66_50_");
                String Purchase_unit1="";
                if (Purchase_unit!=null&&!Purchase_unit.equals("")){
                    Purchase_unit1=Purchase_unit.toString();
                }
                cell31.setCellValue(Purchase_unit1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(18);
                Object valied_date=map.get("yxq");
                if (valied_date!=null&&!valied_date.equals("")){
                    cell31.setCellValue(sdf1.format(valied_date));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(19);
                Object zcCode=map.get("zcCode");
                String zcCode1="";
                if (zcCode!=null&&!zcCode.equals("")){
                    zcCode1=zcCode.toString();
                }
                cell31.setCellValue(zcCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(20);
                Object serialNumbe=map.get("serialNumbe");
                String serialNumbe1="";
                if (serialNumbe!=null&&!serialNumbe.equals("")){
                    serialNumbe1=serialNumbe.toString();
                }
                cell31.setCellValue(serialNumbe1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(21);
                Object standard=map.get("standard");
                String standard1="";
                if (standard!=null&&!standard.equals("")){
                    standard1=standard.toString();
                }
                cell31.setCellValue(standard1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(22);
                Object materiel_name=map.get("materiel_name");
                String materiel_name1="";
                if (materiel_name!=null&&!materiel_name.equals("")){
                    materiel_name1=materiel_name.toString();
                }
                cell31.setCellValue(materiel_name1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(23);
                Object back_Printing=map.get("back_Printing");
                String back_Printing1="";
                if (back_Printing!=null&&!back_Printing.equals("")){
                    back_Printing1=back_Printing.toString();
                }
                cell31.setCellValue(back_Printing1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(24);
                Object applicant_Name=map.get("applicant_Name");
                String applicant_Name1="";
                if (applicant_Name!=null&&!applicant_Name.equals("")){
                    applicant_Name1=applicant_Name.toString();
                }
                cell31.setCellValue(applicant_Name1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(25);
                Object Corporate_domicile=map.get("Corporate_domicile");
                String Corporate_domicile1="";
                if (Corporate_domicile!=null&&!Corporate_domicile.equals("")){
                    Corporate_domicile1=Corporate_domicile.toString();
                }
                cell31.setCellValue(Corporate_domicile1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(26);
                Object ingredient=map.get("ingredient");
                String ingredient1="";
                if (ingredient!=null&&!ingredient.equals("")){
                    ingredient1=ingredient.toString();
                }
                cell31.setCellValue(ingredient1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(27);
                Object product_use=map.get("product_use");
                String product_use1="";
                if (product_use!=null&&!product_use.equals("")){
                    product_use1=product_use.toString();
                }
                cell31.setCellValue(product_use1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(28);
                Object valid_period_unit=map.get("valid_period_unit");
                Object valid_period=map.get("VALID_PERIOD");
                String valid_period_unit1="";
                if (valid_period!=null&&!valid_period.equals("")){
                    valid_period_unit1+=valid_period;
                    if (valid_period_unit!=null&&!valid_period_unit.equals("")){
                        valid_period_unit1+=valid_period_unit.toString();
                    }
                }
                cell31.setCellValue(valid_period_unit1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(29);
                Object weight=map.get("weight");
                String weight1="";
                if (weight!=null&&!weight.equals("")){
                    weight1=weight.toString();
                }
                cell31.setCellValue(weight1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(30);
                Object batch_code=map.get("batch_code");
                String batch_code1="";
                if (batch_code!=null&&!batch_code.equals("")){
                    batch_code1=batch_code.toString();
                }
                cell31.setCellValue(batch_code1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(31);
                cell31.setCellValue("");
                cell31.setCellStyle(cellStyle);
            }
        }
        cell2=row2.createCell(1);
        cell2.setCellValue("共计"+index+"条数据");
        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"物料统计报表";
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

//    searchName=&brand=&batchCode=&ciId=5
//    checkCode=&checkType=&createRen=&startDate=

    public SysRetrunMessage exportCheckInvent(Integer ciId,String ciIds,String searchName,String brand,String batchCode,String checkCode,String checkType,String createRen,String startDate) throws Exception{
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
        SysUserInfo account = this.GetOneSessionAccount();
        Integer rba = null;
        Integer rbs = null;
        Integer rbb = null;
        Integer suiId = null;
        String purchaseType = null;
        if (account.getOrganizaType().equals("20001")) {
            rba = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "1";
        }
        if (account.getOrganizaType().equals("20002")) {
            rbs = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "2";
        }
        if (account.getOrganizaType().equals("20003")) {
            rbb = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "3";
        }
//        SysOrgGx sysOrgGx = new SysOrgGx();
//        sysOrgGx.setOrgGxId(account.getOrgGxId());
//        Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
//        if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
//            mdCheckInventoryEntity.setRbaId(account.getOldId());
//        } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
//            if (orgMap.containsKey("one")) {//如果存在上级集团
//                mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//            }
//            mdCheckInventoryEntity.setRbsId(account.getOldId());
//        } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
//            if (orgMap.containsKey("one")) {//如果存在上级集团
//                mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//            }
//            if (orgMap.containsKey("tow")) {//如果存在上级医院
//                mdCheckInventoryEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
//            }
//            mdCheckInventoryEntity.setRbbId(account.getOldId());
//        }

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
        Font font=wb.createFont();
        font.setFontHeightInPoints((short) 15);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        cellStyle2.setFont(font);
        CellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


        Font font1=wb.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        CellStyle cellStyle7 = wb.createCellStyle();
        cellStyle7.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle7.setFont(font1);
        cellStyle7.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle7.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle7.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle7.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle7.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中



        CellStyle cellStyle4 = wb.createCellStyle();
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        Font font2=wb.createFont();
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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 7 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 20 * 256);
//        sheet.setColumnWidth(3, 15 * 256);
//        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(6, 15 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
//        sheet.setColumnWidth(5, 20 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
//        sheet.setColumnWidth(7, 20 * 256);
//        sheet.setColumnWidth(8, 20 * 256);
//        sheet.setColumnWidth(9, 20 * 256);
//        sheet.setColumnWidth(10, 20 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue(sysUserInfo.getOrgName()+"盘点库存统计报表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 20);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=20; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行数据
        Row row1=sheet.createRow(1);
        Cell cell1=null;

        cell1 =row1.createCell(0);
        cell1.setCellValue("单位:");
        cell1.setCellStyle(cellStyle1);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));
        cell1 =row1.createCell(1);
        cell1.setCellValue(account.getOrgName());
        cell1=row1.createCell(2);
        cell1.setCellValue("");

        cell1=row1.createCell(3);
        cell1.setCellValue("");
        cell1=row1.createCell(4);
        cell1.setCellValue("");
        cell1=row1.createCell(5);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 5));
        cell1=row1.createCell(6);
        cell1.setCellValue("");
        cell1=row1.createCell(7);
        cell1.setCellValue("制表日期：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(8);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 7,8));
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue(sdf3.format(NewDate));
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1=row1.createCell(11);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9,11));
        cell1=row1.createCell(12);
        cell1.setCellValue("制表人：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(13);
        cell1.setCellValue(sysUserInfo.getUserName());
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
        cell1=row1.createCell(14);
        cell1.setCellValue("");
        cell1=row1.createCell(15);
        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(16);
        cell1.setCellValue("报表日期区间：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(17);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 16, 17));
        cell1.setCellValue("");

        cell1=row1.createCell(19);
        cell1.setCellValue("");
        cell1=row1.createCell(20);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 18, 20));
        cell1.setCellValue("");
//        row1.setHeightInPoints(18);
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        //设置行高

        Row row3=sheet.createRow(3);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列号");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(1);
        cell3.setCellValue("盘点单号");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(2);
        cell3.setCellValue("盘点时间");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(3);
        cell3.setCellValue("盘点名称");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(4);
        cell3.setCellValue("盘点类型");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(5);
        cell3.setCellValue("盘点分类");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(6);
        cell3.setCellValue("物料编码");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(7);
        cell3.setCellValue("物料名称");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(8);
        cell3.setCellValue("规格");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(9);
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(10);
        cell3.setCellValue("生产厂家");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(11);
        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(12);
        cell3.setCellValue("原有库存");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(13);
        cell3.setCellValue("现有库存");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(14);
        cell3.setCellValue("盈亏");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(15);
        cell3.setCellValue("备注");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(16);
        cell3.setCellValue("状态");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(17);
        cell3.setCellValue("制作人");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(18);
        cell3.setCellValue("制作时间");
        cell3.setCellStyle(cellStyle7);
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
//        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
        int statisticalCount=0;
        int statistical1=0;
        int statistical2=0;
        String start1Date="";
        String end1Date="";
        List<Map<String,Object>> mxList =expoetExcelDao.exportCheckInvent(ciId,ciIds,searchName,brand,batchCode,checkCode,checkType,createRen,startDate);
        if (mxList!=null&&mxList.size()>0){
            for (Map<String, Object> map : mxList) {
                index++;
                firstSize++;
                startSzie++;
                row4 = sheet.createRow(indexRow++);
                //第0列
                cell31 = row4.createCell(0);
                cell31.setCellValue(index);
                cell31.setCellStyle(cellStyle3);

                //入库单号relationBillCode
                cell31 = row4.createCell(1);
                Object mat_code=map.get("check4_36_");
                String mat_code1="";
                if (mat_code!=null&&!mat_code.equals("")){
                    mat_code1=mat_code.toString();
                }

                if (mat_code1.equals(moodeNext)){
                    countAgentCompany+=1;
                }
                if (!mat_code1.equals(moodeNext)&&index>=2){
                    int dangqianCount=firstSize;
                    int start=dangqianCount-countAgentCompany;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
                    countAgentCompany=0;
                }
                    if (index==mxList.size()&&mat_code1.equals(moodeNext)){
                        System.out.println(mxList.size());
                        int dangQianCount=indexRow-1;
                        int  start=dangQianCount-countAgentCompany;
                        sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    }
                cell31.setCellValue(mat_code1);
                cell31.setCellStyle(cellStyle3);
                moodeNext=mat_code1;

                cell31 = row4.createCell(3);
                Object mat_name=map.get("check5_36_");
                String mat_name1="";
                if (mat_name!=null&&!mat_name.equals("")){
                    mat_name1=mat_name.toString();
                }
                cell31.setCellValue(mat_name1);
                cell31.setCellStyle(cellStyle3);


                cell31 = row4.createCell(2);
                Object mmf=map.get("create8_36_");
                String mmf1="";
                if (mmf!=null&&!mmf.equals("")){
                    mmf1=sdf1.format(mmf);
                    if (index==1){
                        start1Date=sdf3.format(mmf);
                    }else if (index==mxList.size()){
                        end1Date=sdf3.format(mmf);
                    }
                }
                cell31.setCellValue(mmf1);
                cell31.setCellStyle(cellStyle3);


                cell31 = row4.createCell(4);
                Object mmf_code=map.get("check_type");
                String mmf_code1="";
                if (mmf_code!=null&&!mmf_code.equals("")){
                    mmf_code1=mmf_code.toString();
                }
                cell31.setCellValue(mmf_code1);
                cell31.setCellStyle(cellStyle3);


                cell31 = row4.createCell(5);
                Object Basic_unit=map.get("check6_36_");
                String Basic_unit1="";
                if (Basic_unit!=null&&!Basic_unit.equals("")){
                    Basic_unit1=Basic_unit.toString();
                }
                cell31.setCellValue(Basic_unit1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(6);
                Object matCode1=map.get("matCode");
                String matCode="";
                if (matCode1!=null&&!matCode1.equals("")){
                    matCode=matCode1.toString();
                }
                cell31.setCellValue(matCode);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(7);
                Object unit=map.get("mat18_54_");
                String unit1="";
                if (unit!=null&&!unit.equals("")){
                    unit1=unit.toString();
                }
                cell31.setCellValue(unit1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(8);
                Object mmfName1=map.get("mmf31_54_");
                String mmfName="";
                if (mmfName1!=null&&!mmfName1.equals("")){
                    mmfName=mmfName1.toString();
                }
                cell31.setCellValue(mmfName);
                cell31.setCellStyle(cellStyle3);
                cell31 = row4.createCell(9);
                Object brand2=map.get("brand54_");
                String brand1="";
                if (brand2!=null&&!brand2.equals("")){
                    brand1=brand2.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(10);
                Object product_factory=map.get("product_factory");
                String product_factory1="";
                if (product_factory!=null&&!product_factory.equals("")){
                    product_factory1=product_factory.toString();
                }
                cell31.setCellValue(product_factory1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(11);
                Object alias_name=map.get("back_Printing");
                String alias_name1="";
                if (alias_name!=null&&!alias_name.equals("")){
                    alias_name1=alias_name.toString();
                }
                cell31.setCellValue(alias_name1);
                cell31.setCellStyle(cellStyle3);


                cell31 = row4.createCell(12);
                Object basicUnit=map.get("basicUnit");
                Object baseNumber=map.get("base5_54_");
                Object splitNumber=map.get("splitNumber");
                Object nowSplitNumber=map.get("nowSplitNumber");
                Object lackSplitNumber=map.get("lackSplitNumber");
                Object splitUnit=map.get("split_unit");
                String baseNumber1="";
                if (baseNumber!=null&&!baseNumber.equals("")){
                    if (basicUnit!=null&&!basicUnit.equals("")){
                        baseNumber1+=df2.format(baseNumber)+basicUnit.toString();
                    }else {
                        baseNumber1+=df2.format(baseNumber);
                    }
                }
                if (splitUnit!=null&&!splitUnit.equals("")){
                    baseNumber1+=df2.format(splitNumber)+splitUnit.toString();
                }else {
                    if (basicUnit!=null&&!basicUnit.equals("")){
                        baseNumber1+=df2.format(splitNumber)+basicUnit.toString();

                    }else {
                        baseNumber1+=df2.format(splitNumber);
                    }
                }
                cell31.setCellValue(baseNumber1);
                cell31.setCellStyle(cellStyle3);
                cell31 = row4.createCell(13);
                Object nowNumber=map.get("now33_54_");
                String baseNumber2="";
                if (nowNumber!=null&&!nowNumber.equals("")){
                    if (basicUnit!=null&&!basicUnit.equals("")){
                        baseNumber2+=df2.format(nowNumber)+basicUnit.toString();
                    }else {
                        baseNumber2+=df2.format(nowNumber);
                    }
                }
                if (splitUnit!=null&&!splitUnit.equals("")){
                    baseNumber2+=df2.format(nowSplitNumber)+splitUnit.toString();
                }else {
                    if (basicUnit!=null&&!basicUnit.equals("")){
                        baseNumber2+=df2.format(nowSplitNumber)+basicUnit.toString();

                    }else {
                        baseNumber2+=df2.format(nowSplitNumber);
                    }
                }
                cell31.setCellValue(baseNumber2);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(14);
                Object lackNumber=map.get("lackNumber");
                String baseNumber3="";
                if (lackNumber!=null&&!lackNumber.equals("")){
                    if (basicUnit!=null&&!basicUnit.equals("")){
                        baseNumber3+=df2.format(lackNumber)+basicUnit.toString();
                    }else {
                        baseNumber3+=df2.format(lackNumber);
                    }
                }
                if (splitUnit!=null&&!splitUnit.equals("")){
                    baseNumber3+=df2.format(lackSplitNumber)+splitUnit.toString();
                }else {
                    if (basicUnit!=null&&!basicUnit.equals("")){
                        baseNumber3+=df2.format(lackSplitNumber)+basicUnit.toString();
                    }else {
                        baseNumber3+=df2.format(lackSplitNumber);
                    }
                }
                cell31.setCellValue(baseNumber3);
                cell31.setCellStyle(cellStyle3);
//
//

                cell31 = row4.createCell(15);
                Object product_name=map.get("check11_54_");
                String product_name1="";
                if (product_name!=null&&!product_name.equals("")){
                    product_name1=product_name.toString();
                }
                cell31.setCellValue(product_name1);
                cell31.setCellStyle(cellStyle3);

                statisticalCount=index;
                cell31 = row4.createCell(16);
                Object mat_type2=map.get("check10_54_");
                String mat_type1="";
                if (mat_type2!=null&&!mat_type2.equals("")){
                    Integer mat_type=Integer.parseInt(mat_type2.toString());
                    if (!mat_type.equals(1)&&mat_type!=1){
                        mat_type1="未盘点";
                        statistical2+=1;
                    }else {
                        mat_type1="已盘点";
                        statistical1+=1;
                    }
                }else {
                    mat_type1="未盘点";
                    statistical1+=1;
                }
                cell31.setCellValue(mat_type1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(17);
                Object Purchase_unit=map.get("create9_36_");
                String Purchase_unit1="";
                if (Purchase_unit!=null&&!Purchase_unit.equals("")){
                    Purchase_unit1=Purchase_unit.toString();
                }
                cell31.setCellValue(Purchase_unit1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(18);
                Object receipt_datetime=map.get("create_date");
                if (receipt_datetime!=null&&!receipt_datetime.equals("")){
                    cell31.setCellValue(sdf1.format(receipt_datetime));
                }
                cell31.setCellStyle(cellStyle3);
            }
        }

//        String start1Date="";
//        String end1Date="";
        cell1=row1.createCell(18);
        String dates=start1Date+"-"+end1Date;
        cell1.setCellValue(dates);
        //标题
        Row row2=sheet.createRow(2);
        Cell cell2=row2.createCell(0);
        String statisticalCounts="汇总：共计"+statisticalCount+"条数据,"+"已盘点"+statistical1+"条"+"未盘点"+statistical2+"条";
        cell2.setCellValue(statisticalCounts);
        for (int j = 1; j <=24; j++) {
            cell2 = row2.createCell(j);
            cell2.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 0, 24));
        Row row5=sheet.createRow(indexRow);
       Cell cell5 = row5.createCell(0);
       cell5.setCellValue(statisticalCounts);
        for (int j = 1; j <=24; j++) {
            cell5 = row5.createCell(j);
            cell5.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(indexRow,indexRow , 0, 24));


        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"盘点库存统计报表";
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
//    brand=&createDate1=&wiId=6668
    public SysRetrunMessage exportInventoryView(String wiId,String InventoryStart,String InventoryEnd,String searchName,String brand,String createDate1,String batchCode,String checkCode,String checkType,String createRen,String startDate) throws Exception{
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
        SysUserInfo account = this.GetOneSessionAccount();
        Integer rba = null;
        Integer rbs = null;
        Integer rbb = null;
        Integer suiId = null;
        String purchaseType = null;
        if (account.getOrganizaType().equals("20001")) {
            rba = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "1";
        }
        if (account.getOrganizaType().equals("20002")) {
            rbs = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "2";
        }
        if (account.getOrganizaType().equals("20003")) {
            rbb = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "3";
        }
//        SysOrgGx sysOrgGx = new SysOrgGx();
//        sysOrgGx.setOrgGxId(account.getOrgGxId());
//        Map<String, String> orgMap = orgDao.getOldThreeMap(sysOrgGx);
//        if (account.getOrganizaType().equals("20001")) {//如果当前账户为集团账户
//            mdCheckInventoryEntity.setRbaId(account.getOldId());
//        } else if (account.getOrganizaType().equals("20002")) {//如果当前账户为医院账户
//            if (orgMap.containsKey("one")) {//如果存在上级集团
//                mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//            }
//            mdCheckInventoryEntity.setRbsId(account.getOldId());
//        } else if (account.getOrganizaType().equals("20003")) {//如果当前账户为门诊账户
//            if (orgMap.containsKey("one")) {//如果存在上级集团
//                mdCheckInventoryEntity.setRbaId(Integer.parseInt(orgMap.get("one")));
//            }
//            if (orgMap.containsKey("tow")) {//如果存在上级医院
//                mdCheckInventoryEntity.setRbsId(Integer.parseInt(orgMap.get("tow")));
//            }
//            mdCheckInventoryEntity.setRbbId(account.getOldId());
//        }

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
        Font font=wb.createFont();
        font.setFontHeightInPoints((short) 15);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        cellStyle2.setFont(font);
        CellStyle cellStyle3 = wb.createCellStyle();
        cellStyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
        cellStyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle3.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle3.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle3.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle3.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框


        Font font1=wb.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        CellStyle cellStyle7 = wb.createCellStyle();
        cellStyle7.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle7.setFont(font1);
        cellStyle7.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cellStyle7.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cellStyle7.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cellStyle7.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        cellStyle7.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

        CellStyle cellStyle4 = wb.createCellStyle();
        cellStyle4.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        Font font2=wb.createFont();
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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 7 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 16 * 256);
        sheet.setColumnWidth(3, 15 * 256);
//        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(6, 20 * 256);
//        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
//        sheet.setColumnWidth(5, 20 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
//        sheet.setColumnWidth(7, 20 * 256);
//        sheet.setColumnWidth(8, 20 * 256);
//        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(14, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 15 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue(sysUserInfo.getOrgName()+"-库存统计报表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 20);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=20; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行数据
        Row row1=sheet.createRow(1);
        Cell cell1=null;

        cell1 =row1.createCell(0);
        cell1.setCellValue("单位:");
        cell1.setCellStyle(cellStyle1);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));
        cell1 =row1.createCell(1);
        cell1.setCellValue(account.getOrgName());
        cell1=row1.createCell(2);
        cell1.setCellValue("");

        cell1=row1.createCell(3);
        cell1.setCellValue("");
        cell1=row1.createCell(4);
        cell1.setCellValue("");
        cell1=row1.createCell(5);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 5));
        cell1=row1.createCell(6);
        cell1.setCellValue("");
        cell1=row1.createCell(7);
        cell1.setCellValue("制表日期：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(8);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 7,8));
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue(sdf3.format(NewDate));
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1=row1.createCell(11);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9,11));
        cell1=row1.createCell(12);
        cell1.setCellValue("制表人：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(13);
        cell1.setCellValue(sysUserInfo.getUserName());
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
        cell1=row1.createCell(14);
        cell1.setCellValue("");
        cell1=row1.createCell(15);
        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(16);
        cell1.setCellValue("报表日期区间：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(17);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 16, 17));
        cell1.setCellValue("");

        cell1=row1.createCell(19);
        cell1.setCellValue("");
        cell1=row1.createCell(20);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 18, 20));
        cell1.setCellValue("");
//        row1.setHeightInPoints(18);
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        //设置行高

        Row row3=sheet.createRow(3);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列号");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(1);
        cell3.setCellValue("物料编码");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(2);
        cell3.setCellValue("物料名称");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(3);
        cell3.setCellValue("分类");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(4);
        cell3.setCellValue("类型");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(5);
        cell3.setCellValue("状态");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(6);
        cell3.setCellValue("规格");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(7);
        cell3.setCellValue("是否预警");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(8);
        cell3.setCellValue("预警最低最高数");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(9);
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(10);
        cell3.setCellValue("包装方式");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(11);
        cell3.setCellValue("生产厂家");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(12);
        cell3.setCellValue("批次号");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(13);
        cell3.setCellValue("有效期");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(14);
        cell3.setCellValue("关联订单号");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(15);
        cell3.setCellValue("采购均价");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(16);
        cell3.setCellValue("零售均价");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(17);
        cell3.setCellValue("采购金额");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(18);
        cell3.setCellValue("零售金额");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(19);
        cell3.setCellValue("库存数(最大及最小单位)");
        cell3.setCellStyle(cellStyle7);
        cell3=row3.createCell(20);
        cell3.setCellValue("当前库存变化");
        cell3.setCellStyle(cellStyle7);
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
//        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
        int statisticalCount=0;
        int statistical1=0;
        int statistical2=0;
        String start1Date="";
        String end1Date="";
        Double num1=0d;
        Double num2=0d;
        Double num3=0d;
        Double num4=0d;
        List<Map<String,Object>> mxList =expoetExcelDao.exportInventoryView(wiId,InventoryStart,InventoryEnd,searchName,brand,createDate1,rba,rbs,rbb,purchaseType);
        if (mxList!=null&&mxList.size()>0){
            for (Map<String, Object> map : mxList) {
                index++;
                firstSize++;
                startSzie++;
                row4 = sheet.createRow(indexRow++);
                //第0列
                cell31 = row4.createCell(0);
                cell31.setCellValue(index);
                cell31.setCellStyle(cellStyle3);

                //入库单号relationBillCode
                cell31 = row4.createCell(1);
                Object mat_code=map.get("mat11_65_");
                String mat_code1="";
                if (mat_code!=null&&!mat_code.equals("")){
                    mat_code1=mat_code.toString();
                }
                if (mat_code1.equals(moodeNext)){
                    countAgentCompany+=1;
                }
                if (!mat_code1.equals(moodeNext)&&index>=2){
                    int dangqianCount=firstSize;
                    int start=dangqianCount-countAgentCompany;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
                    countAgentCompany=0;
                }
                if (index==mxList.size()&&mat_code1.equals(moodeNext)){
                    System.out.println(mxList.size());
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-countAgentCompany;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                }
                cell31.setCellValue(mat_code1);
                cell31.setCellStyle(cellStyle3);
                moodeNext=mat_code1;

                cell31 = row4.createCell(3);
                Object t2matType1=map.get("t2matType1");
                String t2matType11="";
                if (t2matType1!=null&&!t2matType1.equals("")){
                    t2matType11=t2matType1.toString();
//                    switch(t2matType1.toString()){
//                        case "2200" :
//                            t2matType11="修复、填充类";
//                            break;
//                        case "2201" :
//                            t2matType11="车针/扩锉/磨头";
//                            break;
//                        case "2202" :
//                            t2matType11="口腔常用材料";
//                            break;
//                        case "2203" :
//                            t2matType11="医用耗材类";
//                            break;
//                        case "2204" :
//                            t2matType11="口腔科常用器械";
//                            break;
//                        case "2205" :
//                            t2matType11="正畸产品类";
//                            break;
//                        case "2206" :
//                            t2matType11="口腔护理";
//                            break;
//                        case "2207" :
//                            t2matType11="种植产品类";
//                            break;
//                        case "2208" :
//                            t2matType11="口腔设备类";
//                            break;
//                        default :
//                            t2matType11="其他种类";
//                    }
                }
                cell31.setCellValue(t2matType11);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(2);
                Object mat_name=map.get("mat12_65_");
                String mat_name1="";
                if (mat_name!=null&&!mat_name.equals("")){
                    mat_name1=mat_name.toString();
                }
                cell31.setCellValue(mat_name1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(4);
                Object mmf_code=map.get("t2stype");
                String mmf_code1="";
                if (mmf_code!=null&&!mmf_code.equals("")){
                    mmf_code1=mmf_code.toString();
                }
                cell31.setCellValue(mmf_code1);
                cell31.setCellStyle(cellStyle3);


                cell31 = row4.createCell(5);
                Object Basic_unit=map.get("t2state");
                String Basic_unit1="";
                if (Basic_unit!=null&&!Basic_unit.equals("")){
                    Basic_unit1=Basic_unit.toString();
                }
                cell31.setCellValue(Basic_unit1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(6);
                Object matCode1=map.get("t2mmfName");
                String matCode="";
                if (matCode1!=null&&!matCode1.equals("")){
                    matCode=matCode1.toString();
                }
                cell31.setCellValue(matCode);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(7);
                Object unit=map.get("quantitymax");
                String unit1="";
                if (unit!=null&&!unit.equals("")){
                    unit1=unit.toString();
                }
                cell31.setCellValue(unit1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(8);
                Object shu1=map.get("warning35_65_");
                Object shu2=map.get("max14_65_");
                Object shu1s="";
                Object shu2s="";
                if (shu1!=null&&!shu1.equals("")){
                    shu1s=shu1;
                }
                if (shu2!=null&&!shu2.equals("")){
                    shu2s=shu2;
                }
                if (shu1s!=null&&!shu1s.equals("")){
                    if (shu2s!=null&&!shu2s.equals("")){
                        cell31.setCellValue(df2.format(shu1s)+"-"+df2.format(shu2s));
                    }else {
                        cell31.setCellValue(df2.format(shu1s));
                    }
                }else {
                    if (shu2s!=null&&!shu2s.equals("")){
                        cell31.setCellValue(df2.format(shu2s));
                    }else {
                        cell31.setCellValue("");
                    }
                }
                cell31.setCellStyle(cellStyle3);
                cell31 = row4.createCell(9);
                Object brand2=map.get("t2brand");
                String brand1="";
                if (brand2!=null&&!brand2.equals("")){
                    brand1=brand2.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(10);
                Object product_factory=map.get("t2productName");
                String product_factory1="";
                if (product_factory!=null&&!product_factory.equals("")){
                    product_factory1=product_factory.toString();
                }
                cell31.setCellValue(product_factory1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(11);
                Object alias_name=map.get("product24_65_");
                String alias_name1="";
                if (alias_name!=null&&!alias_name.equals("")){
                    alias_name1=alias_name.toString();
                }
                cell31.setCellValue(alias_name1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(12);
                Object backPrinting=map.get("t3back_Printing");
                String backPrinting1="";
                if (backPrinting!=null&&!backPrinting.equals("")){
                    backPrinting1=backPrinting.toString();
                }
                cell31.setCellValue(backPrinting1);
                cell31.setCellStyle(cellStyle3);

//                cell31 = row4.createCell(13);
//                Object backPrinting=map.get("t3back_Printing");
//                String backPrinting1="";
//                if (backPrinting!=null&&!backPrinting.equals("")){
//                    backPrinting1=backPrinting.toString();
//                }
//                cell31.setCellValue(backPrinting1);
//                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(13);
                Object mmf=map.get("t3valied_date");
                String mmf1="";
                cell31.setCellValue(mmf1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(14);
                Object t2relatedCode=map.get("t2relatedCode");
                String t2relatedCode1="";
                if (t2relatedCode!=null&&!t2relatedCode.equals("")){
                    t2relatedCode1=t2relatedCode.toString();
                }
                cell31.setCellValue(t2relatedCode1);
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(15);
                Object baseNumber=map.get("t2price");
                if (baseNumber!=null&&!baseNumber.equals("")){
//                    cell31.setCellValue(baseNumber);
                    cell31.setCellValue(baseNumber.toString());
                    num1+=Double.parseDouble(baseNumber.toString());
                }else {
                    cell31.setCellValue("0");
                }
                cell31.setCellStyle(cellStyle3);
                cell31 = row4.createCell(16);
                Object nowNumber=map.get("t2retailPrice");
                if (nowNumber!=null&&!nowNumber.equals("")){
                    cell31.setCellValue(nowNumber.toString());
                    num2+=Double.parseDouble(nowNumber.toString());
                }else {
                    cell31.setCellValue("0");
                }
                cell31.setCellStyle(cellStyle3);


                cell31 = row4.createCell(17);
                Object lackNumber=map.get("t2priceNumber");
                if (lackNumber!=null&&!lackNumber.equals("")){
                    cell31.setCellValue(lackNumber.toString());
                    num3+=Double.parseDouble(lackNumber.toString());
                }else {
                    cell31.setCellValue("0");
                }
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(18);
                Object t2retailPriceNumber=map.get("t2retailPriceNumber");
                if (t2retailPriceNumber!=null&&!t2retailPriceNumber.equals("")){
                    cell31.setCellValue(t2retailPriceNumber.toString());
                    num4+=Double.parseDouble(t2retailPriceNumber.toString());
                }else {
                    cell31.setCellValue("0");
                }
                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(19);
//                Object =map.get("t2retailPriceNumber");
                Object t2QUANTITY=map.get("t2QUANTITY");
                Object t2splitQuantity=map.get("t2splitQuantity");
                Object t2unit=map.get("t2unit");
                Object t2BasicUnit=map.get("t2BasicUnit");
                String quantyStr="";
                if (t2BasicUnit!=null&&t2BasicUnit.equals("")){
                    if (t2QUANTITY!=null&&!t2QUANTITY.equals("")){
                        quantyStr+=df2.format(t2QUANTITY);
                    }else {
                        quantyStr+="0";
                    }
                    quantyStr+=t2BasicUnit;
                }
                if (t2unit!=null&&t2unit.equals("")){
                    if (t2splitQuantity!=null&&!t2splitQuantity.equals("")){
                        quantyStr+=df2.format(t2splitQuantity);
                    }else {
                        quantyStr+="0";
                    }
                    quantyStr+=t2unit;
                }
                cell31.setCellStyle(cellStyle3);

                statisticalCount=index;
//                cell31 = row4.createCell(16);
//                Object mat_type=map.get("check10_54_");
//                String mat_type1="";
//                if (mat_type!=null&&!mat_type.equals("")){
//                    if (mat_type.equals("1")){
//                        mat_type1="已盘点";
//                        statistical1+=1;
//                    }else {
//                        mat_type1="未盘点";
//                        statistical2+=1;
//                    }
//                }
//                cell31.setCellValue(mat_type1);
//                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(20);
//                Object curNumber=map.get("curNumber");
                Object maxNumber=map.get("maxNumber");
                Object minNumber=map.get("minNumber");
                String maxMinNumber="";
                if (maxNumber!=null&&!maxNumber.equals("")){
                    if (t2BasicUnit!=null&&!t2BasicUnit.equals("")){
                        maxMinNumber+=df2.format(maxNumber)+t2BasicUnit.toString();
                    }else {
                        maxMinNumber+=df2.format(maxNumber);
                    }
                }
                if (minNumber!=null&&!minNumber.equals("")){
                    if (t2unit!=null&&!t2unit.equals("")){
                        maxMinNumber+=df2.format(minNumber)+t2unit.toString();
                    }else {
                        if (t2BasicUnit!=null&&!t2BasicUnit.equals("")){
                            maxMinNumber+=df2.format(minNumber)+t2BasicUnit.toString();
                        }else {
                            maxMinNumber+=df2.format(minNumber);
                        }
                    }
                }
                cell31.setCellValue(maxMinNumber);
                cell31.setCellStyle(cellStyle3);
            }
        }
//        String start1Date="";
//        String end1Date="";
//        cell1=row1.createCell(18);
//        String dates=start1Date+"-"+end1Date;
//        cell1.setCellValue(dates);
        //标题
        Row row2=sheet.createRow(2);
        Cell cell2=row2.createCell(0);
        cell2.setCellValue("");
        String statisticalCounts="汇总：共计"+index+"条数据,"+"采购均价"+df.format(num1)+",零售均价"+df.format(num2)+",采购金额"+df.format(num3)+",零售金额"+df.format(num4);
        cell2.setCellValue(statisticalCounts);
        for (int j = 1; j <=24; j++) {
            cell2 = row2.createCell(j);
            cell2.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 0, 20));
//        Row row5=sheet.createRow(indexRow);
//        Cell cell5 = row5.createCell(0);
//        cell5.setCellValue(statisticalCounts);
//        for (int j = 1; j <=24; j++) {
//            cell5 = row5.createCell(j);
//            cell5.setCellValue("");
//        }
//        sheet.addMergedRegion(new CellRangeAddress(indexRow,indexRow , 0, 24));


        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"库存统计报表";
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

//&warehousCode="+warehousCode+"&select1="+select1+"&remarks="+remarks+"&billCode="+"&operationDate="+operationDate
    public SysRetrunMessage exportWarehousingInfoList(Integer wewId,String warehousCode,String select1,String remarks,String operationDate,String wewId1) throws Exception{
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
        SysUserInfo account = this.GetOneSessionAccount();
        Integer rba = null;
        Integer rbs = null;
        Integer rbb = null;
        Integer suiId = null;
        String purchaseType = null;
        if (account.getOrganizaType().equals("20001")) {
            rba = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "1";
        }
        if (account.getOrganizaType().equals("20002")) {
            rbs = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "2";
        }
        if (account.getOrganizaType().equals("20003")) {
            rbb = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "3";
        }

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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 22 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 10 * 256);
        sheet.setColumnWidth(9, 10 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 15 * 256);
        sheet.setColumnWidth(12, 15 * 256);
        sheet.setColumnWidth(13, 15 * 256);
        sheet.setColumnWidth(14, 15 * 256);
        sheet.setColumnWidth(15, 15 * 256);
        sheet.setColumnWidth(16, 15 * 256);
        sheet.setColumnWidth(17, 15 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue(sysUserInfo.getOrgName()+"入库报表统计");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 24);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=24; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
//        //第一行数据
//        Row row1=sheet.createRow(1);
//        Cell cell1=null;
//
//        cell1 =row1.createCell(0);
//        cell1.setCellValue("单位:");
//        cell1.setCellStyle(cellStyle1);
////        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));
//        cell1 =row1.createCell(1);
//        cell1.setCellValue(account.getOrgName());
//        cell1=row1.createCell(2);
//        cell1.setCellValue("");
//
//        cell1=row1.createCell(3);
//        cell1.setCellValue("");
//        cell1=row1.createCell(4);
//        cell1.setCellValue("");
//        cell1=row1.createCell(5);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 5));
//        cell1=row1.createCell(6);
//        cell1.setCellValue("");
//        cell1=row1.createCell(7);
//        cell1.setCellValue("制表日期：");
//        cell1=row1.createCell(8);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 7,8));
//        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(9);
//        cell1.setCellValue(sdf3.format(NewDate));
//        cell1=row1.createCell(10);
//        cell1.setCellValue("");
//        cell1=row1.createCell(11);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9,11));
//        cell1=row1.createCell(12);
//        cell1.setCellValue("制表人：");
//        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(13);
//        cell1.setCellValue(sysUserInfo.getUserName());
////        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
//        cell1=row1.createCell(14);
//        cell1.setCellValue("");
//        cell1=row1.createCell(15);
//        cell1.setCellValue("");
////        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(16);
//        cell1.setCellValue("报表日期区间：");
//        cell1=row1.createCell(17);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 16, 17));
//        cell1.setCellValue("");
////        row1.setHeightInPoints(18);
//        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
//        //标题indexRow
//        Row row2=sheet.createRow(2);
//        //设置行高

        Row row3=sheet.createRow(1);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("供应商");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("入库时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("入库单号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("入库类型");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
        cell3.setCellValue("物料编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
        cell3.setCellValue("物料名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
        cell3.setCellValue("规格型号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
        cell3.setCellValue("包装方式");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
        cell3.setCellValue("单位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
        cell3.setCellValue("当前库存");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
        cell3.setCellValue("入库数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(12);
        cell3.setCellValue("单价");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(13);
        cell3.setCellValue("金额");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(14);
        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(15);
        cell3.setCellValue("有效期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(16);
        cell3.setCellValue("注册号/备案号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(17);
        cell3.setCellValue("制单人");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(18);
        cell3.setCellValue("平台订单号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(19);
        cell3.setCellValue("备注");
        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(20);
//        cell3.setCellValue("供应商");
//        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(21);
//        cell3.setCellValue("制作人");
//        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(22);
//        cell3.setCellValue("采购金额");
//        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(23);
//        cell3.setCellValue("零售金额");
//        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(24);
//        cell3.setCellValue("平台订单号");
//        cell3.setCellStyle(cellStyle);
        Row row4=sheet.createRow(2);
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int index=0;
        int indexRow=2;
        int firstSize=2;
        int startSzie=3;
//        int countSalesName=0;
        int countAgentCompany=0;
//        int countSalman=0;
//        int index1=0;
        int index2=0;
        int index3=0;
        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
        /*
        * 金额数量总和  用于汇总
        * */
        Integer ddcount=0;
        Double ddcgmoney=0d;
        Double ddlsmoney=0d;

        Double wlcount=0d;
        Double wlcgmoney=0d;
        Double wllsmoney=0d;

        int indexCount=0;
        //s = s.substring(0, s.length() - 1);
//        wewId1=wewId1.substring(0,wewId1.length-1);
        if (wewId1!=null&&!wewId1.equals("")){
            wewId1=wewId1.substring(0,wewId1.length()-1);
        }
        List<Map<String,Object>> mxList =expoetExcelDao.exportWarehousingInfoList(wewId,warehousCode,select1,remarks,operationDate,wewId1);
        if (mxList == null || mxList.isEmpty()) {
            srm.setCode(2L);
            return srm;
        }
        if (mxList!=null&&mxList.size()>0){
            for (Map<String, Object> map : mxList) {
                index++;
                firstSize++;
                startSzie++;
                row4 = sheet.createRow(indexRow++);
                //入库时间
                cell31 = row4.createCell(0);
                cell31.setCellValue(index);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(1);
                Object applicantName=map.get("applicant_Name");
                String applicantName1="";
                if (applicantName!=null&&!applicantName.equals("")){
                    applicantName1=applicantName.toString();
                }
                cell31.setCellValue(applicantName1);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(3);
                Object mat_code=map.get("a2");
                String mat_code1="";
                if (mat_code!=null&&!mat_code.equals("")){
                    mat_code1=mat_code.toString();
                }
//                if (mat_code1.equals(moodeNext)){
//                    countAgentCompany+=1;
//                }
//                if (!mat_code1.equals(moodeNext)&&index>=2){
//                    int dangqianCount=firstSize;
//                    int start=dangqianCount-countAgentCompany;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 0, 0));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 21, 21));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 22, 22));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 23, 23));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 24, 24));
//                    countAgentCompany=0;
//                }
//                if (index==mxList.size()&&mat_code1.equals(moodeNext)){
//                    int dangQianCount=indexRow-1;
//                    int  start=dangQianCount-countAgentCompany;
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 0, 0));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 21, 21));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 22, 22));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 23, 23));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 24, 24));
//                }
                cell31.setCellValue(mat_code1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(2);
                Object createDate=map.get("a9");
                String createDate1="";
                if (createDate!=null&&!createDate.equals("")){
                    createDate1=sdf1.format(createDate);
                    if (index==1){
                        startDate=createDate1;
                    }
                    if (mxList.size()==index){
                        endDate=createDate1;
                    }
                }
                cell31.setCellValue(createDate1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(4);
                Object a3=map.get("a3");
                String a31="";
                if (a3!=null&&!a3.equals("")){
                    if (a3.equals("1")){
                        a31="物料入库";
//                        wlcount+=1;
                    }if (a3.equals("2")){
//                        ddcount+=1;
                        a31="订单入库";
                    }
                }
                cell31.setCellValue(a31);
                cell31.setCellStyle(cellStyle);

//                cell31 = row4.createCell(3);
//                if (!mat_code1.equals(moodeNext)){
//                    indexCount=1;
//                }else {
//                    indexCount+=1;
//                }
//                cell31.setCellValue(indexCount);
//                cell31.setCellStyle(cellStyle);
//                moodeNext=mat_code1;
                cell31 = row4.createCell(5);
                Object matCode=map.get("mat33_50_");
                String matCode1="";
                if (matCode!=null&&!matCode.equals("")){
                    matCode1=matCode.toString();
                }
                cell31.setCellValue(matCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(6);
                Object matName=map.get("mat34_50_");
                String matName1="";
                if (matName!=null&&!matName.equals("")){
                    matName1=matName.toString();
                }
                cell31.setCellValue(matName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(7);
                Object norm=map.get("mmf44_50_");
                String norm1="";
                if (norm!=null&&!norm.equals("")){
                    norm1=norm.toString();
                }
                cell31.setCellValue(norm1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(8);
                Object praName=map.get("product62_50_");
                String praName1="";
                if (praName!=null&&!praName.equals("")){
                    praName1=praName.toString();
                }
                cell31.setCellValue(praName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(9);
                Object basic=map.get("Basic8_50_");
                String basic1="";
                if (basic!=null&&!basic.equals("")){
                    basic1=basic.toString();
                }
                cell31.setCellValue(basic1);
                cell31.setCellStyle(cellStyle);

//                cell31 = row4.createCell(9);
//                Object brand=map.get("brand50_");
//                String brand1="";
//                if (brand!=null&&!brand.equals("")){
//                    brand1=brand.toString();
//                }
//                cell31.setCellValue(brand1);
//                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(10);
                Object number1=map.get("number52_50_");
                if (number1!=null&&!number1.equals("")){
                    cell31.setCellValue(df2.format(number1)+basic1);
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(11);
                Object enterNumber=map.get("mat_number231");
                if (enterNumber!=null&&!enterNumber.equals("")){
                    cell31.setCellValue(df2.format(enterNumber)+basic1);
                    wlcount+=Double.parseDouble(enterNumber.toString());
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);

//                cell31 = row4.createCell(8);
//                Object mmfCode=map.get("mmf42_50_");
//                String mmfCode1="";
//                if (mmfCode!=null&&!mmfCode.equals("")){
//                    mmfCode1=mmfCode.toString();
//                }
//                cell31.setCellValue(mmfCode1);
//                cell31.setCellStyle(cellStyle);

//                cell31 = row4.createCell(10);
//                Object matNumber=map.get("mat14_87_");
//                if (matNumber!=null&&!matNumber.equals("")){
//                    cell31.setCellValue(df2.format(matNumber)+basic1);
//                }else {
//                    cell31.setCellValue("");
//                }
//                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(12);
                Object cgj=map.get("price50_");
                if (cgj!=null&&!cgj.equals("")){
                    cell31.setCellValue(df.format(cgj));
                }else {
                    cell31.setCellValue("0.00");
                }
                cell31.setCellStyle(cellStyle);

//                cell31 = row4.createCell(13);
//                cell31.setCellValue(unit);
//                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(13);
                Object total=map.get("a4");
                if (total!=null&&!total.equals("")){
                    cell31.setCellValue(df.format(total));
//                    if (a3.equals("1")){
//                        wlcgmoney+=Double.parseDouble(total.toString());
//                    }if (a3.equals("2")){
//                        ddcgmoney+=Double.parseDouble(total.toString());
//                    }
                    wlcgmoney+=Double.parseDouble(total.toString());
                }else {
                    cell31.setCellValue("0.00");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(14);
                Object batchCode=map.get("batch_code");
                String batchCode1="";
                if (batchCode!=null&&!batchCode.equals("")){
                    batchCode1=batchCode.toString();
                }
                cell31.setCellValue(batchCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(15);
                Object valiedDate=map.get("yxq");
                String valiedDate1="";
                if (valiedDate!=null&&!valiedDate.equals("")){
                    valiedDate1=sdf1.format(valiedDate);
                }
                cell31.setCellValue(valiedDate1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(16);
                Object backPrinting=map.get("back_Printing");
                String backPrinting1="";
                if (backPrinting!=null&&!backPrinting.equals("")){
                    backPrinting1=backPrinting.toString();
                }
                cell31.setCellValue(backPrinting1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(17);
                Object createRen=map.get("a8");
                String createRen1="";
                if (createRen!=null&&!createRen.equals("")){
                    createRen1=createRen.toString();
                }
                cell31.setCellValue(createRen1);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(18);
                Object orderCode1=map.get("orderCode");
                String orderCode="";
                if (orderCode1!=null&&!orderCode1.equals("")){
                    orderCode=orderCode1.toString();
                }
                cell31.setCellValue(orderCode);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(19);
                Object warehousingRemarks1=map.get("warehousing_remarks");
                String warehousingRemarks="";
                if (warehousingRemarks1!=null&&!warehousingRemarks1.equals("")){
                    warehousingRemarks=warehousingRemarks1.toString();
                }
                cell31.setCellValue(warehousingRemarks);
                cell31.setCellStyle(cellStyle);
            }
        }

//        Integer ddcount=0;
//        Double ddcgmoney=0d;
//        Double ddlsmoney=0d;
//
//        Integer wlcount=0;
//        Double wlcgmoney=0d;
//        Double wllsmoney=0d;

//        cell1=row1.createCell(18);
////        cell1.setCellValue(startDate);
//        if (startDate!=""&&!startDate.equals("")&&endDate!=""&&!endDate.equals("")){
//            cell1.setCellValue(startDate+"-"+endDate);
//        }else {
//            cell1.setCellValue("");
//        }
//        cell1=row1.createCell(19);
//        cell1.setCellValue("");
//        cell1=row1.createCell(20);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 18, 20));
//        cell1.setCellValue("");
        String countNum="共计"+index+"条数据,其中订单入库"+ddcount+"条,采购金额"+df.format(ddcgmoney)+"元。商品物料入库"+wlcount+"条,采购金额"+df.format(wlcgmoney)+"元。总合计采购金额"+df.format((ddcgmoney+wlcgmoney))+"元。";
//        Cell cell2=row2.createCell(0);
//        cell2.setCellValue("汇总:");
//         cell2=row2.createCell(1);
//        cell2.setCellValue(countNum);
//        for (int j = 2; j <=24; j++) {
//            cell2 = row2.createCell(j);
//            cell2.setCellValue("");
//        }
//        sheet.addMergedRegion(new CellRangeAddress(2,2 , 1, 24));

        //标题indexRow
        Row row5=sheet.createRow(indexRow+1);
        Cell cell5=row5.createCell(0);
        cell5.setCellValue("制表人:");
        cell5=row5.createCell(1);
        cell5.setCellValue(sysUserInfo.getUserName());
        cell5 = row5.createCell(2);
        cell5.setCellValue(sdf1.format(new Date()));
        for (int j = 3; j <=9; j++) {
            cell5 = row5.createCell(j);
            cell5.setCellValue("");
        }
        cell5 = row5.createCell(10);
        cell5.setCellValue("累计入库数量:");
        cell5 = row5.createCell(11);
        cell5.setCellValue(df2.format(wlcount));
        cell5 = row5.createCell(12);
        cell5.setCellValue("累计入库金额");
        cell5 = row5.createCell(13);
        cell5.setCellValue(df.format(wlcgmoney));
        sheet.addMergedRegion(new CellRangeAddress(indexRow,indexRow , 1, 24));

        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"入库报表统计";
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
    //仓管导出调价报表
    public SysRetrunMessage exportPriceAdjustmentInv(String paiIds,String checkCode,String searchName,String select) throws Exception{
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
        SysUserInfo account = this.GetOneSessionAccount();
        Integer rba = null;
        Integer rbs = null;
        Integer rbb = null;
        Integer suiId = null;
        String purchaseType = null;
        if (account.getOrganizaType().equals("20001")) {
            rba = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "1";
        }
        if (account.getOrganizaType().equals("20002")) {
            rbs = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "2";
        }
        if (account.getOrganizaType().equals("20003")) {
            rbb = account.getOldId();
            if (account.getUserRole().equals("1")) {//当拥有管理员角色时查询所有订单
            } else
                suiId = account.getSuiId();
            purchaseType = "3";
        }

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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 18 * 256);
//        sheet.setColumnWidth(2, 15 * 256);
//        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 15 * 256);
//        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(20, 20 * 256);
//        sheet.setColumnWidth(11, 20 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue(sysUserInfo.getOrgName()+"物料统计报表");
        cell.setCellStyle(cellStyle2);
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 18);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cellStyle.setWrapText(true);
        for (int j = 1; j <18; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }

        //第一行数据
        Row row1=sheet.createRow(1);
        Cell cell1=null;
        cell1 =row1.createCell(0);
        cell1.setCellValue("单位:");
        cell1.setCellStyle(cellStyle1);
        cell1 =row1.createCell(1);
        cell1.setCellValue(account.getOrgName());
        cell1=row1.createCell(2);
        cell1.setCellValue("");

        cell1=row1.createCell(3);
        cell1.setCellValue("");
        cell1=row1.createCell(4);
        cell1.setCellValue("");
        cell1=row1.createCell(5);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 5));
        cell1=row1.createCell(6);
        cell1.setCellValue("");
        cell1=row1.createCell(7);
        cell1.setCellValue("制表日期：");
        cell1=row1.createCell(8);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 7,8));
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue(sdf3.format(NewDate));
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1=row1.createCell(11);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9,11));
        cell1=row1.createCell(12);
        cell1.setCellValue("制表人：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(13);
        cell1.setCellValue(sysUserInfo.getUserName());
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
        cell1=row1.createCell(14);
        cell1.setCellValue("");
        cell1=row1.createCell(15);
        cell1.setCellValue("");
//        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(16);
        cell1.setCellValue("");
        cell1=row1.createCell(17);
        cell1.setCellValue("");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        //标题indexRow
        Row row2=sheet.createRow(2);
        //设置行高
        Cell cell2=row2.createCell(0);
        cell2.setCellValue("汇总");


        Row row3=sheet.createRow(3);
        //设置行高
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("调价日期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("调价号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("制作部门/人");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("物料编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
        cell3.setCellValue("物料名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
        cell3.setCellValue("规格型号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
        cell3.setCellValue("型号编码");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
        cell3.setCellValue("调价单位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
        cell3.setCellValue("采购均价(元)");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
        cell3.setCellValue("原零售价(元)");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(12);
        cell3.setCellValue("现零售价(元)");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(13);
        cell3.setCellValue("差价(元)");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(14);
        cell3.setCellValue("调价百分比");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(15);
        cell3.setCellValue("注册证号/备案号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(16);
        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(17);
        cell3.setCellValue("备注");
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
//        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
        int statisticalCount=0;
        int statistical1=0;
        int statistical2=0;
        String start1Date="";
        String end1Date="";
        List<Map<String,Object>> mxList =expoetExcelDao.exportPriceAdjustmentInv(paiIds,checkCode,searchName,select);
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
                //入库单号relationBillCode
                cell31 = row4.createCell(2);
                Object mat_code=map.get("pai_code");
                String mat_code1="";
                if (mat_code!=null&&!mat_code.equals("")){
                    mat_code1=mat_code.toString();
                }
                if (mat_code1.equals(moodeNext)){
                    countAgentCompany+=1;
                }
                if (!mat_code1.equals(moodeNext)&&index>=2){
                    int dangqianCount=firstSize;
                    int start=dangqianCount-countAgentCompany;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
                    countAgentCompany=0;
                }
                if (index==mxList.size()&&mat_code1.equals(moodeNext)){
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-countAgentCompany;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                }
                cell31.setCellValue(mat_code1);
                cell31.setCellStyle(cellStyle);
                moodeNext=mat_code1;

//                cell31 = row4.createCell(2);
//                cell31.setCellValue(mat_code1);
//                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(3);
                Object mat_name=map.get("create3_63_");
                String mat_name1="";
                if (mat_name!=null&&!mat_name.equals("")){
                    mat_name1=mat_name.toString();
                }
                cell31.setCellValue(mat_name1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(1);
                Object mmf=map.get("create2_63_");
                String mmf1="";
                if (mmf!=null&&!mmf.equals("")){
                    mmf1=sdf1.format(mmf);
                }
                cell31.setCellValue(mmf1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(4);
                Object mmf_code=map.get("mat12_60_");
                String mmf_code1="";
                if (mmf_code!=null&&!mmf_code.equals("")){
                    mmf_code1=mmf_code.toString();
                }
                cell31.setCellValue(mmf_code1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(5);
                Object Basic_unit=map.get("mat13_60_");
                String Basic_unit1="";
                if (Basic_unit!=null&&!Basic_unit.equals("")){
                    Basic_unit1=Basic_unit.toString();
                }
                cell31.setCellValue(Basic_unit1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(6);
                Object matCode1=map.get("brand60_");
                String matCode="";
                if (matCode1!=null&&!matCode1.equals("")){
                    matCode=matCode1.toString();
                }
                cell31.setCellValue(matCode);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(7);
                Object unit=map.get("mmf16_60_");
                String unit1="";
                if (unit!=null&&!unit.equals("")){
                    unit1=unit.toString();
                }
                cell31.setCellValue(unit1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(8);
                Object mmfName1=map.get("mmf14_60_");
                String mmfName="";
                if (mmfName1!=null&&!mmfName1.equals("")){
                    mmfName=mmfName1.toString();
                }
                cell31.setCellValue(mmfName);
                cell31.setCellStyle(cellStyle);
//                cell31 = row4.createCell(9);
//                Object brand2=map.get("basic5_60_");
//                String brand1="";
//                if (brand2!=null&&!brand2.equals("")){
//                    brand1=brand2.toString();
//                }
//                cell31.setCellValue(brand1);
//                cell31.setCellStyle(cellStyle3);

                cell31 = row4.createCell(9);
                Object product_factory=map.get("basic5_60_");
                String product_factory1="";
                if (product_factory!=null&&!product_factory.equals("")){
                    product_factory1=product_factory.toString();
                }
                cell31.setCellValue(product_factory1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(10);
                Object mmfIdObj=map.get("mmf_id");
                if (mmfIdObj!=null&&!mmfIdObj.equals("")){
                    Integer mmfIdInt=Integer.parseInt(mmfIdObj.toString());
                    Double avgPrice=expoetExcelDao.exportPriceAdjustmentInv2(mmfIdInt,rba,rbs,rbb,purchaseType);
                    cell31.setCellValue(df.format(avgPrice));
                }else {
                    cell31.setCellValue("0.00");
                }
                cell31.setCellStyle(cellStyle);
//                Object avg3Price=map.get("avg3_60_");
//                if (avg3Price!=null&&!avg3Price.equals("")){
//                    cell31.setCellValue(df.format(avg3Price));
//                }else {
//                    cell31.setCellValue("0.00");
//                }
//                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(11);
                Object origionprice=map.get("origion17_60_");
                if (origionprice!=null&&!origionprice.equals("")){
                    cell31.setCellValue(df.format(origionprice));
                }else {
                    cell31.setCellValue("0.00");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(12);
                Object retail=map.get("retail27_60_");
                if (retail!=null&&!retail.equals("")){
                    cell31.setCellValue(df.format(retail));
                }else {
                    cell31.setCellValue("0.00");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(13);
                Object lackPrice=map.get("lackPrice");
                if (lackPrice!=null&&!lackPrice.equals("")){
                    cell31.setCellValue(df.format(lackPrice));
                }else {
                    cell31.setCellValue("0.00");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(14);
                Object basePrice=map.get("basePrice");
                if (basePrice!=null&&!basePrice.equals("")){
                    cell31.setCellValue(df2.format(basePrice)+".00%");
                }else {
                    cell31.setCellValue("0.00");
                }
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(15);
                Object product_name=map.get("back4_60_");
                String product_name1="";
                if (product_name!=null&&!product_name.equals("")){
                    product_name1=product_name.toString();
                }
                cell31.setCellValue(product_name1);
                cell31.setCellStyle(cellStyle);

                statisticalCount=index;


                cell31 = row4.createCell(16);
                Object Purchase_unit=map.get("batch6_60_");
                String Purchase_unit1="";
                if (Purchase_unit!=null&&!Purchase_unit.equals("")){
                    Purchase_unit1=Purchase_unit.toString();
                }
                cell31.setCellValue(Purchase_unit1);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(17);
                Object remark60_=map.get("remark60_");
                String remark60_1="";
                if (remark60_!=null&&!remark60_.equals("")){
                    remark60_1=remark60_.toString();
                }
                cell31.setCellValue(remark60_1);
                cell31.setCellStyle(cellStyle);

            }
        }
        cell2=row2.createCell(1);
        cell2.setCellValue("共"+index+"条数据");
        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"物料统计报表";
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
//"&mooCode="+mooCode+"&customer="+customer+"&customerName="+customerName+"&orderTimeStart="+orderTimeStart+"&orderTimeEnd="+orderTimeEnd+"&outTimeStart="+outTimeStart+"&outTimeEnd="+outTimeEnd+"&flowState="+flowState;

    public SysRetrunMessage exportOutWarehousing(Integer mooId,Integer wowId,String mooIds,String wowIds,String mooCode,String customer,String customerName,String orderTimeStart,String orderTimeEnd,String outTimeStart,String outTimeEnd,String flowState) throws Exception{
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
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        SysUserInfo account = this.GetOneSessionAccount();

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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 22 * 256);
        sheet.setColumnWidth(3, 18 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 12 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 13 * 256);
        sheet.setColumnWidth(9, 10 * 256);
        sheet.setColumnWidth(10, 10 * 256);
        sheet.setColumnWidth(11, 10 * 256);
        sheet.setColumnWidth(12, 10 * 256);
        sheet.setColumnWidth(13, 10 * 256);
        sheet.setColumnWidth(14, 12 * 256);
        sheet.setColumnWidth(15, 10 * 256);
        sheet.setColumnWidth(16, 10 * 256);
        sheet.setColumnWidth(17, 12 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 12 * 256);
        sheet.setColumnWidth(21, 12 * 256);
//        sheet.setColumnWidth(11, 20 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue(sysUserInfo.getOrgName()+"出库报表统计");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 25);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=25; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
//        //第一行数据
//        Row row1=sheet.createRow(1);
//        Cell cell1=null;
//
//        cell1 =row1.createCell(0);
//        cell1.setCellValue("单位:");
//        cell1.setCellStyle(cellStyle1);
////        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));
//        cell1 =row1.createCell(1);
//        cell1.setCellValue(account.getOrgName());
//        cell1=row1.createCell(2);
//        cell1.setCellValue("");
//
//        cell1=row1.createCell(3);
//        cell1.setCellValue("");
//        cell1=row1.createCell(4);
//        cell1.setCellValue("");
//        cell1=row1.createCell(5);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 5));
//        cell1=row1.createCell(6);
//        cell1.setCellValue("");
//        cell1=row1.createCell(7);
//        cell1.setCellValue("制表日期：");
//        cell1=row1.createCell(8);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 7,8));
//        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(9);
//        cell1.setCellValue(sdf3.format(NewDate));
//        cell1=row1.createCell(10);
//        cell1.setCellValue("");
//        cell1=row1.createCell(11);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9,11));
//        cell1=row1.createCell(12);
//        cell1.setCellValue("制表人：");
//        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(13);
//        cell1.setCellValue(sysUserInfo.getUserName());
////        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
//        cell1=row1.createCell(14);
//        cell1.setCellValue("");
//        cell1=row1.createCell(15);
//        cell1.setCellValue("");
////        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(16);
//        cell1.setCellValue("报表日期区间：");
//        cell1=row1.createCell(17);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 16, 17));
//        cell1.setCellValue("");
////        row1.setHeightInPoints(18);
//        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
//        //标题indexRow
//        Row row2=sheet.createRow(2);
        //设置行高

        Row row3=sheet.createRow(1);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("出库单号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("出库时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("机构名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("部门名称");
//         cell3.setCellValue("出库状态");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
//        cell3.setCellValue("接收部门");
        cell3.setCellValue("出库类型");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
//        cell3.setCellValue("接收人");
        cell3.setCellValue("物料名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
//        cell3.setCellValue("物料编码");
        cell3.setCellValue("规格型号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
//        cell3.setCellValue("物料名称");
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
//        cell3.setCellValue("物料规格");
        cell3.setCellValue("包装方式");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
//        cell3.setCellValue("品牌");
        cell3.setCellValue("单位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
//        cell3.setCellValue("包装方式");
        cell3.setCellValue("申领数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(12);
        cell3.setCellValue("出库数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(13);
//        cell3.setCellValue("出库人");
        cell3.setCellValue("缺少数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(14);
//        cell3.setCellValue("采购均价");
        cell3.setCellValue("出库人");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(15);
//        cell3.setCellValue("零售价");
        cell3.setCellValue("采购均价");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(16);
        cell3.setCellValue("采购金额");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(17);
//        cell3.setCellValue("零售金额");
        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(18);
//        cell3.setCellValue("注册号/备案号");
        cell3.setCellValue("有效期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(19);
//        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle);
        cell3.setCellValue("注册号/备案号");
        cell3=row3.createCell(20);
//        cell3.setCellValue("有效期");
        cell3.setCellValue("出库状态");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(21);
//        cell3.setCellValue("申领单号");
        cell3.setCellValue("备注");
        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(22);
//        cell3.setCellValue("申领日期");
//        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(23);
//        cell3.setCellValue("申领数量");
//        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(24);
//        cell3.setCellValue("缺少数量");
//        cell3.setCellStyle(cellStyle);
//        cell3=row3.createCell(25);
//        cell3.setCellValue("备注");
//        cell3.setCellStyle(cellStyle);

        Row row4=sheet.createRow(4);
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int index=0;
        int indexRow=2;
        int firstSize=2;
        int startSzie=3;
//        int countSalesName=0;
        int countAgentCompany=0;
//        int countSalman=0;
//        int index1=0;
        int index2=0;
        int index3=0;
        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
        /*
         * 金额数量总和  用于汇总
         * */
        Integer ddcount=0;
        Double ddcgmoney=0d;
        Double ddlsmoney=0d;

        Integer wlcount=0;
        Double wlcgmoney=0d;
        Double wllsmoney=0d;

        Double sq=0d;
        Double ck=0d;
        Double qs=0d;

        Double lsje=0d;
        Double cgje=0d;
        List<Map<String,Object>> mxList =null;
        if (mooId!=null||wowId!=null){
             mxList =expoetExcelDao.exportOutWarehousingMooIdAndWowId(mooId,wowId);
        }
        else {
            mxList =expoetExcelDao.exportOutWarehousing(mooId,wowId,mooIds,wowIds,mooCode,customer,customerName,orderTimeStart,orderTimeEnd,outTimeStart,outTimeEnd,flowState);
        }
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
                //入库时间
                cell31 = row4.createCell(2);
                Object createDate=map.get("FINSH_DATE");
                String createDate1="";
                if (createDate!=null&&!createDate.equals("")){
                    createDate1=sdf1.format(createDate);
                    if (index==1){
                        startDate=createDate1;
                    }
                    if (mxList.size()==index){
                        endDate=createDate1;
                    }
                }
                cell31.setCellValue(createDate1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(1);
                Object mat_code=map.get("wowCode");
                if (mat_code!=null&&!mat_code.equals("")){
                    String mat_code1="";
                    if (mat_code!=null&&!mat_code.equals("")){
                        mat_code1=mat_code.toString();
                    }
                    if (mat_code1.equals(moodeNext)){
                        countAgentCompany+=1;
                    }
                    if (!mat_code1.equals(moodeNext)&&index>=2){
                        int dangqianCount=firstSize;
                        int start=dangqianCount-countAgentCompany;
//                        sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 0, 0));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 21, 21));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 22, 22));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 23, 23));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 24, 24));
                        countAgentCompany=0;
                    }
                    if (index==mxList.size()&&mat_code1.equals(moodeNext)){
                        int dangQianCount=indexRow-1;
                        int  start=dangQianCount-countAgentCompany;
//                        sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 0, 0));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 21, 21));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 22, 22));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 23, 23));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 24, 24));
                    }
                    cell31.setCellValue(mat_code1);
                    cell31.setCellStyle(cellStyle);
                    moodeNext=mat_code1;
                }else {
                    cell31.setCellValue("");
                    cell31.setCellStyle(cellStyle);
                }


//                cell31 = row4.createCell(3);
//                Object matCode=map.get("mat33_50_");
//                String matCode1="";
//                if (matCode!=null&&!matCode.equals("")){
//                    matCode1=matCode.toString();
//                }
//                cell31.setCellValue(matCode1);
//                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(3);
                Object brand=map.get("groupName");
                String brand1="";
                if (brand!=null&&!brand.equals("")){
                    brand1=brand.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(4);
                Object userName=map.get("userName");
                String userName1="";
                if (userName!=null&&!userName.equals("")){
                    userName1=userName.toString();
                }
                cell31.setCellValue(userName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(5);
                Object wow_type=map.get("wow_type");
                String wow_type1="";
                if (wow_type!=null&&!wow_type.equals("")){
                    wow_type1=wow_type.toString();
                }
                cell31.setCellValue(wow_type1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(6);
                Object basic=map.get("mat_name");
                String basic1="";
                if (basic!=null&&!basic.equals("")){
                    basic1=basic.toString();
                }
                cell31.setCellValue(basic1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(7);
                Object norm=map.get("norm");
                String norm1="";
                if (norm!=null&&!norm.equals("")){
                    norm1=norm.toString();
                }
                cell31.setCellValue(norm1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(8);
                Object brand2=map.get("brand");
                String brand21="";
                if (brand2!=null&&!brand2.equals("")){
                    brand21=brand2.toString();
                }
                cell31.setCellValue(brand21);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(9);
                Object productName=map.get("productName");
                String productName1="";
                if (productName!=null&&!productName.equals("")){
                    productName1=productName.toString();
                }
                cell31.setCellValue(productName1);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(10);
                Object mmfCode=map.get("Basic_unit");
                String mmfCode1="";
                if (mmfCode!=null&&!mmfCode.equals("")){
                    mmfCode1=mmfCode.toString();
                }
                cell31.setCellValue(mmfCode1);
                cell31.setCellStyle(cellStyle);











                cell31 = row4.createCell(11);
                Object matNumber=map.get("baseNumber1");
//                Object basicUnit=map.get("Basic_unit");
//                Object split_quantity=map.get("split_number");
//                Object split_unit=map.get("split_unit");
                String baseNumberVal="";
                if (matNumber!=null&&!matNumber.equals("")) {
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        baseNumberVal=df2.format(matNumber)+basicUnit.toString();
//                    }
//                    else {
                    baseNumberVal = df2.format(matNumber);
                    sq+=Double.parseDouble(matNumber.toString());
//                    }
                }
//                }else {
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
////                        baseNumberVal=df2.format(matNumber)+basicUnit.toString();
//                        baseNumberVal="0"+basicUnit.toString();
//                    }else {
//                        baseNumberVal="0";
//                    }
//                }
//                if (split_quantity!=null&&!split_quantity.equals("")){
//                    if (split_unit!=null&&!split_unit.equals("")){
//                        baseNumberVal+="\t"+df2.format(split_quantity)+split_unit.toString();
//                    }else {
//                        baseNumberVal+="\t"+df2.format(split_quantity)+basicUnit.toString();
//                    }
//                }else {
//                    if (split_unit!=null&&!split_unit.equals("")){
//                        baseNumberVal+="\t0"+split_unit.toString();
//                    }else {
//                        baseNumberVal+="\t0"+basicUnit.toString();
//                    }
//                }
                cell31.setCellValue(baseNumberVal);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(12);
                Object enterNumber=map.get("baseNumber2");
//                Object split_number=map.get("split_quantity");
                String baseNumber2Val="";

                if (enterNumber!=null&&!enterNumber.equals("")){
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        baseNumber2Val=df2.format(enterNumber)+basicUnit.toString();
//                    }
//                    else {
                        baseNumber2Val=df2.format(enterNumber);
                        ck+=Double.parseDouble(enterNumber.toString());
                    }
//                }else {
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        baseNumber2Val="0"+basicUnit.toString();
//                    }else {
//                        baseNumber2Val="0";
//                    }
//                }
//                if (split_number!=null&&!split_number.equals("")){
//                    if (split_unit!=null&&!split_unit.equals("")){
//                        baseNumber2Val+="\t"+df2.format(split_number)+split_unit.toString();
//                    }else {
//                        baseNumber2Val+="\t"+df2.format(split_number)+basicUnit.toString();
//                    }
//                }else {
//                    if (split_unit!=null&&!split_unit.equals("")){
//                        baseNumber2Val+="\t0"+split_unit.toString();
//                    }else {
//                        baseNumber2Val+="\t0"+basicUnit.toString();
//                    }
//                }
                cell31.setCellValue(baseNumber2Val);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(13);
                Object lacknumber=map.get("lacknumber");
//                Object lackSplitNumber=map.get("lackSplitNumber");
                String baseNumber3Val="";
                if (lacknumber!=null&&!lacknumber.equals("")){
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        baseNumber3Val=df2.format(lacknumber)+basicUnit.toString();
//                    }
//                    else {
                        baseNumber3Val=df2.format(lacknumber);
                    qs+=Double.parseDouble(lacknumber.toString());
                    }
//                }else {
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        baseNumber3Val="0"+basicUnit.toString();
//                    }else {
//                        baseNumber3Val="0";
//                    }
//                }
//                if (lackSplitNumber!=null&&!lackSplitNumber.equals("")){
//                    if (split_unit!=null&&!split_unit.equals("")){
//                        baseNumber3Val+="\t"+df2.format(lackSplitNumber)+split_unit.toString();
//                    }else {
//                        baseNumber3Val+="\t"+df2.format(lackSplitNumber)+basicUnit.toString();
//                    }
//                }else {
//                    if (split_unit!=null&&!split_unit.equals("")){
//                        baseNumber3Val+="\t0"+split_unit.toString();
//                    }else {
//                        baseNumber3Val+="\t0"+basicUnit.toString();
//                    }
//                }
                cell31.setCellValue(baseNumber3Val);
                cell31.setCellStyle(cellStyle);



                cell31 = row4.createCell(14);
                Object createRen=map.get("createRen");
                String createRen1="";
                if (createRen!=null&&!createRen.equals("")){
                    createRen1=createRen.toString();
                }
                cell31.setCellValue(createRen1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(15);
                Object price=map.get("price");
                if (price!=null&&!price.equals("")){
                    cell31.setCellValue(df.format(price));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);


//                cell31 = row4.createCell(15);
//                Object retailPrice=map.get("retailPrice");
//                if (retailPrice!=null&&!retailPrice.equals("")){
//                    cell31.setCellValue(df.format(retailPrice));
//                }else {
//                    cell31.setCellValue("");
//                }
//                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(16);
                Object prices=map.get("prices");
                if (prices!=null&&!prices.equals("")){
                    cell31.setCellValue(df.format(prices));
                    cgje+=Double.parseDouble(prices.toString());
                }else {
                    cell31.setCellValue("");
                    cgje+=0.0;
                }
                cell31.setCellStyle(cellStyle);

//                cell31 = row4.createCell(17);
//                Object retailPrices=map.get("retailPrices");
//                if (retailPrices!=null&&!retailPrices.equals("")){
//                    cell31.setCellValue(df.format(retailPrices));
//                    lsje+=Double.parseDouble(retailPrices.toString());
//                }else {
//                    cell31.setCellValue("");
//                }
//                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(17);
                Object batch_code=map.get("batch_code");
                String batch_code1="";
                if (batch_code!=null&&!batch_code.equals("")){
                    batch_code1=batch_code.toString();
                }
                cell31.setCellValue(batch_code1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(19);
                Object back_Printing=map.get("back_Printing");
                String back_Printing1="";
                if (back_Printing!=null&&!back_Printing.equals("")){
                    back_Printing1=back_Printing.toString();
                }
                cell31.setCellValue(back_Printing1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(18);
                Object valiedDate=map.get("valied_date");
                String valiedDate1="";
                if (valiedDate!=null&&!valiedDate.equals("")){
                    valiedDate1=sdf1.format(valiedDate);
                }
                cell31.setCellValue(valiedDate1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(20);
                Object matName=map.get("flow_state");
                String matName1="";
                if (matName!=null&&!matName.equals("")){
                    matName1=matName.toString();
                    if (matName1.equals("2")){
                        matName1="申请中";
                    }else if (matName1.equals("3")){
                        matName1="部分发货";
                    }else if (matName1.equals("4")){
                        matName1="已完成";
                    }else if (matName1.equals("5")){
                        matName1="撤销";
                    }else if (matName1.equals("7")){
                        matName1="售后";
                    }
                }else {
                    if (mat_code!=null&&!mat_code.equals("")){
                        matName1="已完成";
                    }
                }
                cell31.setCellValue(matName1);
                cell31.setCellStyle(cellStyle);

//                cell31 = row4.createCell(21);
//                Object code=map.get("moo_code");
//                String code1="";
//                if (code!=null&&!code.equals("")){
//                    code1=code.toString();
//                }
//                cell31.setCellValue(code1);
//                cell31.setCellStyle(cellStyle);
//
//                cell31 = row4.createCell(22);
//                Object create_date=map.get("create_date");
//                String create_date1="";
//                if (create_date!=null&&!create_date.equals("")){
//                    create_date1=sdf1.format(create_date);
//                }
//                cell31.setCellValue(create_date1);
//                cell31.setCellStyle(cellStyle);




                //                cell31 = row4.createCell(12);
//                Object matNumber=map.get("baseNumber1");
//                Object basicUnit=map.get("Basic_unit");
//                Object split_quantity=map.get("split_number");
//                Object split_unit=map.get("split_unit");
//                String baseNumberVal="";
//                if (matNumber!=null&&!matNumber.equals("")){
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        baseNumberVal=df2.format(matNumber)+basicUnit.toString();
//                    }
//                    else {
//                        baseNumberVal=df2.format(matNumber);
//                    }
//                }else {
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
////                        baseNumberVal=df2.format(matNumber)+basicUnit.toString();
//                        baseNumberVal="0"+basicUnit.toString();
//                    }else {
//                        baseNumberVal="0";
//                    }
//                }
//                if (split_quantity!=null&&!split_quantity.equals("")){
//                    if (split_unit!=null&&!split_unit.equals("")){
//                        baseNumberVal+="\t"+df2.format(split_quantity)+split_unit.toString();
//                    }else {
//                        baseNumberVal+="\t"+df2.format(split_quantity)+basicUnit.toString();
//                    }
//                }else {
//                    if (split_unit!=null&&!split_unit.equals("")){
//                        baseNumberVal+="\t0"+split_unit.toString();
//                    }else {
//                        baseNumberVal+="\t0"+basicUnit.toString();
//                    }
//                }
//                cell31.setCellValue(baseNumberVal);
//                cell31.setCellStyle(cellStyle);



                cell31 = row4.createCell(21);
                Object unit=map.get("wow_remarks");
                String unit1="";
                if (unit!=null&&!unit.equals("")){
                    unit1=unit.toString();
                }
                cell31.setCellValue(unit1);
                cell31.setCellStyle(cellStyle);

            }
        }

//        cell1=row1.createCell(18);
////        cell1.setCellValue(startDate);
//        if (startDate!=""&&!startDate.equals("")&&endDate!=""&&!endDate.equals("")){
//            cell1.setCellValue(startDate+"-"+endDate);
//        }else {
//            cell1.setCellValue("");
//        }
//        cell1=row1.createCell(19);
//        cell1.setCellValue("");
//        cell1=row1.createCell(20);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 18, 20));
//        cell1.setCellValue("");

        // Double lsje=0d;
        // Double cgje=0d;
//        String countNum="共计"+index+"条数据,采购金额"+df.format(cgje)+"元,零售金额"+df.format(lsje)+"元";
//        String countNum="共计"+index+"条数据,采购金额"+df.format(cgje)+"元。";
//        Cell cell2=row2.createCell(0);
//        cell2.setCellValue("汇总:");
//        cell2=row2.createCell(1);
//        cell2.setCellValue(countNum);
//        for (int j = 2; j <=24; j++) {
//            cell2 = row2.createCell(j);
//            cell2.setCellValue("");
//        }
//        sheet.addMergedRegion(new CellRangeAddress(2,2 , 1, 24));

        //标题indexRow
        Row row5=sheet.createRow(indexRow);
        Cell cell5=row5.createCell(0);
        cell5.setCellValue("制表人:");
        cell5=row5.createCell(1);
        cell5.setCellValue(sysUserInfo.getUserName());
        cell5=row5.createCell(2);
        cell5.setCellValue("制表日期:");
        cell5=row5.createCell(3);
        cell5.setCellValue(sdf1.format(new Date()));
//        for (int j = 4; j <=; j++) {
//            cell5 = row5.createCell(j);
//            cell5.setCellValue("");
//        }
        cell5=row5.createCell(9);
        cell5.setCellValue("累计申领数量:");
        cell5=row5.createCell(10);
        cell5.setCellValue(df2.format(sq));
        cell5=row5.createCell(11);
        cell5.setCellValue("累计出库:");
        cell5=row5.createCell(12);
        cell5.setCellValue(df2.format(ck));
        cell5=row5.createCell(13);
        cell5.setCellValue("累计缺少数量:");
        cell5=row5.createCell(14);
        cell5.setCellValue(df2.format(qs));

        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"出库报表统计";
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
    //导出物料日志中的表格
    public SysRetrunMessage exportWzidExcel(Integer wiId,String matCode,String matName,String quantity) throws Exception{
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
        SysUserInfo account = this.GetOneSessionAccount();


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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 10 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        sheet.setColumnWidth(6, 15 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 15 * 256);
        sheet.setColumnWidth(9, 15 * 256);
        sheet.setColumnWidth(10, 15 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue("物料编号"+matCode+"出库明细表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 12);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=12; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行数据
        Row row1=sheet.createRow(1);
        Cell cell1=null;
        cell1 =row1.createCell(0);
        cell1.setCellValue("单位:");
        cell1.setCellStyle(cellStyle1);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));
        cell1 =row1.createCell(1);
        cell1.setCellValue(account.getOrgName());
        cell1=row1.createCell(2);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 2));
        cell1=row1.createCell(3);
        cell1.setCellValue("");
        cell1=row1.createCell(4);
        cell1.setCellValue("制表日期：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(5);
        cell1.setCellValue(sdf3.format(NewDate));
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(6);
        cell1.setCellValue("");
        cell1=row1.createCell(7);
        cell1.setCellValue("制表人：");
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(8);
        cell1.setCellValue(sysUserInfo.getUserName());
        cell1.setCellStyle(cellStyle1);
        cell1=row1.createCell(9);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 8,9));
        cell1=row1.createCell(10);
        cell1.setCellValue("");
        cell1=row1.createCell(11);
        cell1.setCellValue("");

        cell1=row1.createCell(12);
        cell1.setCellValue("");
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 10,12));
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        //标题indexRow
        Row row2=sheet.createRow(2);
        //设置行高
        Cell cell2=null;
        cell2=row2.createCell(0);
        cell2.setCellValue("物料编号:"+matCode+"\t\t物料名称:"+matName+"\t\t库存数量:"+quantity);
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 0,11));
        for (int j = 1; j <=12; j++) {
            cell2 = row2.createCell(j);
            cell2.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell2.setCellValue("");
        }
        Row row21=sheet.createRow(3);
        Cell cell21=null;
        cell21=row21.createCell(0);
        cell21.setCellValue("汇总");
        cell21.setCellStyle(cellStyle1);
        Row row3=sheet.createRow(4);
        //设置行高
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("类型");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("规格型号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("单价");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
        cell3.setCellValue("供应商");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
        cell3.setCellValue("有效期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
        cell3.setCellValue("注册号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
        cell3.setCellValue("总金额");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
        cell3.setCellValue("出入库单号");
        cell3.setCellStyle(cellStyle);
        Row row4=sheet.createRow(5);
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int index=0;
        int firstSize=0;
        int startSzie=0;
        int indexRow=5;
        int enterNumber=0;
        int outCount=0;
        Cell cell31=null;
        List<Map<String,Object>> mxList =expoetExcelDao.exportWzidExcel(wiId);
        if (mxList!=null&&mxList.size()>0){
            for (Map<String, Object> map : mxList) {
                index++;
                firstSize++;
                startSzie++;
                row4 = sheet.createRow(indexRow++);

                cell31 = row4.createCell(0);
                Object createDate1=map.get("createDate1");
                if (createDate1!=null&&!createDate1.equals("")){
                    cell31.setCellValue(createDate1.toString());
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(1);
                Object stype=map.get("stype");
                String stype1="";
                if (stype!=null&&!stype.equals("")){
                    if (stype.toString().equals("1")||stype=="1"){
                        stype1="入库";
                        enterNumber+=1;
                    }else if (stype.toString().equals("2")||stype=="2"){
                        stype1="出库";
                        outCount+=1;
                    }
                }
                cell31.setCellValue(stype1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(2);
                Object mmfName=map.get("mmfName");
                String mmfName1="";
                if (mmfName!=null&&!mmfName.equals("")){
                    mmfName1=mmfName.toString();
                }
                cell31.setCellValue(mmfName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(3);
                Object number=map.get("number");
                if (number!=null&&!number.equals("")){
                    cell31.setCellValue(df2.format(number));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(4);
                Object price=map.get("price");
                if (price!=null&&!price.equals("")){
                    cell31.setCellValue(df.format(price));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(5);
                Object brand=map.get("brand");
                String brand1="";
                if (brand!=null&&!brand.equals("")){
                    brand1=brand.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(6);
                Object retailPrice=map.get("retailPrice");
                if (retailPrice!=null&&!retailPrice.equals("")){
                    cell31.setCellValue(df.format(retailPrice));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);
                  cell31 = row4.createCell(7);
                Object validDate=map.get("validDate");
                if (validDate!=null&&!validDate.equals("")){
                    cell31.setCellValue(sdf1.format(validDate));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(8);
                Object backPrinting=map.get("backPrinting");
                String backPrinting1="";
                if (backPrinting!=null&&!backPrinting.equals("")){
                    backPrinting1=backPrinting.toString();
                }
                cell31.setCellValue(backPrinting1);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(9);
                Object batchCode=map.get("batchCode");
                String batchCode1="";
                if (batchCode!=null&&!batchCode.equals("")){
                    batchCode1=batchCode.toString();
                }
                cell31.setCellValue(batchCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(10);
                Object allPrice=map.get("allPrice");
                if (allPrice!=null&&!allPrice.equals("")){
                    cell31.setCellValue(df.format(allPrice));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(11);
                Object cCode=map.get("cCode");
                String cCode1="";
                if (cCode!=null&&!cCode.equals("")){
                    cCode1=cCode.toString();
                }
                cell31.setCellValue(cCode1);
                cell31.setCellStyle(cellStyle);

            }
        }
        cell21=row21.createCell(1);
        cell21.setCellValue("共计"+index+"条数据,"+"入库"+enterNumber+"条,出库"+outCount+"条");
        for (int i = 2; i <=11 ; i++) {
            cell21=row21.createCell(i);
            cell21.setCellValue("");
        }
        sheet.addMergedRegion(new CellRangeAddress(3,3 , 1,11));
        Row row51=sheet.createRow(indexRow+1);
        Cell cell51=null;
        cell51=row51.createCell(0);
        cell51.setCellValue("汇总");
        cell51.setCellStyle(cellStyle1);
        cell51=row51.createCell(1);
        cell51.setCellValue("共计"+index+"条数据,"+"入库"+enterNumber+"条,出库"+outCount+"条");
        sheet.addMergedRegion(new CellRangeAddress(indexRow+1,indexRow+1 , 1,11));
        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"出库报表统计";
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

    //重新整理批量导出出库报表统计
    public SysRetrunMessage plExportOutWarehousing(Integer count,Integer stateMooId,String mooIds,String wowIds,String mooCode,String customer,String customerName,String orderTimeStart,String orderTimeEnd,String outTimeStart,String outTimeEnd,String flowState) throws Exception {
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
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        SysUserInfo account = this.GetOneSessionAccount();

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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
//        sheet.setColumnWidth(0, 8 * 256);
//        sheet.setColumnWidth(1, 20 * 256);
//        sheet.setColumnWidth(2, 22 * 256);
//        sheet.setColumnWidth(3, 18 * 256);
//        sheet.setColumnWidth(4, 15 * 256);
//        sheet.setColumnWidth(5, 15 * 256);
////        sheet.setColumnWidth(6, 20 * 256);
//        sheet.setColumnWidth(7, 15 * 256);
//        sheet.setColumnWidth(8, 15 * 256);
//        sheet.setColumnWidth(15, 18 * 256);
//        sheet.setColumnWidth(16, 18 * 256);
////        sheet.setColumnWidth(9, 20 * 256);
//        sheet.setColumnWidth(20, 20 * 256);
//        sheet.setColumnWidth(21, 20 * 256);
//        sheet.setColumnWidth(22, 20 * 256);
////        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 18 * 256);
        sheet.setColumnWidth(2, 22 * 256);
        sheet.setColumnWidth(3, 18 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 12 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 13 * 256);
        sheet.setColumnWidth(9, 10 * 256);
        sheet.setColumnWidth(10, 10 * 256);
        sheet.setColumnWidth(11, 10 * 256);
        sheet.setColumnWidth(12, 10 * 256);
        sheet.setColumnWidth(13, 10 * 256);
        sheet.setColumnWidth(14, 12 * 256);
        sheet.setColumnWidth(15, 10 * 256);
        sheet.setColumnWidth(16, 10 * 256);
        sheet.setColumnWidth(17, 12 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 15 * 256);
        sheet.setColumnWidth(20, 12 * 256);
        sheet.setColumnWidth(21, 12 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue(sysUserInfo.getOrgName()+"出库报表统计");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 25);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=25; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
//        //第一行数据
//        Row row1=sheet.createRow(1);
//        Cell cell1=null;
//
//        cell1 =row1.createCell(0);
//        cell1.setCellValue("单位:");
//        cell1.setCellStyle(cellStyle1);
////        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 1));
//        cell1 =row1.createCell(1);
//        cell1.setCellValue(account.getOrgName());
//        cell1=row1.createCell(2);
//        cell1.setCellValue("");
//
//        cell1=row1.createCell(3);
//        cell1.setCellValue("");
//        cell1=row1.createCell(4);
//        cell1.setCellValue("");
//        cell1=row1.createCell(5);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 1, 5));
//        cell1=row1.createCell(6);
//        cell1.setCellValue("");
//        cell1=row1.createCell(7);
//        cell1.setCellValue("制表日期：");
//        cell1=row1.createCell(8);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 7,8));
//        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(9);
//        cell1.setCellValue(sdf3.format(NewDate));
//        cell1=row1.createCell(10);
//        cell1.setCellValue("");
//        cell1=row1.createCell(11);
//        cell1.setCellValue("");
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 9,11));
//        cell1=row1.createCell(12);
//        cell1.setCellValue("制表人：");
//        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(13);
//        cell1.setCellValue(sysUserInfo.getUserName());
////        sheet.addMergedRegion(new CellRangeAddress(1,1 , 11, 13));
//        cell1=row1.createCell(14);
//        cell1.setCellValue("");
//        cell1=row1.createCell(15);
//        cell1.setCellValue("");
////        cell1.setCellStyle(cellStyle1);
//        cell1=row1.createCell(16);
//        cell1.setCellValue("报表日期区间：");
//        cell1=row1.createCell(17);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 16, 17));
//        cell1.setCellValue("");
////        row1.setHeightInPoints(18);
//        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
//        //标题indexRow
//        Row row2=sheet.createRow(2);
//        //设置行高

        Row row3=sheet.createRow(1);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("出库单号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("出库时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("机构名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("部门名称");
//         cell3.setCellValue("出库状态");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
//        cell3.setCellValue("接收部门");
        cell3.setCellValue("出库类型");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
//        cell3.setCellValue("接收人");
        cell3.setCellValue("物料名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
//        cell3.setCellValue("物料编码");
        cell3.setCellValue("规格型号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
//        cell3.setCellValue("物料名称");
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
//        cell3.setCellValue("物料规格");
        cell3.setCellValue("包装方式");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
//        cell3.setCellValue("品牌");
        cell3.setCellValue("单位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
//        cell3.setCellValue("包装方式");
        cell3.setCellValue("申领数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(12);
        cell3.setCellValue("出库数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(13);
//        cell3.setCellValue("出库人");
        cell3.setCellValue("缺少数量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(14);
//        cell3.setCellValue("采购均价");
        cell3.setCellValue("出库人");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(15);
//        cell3.setCellValue("零售价");
        cell3.setCellValue("采购均价");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(16);
        cell3.setCellValue("采购金额");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(17);
//        cell3.setCellValue("零售金额");
        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(18);
//        cell3.setCellValue("注册号/备案号");
        cell3.setCellValue("有效期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(19);
//        cell3.setCellValue("批号");
        cell3.setCellStyle(cellStyle);
        cell3.setCellValue("注册号/备案号");
        cell3=row3.createCell(20);
//        cell3.setCellValue("有效期");
        cell3.setCellValue("出库状态");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(21);
//        cell3.setCellValue("申领单号");
        cell3.setCellValue("备注");
        cell3.setCellStyle(cellStyle);

        Row row4=sheet.createRow(4);
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int index=0;
        int indexRow=2;
        int firstSize=2;
        int startSzie=3;
//        int countSalesName=0;
        int countAgentCompany=0;
//        int countSalman=0;
//        int index1=0;
        int index2=0;
        int index3=0;
        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
        /*
         * 金额数量总和  用于汇总
         * */
//        Integer ddcount=0;
//        Double ddcgmoney=0d;
//        Double ddlsmoney=0d;
//
//        Integer wlcount=0;
//        Double wlcgmoney=0d;
//        Double wllsmoney=0d;
        Double sq=0d;
        Double ck=0d;
        Double qs=0d;

        Double lsje=0d;
        Double cgje=0d;
        if (mooIds.equals("")){
            mooIds=null;
        }
        if (wowIds.equals("")){
            wowIds=null;
        }
        List<Map<String,Object>> mxList =null;
            mxList =expoetExcelDao.plExportOutWarehousing(stateMooId,mooIds,wowIds,mooCode,customer,customerName,orderTimeStart,orderTimeEnd,outTimeStart,outTimeEnd,flowState);
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
                //入库时间
                cell31 = row4.createCell(2);
                Object createDate=map.get("finishDate");
                String createDate1="";
                if (createDate!=null&&!createDate.equals("")){
                    createDate1=createDate.toString();
                    if (index==1){
                        startDate=createDate1;
                    }
                    if (mxList.size()==index){
                        endDate=createDate1;
                    }
                }
                cell31.setCellValue(createDate1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(1);
                Object mat_code=map.get("wowCode");
                if (mat_code!=null&&!mat_code.equals("")){
                    String mat_code1="";
                    if (mat_code!=null&&!mat_code.equals("")){
                        mat_code1=mat_code.toString();
                    }
                    if (mat_code1.equals(moodeNext)){
                        countAgentCompany+=1;
                    }
                    if (!mat_code1.equals(moodeNext)&&index>=2){
                        int dangqianCount=firstSize;
                        int start=dangqianCount-countAgentCompany;
//                        sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 1, 1));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 0, 0));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 2, 2));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 21, 21));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 22, 22));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 23, 23));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangqianCount, 24, 24));
                        countAgentCompany=0;
                    }
                    if (index==mxList.size()&&mat_code1.equals(moodeNext)){
                        int dangQianCount=indexRow-1;
                        int  start=dangQianCount-countAgentCompany;
//                        sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 0, 0));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 21, 21));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 22, 22));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 23, 23));
//                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 24, 24));
                    }
                    cell31.setCellValue(mat_code1);
                    cell31.setCellStyle(cellStyle);
                    moodeNext=mat_code1;
                }else {
                    cell31.setCellValue("");
                    cell31.setCellStyle(cellStyle);
                }

                cell31 = row4.createCell(3);
                Object brand=map.get("groupName");
                String brand1="";
                if (brand!=null&&!brand.equals("")){
                    brand1=brand.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(4);
                Object userName=map.get("userName");
                String userName1="";
                if (userName!=null&&!userName.equals("")){
                    userName1=userName.toString();
                }
                cell31.setCellValue(userName1);
                cell31.setCellStyle(cellStyle);


//                cell31 = row4.createCell(3);
//                Object matCode=map.get("mat33_50_");
//                String matCode1="";
//                if (matCode!=null&&!matCode.equals("")){
//                    matCode1=matCode.toString();
//                }
//                cell31.setCellValue(matCode1);
//                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(5);
                Object wow_type=map.get("wowType");
                String wow_type1="";
                if (wow_type!=null&&!wow_type.equals("")){
                    if (Integer.parseInt(wow_type.toString())==1){
                        wow_type1="领料出库";
                    }else if (Integer.parseInt(wow_type.toString())==2||Integer.parseInt(wow_type.toString())==4){
                        wow_type1="退货出库";
                    }else if (Integer.parseInt(wow_type.toString())==3){
                        wow_type1="报损出库";
                    }else {
                        wow_type1="领料出库";
                    }
                }else {
                    wow_type1="领料出库";
                }
                cell31.setCellValue(wow_type1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(6);
                Object basic=map.get("matName");
                String basic1="";
                if (basic!=null&&!basic.equals("")){
                    basic1=basic.toString();
                }
                cell31.setCellValue(basic1);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(7);
                Object norm=map.get("NORM");
                String norm1="";
                if (norm!=null&&!norm.equals("")){
                    norm1=norm.toString();
                }
                cell31.setCellValue(norm1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(8);
                Object brand2=map.get("brand");
                String brand21="";
                if (brand2!=null&&!brand2.equals("")){
                    brand21=brand2.toString();
                }
                cell31.setCellValue(brand21);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(9);
                Object productName=map.get("product_name");
                String productName1="";
                if (productName!=null&&!productName.equals("")){
                    productName1=productName.toString();
                }
                cell31.setCellValue(productName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(10);
                Object mmfCode=map.get("Basic_unit");
                String mmfCode1="";
                if (mmfCode!=null&&!mmfCode.equals("")){
                    mmfCode1=mmfCode.toString();
                }
                cell31.setCellValue(mmfCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(12);
                Object matNumber=map.get("outNumber");
                Object basicUnit=map.get("Basic_unit");
                Object splitUnit=map.get("split_unit");
                Object splitQuantity=map.get("split_quantity");
                Object splitNumber1=map.get("split_number1");
                Object lacksplitNumber=map.get("lacksplitNumber");
                String outNumbers="";
                if (matNumber!=null&&!matNumber.equals("")){
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
                        outNumbers+=df2.format(matNumber);
                        ck+=Double.parseDouble(matNumber.toString());
                    }
//                    else {
//                        outNumbers+=df2.format(matNumber);
//                    }
//                }
//                if (splitUnit!=null&&!splitUnit.equals("")){
//                    outNumbers+=df2.format(splitNumber1)+splitUnit.toString();
//                }else {
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        outNumbers+=df2.format(splitNumber1)+basicUnit.toString();
//                    }else {
//                        outNumbers+=df2.format(splitNumber1);
//                    }
//                }
                cell31.setCellValue(outNumbers);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(11);
                Object enterNumber=map.get("sqNumber");
//                Object basicUnit1=map.get("Basic_unit");
                String a1="";
                if (enterNumber!=null&&!enterNumber.equals("")){
                    a1+=df2.format(enterNumber);
                    sq+=Double.parseDouble(enterNumber.toString());
//                    if (basicUnit1!=null&&!basicUnit1.equals("")){
//                        a1+=basicUnit1.toString();
//                    }
                }
//                if (splitUnit!=null&&!splitUnit.equals("")){
//                    a1+=df2.format(splitQuantity)+splitUnit.toString();
//                }else {
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        a1+=df2.format(splitQuantity)+basicUnit.toString();
//                    }else {
//                        a1+=df2.format(splitQuantity);
//                    }
//                }
                cell31.setCellValue(a1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(13);
                Object lacknumber=map.get("lackNumber");
                String lackNUmberStr="";
                if (lacknumber!=null&&!lacknumber.equals("")){
//                    if (basicUnit1!=null&&!basicUnit1.equals("")){
//                        lackNUmberStr+=df2.format(lacknumber)+basicUnit1.toString();
//                    }else {
                        lackNUmberStr+=df2.format(lacknumber);
                    qs+=Double.parseDouble(lacknumber.toString());
//                    }
                }
//                if (splitUnit!=null&&!splitUnit.equals("")){
//                    lackNUmberStr+=df2.format(lacksplitNumber)+splitUnit.toString();
//                }else {
//                    if (!basicUnit.equals("")&&basicUnit!=null&&basicUnit!="null"){
//                        lackNUmberStr+=df2.format(lacksplitNumber)+basicUnit.toString();
//                    }else {
//                        lackNUmberStr+=df2.format(lacksplitNumber);
//                    }
//                }
                cell31.setCellValue(lackNUmberStr);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(14);
                Object createRen=map.get("outRen");
                String createRen1="";
                if (createRen!=null&&!createRen.equals("")){
                    createRen1=createRen.toString();
                }
                cell31.setCellValue(createRen1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(15);
                Object price=map.get("price");
                if (price!=null&&!price.equals("")){
                    cell31.setCellValue(df.format(price));
                }else {
                    cell31.setCellValue("");
                }
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(16);
//                Object prices=map.get("prices");
                if (matNumber!=null&&!matNumber.equals("")){
                    if (price!=null&&!price.equals("")){
                        cell31.setCellValue(df.format(Double.parseDouble(price.toString())*Double.parseDouble(matNumber.toString())));
                        cgje+=Double.parseDouble(price.toString())*Double.parseDouble(matNumber.toString());
                    }else {
                        cell31.setCellValue(df.format(0.00*Double.parseDouble(matNumber.toString())));
                        cgje+=0.00*Double.parseDouble(matNumber.toString());
                    }

                }else {
                    cell31.setCellValue("0.00");
                    cgje+=0.0;
                }
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(17);
                Object batch_code=map.get("batch_code");
                String batch_code1="";
                if (batch_code!=null&&!batch_code.equals("")){
                    batch_code1=batch_code.toString();
                }
                cell31.setCellValue(batch_code1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(18);
                Object valiedDate=map.get("valied_date");
                String valiedDate1="";
                if (valiedDate!=null&&!valiedDate.equals("")){
                    valiedDate1=sdf1.format(valiedDate);
                }
                cell31.setCellValue(valiedDate1);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(19);
                Object back_Printing=map.get("backPrinting");
                String back_Printing1="";
                if (back_Printing!=null&&!back_Printing.equals("")){
                    back_Printing1=back_Printing.toString();
                }
                cell31.setCellValue(back_Printing1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(20);
                Object matName=map.get("flowState");
                String matName1="";
                if (matName!=null&&!matName.equals("")){
                    if (Integer.parseInt(matName.toString())==2){
                        matName1="申请中";
                    }else if (Integer.parseInt(matName.toString())==3){
                        matName1="部分发货";
                    }else if (Integer.parseInt(matName.toString())==4){
                        matName1="已完成";
                    }else if (Integer.parseInt(matName.toString())==5){
                        matName1="撤销";
                    }else if (Integer.parseInt(matName.toString())==7){
                        matName1="售后";
                    }
                }
                cell31.setCellValue(matName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(21);
                Object unit=map.get("wow_remarks");
                String unit1="";
                if (unit!=null&&!unit.equals("")){
                    unit1=unit.toString();
                }
                cell31.setCellValue(unit1);
                cell31.setCellStyle(cellStyle);

//
//                cell31 = row4.createCell(15);
//                Object retailPrice=map.get("retail_price");
//                if (retailPrice!=null&&!retailPrice.equals("")){
//                    cell31.setCellValue(df.format(retailPrice));
//                }else {
//                    cell31.setCellValue("");
//                }
//                cell31.setCellStyle(cellStyle);

//                if (prices!=null&&!prices.equals("")){
//                    cell31.setCellValue(df.format(prices));
//                    cgje+=Double.parseDouble(prices.toString());
//                }else {
//                    cell31.setCellValue("");
//                    cgje+=0.0;
//                }


//                cell31 = row4.createCell(17);
//                if (matNumber!=null&&!matNumber.equals("")){
//                    cell31.setCellValue(df.format(Double.parseDouble(retailPrice.toString())*Double.parseDouble(matNumber.toString())));
//                    cgje+=Double.parseDouble(retailPrice.toString())*Double.parseDouble(matNumber.toString());
//                }else {
//                    cell31.setCellValue("0.00");
//                    cgje+=0.0;
//                }
//                Object retailPrices=map.get("retailPrices");
//                if (retailPrices!=null&&!retailPrices.equals("")){
//                    cell31.setCellValue(df.format(retailPrices));
//                    lsje+=Double.parseDouble(retailPrices.toString());
//                }else {
//                    cell31.setCellValue("");
//                }
//                cell31.setCellStyle(cellStyle);
//
//                cell31 = row4.createCell(21);
//                Object code=map.get("mooCode");
//                String code1="";
//                if (code!=null&&!code.equals("")){
//                    code1=code.toString();
//                }
//                cell31.setCellValue(code1);
//                cell31.setCellStyle(cellStyle);
//
//                cell31 = row4.createCell(22);
//                Object create_date=map.get("createDate2");
//                String create_date1="";
//                if (create_date!=null&&!create_date.equals("")){
//                    create_date1=sdf1.format(create_date);
//                }
//                cell31.setCellValue(create_date1);
//                cell31.setCellStyle(cellStyle);
            }
        }
//        cell1=row1.createCell(18);
////        cell1.setCellValue(startDate);
//        if (startDate!=""&&!startDate.equals("")&&endDate!=""&&!endDate.equals("")){
//            cell1.setCellValue(startDate+"-"+endDate);
//        }else {
//            cell1.setCellValue("");
//        }
//        cell1=row1.createCell(19);
//        cell1.setCellValue("");
//        cell1=row1.createCell(20);
//        sheet.addMergedRegion(new CellRangeAddress(1,1 , 18, 20));
//        cell1.setCellValue("");
//
//        // Double lsje=0d;
//        // Double cgje=0d;
//        String countNum="共计申请"+count+"条,采购金额"+df.format(cgje)+"元,零售金额"+df.format(lsje)+"元";
//        Cell cell2=row2.createCell(0);
//        cell2.setCellValue("汇总:");
//        cell2=row2.createCell(1);
//        cell2.setCellValue(countNum);
//        for (int j = 2; j <=24; j++) {
//            cell2 = row2.createCell(j);
//            cell2.setCellValue("");
//        }
//        sheet.addMergedRegion(new CellRangeAddress(2,2 , 1, 24));

        //标题indexRow
        Row row5=sheet.createRow(indexRow);
        Cell cell5=row5.createCell(0);
        cell5.setCellValue("制表人:");
        cell5=row5.createCell(1);
        cell5.setCellValue(sysUserInfo.getUserName());
        cell5=row5.createCell(2);
        cell5.setCellValue("制表日期:");
        cell5=row5.createCell(3);
        cell5.setCellValue(sdf1.format(new Date()));
//        for (int j = 4; j <=; j++) {
//            cell5 = row5.createCell(j);
//            cell5.setCellValue("");
//        }
        cell5=row5.createCell(9);
        cell5.setCellValue("累计申领数量:");
        cell5=row5.createCell(10);
        cell5.setCellValue(df2.format(sq));
        cell5=row5.createCell(11);
        cell5.setCellValue("累计出库:");
        cell5=row5.createCell(12);
        cell5.setCellValue(df2.format(ck));
        cell5=row5.createCell(13);
        cell5.setCellValue("累计缺少数量:");
        cell5=row5.createCell(14);
        cell5.setCellValue(df2.format(qs));
//        sheet.addMergedRegion(new CellRangeAddress(indexRow,indexRow , 1, 24));

        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName=sysUserInfo.getOrgName()+"出库报表统计";
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

    //导出商品模型报表模板
    public SysRetrunMessage exportmMdMaterielModel(String wmsModelIds) throws Exception{
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
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        SysUserInfo account = this.GetOneSessionAccount();

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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 22 * 256);
        sheet.setColumnWidth(3, 18 * 256);
        sheet.setColumnWidth(4, 15 * 256);
        sheet.setColumnWidth(5, 15 * 256);
        sheet.setColumnWidth(6, 15 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 15 * 256);
        sheet.setColumnWidth(15, 18 * 256);
        sheet.setColumnWidth(16, 18 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(14, 18 * 256);
        sheet.setColumnWidth(20, 20 * 256);
        sheet.setColumnWidth(21, 20 * 256);
        sheet.setColumnWidth(22, 20 * 256);
//        sheet.setColumnWidth(11, 20 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue("商品模型管理商品明细表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 22);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=22; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行数据
        Row row1=sheet.createRow(1);
        //设置行高
        Cell cell1=row1.createCell(0);
//        userType;//用户类型:1:管理员；2：供应商；3集团；4医院；5门诊;6业务员
        String userTypeName="";
        if (account.getUserType()==null){
            userTypeName="管理人员岗位\t\t";
        }else {
            userTypeName="运营人员岗位\t\t";
        }
        String cell1Value="制表岗位:"+userTypeName+"\t\t\t    "+"制作人:"+account.getUserName()+"\t\t\t    "+"制作时间:"+sdf3.format(new Date());
        cell1.setCellValue(cell1Value);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 0, 13));//标题合并单元格操作，11为总列数
        cell1.setCellStyle(cellStyle3);
//        cellStyle.setWrapText(true);
        for (int j = 1; j <=13; j++) {
            cell1 = row1.createCell(j);
            cell1.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell1.setCellValue("");
        }
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        //标题indexRow
        Row row2=sheet.createRow(2);
        //设置行高
//        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 22);

        Row row3=sheet.createRow(3);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("商品模型编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("商品通用名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("商品副标题");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("关联属性");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
        cell3.setCellValue("规格型号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
        cell3.setCellValue("型号检索");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
        cell3.setCellValue("单位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
        cell3.setCellValue("被发布数");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
        cell3.setCellValue("状态");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
        cell3.setCellValue("商品介绍");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
        cell3.setCellValue("创建时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(12);
        cell3.setCellValue("商品关键字");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(13);
        cell3.setCellValue("商品备注");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(14);
        cell3.setCellValue("所属分类");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(15);
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(16);
        cell3.setCellValue("注册证号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(17);
        cell3.setCellValue("注册证有效期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(18);
        cell3.setCellValue("包装方式");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(19);
        cell3.setCellValue("生产企业");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(20);
        cell3.setCellValue("产地");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(21);
        cell3.setCellValue("保质期");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(22);
        cell3.setCellValue("主要成分");
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
        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
        int stateCount1=0;
        int stateCount2=0;
        String orderCodeNext="";
        int count1=0;
        List<Map<String,Object>> mxList =expoetExcelDao.exportmMdMaterielModel(wmsModelIds);
        if (mxList!=null&&mxList.size()>0){
            for (Map<String, Object> map : mxList) {
                index++;
                firstSize++;
                startSzie++;
                row4 = sheet.createRow(indexRow++);
                //序号
                cell31 = row4.createCell(0);
                cell31.setCellValue(index);
                cell31.setCellStyle(cellStyle);
                //商家商品编号
                cell31 = row4.createCell(1);
//                Object orderCode=map.get("model_mat_code");
                Object orderCode1 = map.get("model_mat_code");
                String orderCode="";
                if (orderCode1!=null&&orderCode1!="null"&&!orderCode1.equals("")){
                    orderCode=orderCode1.toString();
                }
                if (orderCode.equals(orderCodeNext)){
                    count1+=1;
                }
                if(!orderCode.equals(orderCodeNext)&&index>=2){
                    int dangQianCount=firstSize;
                    int start =dangQianCount-count1;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 6, 6));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 7, 7));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 8, 8));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 10, 10));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 11, 11));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 12, 12));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 13, 13));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 14, 14));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 15, 15));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 16, 16));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 17, 17));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 18, 18));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 19, 19));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 20, 20));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 21, 21));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 22, 22));
                    count1=0;
                }
                if (index==mxList.size()&&orderCode.equals(orderCodeNext)){
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-count1;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 6, 6));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 7, 7));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 8, 8));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 10, 10));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 11, 11));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 12, 12));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 13, 13));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 14, 14));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 15, 15));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 16, 16));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 17, 17));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 18, 18));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 19, 19));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 20, 20));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 21, 21));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 22, 22));
                }
                cell31.setCellValue(orderCode);
                orderCodeNext=orderCode;
                cell31.setCellStyle(cellStyle);


//                String orderCode1="";
//                if (orderCode!=null&&!orderCode.equals("")){
//                    orderCode1=orderCode.toString();
//                }
//                cell31.setCellValue(orderCode1);
//                cell31.setCellStyle(cellStyle);
                //商家商品编号
                cell31 = row4.createCell(2);
                Object matName=map.get("model_mat_name");
                String matName1="";
                if (matName!=null&&!matName.equals("")){
                    matName1=matName.toString();
                }
                cell31.setCellValue(matName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(3);
                Object brand=map.get("alias_name");
                String brand1="";
                if (brand!=null&&!brand.equals("")){
                    brand1=brand.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle);

                //入库单号relationBillCode
                cell31 = row4.createCell(4);
                Object matCode=map.get("attr_content");
                String matCode1="";
                if (matCode!=null&&!matCode.equals("")){
                    matCode1=matCode.toString();
                }
                cell31.setCellValue(matCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(5);
                Object mmfName=map.get("mmf_name");
                String mmfName1="";
                if (mmfName!=null&&!mmfName.equals("")){
                    mmfName1=mmfName.toString();
                }
                cell31.setCellValue(mmfName1);
                cell31.setCellStyle(cellStyle);


//                cell31 = row4.createCell(6);
//                Object matNumber=map.get("retail_price");
//                if (matNumber!=null&&!matNumber.equals("")){
//                    cell31.setCellValue(df.format(matNumber));
//                }else {
//                    cell31.setCellValue("\t");
//                }
//                cell31.setCellStyle(cellStyle3);
                cell31 = row4.createCell(6);
                Object norm=map.get("can_search");
                String norm1="";
                if (norm!=null&&!norm.equals("")){
                    if (norm.equals("1")||norm=="1"||Integer.parseInt(norm.toString())==1){
                        norm1="需要检索";
                    }else {
                        norm1="不需要检索";
                    }

                }
                cell31.setCellValue(norm1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(7);
                Object mmfCode=map.get("sell_unit");
                String mmfCode1="";
                if (mmfCode!=null&&!mmfCode.equals("")){
                    mmfCode1=mmfCode.toString();
                }
                cell31.setCellValue(mmfCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(8);
                Object number1=map.get("count1");
                if (number1!=null&&!number1.equals("")){
                    cell31.setCellValue(df2.format(number1));
                }else {
                    cell31.setCellValue("0");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(9);
                Object state=map.get("state");
                String state1="";
                if (state!=null&&!state.equals("")){
                    if (state.equals("1")){
                        stateCount1+=1;
                        state1="显示";
                    }else if (state.equals("2")){
                        stateCount2+=1;
                        state1="不显示";
                    }
                }
                cell31.setCellValue(state1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(10);
                Object introduction=map.get("introduction");
                String introduction1=" ";
                if (introduction!=null&&!introduction.equals("")){
                    introduction1=introduction.toString();
                }
                cell31.setCellValue(introduction1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(11);
                Object create_date=map.get("create_date");
                if (create_date!=null&&!create_date.equals("")){
                    cell31.setCellValue(sdf1.format(create_date));
                }else {
                    cell31.setCellValue("\t");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(12);
                Object key_word=map.get("key_word");
                String key_word1=" ";
                if (key_word!=null&&!key_word.equals("")){
                    key_word1=key_word.toString();
                }
                cell31.setCellValue(key_word1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(13);
                Object remark=map.get("remark");
                String remark1=" ";
                if (remark!=null&&!remark.equals("")){
                    remark1=remark.toString();
                }
                cell31.setCellValue(remark1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(14);
                Object mattype=map.get("mattype");
                Object mattype2=map.get("mattype2");
                Object mattype3=map.get("mattype3");

                String mattype1=" ";
                if (mattype!=null&&!mattype.equals("")){
                    mattype1=mattype.toString();
                }
                if (mattype2!=null&&!mattype2.equals("")){
                    mattype1+="/"+mattype2.toString();
                }
                if (mattype3!=null&&!mattype3.equals("")){
                    mattype1+="/"+mattype3.toString();
                }
                cell31.setCellValue(mattype1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(15);
                Object brands=map.get("brand");
                String brands1=" ";
                if (brands!=null&&!brands.equals("")){
                    brands1=brands.toString();
                }
                cell31.setCellValue(brands1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(16);
                Object backPrinting=map.get("back_Printing");
                String backPrinting1=" ";
                if (backPrinting!=null&&!backPrinting.equals("")){
                    backPrinting1=backPrinting.toString();
                }
                cell31.setCellValue(backPrinting1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(17);
                Object BasicUnitAccuracy=map.get("BasicUnitAccuracy");
                String BasicUnitAccuracy1=" ";
                if (BasicUnitAccuracy!=null&&!BasicUnitAccuracy.equals("")){
                    BasicUnitAccuracy1=BasicUnitAccuracy.toString();
                }
                cell31.setCellValue(BasicUnitAccuracy1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(18);
                Object productName=map.get("product_name");
                String productName1=" ";
                if (productName!=null&&!productName.equals("")){
                    productName1=productName.toString();
                }
                cell31.setCellValue(productName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(19);
                Object factory=map.get("factory");
                String factory1=" ";
                if (factory!=null&&!factory.equals("")){
                    factory1=factory.toString();
                }
                cell31.setCellValue(factory1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(20);
                Object origin=map.get("origin");
                String origin1=" ";
                if (origin!=null&&!origin.equals("")){
                    origin1=origin.toString();
                }
                cell31.setCellValue(origin1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(21);
                Object guarantee=map.get("guarantee");
                String guarantee1=" ";
                if (guarantee!=null&&!guarantee.equals("")){
                    guarantee1=guarantee.toString();
                }
                cell31.setCellValue(guarantee1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(22);
                Object components=map.get("components");
                String components1=" ";
                if (components!=null&&!components.equals("")){
                    components1=components.toString();
                }
                cell31.setCellValue(components1);
                cell31.setCellStyle(cellStyle);
            }
        }
        Cell cell2=null;
        cell2=row2.createCell(0);
        String counts="统计：共计"+index+"条数据，其中可显示商品数量"+stateCount1+",不显示商品数量"+stateCount2;
        cell2.setCellValue(counts);
        cell1.setCellStyle(cellStyle3);


        sheet.addMergedRegion(new CellRangeAddress(2,2 , 0, 14));//标题合并单元格操作，11为总列数
        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName="商品模型管理商品明细表";
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
    //导出商品管理商品明细表模板
    public SysRetrunMessage exportmMdMateriel(String wmsMiIds) throws Exception{
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
        SysUserInfo sysUserInfo=this.GetOneSessionAccount();
        SysUserInfo account = this.GetOneSessionAccount();

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

        CellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 居中

        //设置列宽
        sheet.setColumnWidth(0, 8 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 22 * 256);
        sheet.setColumnWidth(3, 10 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 12 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 15 * 256);
        sheet.setColumnWidth(8, 15 * 256);
        sheet.setColumnWidth(15, 18 * 256);
        sheet.setColumnWidth(16, 18 * 256);
        sheet.setColumnWidth(11, 20 * 256);
//        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(20, 20 * 256);
        sheet.setColumnWidth(21, 20 * 256);
        sheet.setColumnWidth(22, 20 * 256);
//        sheet.setColumnWidth(11, 20 * 256);
        //设置列宽
//        sheet.setColumnWidth(0, 8 * 256);
//        sheet.setColumnWidth(1, 18 * 256);
//        sheet.setColumnWidth(2, 22 * 256);
//        sheet.setColumnWidth(3, 18 * 256);
//        sheet.setColumnWidth(4, 15 * 256);
//        sheet.setColumnWidth(5, 12 * 256);
//        sheet.setColumnWidth(6, 20 * 256);
//        sheet.setColumnWidth(7, 15 * 256);
//        sheet.setColumnWidth(8, 13 * 256);
//        sheet.setColumnWidth(9, 10 * 256);
//        sheet.setColumnWidth(10, 10 * 256);
//        sheet.setColumnWidth(11, 10 * 256);
//        sheet.setColumnWidth(12, 10 * 256);
//        sheet.setColumnWidth(13, 10 * 256);
//        sheet.setColumnWidth(14, 12 * 256);
//        sheet.setColumnWidth(15, 10 * 256);
//        sheet.setColumnWidth(16, 10 * 256);
//        sheet.setColumnWidth(17, 12 * 256);
//        sheet.setColumnWidth(18, 20 * 256);
//        sheet.setColumnWidth(19, 15 * 256);
//        sheet.setColumnWidth(20, 12 * 256);
//        sheet.setColumnWidth(21, 12 * 256);
        //标题
        Row row=sheet.createRow(0);
        //设置行高
        Cell cell=row.createCell(0);
        cell.setCellValue("商品管理商品明细表");
        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 14);
        sheet.addMergedRegion(region);//标题合并单元格操作，11为总列数
        cell.setCellStyle(cellStyle2);
        cellStyle.setWrapText(true);
        for (int j = 1; j <=14; j++) {
            cell = row.createCell(j);
            cell.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell.setCellValue("");
        }
        //第一行数据
        Row row1=sheet.createRow(1);
        Cell cell1=row1.createCell(0);
//        userType;//用户类型:1:管理员；2：供应商；3集团；4医院；5门诊;6业务员
        String userTypeName="";
//        switch (account.getUserType()){
//            case 1:
//                userTypeName="管理员";
//                break;
//            case 2:
//                userTypeName="供应商";
//                break;
//            case 3:
//                userTypeName="集团";
//                break;
//            case 4:
//                userTypeName="医院";
//                break;
//            case 5:
//                userTypeName="门诊";
//                break;
//            case 6:
//                userTypeName="业务员";
//                break;
//                default:
//                    userTypeName="管理员";
//                    break;
//        }
        if (account.getUserType()==null){
            userTypeName="管理人员岗位";
        }else {
            userTypeName="运营人员岗位";
        }
//        String usetTypeStr="";
//        if ()
        String cell1Value="制表岗位:"+userTypeName+"   \t\t\t"+"制作人:"+account.getUserName()+"    \t\t\t"+"制作时间:"+sdf3.format(new Date());
        cell1.setCellValue(cell1Value);
        sheet.addMergedRegion(new CellRangeAddress(1,1 , 0, 14));//标题合并单元格操作，11为总列数
        cell1.setCellStyle(cellStyle3);
        for (int j = 1; j <=13; j++) {
            cell1 = row1.createCell(j);
            cell1.setCellStyle(cellStyle3); //style为带边框的样式 上面有定义
            cell1.setCellValue("");
        }
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日");
        //标题indexRow
        Row row2=sheet.createRow(2);
        //设置行高
//        CellRangeAddress region = new CellRangeAddress(0,0 , 0, 22);

        Row row3=sheet.createRow(3);
        //设置行高
//        row3.setHeightInPoints(20);
        Cell cell3=null;
        cell3=row3.createCell(0);
        cell3.setCellValue("序列号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(1);
        cell3.setCellValue("商家商品编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(2);
        cell3.setCellValue("商品名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(3);
        cell3.setCellValue("品牌");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(4);
        cell3.setCellValue("规格型号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(5);
        cell3.setCellValue("型号编码");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(6);
        cell3.setCellValue("销售价格");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(7);
        cell3.setCellValue("单位");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(8);
        cell3.setCellValue("销量");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(9);
        cell3.setCellValue("商品状态");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(10);
        cell3.setCellValue("商家名称");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(11);
        cell3.setCellValue("创建时间");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(12);
        cell3.setCellValue("商品模型编号");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(13);
        cell3.setCellValue("违规原因");
        cell3.setCellStyle(cellStyle);
        cell3=row3.createCell(14);
        cell3.setCellValue("所属分类");
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
        String startDate="";
        String endDate="";
        Cell cell31=null;
        String moodeNext="";
        int count1=0;
        int Cell1firstSize=2;
        Integer onState=0;
        Integer lowerState=0;
        Integer weiGUi=0;
        String orderCodeNext="";
        List<Map<String,Object>> mxList =expoetExcelDao.exportmMdMateriel(wmsMiIds);
        if (mxList!=null&&mxList.size()>0){
            for (Map<String, Object> map : mxList) {
                index++;
                firstSize++;
                startSzie++;
                row4 = sheet.createRow(indexRow++);
                //序号
                cell31 = row4.createCell(0);
                cell31.setCellValue(index);
                cell31.setCellStyle(cellStyle);
                //商家商品编号
                cell31 = row4.createCell(1);
                Object orderCode1 = map.get("matCode");//
                String orderCode="";
                String orderCode2="";
                if (orderCode1!=null&&orderCode1!="null"&&!orderCode1.equals("")){
                    orderCode=orderCode1.toString();
                    orderCode2=orderCode1.toString();
                }else {
                    Object matName=map.get("matName");
                    orderCode=orderCode1.toString()+matName.toString();
                }
                if (orderCode.equals(orderCodeNext)){
                    count1+=1;
                }
                if(!orderCode.equals(orderCodeNext)&&index>=2){
                    int dangQianCount=firstSize;
                    int start =dangQianCount-count1;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 8, 8));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 9, 9));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 10, 10));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 11, 11));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 12, 12));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 13, 13));
                    count1=0;
                }
                if (index==mxList.size()&&orderCode.equals(orderCodeNext)){
                    int dangQianCount=indexRow-1;
                    int  start=dangQianCount-count1;
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 1, 1));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 2, 2));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 3, 3));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 8, 8));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 9, 9));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 10, 10));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 11, 11));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 12, 12));
                    sheet.addMergedRegion(new CellRangeAddress(start,dangQianCount, 13, 13));
                }
                cell31.setCellValue(orderCode2);
                orderCodeNext=orderCode;
                cell31.setCellStyle(cellStyle);
                //商家商品编号
                cell31 = row4.createCell(2);
                Object matName=map.get("matName");
                String matName1="";
                if (matName!=null&&!matName.equals("")){
                    matName1=matName.toString();
                }
                cell31.setCellValue(matName1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(3);
                Object brand=map.get("brand");
                String brand1="";
                if (brand!=null&&!brand.equals("")){
                    brand1=brand.toString();
                }
                cell31.setCellValue(brand1);
                cell31.setCellStyle(cellStyle);

                //入库单号relationBillCode
                cell31 = row4.createCell(4);
                Object matCode=map.get("mmf_code");
                String matCode1="";
                if (matCode!=null&&!matCode.equals("")){
                    matCode1=matCode.toString();
                }
                cell31.setCellValue(matCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(5);
                Object mmfName=map.get("mmf_name");
                String mmfName1="";
                if (mmfName!=null&&!mmfName.equals("")){
                    mmfName1=mmfName.toString();
                }
                cell31.setCellValue(mmfName1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(6);
                Object matNumber=map.get("retail_price");
                if (matNumber!=null&&!matNumber.equals("")){
                    cell31.setCellValue(df.format(matNumber));
                }else {
                    cell31.setCellValue("\t");
                }
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(7);
                Object norm=map.get("Basic_unit");
                String norm1="";
                if (norm!=null&&!norm.equals("")){
                    norm1=norm.toString();
                }
                cell31.setCellValue(norm1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(8);
                Object number1=map.get("number1");
                if (number1!=null&&!number1.equals("")){
                    cell31.setCellValue(df2.format(number1));
                }else {
                    cell31.setCellValue("0");
                }
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(9);
                Object state=map.get("state");
                String state1="";
                if (state!=null&&!state.equals("")){
                    if (state.equals("1")){
                        state1="上架";
                        onState+=1;
                    }else if (state.equals("2")){
                        state1="下架";
                        lowerState+=1;
                    }else if (state.equals("3")){
                        state1="违规下架";
                        weiGUi+=1;
                    }else {
                        state1="下架";
                        lowerState+=1;
                    }
                }else {
                    state1="下架";
                    lowerState+=1;
                }
                cell31.setCellValue(state1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(10);
                Object mmfCode=map.get("applicant_Name");
                String mmfCode1="";
                if (mmfCode!=null&&!mmfCode.equals("")){
                    mmfCode1=mmfCode.toString();
                }
                cell31.setCellValue(mmfCode1);
                cell31.setCellStyle(cellStyle);

                cell31 = row4.createCell(11);
                Object create_date=map.get("create_date");
                if (create_date!=null&&!create_date.equals("")){
                    cell31.setCellValue(sdf1.format(create_date));
                }else {
                    cell31.setCellValue("\t");
                }
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(12);
                Object modelMatcode=map.get("model_mat_code");
                String modelMatcode1=" ";
                if (modelMatcode!=null&&!modelMatcode.equals("")){
                    modelMatcode1=modelMatcode.toString();
                }
                cell31.setCellValue(modelMatcode1);
                cell31.setCellStyle(cellStyle);


                cell31 = row4.createCell(13);
                Object matRemovalReasons=map.get("mat_removal_reasons");
                String matRemovalReasons1=" ";
                if (matRemovalReasons!=null&&!matRemovalReasons.equals("")){
                    matRemovalReasons1=matRemovalReasons.toString();
                }
                cell31.setCellValue(matRemovalReasons1);
                cell31.setCellStyle(cellStyle);
                cell31 = row4.createCell(14);
                Object matType=map.get("matType");
                String matType1=" ";
                if (matType!=null&&!matType.equals("")){
                    matType1=matType.toString();
                }
                cell31.setCellValue(matType1);
                cell31.setCellStyle(cellStyle);
            }
        }
        Cell cell2=null;
        cell2=row2.createCell(0);
        String counts="统计：共计"+index+"条数据，其中上架商品数量"+onState+"下架商品数量"+lowerState+"违规下架商品数量"+weiGUi;
        cell2.setCellValue(counts);
        cell1.setCellStyle(cellStyle3);
        sheet.addMergedRegion(new CellRangeAddress(2,2 , 0, 14));//标题合并单元格操作，11为总列数

        String tmpPath = SystemContext.updown_File_path + TEMP_EXPORT_PATH;//获得工程运行web的目录
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        Date newDate1=new Date();
        String fileName="商品管理商品明细表";
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

package com.hsk.dentistmall.api.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author Hongten
 * @created 2014-5-20
 */
public class ReadExcel {
    
    /**
     * read the Excel file
     * @param path the path of the Excel file
     * @return
     * @throws IOException
     */
    public List<List<String>> readExcel(String path) throws IOException {
        if (path == null || "".equals(path.trim())) {
            return null;
        } else {
            String postfix = path.substring(path.lastIndexOf(".") + 1, path.length());
            if (!"".equals(postfix)) {
                if ("xls".equals(postfix)) {
                    return readXls(path);
                } else if ("xlsx".equals(postfix)) {
                    return readXlsx(path);
                }
            } 
        }
        return null;
    }
    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public List<List<String>> readXlsx(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<List<String>> reList = new ArrayList<List<String>>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                	List<String> list = new ArrayList<String>();
                	for (int j = 0; j < xssfRow.getLastCellNum(); j++){
                		if(xssfRow.getCell(j) != null)
                			list.add(getValue(xssfRow.getCell(j)));
                		else
                			list.add("");
                	}
                	reList.add(list);
                }
            }
        }
        return reList;
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public List<List<String>> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List<String>> reList = new ArrayList<List<String>>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                	List<String> list = new ArrayList<String>();
                	for (int j = 0; j < hssfRow.getLastCellNum(); j++){
                		if(hssfRow.getCell(j) != null)
                			list.add(getValue(hssfRow.getCell(j)));
                		else
                			list.add("");
                	}
                	reList.add(list);
                    
                }
            }
        }
        return reList;
    }

    private String getValue(XSSFCell xssfRow) {
    	DecimalFormat df = new DecimalFormat("#");
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
        	if (HSSFDateUtil.isCellDateFormatted(xssfRow)) {// 处理日期格式、时间格式  
                Date date = xssfRow.getDateCellValue();  
                return formater.format(date);  
            }
            return String.valueOf(df.format(xssfRow.getNumericCellValue())).replace(".0", "");
        }else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_STRING) {
        	 return String.valueOf(xssfRow.getStringCellValue());
		} 
        return "";
    }

    private String getValue(HSSFCell hssfCell) {
    	//DecimalFormat df = new DecimalFormat("#");
    	SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
        	if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {// 处理日期格式、时间格式  
                Date date = hssfCell.getDateCellValue();  
                return formater.format(date);  
            }
            return String.valueOf(hssfCell.getNumericCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_STRING) {
        	return String.valueOf(hssfCell.getStringCellValue());
		}
        return "";
    }
    
    public static void main(String[] args){
    	ReadExcel re= new ReadExcel();
    	try {
			List<List<String>> list = re.readExcel("d:\\aa.xls");
			for(List<String> row : list){
				String oneName=row.get(0);
				String twoName=row.get(1);
				String threeNames= row.get(2);
				threeNames=threeNames.substring(1, threeNames.length());
				String[] threeNameArray = threeNames.split(",");
				for(String threeName :threeNameArray)
					System.out.println("INSERT INTO test1(one_name,two_name,three_name) VALUES('"+oneName+"','"+twoName+"','"+threeName+"');");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
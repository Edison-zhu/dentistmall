package com.hsk.miniapi.xframe.api.utils.freeMarker;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;


/**
 * 
 * 需要用到的jar包 poi-3.8.jar,commons-codec-1.6.jar
 * 
 * 
 * @author jincc
 * 
 * @version v1.1
 * 
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 * 
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 * 
 *            byte[]表jpg格式的图片数据
 */

public class ExportExcel<T> {
	
	private SimpleDateFormat sdf;
	private Date date;
	private Calendar cal;
	
	/**
	 * 用来存储类中定义的boolea类型的属性和属性对应的显示名称
	 * key  是类中属性+对应的true/false  例如： sextrue 表示性别为true(1) , sexfalse表示false(0)
	 * value 是需要显示的名字
	 */
	Map<String, String> booleanPrse;
	
	public Map<String, String> getBooleanPrse() {
		return booleanPrse;
	}

	public void setBooleanPrse(Map<String, String> booleanPrse) {
		this.booleanPrse = booleanPrse;
	}

	//默认从第一个属性读取
	int startAttr=0;
	
	public int getStartAttr() {
		return startAttr;
	}

	public void setStartAttr(int startAttr) {
		this.startAttr = startAttr;
	}

	public void exportExcel(Collection<T> dataset, OutputStream out) {

		exportExcel("测试POI导出EXCEL文档", null, dataset, out, "yyyy-MM-dd");

	}

	public void exportExcel(String[] headers, Collection<T> dataset,

	OutputStream out) {

		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, "yyyy-MM-dd");

	}

	public void exportExcel(String[] headers, Collection<T> dataset,

	OutputStream out, String pattern) {

		exportExcel("测试POI导出EXCEL文档", headers, dataset, out, pattern);

	}
	

	/**
	 * 
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 * 
	 *            表格标题名
	 * 
	 * @param headers
	 * 
	 *            表格属性列名数组
	 * 
	 * @param dataset
	 * 
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	 * 
	 * @param out
	 * 
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * 
	 * @param pattern
	 * 
	 *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 */

	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers,

	Collection<T> dataset, OutputStream out, String pattern) {
		
		sdf = new SimpleDateFormat(pattern);
		
		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet(title);

		// 设置表格默认列宽度为15个字节

		sheet.setDefaultColumnWidth(15);

		// 生成一个样式

		HSSFCellStyle style = workbook.createCellStyle();

		// 设置这些样式

		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);

		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成一个字体

		HSSFFont font = workbook.createFont();

		font.setColor(HSSFColor.VIOLET.index);

		font.setFontHeightInPoints((short) 12);

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// 把字体应用到当前的样式

		style.setFont(font);

		// 生成并设置另一个样式

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);

		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 生成另一个字体

		HSSFFont font2 = workbook.createFont();

		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式

		style2.setFont(font2);

		// 声明一个画图的顶级管理器

		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	

		HSSFFont font3 = workbook.createFont();

		font3.setColor(HSSFColor.BLUE.index);
		
		// 定义注释的大小和位置,详见文档

		//HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));

		// 设置注释内容

//		comment.setString(new HSSFRichTextString("可以添加注释！"));

		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.

		//comment.setAuthor("jincc");

		// 产生表格标题行
		CellRangeAddress region = new CellRangeAddress(0, // first row
				0, // last row
		        0, // first column
		        headers.length-1 // last column
		);
		sheet.addMergedRegion(region);
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(style);
		HSSFRichTextString titleText = new HSSFRichTextString(title);
		titleCell.setCellValue(titleText);
		
		HSSFRow row = sheet.createRow(1);
		
		for (int i = 0; i < headers.length; i++) {

			HSSFCell cell = row.createCell(i);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(headers[i]);

			cell.setCellValue(text);

		}

		// 遍历集合数据，产生数据行

		Iterator<T> it = dataset.iterator();

		int index = 1;

		while (it.hasNext()) {

			index++;

			row = sheet.createRow(index);

			T t = (T) it.next();

			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值

			Field[] fields = t.getClass().getDeclaredFields();
			for (int i=startAttr; i < fields.length; i++) {

				HSSFCell cell = row.createCell(i-startAttr);
				cell.setCellStyle(style2);

				Field field = fields[i];

				String fieldName = field.getName();

				String getMethodName = "get"

				+ fieldName.substring(0, 1).toUpperCase()

				+ fieldName.substring(1);

				try {

					Class tCls = t.getClass();

					Method getMethod = tCls.getMethod(getMethodName,

					new Class[] {});

					Object value = getMethod.invoke(t, new Object[] {});

					// 判断值的类型后进行强制类型转换

					String textValue = null;

					// if (value instanceof Integer) {

					// int intValue = (Integer) value;

					// cell.setCellValue(intValue);

					// } else if (value instanceof Float) {

					// float fValue = (Float) value;

					// textValue = new HSSFRichTextString(

					// String.valueOf(fValue));

					// cell.setCellValue(textValue);

					// } else if (value instanceof Double) {

					// double dValue = (Double) value;

					// textValue = new HSSFRichTextString(

					// String.valueOf(dValue));

					// cell.setCellValue(textValue);

					// } else if (value instanceof Long) {

					// long longValue = (Long) value;

					// cell.setCellValue(longValue);

					// }

					if (value instanceof Boolean) {

						//boolean bValue = (Boolean) value;
						
						if(null == booleanPrse || booleanPrse.isEmpty()) {
							
							throw new RuntimeException("请设置boolean类型对应的显示名称");
						
						}
						
						if(booleanPrse.containsKey(field.getName()+"true")){
							
							textValue = booleanPrse.get(field.getName()+"true");
							
						} else if(booleanPrse.containsKey(field.getName()+"false")) {
							
							textValue = booleanPrse.get(field.getName()+"false");
							
						} else {
							//throw new RuntimeException("没有找到boolean类型,属性名: "+field.getName()+ " 对应设置的值");
							textValue = "";//当此boolean类型的属性没有正确设置显示的map那么默认显示空串
						}
						
						/*textValue = "男";

						if (!bValue) {

							textValue = "女";

						}*/

					} else if (value instanceof Date) {

						date = (Date) value;

//						sdf = new SimpleDateFormat(pattern);

						textValue = sdf.format(date);

					} else if (value instanceof Calendar) {
						
						cal = (Calendar)value;
						
						textValue = sdf.format(cal.getTime());
						
					} else if (value instanceof byte[]) {

						// 有图片时，设置行高为60px;

						row.setHeightInPoints(60);

						// 设置图片所在列宽度为80px,注意这里单位的一个换算

						sheet.setColumnWidth(i, (35 * 80));

						// sheet.autoSizeColumn(i);

						byte[] bsValue = (byte[]) value;

						/**
						 * public HSSFClientAnchor(int dx1,int dy1,int dx2,int dy2,short col1,int row1,short col2, int row2);
						 * dx1此单元格开始x坐标(默认0)
						 * dy1此单元格开始y坐标(默认0)
						 * dx1，dy1都是0默认顶当前单元格交叉点开始处
						 * dx2:此单元格的结束X坐标
						 * dy2:此单元格的结束y坐标 
						 * col1 图片的左上角放在第几个列cell
						 * row1 图片的左上角放在第几个行cell
						 * col2 图片的右下角放在第几个列cell
						 * row2 图片的右下角放在第几个行cell
						 * 
						 * 为了动态指定列，根据当前循环到第几个数据的循环位置来定
						 * row1,row2 ,col1,col2可以进行合并行或列，最好不要更改，此处封装的默认是一个单元格
						 * 
						 * dy2 must be between 0 and 255
						 * dx2 must be between 0 and 1023
						 */
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,

						1023, 255, (short) (i-startAttr), index, (short) (i-startAttr), index);

						anchor.setAnchorType(2);

						patriarch.createPicture(anchor, workbook.addPicture(

						bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

					} else {

						// 其它数据类型都当作字符串简单处理
						if(null == value){
							textValue = "";
						} else {
							textValue = value.toString();
						}

					}

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

					if (textValue != null) {

						Pattern p = Pattern.compile("^//d+(//.//d+)?$");

						Matcher matcher = p.matcher(textValue);

						if (matcher.matches()) {

							// 是数字当作double处理

							cell.setCellValue(Double.parseDouble(textValue));

						} else {
							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);
							richString.applyFont(font3);
							cell.setCellValue(richString);

						}

					}

				} catch (SecurityException e) {
					e.printStackTrace();

				} catch (NoSuchMethodException e) {
					e.printStackTrace();

				} catch (IllegalArgumentException e) {
					e.printStackTrace();

				} catch (IllegalAccessException e) {
					e.printStackTrace();

				} catch (InvocationTargetException e) {
					e.printStackTrace();

				} finally {

					

				}

			}

		}

		try {

			workbook.write(out);

		} catch (IOException e) {
			e.printStackTrace();

		}

	}
	
	@SuppressWarnings("unchecked")
	public void exportExcel(String title, String[] headers,String[] columns,

	Collection<T> dataset, OutputStream out, String pattern) {
		
		sdf = new SimpleDateFormat(pattern);
		
		// 声明一个工作薄

		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet(title);

		// 设置表格默认列宽度为15个字节

		sheet.setDefaultColumnWidth(15);

		// 生成一个样式

		HSSFCellStyle style = workbook.createCellStyle();

		// 设置这些样式

		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);

		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成一个字体

		HSSFFont font = workbook.createFont();

		font.setColor(HSSFColor.VIOLET.index);

		font.setFontHeightInPoints((short) 12);

		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// 把字体应用到当前的样式

		style.setFont(font);

		// 生成并设置另一个样式

		HSSFCellStyle style2 = workbook.createCellStyle();

		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);

		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);

		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);

		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);

		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		// 生成另一个字体

		HSSFFont font2 = workbook.createFont();

		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		// 把字体应用到当前的样式

		style2.setFont(font2);

		// 声明一个画图的顶级管理器

		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// 定义注释的大小和位置,详见文档

		//HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));

		// 设置注释内容

//		comment.setString(new HSSFRichTextString("可以添加注释！"));

		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.

		//comment.setAuthor("jincc");

		// 产生表格标题行
		CellRangeAddress region = new CellRangeAddress(0, // first row
			0, // last row
			0, // first column
			headers.length-1 // last column
		);
		sheet.addMergedRegion(region);
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell titleCell = titleRow.createCell(0);
		HSSFRichTextString titleText = new HSSFRichTextString(title);
		titleCell.setCellValue(titleText);
		HSSFRow row = sheet.createRow(1);
		for (int i = 0; i < headers.length; i++) {

			HSSFCell cell = row.createCell(i);

			cell.setCellStyle(style);

			HSSFRichTextString text = new HSSFRichTextString(headers[i]);

			cell.setCellValue(text);

		}

		// 遍历集合数据，产生数据行

		Iterator<T> it = dataset.iterator();

		int index =1;

		while (it.hasNext()) {

			index++;

			row = sheet.createRow(index);

			T t = (T) it.next();

			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值

			Field[] fields = t.getClass().getDeclaredFields();
			for (int i=startAttr; i < columns.length; i++) {

				HSSFCell cell = row.createCell(i-startAttr);

				cell.setCellStyle(style2);

				//Field field = fields[i];

				String fieldName = columns[i];

				String getMethodName = "get"

				+ fieldName.substring(0, 1).toUpperCase()

				+ fieldName.substring(1);

				try {

					Class tCls = t.getClass();

					Method getMethod = tCls.getMethod(getMethodName,

					new Class[] {});

					Object value = getMethod.invoke(t, new Object[] {});

					// 判断值的类型后进行强制类型转换

					String textValue = null;

					// if (value instanceof Integer) {

					// int intValue = (Integer) value;

					// cell.setCellValue(intValue);

					// } else if (value instanceof Float) {

					// float fValue = (Float) value;

					// textValue = new HSSFRichTextString(

					// String.valueOf(fValue));

					// cell.setCellValue(textValue);

					// } else if (value instanceof Double) {

					// double dValue = (Double) value;

					// textValue = new HSSFRichTextString(

					// String.valueOf(dValue));

					// cell.setCellValue(textValue);

					// } else if (value instanceof Long) {

					// long longValue = (Long) value;

					// cell.setCellValue(longValue);

					// }

					if (value instanceof Boolean) {

						//boolean bValue = (Boolean) value;
						
						if(null == booleanPrse || booleanPrse.isEmpty()) {
							
							throw new RuntimeException("请设置boolean类型对应的显示名称");
						
						}
						
						if(booleanPrse.containsKey(fieldName+"true")){
							
							textValue = booleanPrse.get(fieldName+"true");
							
						} else if(booleanPrse.containsKey(fieldName+"false")) {
							
							textValue = booleanPrse.get(fieldName+"false");
							
						} else {
							//throw new RuntimeException("没有找到boolean类型,属性名: "+field.getName()+ " 对应设置的值");
							textValue = "";//当此boolean类型的属性没有正确设置显示的map那么默认显示空串
						}
						
						/*textValue = "男";

						if (!bValue) {

							textValue = "女";

						}*/

					} else if (value instanceof Date) {

						date = (Date) value;

//						sdf = new SimpleDateFormat(pattern);

						textValue = sdf.format(date);

					} else if (value instanceof Calendar) {
						
						cal = (Calendar)value;
						
						textValue = sdf.format(cal.getTime());
						
					} else if (value instanceof byte[]) {

						// 有图片时，设置行高为60px;

						row.setHeightInPoints(60);

						// 设置图片所在列宽度为80px,注意这里单位的一个换算

						sheet.setColumnWidth(i, (35 * 80));

						// sheet.autoSizeColumn(i);

						byte[] bsValue = (byte[]) value;

						/**
						 * public HSSFClientAnchor(int dx1,int dy1,int dx2,int dy2,short col1,int row1,short col2, int row2);
						 * dx1此单元格开始x坐标(默认0)
						 * dy1此单元格开始y坐标(默认0)
						 * dx1，dy1都是0默认顶当前单元格交叉点开始处
						 * dx2:此单元格的结束X坐标
						 * dy2:此单元格的结束y坐标 
						 * col1 图片的左上角放在第几个列cell
						 * row1 图片的左上角放在第几个行cell
						 * col2 图片的右下角放在第几个列cell
						 * row2 图片的右下角放在第几个行cell
						 * 
						 * 为了动态指定列，根据当前循环到第几个数据的循环位置来定
						 * row1,row2 ,col1,col2可以进行合并行或列，最好不要更改，此处封装的默认是一个单元格
						 * 
						 * dy2 must be between 0 and 255
						 * dx2 must be between 0 and 1023
						 */
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,

						1023, 255, (short) (i-startAttr), index, (short) (i-startAttr), index);

						anchor.setAnchorType(2);

						patriarch.createPicture(anchor, workbook.addPicture(

						bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

					} else {

						// 其它数据类型都当作字符串简单处理
						if(null == value){
							textValue = "";
						} else {
							textValue = value.toString();
						}

					}

					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成

					if (textValue != null) {

						Pattern p = Pattern.compile("^//d+(//.//d+)?$");

						Matcher matcher = p.matcher(textValue);

						if (matcher.matches()) {

							// 是数字当作double处理

							cell.setCellValue(Double.parseDouble(textValue));

						} else {

							HSSFRichTextString richString = new HSSFRichTextString(
									textValue);

							HSSFFont font3 = workbook.createFont();

							font3.setColor(HSSFColor.BLUE.index);

							richString.applyFont(font3);

							cell.setCellValue(richString);

						}

					}

				} catch (SecurityException e) {
					e.printStackTrace();

				} catch (NoSuchMethodException e) {
					e.printStackTrace();

				} catch (IllegalArgumentException e) {
					e.printStackTrace();

				} catch (IllegalAccessException e) {
					e.printStackTrace();

				} catch (InvocationTargetException e) {
					e.printStackTrace();

				} finally {

					

				}

			}

		}

		try {

			workbook.write(out);

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	public static void main(String[] args) {
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("sextrue", "男");//true时显示的
		map.put("sexfalse", "女");//false时显示的
		// 测试学生

		ExportExcel<Student> ex = new ExportExcel<Student>();

		String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };

		List<Student> dataset = new ArrayList<Student>();

		dataset.add(new Student(10000001, "张三", 20, true, new Date()));

		dataset.add(new Student(20000002, "李四", 24, false, new Date()));

		dataset.add(new Student(30000003, "王五", 22, true, new Date()));
		ex.setBooleanPrse(map);
		// 测试图书

		ExportExcel<Book> ex2 = new ExportExcel<Book>();
		ex2.setBooleanPrse(map);
		String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN",

		"图书出版社", "封面图片" };

		List<Book> dataset2 = new ArrayList<Book>();

		try {

			BufferedInputStream bis = new BufferedInputStream(

			new FileInputStream("d://a.jpg"));

			byte[] buf = new byte[bis.available()];

			while ((bis.read(buf)) != -1) {

				//

			}

			dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",

			"清华出版社", buf));

			dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",

			"阳光出版社", buf));

			dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",

			"清华出版社", buf));

			dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",

			"清华出版社", buf));

			dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",

			"汤春秀出版社", buf));
			Book bk = new Book();
			bk.setName("测试");
			bk.setBookId(13);
			bk.setPrice(123f);
			bk.setPreface(buf);
			
			dataset2.add(bk);

			OutputStream out = new FileOutputStream("E://a.xls");

			OutputStream out2 = new FileOutputStream("E://b.xls");

			ex.exportExcel(headers, dataset, out);

			ex2.exportExcel(headers2, dataset2, out2);

			out.close();

			JOptionPane.showMessageDialog(null, "导出成功!");

			System.out.println("excel导出成功！");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
*/
	}

}

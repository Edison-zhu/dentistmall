package com.hsk.xframe.api.utils.freeMarker;

import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

 
/**
 * @date 20150524;
 * @author renxin
 *
 */
public class ImageBase64Util {
	
/**
 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
 * @param imgFile 待处理的图片
 * @return 返回Base64编码过的字节数组字符串
 */
public static String GetImageStr(String imgFile) {
	InputStream in = null;
	byte[] data = null;
	//读取图片字节数组
	try {
		in = new FileInputStream(imgFile);
		data = new byte[in.available()];
		in.read(data);
		in.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	//对字节数组Base64编码
//	BASE64Encoder encoder = new BASE64Encoder();
	//返回Base64编码过的字节数组字符串
//	return encoder.encode(data);
	return null;
}


/**
 * 对字节数组字符串进行Base64解码并生成图片
 * @param imgStr Base64加码的字符串
 * @param deskSrc 图片要保存的路径
 * @return
 */
public static String GenerateImage(String imgStr,String fileName,String deskSrc) {
	if (imgStr == null) //图像数据为空
		return null;
	BASE64Decoder decoder = new BASE64Decoder();
	try {
		imgStr= imgStr.replace("data:image/png;base64,","").trim();
		//Base64解码
		byte[] b =  decoder.decodeBuffer(imgStr);
//		byte[]b =null;
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {//调整异常数据
				b[i] += 256;
			}
		}
		//生成png图片
		String imgFilePath =deskSrc+fileName+".png";//新生成的图片
		OutputStream out = new FileOutputStream(imgFilePath);
		out.write(b);
		out.flush();
		out.close();
		return imgFilePath;
	} catch (Exception e) {
		return null;
	}
}
}

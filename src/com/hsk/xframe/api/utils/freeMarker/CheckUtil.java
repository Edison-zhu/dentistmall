package com.hsk.xframe.api.utils.freeMarker;

public class CheckUtil {
	
	public static boolean isNumeric(String str) {
		Boolean strResult = str.matches("[0-9]+.*[0-9]*");  
		return strResult;
		
	}

}

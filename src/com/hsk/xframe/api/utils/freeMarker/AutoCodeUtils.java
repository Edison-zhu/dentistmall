package com.hsk.xframe.api.utils.freeMarker;

import java.io.File;

public class AutoCodeUtils {
	
	
	public static void deleteFile(File file){
		       if(file.isDirectory()){
		            File[] files = file.listFiles();
	           for(int i=0; i<files.length; i++){
	                deleteFile(files[i]);
		            }
		       }
		       file.delete();
	  }
	
	/**
	 * 默认连接字符
	 */
	private static final char SEPARATOR = '_';

	/**
	 * 将驼峰命名法生成的字符转换为采用"_"进行连接的字符串
	 * 
	 * @param InputStr
	 *            输入字符串
	 * @return 返回采用"_"进行连接的字符串
	 */
	public static String toUnderScoreCase(String InputStr) {
		if (InputStr == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < InputStr.length(); i++) {
			char c = InputStr.charAt(i);
			boolean nextUpperCase = true;
			if (i < (InputStr.length() - 1)) {
				nextUpperCase = Character.isUpperCase(InputStr.charAt(i + 1));
			}
			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}
		return sb.toString();
	}

	/**
	 * 将输入字符串格式化为驼峰命名法的字符串,并将首字符大写
	 * 
	 * @param InputStr
	 *            输入 字符串
	 * @return 返回驼峰命名法的字符串（首字符大写）
	 */
	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 将输入字符串格式化为驼峰命名法的字符串
	 * 
	 * @param InputStr
	 *            输入 字符串
	 * @return 返回驼峰命名法的字符串
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}

		s = s.toLowerCase();

		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String FilePathToPackage(String FilePath) {
		String ls_path = null;
		if (FilePath.indexOf("//") >= 0) {
			ls_path = FilePath.replaceAll("//", ".");
		}
		if (FilePath.indexOf("/") >= 0) {
			ls_path = FilePath.replaceAll("/", ".");
		}
		if (FilePath.indexOf("\\") >= 0) {
			ls_path = FilePath.replaceAll("\\", ".");
		}

		if (ls_path != null && ls_path.indexOf("src.") >= 0) {
			ls_path = ls_path.replaceAll("src.", "");
			String tt = ls_path.substring(ls_path.length() - 1, ls_path
					.length());
			if (".".equals(tt)) {
				ls_path = ls_path.substring(0, ls_path.length() - 1);
			}
		}
		return ls_path;
	}

	public static String PackageToFilePath(String Package) {
		String ls_path = null;
		if (Package.indexOf(".") >= 0) {
			ls_path = Package.replaceAll(".", "/");
		}
		if (ls_path.indexOf("src/") >= 0) {
			ls_path = "src/" + ls_path;
		}
		return ls_path;
	}


}

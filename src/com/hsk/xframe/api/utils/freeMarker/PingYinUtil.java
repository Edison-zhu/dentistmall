package com.hsk.xframe.api.utils.freeMarker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PingYinUtil {
	public static String getLetterFormChinese(char word) throws BadHanyuPinyinOutputFormatCombination{
		String reg = "[a-zA-Z0-9]";
		String checkStr = word+"";
		if(checkStr.matches(reg))
			return checkStr.toUpperCase();
		else{
			if(isChineseChar(checkStr)){
				HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
				format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
				format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
				String[] str = PinyinHelper.toHanyuPinyinStringArray(word, format);
				return str[0].substring(0, 1);
			}else
				return "";
			
		}
		
	}
	
	public static boolean isChineseChar(String str){
        boolean temp = false;
        Pattern p=Pattern.compile("[\u4e00-\u9fa5]"); 
        Matcher m=p.matcher(str); 
        if(m.find()){ 
            temp =  true;
        }
        return temp;
    }
}

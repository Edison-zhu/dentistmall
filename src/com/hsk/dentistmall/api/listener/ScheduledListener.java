package com.hsk.dentistmall.api.listener;

import org.springframework.stereotype.Component;

@Component
public class ScheduledListener {
	
	//备份数据库
	public void backDb() throws Exception{
		Runtime rt = Runtime.getRuntime();
		String path = ""; 
		rt.exec("cmd /c "+path+"\\mysqldump -hlocalhost -uroot -p123456 --opt databaseName>d:\\bk.sql"); 
	}

}

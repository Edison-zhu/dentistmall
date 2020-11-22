package com.hsk.xframe.api.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.xframe.api.daobbase.ISysUserInfoDao;
import com.hsk.xframe.api.persistence.SysUserInfo;
import com.hsk.xframe.api.service.Iuser;

@Service
public class userImpl extends DSTService implements Iuser {
	@Autowired
	private ISysUserInfoDao sysUserInfoDao;// 看到妙处没
	
 

//	@Transactional(rollbackFor = Exception.class)
	public void savetest() throws Exception {

//		SysUserInfo user01 = new SysUserInfo();
//		user01.setUserCode("123456789012345");
//		userDao.save(user01);
//		System.out.println("user1 success");
//		SysUserInfo user02 = new SysUserInfo();
//		user02.setUserCode("123456789012345678901234567890123");
//		userDao.save(user02);
//		System.out.println("user2 success");
	}

	public void saveBD() throws Exception {
		SysUserInfo user01 = new SysUserInfo();
		user01.setUserCode("123456789012345");
		sysUserInfoDao.saveUser(user01);
		System.out.println("user1 success");
		SysUserInfo user02 = new SysUserInfo();
		user02.setUserCode("123456789012345678901234567890123");
		sysUserInfoDao.saveUser(user02);
		System.out.println("user2 success");
	}
}
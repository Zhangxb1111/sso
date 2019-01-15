package com.wlgzs.org;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wlgzs.website.OrgApplication;
import org.wlgzs.website.controller.user.UserController;
import org.wlgzs.website.entity.TbUser;
import org.wlgzs.website.util.AesUtil;
import org.wlgzs.website.util.Result.Result;

//@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrgApplication.class)
public class OrgApplicationTests {

	private final Logger log = LoggerFactory.getLogger(AesUtil.class);
	@Autowired
	private UserController userController;

	@Test
	public void insert(){
		log.info("start test");
		TbUser tbUser = new TbUser(
			"zhangxubin","张绪宾","1234","zxb1234","男","18738357910","大二","计科163",1,"JavaWeb","","","",""
		);
		Result result = userController.insertUser(tbUser);
		log.info(result.toString());
		log.info(tbUser.toString());
	}



}

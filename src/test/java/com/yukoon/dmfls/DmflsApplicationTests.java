package com.yukoon.dmfls;

import com.yukoon.dmfls.Entities.Securities;
import com.yukoon.dmfls.Services.SCServices;
import com.yukoon.dmfls.repositories.SecurityRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DmflsApplicationTests {
	@Autowired
	private SCServices scServices;
	@Autowired
	private SecurityRepo securityRepo;

	@Test
	void contextLoads() {
		Securities securities = new Securities();
		securities.setOrder(2);
		System.out.println(securityRepo.findAll().size());
	}

}

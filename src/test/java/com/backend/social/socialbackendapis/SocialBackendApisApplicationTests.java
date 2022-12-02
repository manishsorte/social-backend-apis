package com.backend.social.socialbackendapis;

import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class SocialBackendApisApplicationTests {

	public static Logger logger = LoggerFactory.getLogger(SocialBackendApisApplicationTests.class);

	@Test
	void contextLoads() {
		logger.info("Testing test class");
		assertEquals(true,true);
	}

}

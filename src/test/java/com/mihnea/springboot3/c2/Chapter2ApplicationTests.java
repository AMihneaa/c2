package com.mihnea.springboot3.c2;

import com.mihnea.springboot3.c2.Home.HomeControllerTest;
import com.mihnea.springboot3.c2.Video.CoreDomainTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		CoreDomainTest.class,
		HomeControllerTest.class
})
@SpringBootTest
class Chapter2ApplicationTests {

	@Test
	void contextLoads() {

	}

}

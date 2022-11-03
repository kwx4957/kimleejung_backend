package com.capstone.kimleejung;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev") // 괄호 안에 실행 환경을 명시해준다.
class KimleejungApplicationTests {

	@Test
	void contextLoads() {
	}

}

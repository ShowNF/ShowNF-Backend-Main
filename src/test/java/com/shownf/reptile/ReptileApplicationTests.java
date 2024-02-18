package com.shownf.reptile;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"AWS_S3_ACCESSKEY=" // AWS 관련 프로퍼티를 빈 값으로 설정하여 오버라이드
})
class ReptileApplicationTests {

	@Test
	void contextLoads() {
	}

}

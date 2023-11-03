package com.tagsolution.ecommercebackend;

import com.padillatom.asadoremotobackend.AsadoRemotoBackendApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AsadoRemotoBackendApplicationTests {

	@Test
	void contextLoads() {
		var ecommerceBackendApplication = new AsadoRemotoBackendApplication();
		assertNotNull(ecommerceBackendApplication.getClass());
	}
}

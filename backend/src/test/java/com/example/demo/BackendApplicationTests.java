package com.example.demo;

import com.example.demo.user.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;




import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackendApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void getProductsFromCategory(){
		ResponseEntity<String> response = restTemplate.getForEntity("/api/product/category/MEN",String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		// Research how to check for the boddy
	}

	@Test
	void login(){
		LoginRequestDTO requestDTO = new LoginRequestDTO("username", "password");
		ResponseEntity<String> response = restTemplate.withBasicAuth("username", "password").postForEntity("/api/auth/login", requestDTO, String.class);

	}




}

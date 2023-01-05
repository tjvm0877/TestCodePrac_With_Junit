package com.hyun.TestCodePrac.controller;

import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Documented;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyun.TestCodePrac.Service.BookService;
import com.hyun.TestCodePrac.dto.request.BookSaveReqDto;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

// Controller 단위 테스트가 아닌 통합 테스트
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookApiControllerTest {

	@Autowired
	TestRestTemplate rt;

	private static ObjectMapper om;
	private static HttpHeaders headers;

	@BeforeAll
	public static void init() {
		om = new ObjectMapper();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@DisplayName("책 등록")
	@Test
	public void saveBookTest() throws JsonProcessingException {
		/* given - 데이터 준비 */
		BookSaveReqDto bookSaveReqDto = new BookSaveReqDto("스프링", "hyun");
		String body = om.writeValueAsString(bookSaveReqDto);

		/* when - 테스트 실행 */
		HttpEntity<String> request = new HttpEntity<>(body, headers);
		ResponseEntity<String> response = rt.exchange("/api/v1/book", HttpMethod.POST, request, String.class);

		/* then - 검증 */
		DocumentContext dc = JsonPath.parse(response.getBody());
		String title = dc.read("$.data.title");
		String author = dc.read("$.data.author");

		assertThat(title).isEqualTo("스프링");
		assertThat(author).isEqualTo("hyun");
	}
}
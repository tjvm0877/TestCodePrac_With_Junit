package com.hyun.TestCodePrac.controller;

import static org.assertj.core.api.Assertions.*;

import java.lang.annotation.Documented;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyun.TestCodePrac.Entity.Book;
import com.hyun.TestCodePrac.Service.BookService;
import com.hyun.TestCodePrac.dto.request.BookSaveReqDto;
import com.hyun.TestCodePrac.repository.BookRepository;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

// Controller 단위 테스트가 아닌 통합 테스트
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookApiControllerTest {

	@Autowired
	TestRestTemplate rt;

	@Autowired
	private BookRepository bookRepository;

	private static ObjectMapper om;
	private static HttpHeaders headers;

	@BeforeAll
	public static void init() {
		om = new ObjectMapper();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	@BeforeEach
	public void dataSet() {
		String title = "Junit5";
		String author = "hyun";
		Book book = Book.builder()
			.title(title)
			.author(author)
			.build();
		bookRepository.save(book);
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

	@Sql("classpath:db/tableInit.sql")
	@DisplayName("책 목록조회")
	@Test
	public void getBookListTest() {
		/* given - 데이터 준비 */

		/* when - 테스트 실행 */
		HttpEntity<String> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = rt.exchange("/api/v1/book", HttpMethod.GET, request,String.class);

		/* then - 검증 */
		DocumentContext dc = JsonPath.parse(response.getBody());
		String result = dc.read("$.result");
		String title = dc.read("$.data.items[0].title");
		String author = dc.read("$.data.items[0].author");

		assertThat(result).isEqualTo("success");
		assertThat(title).isEqualTo("Junit5");
		assertThat(author).isEqualTo("hyun");
	}

	@Sql("classpath:db/tableInit.sql")
	@DisplayName("책 한권 조회")
	@Test
	public void getBookTest() {
		/* given - 데이터 준비 */

		/* when - 테스트 실행 */
		HttpEntity<String> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = rt.exchange("/api/v1/book/1", HttpMethod.GET, request,String.class);

		/* then - 검증 */
		DocumentContext dc = JsonPath.parse(response.getBody());
		String result = dc.read("$.result");
		String title = dc.read("$.data.title");
		String author = dc.read("$.data.author");

		assertThat(result).isEqualTo("success");
		assertThat(title).isEqualTo("Junit5");
		assertThat(author).isEqualTo("hyun");
	}

	@Sql("classpath:db/tableInit.sql")
	@DisplayName("책 삭제")
	@Test
	public void deleteBookTest() {
		/* given - 데이터 준비 */

		/* when - 테스트 실행 */
		HttpEntity<String> request = new HttpEntity<>(null, headers);
		ResponseEntity<String> response = rt.exchange("/api/v1/book/1", HttpMethod.GET, request,String.class);

		/* then - 검증 */
		DocumentContext dc = JsonPath.parse(response.getBody());
		String result = dc.read("$.result");
		assertThat(result).isEqualTo("success");
	}

	
}
package com.hyun.TestCodePrac.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.hyun.TestCodePrac.Entity.Book;

@DataJpaTest // DB에 관련된 컴포넌트만 메모리에 로딩
class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@BeforeEach
	public void dataSet() {
		String title = "Junit5";
		String author = "hyun";
		Book book = Book.builder()
			.title(title)
			.author(author)
			.build();
		bookRepository.save(book);
		System.out.println("==================================" + book.getId());
		List<Book> books = bookRepository.findAll();
		for (Book onebook : books) {
			System.out.println(onebook.toString());
		}
	}

	// 1. 책 등록하기
	@Test
	@DisplayName("책 등록")
	public void 책_등록하기() {
		/* given - 데이터 준비 */
		String title = "Junit5";
		String author = "hyun";
		Book book = Book.builder()
			.title(title)
			.author(author)
			.build();

		/* when - 테스트 실행 */
		Book bookPS = bookRepository.save(book);

		/* then - 검증 */
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	}

	// 2. 책 목록보기
	@Test
	@DisplayName("목록조회")
	public void 책_목록조회() {

		/* given - 데이터 준비 */
		String title = "Junit5";
		String author = "hyun";

		/* when - 테스트 실행 */
		List<Book> booksPS = bookRepository.findAll();

		/* then - 검증 */
		assertEquals(title, booksPS.get(0).getTitle());
		assertEquals(author, booksPS.get(0).getAuthor());
	}

	// 3. 책 한건보기
	@Sql("classpath:db/tableInit.sql")
	@Test
	@DisplayName("한 건 조회")
	public void 책_한건보기() {

		/* given - 데이터 준비 */
		String title = "Junit5";
		String author = "hyun";

		/* when - 테스트 실행 */
		Book bookPS = bookRepository.findById(1L).get();

		/* then - 검증 */
		assertEquals(title, bookPS.getTitle());
		assertEquals(author, bookPS.getAuthor());
	}

	@Sql("classpath:db/tableInit.sql")
	@Test
	@DisplayName("삭제")
	public void 책_삭제() {
		/* given - 데이터 준비 */
		Long id = 1L;

		/* when - 테스트 실행 */
		bookRepository.deleteById(id);

		/* then - 검증 */
		assertFalse(bookRepository.findById(id).isPresent());
	}
	// 4. 책 삭제

	// 5. 책 수정
}
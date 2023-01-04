package com.hyun.TestCodePrac.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hyun.TestCodePrac.Entity.Book;

@DataJpaTest // DB에 관련된 컴포넌트만 메모리에 로딩
class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	// 1. 책 등록하기
	@Test
	public void 책_등록하기() {
		/* given - 데이터 실행 */
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

	// 3. 책 한건보기

	// 4. 책 수정

	// 5. 책 삭제
}
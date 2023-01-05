package com.hyun.TestCodePrac.Service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hyun.TestCodePrac.Entity.Book;
import com.hyun.TestCodePrac.dto.response.BookRespDto;
import com.hyun.TestCodePrac.dto.request.BookSaveReqDto;
import com.hyun.TestCodePrac.repository.BookRepository;
import com.hyun.TestCodePrac.util.MailSender;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	private BookService bookService;

	// 가짜 객체 생성
	@Mock
	private BookRepository bookRepository;
	@Mock
	private MailSender mailSender;

	@Test
	@DisplayName("책 등록하기")
	void createBook() {
		/* given - 데이터 준비 */
		BookSaveReqDto dto = new BookSaveReqDto("JUnit", "hyun");

		// 가짜 객체들의 행동 정의
		when(bookRepository.save(any())).thenReturn(dto.toEntity());
		when(mailSender.send()).thenReturn(true);

		/* when - 테스트 실행 */
		BookRespDto bookRespDto = bookService.createBook(dto);

		/* then - 검증 */
		assertThat(dto.getTitle()).isEqualTo(bookRespDto.getTitle());
		assertThat(dto.getAuthor()).isEqualTo(bookRespDto.getAuthor());
	}

	@Test
	@DisplayName("책 목록조회")
	void getBookList() {
		/* given - 데이터 준비 */
		// 입력될 데이터가 없기 때문에 비어있음

		// 가짜 객체의 행동 정의
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "JUnit", "hyun"));
		books.add(new Book(2L, "Spring Boot", "hyun"));
		when(bookRepository.findAll()).thenReturn(books);

		/* when - 테스트 실행 */
		List<BookRespDto> bookRespDtoList = bookService.getBookList();

		/* then - 검증 */
		assertThat(bookRespDtoList.get(0).getTitle()).isEqualTo("JUnit");
		assertThat(bookRespDtoList.get(1).getTitle()).isEqualTo("Spring Boot");
		assertThat(bookRespDtoList.get(0).getAuthor()).isEqualTo("hyun");
		assertThat(bookRespDtoList.get(1).getAuthor()).isEqualTo("hyun");
	}

	@Test
	@DisplayName("책 한권 조회")
	void getBook() {
		/* given - 데이터 준비 */
		Long id = 1L;

		// 가짜 객체의 행동 정의
		Book book = Book.builder()
			.id(1L)
			.title("JUnit")
			.author("hyun")
			.build();
		Optional<Book> bookOP = Optional.of(book);
		when(bookRepository.findById(id)).thenReturn(bookOP);

		/* when - 테스트 실행 */
		BookRespDto bookRespDto = bookService.getBook(id);

		/* then - 검증 */
		assertThat(bookRespDto.getTitle()).isEqualTo(book.getTitle());
		assertThat(bookRespDto.getAuthor()).isEqualTo(book.getAuthor());
	}

	@Test
	@DisplayName("책 수정")
	void updateBook() {
		/* given - 데이터 준비 */
		Long id = 1L;
		BookSaveReqDto dto = new BookSaveReqDto("JUnit", "jae");

		// 가짜 객체의 행동 정의
		Book book = Book.builder()
			.id(1L)
			.title("Spring Boot")
			.author("hyun")
			.build();
		Optional<Book> bookOP = Optional.of(book);
		when(bookRepository.findById(id)).thenReturn(bookOP);

		/* when - 테스트 실행 */
		BookRespDto bookRespDto = bookService.updateBook(id, dto);

		/* then - 검증 */
		assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
		assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());
	}
}
package com.hyun.TestCodePrac.Service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hyun.TestCodePrac.dto.BookRespDto;
import com.hyun.TestCodePrac.dto.BookSaveReqDto;
import com.hyun.TestCodePrac.repository.BookRepository;
import com.hyun.TestCodePrac.util.MailSender;
import com.hyun.TestCodePrac.util.MailSenderStub;

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
		BookSaveReqDto dto = new BookSaveReqDto();
		dto.setTitle("JUnit");
		dto.setAuthor("hyun");

		// 가짜 객체들의 행동 정의
		when(bookRepository.save(any())).thenReturn(dto.toEntity());
		when(mailSender.send()).thenReturn(true);

		/* when - 테스트 실행 */
		BookRespDto bookRespDto = bookService.createBook(dto);

		/* then - 검증 */
		assertThat(dto.getTitle()).isEqualTo(bookRespDto.getTitle());
		assertThat(dto.getAuthor()).isEqualTo(bookRespDto.getAuthor());
	}
}
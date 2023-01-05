package com.hyun.TestCodePrac.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyun.TestCodePrac.Entity.Book;
import com.hyun.TestCodePrac.dto.response.BookRespDto;
import com.hyun.TestCodePrac.dto.request.BookSaveReqDto;
import com.hyun.TestCodePrac.repository.BookRepository;
import com.hyun.TestCodePrac.util.MailSender;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	private final MailSender mailSender;

	// 1. 책 등록
	@Transactional(rollbackFor = RuntimeException.class)
	public BookRespDto createBook(BookSaveReqDto dto) {
		Book bookPS = bookRepository.save(dto.toEntity());

		if (bookPS != null) {
			if (!mailSender.send()) {
				throw new RuntimeException("메일이 전송되지 않았습니다.");
			}
		}

		return BookRespDto.from(bookPS);
	}

	// 2. 책 목록조회
	@Transactional(readOnly = true)
	public List<BookRespDto> getBookList() {
		List<Book> bookList = bookRepository.findAll();

		List<BookRespDto> response = new ArrayList<>();
		for (Book book : bookList) {
			response.add(BookRespDto.from(book));
		}

		return response;
	}

	// 3. 책 한권 조회
	@Transactional(readOnly = true)
	public BookRespDto getBook(Long id) {
		Book bookOP = bookRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 책을 찾을 수 없습니다.")
		);

		return BookRespDto.from(bookOP);
	}

	// 4. 책 삭제
	@Transactional(rollbackFor = RuntimeException.class)
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	// 5. 책 수정
	@Transactional(rollbackFor = RuntimeException.class)
	public BookRespDto updateBook(Long id, BookSaveReqDto dto) {
		Book bookPS = bookRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 책을 찾을 수 없습니다.")
		);

		bookPS.update(dto.getTitle(), dto.getAuthor());

		return BookRespDto.from(bookPS);
	} // 메서드 종료시에 더티채킹(flush)으로 업데이트
}

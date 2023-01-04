package com.hyun.TestCodePrac.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyun.TestCodePrac.Entity.Book;
import com.hyun.TestCodePrac.dto.BookRespDto;
import com.hyun.TestCodePrac.dto.BookSaveReqDto;
import com.hyun.TestCodePrac.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	// 1. 책 등록
	@Transactional(rollbackFor = RuntimeException.class)
	public BookRespDto createBook(BookSaveReqDto dto) {
		Book bookPS = bookRepository.save(dto.toEntity());

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
		Book book = bookRepository.findById(id).orElseThrow(
			() -> new IllegalArgumentException("해당 책을 찾을 수 없습니다.")
		);

		return BookRespDto.from(book);
	}

	// 4. 책 삭제
	@Transactional(rollbackFor = RuntimeException.class)
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

	// 5. 책 수정
	
}

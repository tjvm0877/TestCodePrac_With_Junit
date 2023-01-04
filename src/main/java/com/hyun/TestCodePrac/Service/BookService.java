package com.hyun.TestCodePrac.Service;

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

	// 3. 책 한 권 조회

	// 4. 책 삭제

	// 5. 책 수정
}

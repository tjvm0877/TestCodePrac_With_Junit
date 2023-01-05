package com.hyun.TestCodePrac.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyun.TestCodePrac.Service.BookService;
import com.hyun.TestCodePrac.dto.response.BookRespDto;
import com.hyun.TestCodePrac.dto.request.BookSaveReqDto;
import com.hyun.TestCodePrac.dto.response.CMResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookApiController {

	private final BookService bookService;

	// 1. 책 등록
	@PostMapping("/book")
	public ResponseEntity<?> createBook(@Valid @RequestBody BookSaveReqDto bookSaveReqDto) {
		BookRespDto response = bookService.createBook(bookSaveReqDto);
		CMResponseDto<?> cmresponseDto = CMResponseDto.builder()
			.result("success")
			.msg("책 저장 성공")
			.data(response)
			.build();

		return new ResponseEntity<>(cmresponseDto, HttpStatus.CREATED); // 201 = insert
	}

	// 2. 책 목록조회
	// public ResponseEntity<?> getBookList() {
	//
	// }
	// 3. 책 한권 조회
	// 4. 책 삭제
	// 5. 책 수정
}

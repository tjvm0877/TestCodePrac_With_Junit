package com.hyun.TestCodePrac.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyun.TestCodePrac.Service.BookService;
import com.hyun.TestCodePrac.dto.response.BookListRespDto;
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
		BookRespDto book = bookService.createBook(bookSaveReqDto);
		CMResponseDto<?> response = CMResponseDto.builder()
			.result("success")
			.msg("책 저장 성공")
			.data(book)
			.build();
		return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 = insert
	}

	// 2. 책 목록조회
	@GetMapping("/book")
	public ResponseEntity<?> getBookList() {
		BookListRespDto bookList = bookService.getBookList();
		CMResponseDto<?> response = CMResponseDto.builder()
			.result("success")
			.msg("책 리스트 조회 성공")
			.data(bookList)
			.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// 3. 책 한권 조회
	@GetMapping("/book/{id}")
	public ResponseEntity<?> getBook(@PathVariable Long id) {
		BookRespDto bookRespDto = bookService.getBook(id);
		CMResponseDto<?> response = CMResponseDto.builder()
			.result("success")
			.msg("책 리스트 조회 성공")
			.data(bookRespDto)
			.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// 4. 책 삭제
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		CMResponseDto response = CMResponseDto.builder()
			.result("success")
			.msg("책 삭제 조회 성공")
			.data(null)
			.build();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// 5. 책 수정
	@PutMapping("/book/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody BookSaveReqDto dto) {
		BookRespDto book = bookService.updateBook(id, dto);
		CMResponseDto<?> response = CMResponseDto.builder()
			.result("success")
			.msg("책 정보 수정 성공")
			.data(book)
			.build();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}

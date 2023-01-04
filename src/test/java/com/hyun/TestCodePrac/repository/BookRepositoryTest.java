package com.hyun.TestCodePrac.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // DB에 관련된 컴포넌트만 메모리에 로딩
class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	// 1. 책 등록하기
	@Test
	public void 책_등록하기() {
		System.out.println("책 등록 실행");
	}
	// 2. 책 목록보기

	// 3. 책 한건보기

	// 4. 책 수정

	// 5. 책 삭제
}
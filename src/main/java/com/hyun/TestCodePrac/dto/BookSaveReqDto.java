package com.hyun.TestCodePrac.dto;

import com.hyun.TestCodePrac.Entity.Book;

public class BookSaveReqDto {

	private String title;

	private String author;

	public Book toEntity() {
		return Book.builder()
			.title(title)
			.author(author)
			.build();
	}
}

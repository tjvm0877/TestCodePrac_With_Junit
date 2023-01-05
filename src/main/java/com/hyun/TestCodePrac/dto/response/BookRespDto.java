package com.hyun.TestCodePrac.dto.response;

import com.hyun.TestCodePrac.Entity.Book;

import lombok.Getter;

@Getter
public class BookRespDto {
	private Long id;

	private String title;

	private String author;

	public BookRespDto(Long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public static BookRespDto from(Book book) {
		return new BookRespDto(book.getId(), book.getTitle(), book.getAuthor());
	}
}

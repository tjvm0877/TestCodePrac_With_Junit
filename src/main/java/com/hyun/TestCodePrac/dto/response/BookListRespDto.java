package com.hyun.TestCodePrac.dto.response;

import java.util.List;

import lombok.Getter;

@Getter
public class BookListRespDto {
	List<BookRespDto> items;

	public BookListRespDto(List<BookRespDto> items) {
		this.items = items;
	}
}

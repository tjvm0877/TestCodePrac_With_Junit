package com.hyun.TestCodePrac.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.hyun.TestCodePrac.Entity.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookSaveReqDto {

	@Size(min = 1, max = 50, message = "제목은 1글자 이상, 50글자 미만으로 작성해주세요.")
	@NotBlank(message = "빈 제목입니다. 제목을 확인해주세요.") // null, 공백 검사
	private String title;

	@Size(min = 2, max = 20,message = "작성자 이름은 2글자 이상, 20글자 미만으로 작성해주세요.")
	@NotBlank(message = "빈 제목입니다. 제목을 확인해주세요.")
	private String author;

	public Book toEntity() {
		return Book.builder()
			.title(title)
			.author(author)
			.build();
	}

	public BookSaveReqDto(String title, String author) {
		this.title = title;
		this.author = author;
	}
}

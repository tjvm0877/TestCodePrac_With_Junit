package com.hyun.TestCodePrac.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CMResponseDto<T> {
	private String result; // success : 성공, fail : 실패

	private String msg; // 에러 메시지, 성공에 대한 메시지

	private T data;

	@Builder
	public CMResponseDto(String result, String msg, T data) {
		this.result = result;
		this.msg = msg;
		this.data = data;
	}
}

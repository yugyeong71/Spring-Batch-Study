package com.example.springbatchstudy.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VerifyType {

	VERIFIED("인증"),

	UNVERIFIED("미인증");

	private final String description;

}

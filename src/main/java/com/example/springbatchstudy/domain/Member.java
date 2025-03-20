package com.example.springbatchstudy.domain;

import java.io.Serializable;

import com.example.springbatchstudy.domain.enums.VerifyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "member")
public class Member implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private VerifyType status;

	/**
	 * 인증 상태 업데이트
	 */
	public void setStatus(VerifyType status) {
		this.status = status;
	}

}

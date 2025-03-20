package com.example.springbatchstudy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbatchstudy.domain.Member;
import com.example.springbatchstudy.domain.enums.VerifyType;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findAllByStatus(VerifyType status);

}

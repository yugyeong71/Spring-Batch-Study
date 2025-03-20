package com.example.springbatchstudy.batch.tasklet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbatchstudy.dao.MemberRepository;
import com.example.springbatchstudy.domain.Member;
import com.example.springbatchstudy.domain.enums.VerifyType;

import lombok.RequiredArgsConstructor;

/**
 * Tasklet 2. 미인증 사용자 인증 처리
 */
@Component
@RequiredArgsConstructor
public class UpdateStatusTasklet implements Tasklet {

	private final MemberRepository memberRepository;

	@Transactional
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		List<Member> memberList = Optional.ofNullable((List<Member>)
			chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("memberList"))
			.orElse(Collections.emptyList());


		for (Member member : memberList) {
			member.setStatus(VerifyType.VERIFIED);
		}

		memberRepository.saveAll(memberList);

		System.out.println("미인증 사용자 인증 처리 완료");

		return RepeatStatus.FINISHED;
	}

}

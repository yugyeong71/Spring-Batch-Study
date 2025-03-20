package com.example.springbatchstudy.batch.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.springbatchstudy.dao.MemberRepository;
import com.example.springbatchstudy.domain.Member;
import com.example.springbatchstudy.domain.enums.VerifyType;

import lombok.RequiredArgsConstructor;

/**
 * Tasklet 1. 미인증 사용자 조회
 */
@Component
@RequiredArgsConstructor
public class UnverifiedMemberTasklet implements Tasklet {

	private final MemberRepository memberRepository;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		List<Member> memberList = memberRepository.findAllByStatus(VerifyType.UNVERIFIED);

		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("memberList", memberList);

		memberList.forEach(member -> System.out.println("미인증 사용자 : " + member.getName() + " (" + member.getStatus() + ")"));

		return RepeatStatus.FINISHED;
	}

}

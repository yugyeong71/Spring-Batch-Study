package com.example.springbatchstudy.batch.tasklet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.example.springbatchstudy.domain.Member;

/**
 * Tasklet 3. 인증 처리된 사용자 조회
 */
@Component
public class VerifiedMemberTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		List<Member> memberList = Optional.ofNullable((List<Member>) chunkContext.getStepContext().getStepExecution()
				.getJobExecution().getExecutionContext().get("memberList"))
			.orElse(Collections.emptyList());

		memberList.forEach(member -> System.out.println("사용자 : " + member.getName() + " (" + member.getStatus() + ")"));

		return RepeatStatus.FINISHED;
	}

}

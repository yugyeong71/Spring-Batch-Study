package com.example.springbatchstudy.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.springbatchstudy.batch.tasklet.UnverifiedMemberTasklet;
import com.example.springbatchstudy.batch.tasklet.VerifiedMemberTasklet;
import com.example.springbatchstudy.batch.tasklet.UpdateStatusTasklet;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MemberJobConfig {

	private final JobRepository jobRepository;

	private final PlatformTransactionManager transactionManager;

	private final UnverifiedMemberTasklet unverifiedMemberTasklet;

	private final UpdateStatusTasklet updateStatusTasklet;

	private final VerifiedMemberTasklet verifiedMemberTasklet;

	@Bean
	public Job job() {
		return new JobBuilder("job", jobRepository)
			.start(unverifiedMemberStep())
			.next(updateStatusStep())
			.next(verifiedMemberStep())
			.build();
	}

	@Bean
	public Step unverifiedMemberStep() {
		return new StepBuilder("unverifiedMemberStep", jobRepository)
			.tasklet(unverifiedMemberTasklet, transactionManager)
			.build();
	}

	@Bean
	public Step updateStatusStep() {
		return new StepBuilder("updateStatusStep", jobRepository)
			.tasklet(updateStatusTasklet, transactionManager)
			.build();
	}

	@Bean
	public Step verifiedMemberStep() {
		return new StepBuilder("verifiedMemberStep", jobRepository)
			.tasklet(verifiedMemberTasklet, transactionManager)
			.build();
	}

}

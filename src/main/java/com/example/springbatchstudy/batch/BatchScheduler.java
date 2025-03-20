package com.example.springbatchstudy.batch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchScheduler {

	private final JobLauncher jobLauncher;

	private final MemberJobConfig job;

	@Scheduled(cron = "0 */1 * * * ?") // 1분 마다 실행
	public void runJob() {
		try {
			JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
				.toJobParameters();

			jobLauncher.run(job.job(), jobParameters);

			log.info("Batch 자동 실행 성공");
		} catch (Exception e) {
			log.error("Batch 자동 실행 실패 : " + e.getMessage());
		}
	}
}


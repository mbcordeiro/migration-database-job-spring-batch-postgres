package com.matheuscordeiro.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseMigrationJobConfig {
	private final JobBuilderFactory jobBuilderFactory;

	public DatabaseMigrationJobConfig(JobBuilderFactory jobBuilderFactory) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
	}

	@Bean
	public Job databaseMigrationJob(Step migrationPersonStep, Step migrationBankDataStep) {
		return jobBuilderFactory.get("databaseMigrationJob")
				.start(migrationBankDataStep)
				.next(migrationBankDataStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}

}

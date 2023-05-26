package com.matheuscordeiro.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@EnableBatchProcessing
@Configuration
public class DatabaseMigrationJobConfig {
	private final JobBuilderFactory jobBuilderFactory;

	public DatabaseMigrationJobConfig(JobBuilderFactory jobBuilderFactory) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
	}

	@Bean
	public Job databaseMigrationJob(@Qualifier("migratePersonStep") Step migratePersonStep,
			@Qualifier("migrateBankDataStep") Step migrateBankDataStep) {
		return jobBuilderFactory.get("databaseMigrationJob")
				.start(parallelSteps(migratePersonStep, migrateBankDataStep)).end().incrementer(new RunIdIncrementer())
				.build();
	}

	private Flow parallelSteps(Step migratePersonStep, Step migrateBankDataStep) {
		final var migratePersonStepFlow = new FlowBuilder<Flow>("migrateBankDataStep").start(migrateBankDataStep)
				.build();

		final var stepsParallel = new FlowBuilder<Flow>("migratePersonStep").start(migratePersonStep)
				.split(new SimpleAsyncTaskExecutor()).add(migratePersonStepFlow).build();
		return stepsParallel;
	}

}

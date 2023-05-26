package com.matheuscordeiro.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matheuscordeiro.domain.BankData;
import com.matheuscordeiro.domain.Person;

@Configuration
public class MigrateBankDataStepConfig {
	private final StepBuilderFactory stepBuilderFactory;

	public MigrateBankDataStepConfig(StepBuilderFactory stepBuilderFactory) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Step migrateBankDataStep(ItemReader<BankData> fileBankDataItemReader, ItemWriter<BankData> databaseBankDataItemWriter) {
		return stepBuilderFactory.get("migrateBankDataStep").<BankData, BankData>chunk(10000).reader(fileBankDataItemReader)
				.writer(databaseBankDataItemWriter).build();
	}
}

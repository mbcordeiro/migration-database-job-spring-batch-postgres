package com.matheuscordeiro.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matheuscordeiro.domain.Person;

@Configuration
public class MigratePersonStepConfig {
	private final StepBuilderFactory stepBuilderFactory;

	public MigratePersonStepConfig(StepBuilderFactory stepBuilderFactory) {
		super();
		this.stepBuilderFactory = stepBuilderFactory;
	}

	@Bean
	public Step migratePersonStep(ItemReader<Person> filePersonItemReader, ItemWriter<Person> databasePersonItemWriter) {
		return stepBuilderFactory.get("migratePersonStep").<Person, Person>chunk(1).reader(filePersonItemReader)
				.writer(databasePersonItemWriter).build();
	}
}

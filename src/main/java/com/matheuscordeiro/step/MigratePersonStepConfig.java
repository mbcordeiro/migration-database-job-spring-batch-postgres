package com.matheuscordeiro.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
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
	public Step migratePersonStep(ItemReader<Person> filePersonItemReader,
			ClassifierCompositeItemWriter<Person> personClassifierWriter,
			FlatFileItemWriter<Person> filePersonInvalidWriter) {
		return stepBuilderFactory.get("migratePersonStep").<Person, Person>chunk(10000).reader(filePersonItemReader)
				.writer(personClassifierWriter).stream(filePersonInvalidWriter).build();
	}
}

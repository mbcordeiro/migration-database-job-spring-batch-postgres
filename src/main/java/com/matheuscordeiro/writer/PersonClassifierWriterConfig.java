package com.matheuscordeiro.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matheuscordeiro.domain.Person;

@Configuration
public class PersonClassifierWriterConfig {
	@Bean
	public ClassifierCompositeItemWriter<Person> personClassifierWriter(JdbcBatchItemWriter<Person> databsePersonWriter,
			FlatFileItemWriter<Person> filePersonInvalidWriter) {
		return new ClassifierCompositeItemWriterBuilder<Person>()
				.classifier(classifier(databsePersonWriter, filePersonInvalidWriter)).build();
	}

	@SuppressWarnings("serial")
	private Classifier<Person, ItemWriter<? super Person>> classifier(JdbcBatchItemWriter<Person> databsePersonWriter,
			FlatFileItemWriter<Person> filePersonInvalidWriter) {
		return new Classifier<Person, ItemWriter<? super Person>>() {

			@Override
			public ItemWriter<? super Person> classify(Person person) {
				if (person.isValid()) {
					return databsePersonWriter;
				} else {
					return filePersonInvalidWriter;
				}
			}

		};
	}
}

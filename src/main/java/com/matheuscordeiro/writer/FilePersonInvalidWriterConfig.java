package com.matheuscordeiro.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.matheuscordeiro.domain.Person;

@Configuration
public class FilePersonInvalidWriterConfig {
	@Bean
	public FlatFileItemWriter<Person> filePersonInvalidWriter() {
		return new FlatFileItemWriterBuilder<Person>().name("filePersonInvalidWriter")
				.resource(new FileSystemResource("files/person_invalid")).delimited().names("id").build();
	}
}

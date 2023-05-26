package com.matheuscordeiro.reader;

import java.util.Date;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import com.matheuscordeiro.domain.Person;

@Configuration
public class FilePersonReaderConfig {
	@Bean
	public FlatFileItemReader<Person> filePersonReader() {
		return new FlatFileItemReaderBuilder<Person>().name("filePersonReader")
				.resource(new FileSystemResource("files/persons.csv")).delimited()
				.names("name", "email", "dateOfBirth", "age", "id").addComment("--").fieldSetMapper(fieldSetMapper()).build();
	}

	private FieldSetMapper<Person> fieldSetMapper() {
		return new FieldSetMapper<Person>() {
			@Override
			public Person mapFieldSet(FieldSet fieldSet) throws BindException {
				final var person = new Person();
				person.setName(fieldSet.readString("name"));
				person.setEmail(fieldSet.readString("email"));
				person.setDateOfBirth(new Date(fieldSet.readDate("dateOfBirth", "yyyy-MM-dd HH:mm:ss").getTime()));
				person.setAge(fieldSet.readInt("age"));
				person.setId(fieldSet.readInt("id"));
				return person;
			}
		};
	}
}

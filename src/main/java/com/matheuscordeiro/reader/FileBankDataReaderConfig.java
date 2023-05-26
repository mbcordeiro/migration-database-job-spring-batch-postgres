package com.matheuscordeiro.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.matheuscordeiro.domain.BankData;

@Configuration
public class FileBankDataReaderConfig {
	@Bean
	public FlatFileItemReader<BankData> fileBankDataReader() {
		return new FlatFileItemReaderBuilder<BankData>().name("fileBankDataReader")
				.resource(new FileSystemResource("files/bank_data.csv")).delimited()
				.names("personId", "agency", "agency", "bank", "id").addComment("--").targetType(BankData.class).build();
	}
}

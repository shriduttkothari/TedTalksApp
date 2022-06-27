package ted.talks.dao.batch.config;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import ted.talks.dao.batch.model.TedTalkFromBatch;

@Configuration
public class CsvBatchConfiguration {

	@Value("classpath*:/*.csv")
	public Resource[] csvInputResources;

	private static final String[] CSV_TOKEN_NAMES = new String[] { "title", "author", "date", "views", "likes",
			"link" };

	@Bean
	public MultiResourceItemReader<TedTalkFromBatch> multiCSVResourceItemReader(
			FlatFileItemReader<TedTalkFromBatch> csvReader) {
		MultiResourceItemReader<TedTalkFromBatch> resourceItemReader = new MultiResourceItemReader<TedTalkFromBatch>();
		resourceItemReader.setResources(csvInputResources);
		resourceItemReader.setDelegate(csvReader);
		return resourceItemReader;
	}

	@Bean
	public FlatFileItemReader<TedTalkFromBatch> csvReader(DefaultLineMapper<TedTalkFromBatch> csvDefaultLineMapper) {
		// Create reader instance
		FlatFileItemReader<TedTalkFromBatch> reader = new FlatFileItemReader<TedTalkFromBatch>();
		// Set number of lines to skips. Use it if file has header rows.
		reader.setLinesToSkip(1);
		// Configure how lines will be parsed and mapped to different values
		reader.setLineMapper(csvDefaultLineMapper);
		return reader;
	}

	@Bean
	public DefaultLineMapper<TedTalkFromBatch> csvDefaultLineMapper(LineTokenizer csvLineTokenizer,
			BeanWrapperFieldSetMapper<TedTalkFromBatch> csvBeanWrapperFieldSetMapper) {
		DefaultLineMapper<TedTalkFromBatch> defaultLineMapper = new DefaultLineMapper<>();
		defaultLineMapper.setLineTokenizer(csvLineTokenizer);
		defaultLineMapper.setFieldSetMapper(csvBeanWrapperFieldSetMapper);
		return defaultLineMapper;
	}

	@Bean
	public BeanWrapperFieldSetMapper<TedTalkFromBatch> csvBeanWrapperFieldSetMapper() {
		BeanWrapperFieldSetMapper<TedTalkFromBatch> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		// Set values in TedTalk class
		beanWrapperFieldSetMapper.setTargetType(TedTalkFromBatch.class);
		return beanWrapperFieldSetMapper;
	}

	@Bean
	public DelimitedLineTokenizer csvLineTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
		tokenizer.setIncludedFields(IntStream.range(0, CSV_TOKEN_NAMES.length).toArray());
		tokenizer.setNames(CSV_TOKEN_NAMES);
		return tokenizer;
	}
}

package ted.talks.dao.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import ted.talks.dao.batch.model.TedTalkFromBatch;
import ted.talks.dao.batch.processor.CsvTedTalksItemProcessor;
import ted.talks.dao.model.TedTalk;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfiguration {

	private static final int CHUNK_SIZE = 10;
	public JobBuilderFactory jobBuilderFactory;
	public StepBuilderFactory stepBuilderFactory;

	/**
	 * Job to import ted talks are completed
	 * 
	 * @param step1ImportTeTalksFromCsv
	 * @return
	 */
	@Bean
	public Job importTedTalksJob(Step step1ImportTedTalksFromCsv) {
		return jobBuilderFactory.get("importTedTalksJob").incrementer(new RunIdIncrementer())
				.start(step1ImportTedTalksFromCsv).build();
	}

	/**
	 * Step to read and write in the chunk of 10 from csv files
	 * 
	 * @param writer
	 * @return
	 */
	@Bean
	public Step step1ImportTedTalksFromCsv(JdbcBatchItemWriter<TedTalk> writer,
			MultiResourceItemReader<TedTalkFromBatch> multiCSVResourceItemReader,
			CsvTedTalksItemProcessor csvTedTalksItemProcessor) {
		return stepBuilderFactory.get("step1ImportTedTalksFromCsv").<TedTalkFromBatch, TedTalk>chunk(CHUNK_SIZE)
				.reader(multiCSVResourceItemReader).processor(csvTedTalksItemProcessor).writer(writer).build();
	}

}
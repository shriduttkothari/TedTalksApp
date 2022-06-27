package ted.talks.dao.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ted.talks.dao.model.TedTalk;

@Configuration
public class JdbcWriterConfiguration {

	/**
	 * JdbcBatchItemWriter which writes data into given data source, <br>
	 * Note: we are currently using in memory DB
	 */
	@Bean
	public JdbcBatchItemWriter<TedTalk> writer(DataSource dataSource) {
		JdbcBatchItemWriter<TedTalk> jdbcBatchItemWriter = new JdbcBatchItemWriterBuilder<TedTalk>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO ted_talk (title, author, date, views, likes, link) VALUES (:title, :author, :date, :views, :likes, :link)")
				.dataSource(dataSource)
				.build();

		return jdbcBatchItemWriter;
	}
}

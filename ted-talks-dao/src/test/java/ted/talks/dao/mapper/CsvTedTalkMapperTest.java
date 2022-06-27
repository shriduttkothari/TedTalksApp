package ted.talks.dao.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ted.talks.dao.batch.model.TedTalkFromBatch;
import ted.talks.dao.model.TedTalk;

public class CsvTedTalkMapperTest {

	private static CsvTedTalkMapper classUnderTest;

	@BeforeAll
	public static void init() {
		classUnderTest = new CsvTedTalkMapper();
	}

	@Test
	public void test_map_ted_talk_from_batch_to_ted_talk_returns_null_for_null_input() {

		TedTalk tedTalkActual = classUnderTest.mapTedTalkFromBatchToTedTalk(null);

		assertThat(tedTalkActual).isNull();
	}

	@Test
	public void test_author_mapped_corectly() {

		TedTalkFromBatch tedTalkFromBatch = new TedTalkFromBatch();
		tedTalkFromBatch.setAuthor("Test Name");

		TedTalk tedTalkActual = classUnderTest.mapTedTalkFromBatchToTedTalk(tedTalkFromBatch);

		assertThat(tedTalkActual.getAuthor()).isEqualTo("Test Name");
	}

	@Test
	public void test_title_mapped_corectly() {

		TedTalkFromBatch tedTalkFromBatch = new TedTalkFromBatch();
		tedTalkFromBatch.setTitle("test title");

		TedTalk tedTalkActual = classUnderTest.mapTedTalkFromBatchToTedTalk(tedTalkFromBatch);

		assertThat(tedTalkActual.getTitle()).isEqualTo("test title");
	}

	@Test
	public void test_link_mapped_corectly() {

		TedTalkFromBatch tedTalkFromBatch = new TedTalkFromBatch();
		tedTalkFromBatch.setLink("test link");

		TedTalk tedTalkActual = classUnderTest.mapTedTalkFromBatchToTedTalk(tedTalkFromBatch);

		assertThat(tedTalkActual.getLink()).isEqualTo("test link");
	}
	
	@Test
	public void test_likes_mapped_corectly() {

		TedTalkFromBatch tedTalkFromBatch = new TedTalkFromBatch();
		tedTalkFromBatch.setLikes("123");

		TedTalk tedTalkActual = classUnderTest.mapTedTalkFromBatchToTedTalk(tedTalkFromBatch);

		assertThat(tedTalkActual.getLikes()).isEqualTo(123);
	}
	
	@Test
	public void test_views_mapped_corectly() {

		TedTalkFromBatch tedTalkFromBatch = new TedTalkFromBatch();
		tedTalkFromBatch.setViews("1234");

		TedTalk tedTalkActual = classUnderTest.mapTedTalkFromBatchToTedTalk(tedTalkFromBatch);

		assertThat(tedTalkActual.getViews()).isEqualTo(1234);
	}
	
	@Test
	public void test_date_mapped_corectly() throws ParseException {

		TedTalkFromBatch tedTalkFromBatch = new TedTalkFromBatch();
		tedTalkFromBatch.setDate("December 2021");

		TedTalk tedTalkActual = classUnderTest.mapTedTalkFromBatchToTedTalk(tedTalkFromBatch);

		assertThat(tedTalkActual.getDate()).isEqualTo("December 2021");
	}
	
}

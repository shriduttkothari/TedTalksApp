package ted.talks.dao.batch.processor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.SneakyThrows;
import ted.talks.dao.batch.model.TedTalkFromBatch;
import ted.talks.dao.mapper.TedTalkMapper;
import ted.talks.dao.model.TedTalk;

/**
 * Example of JUnit using Mockito with Mockito annotations
 * 
 * @author Shridutt.Kothari
 *
 */
@ExtendWith(MockitoExtension.class)
public class CsvTedTalksItemProcessorTest {

	@InjectMocks
	private CsvTedTalksItemProcessor classUnderTest;

	@Mock
	private TedTalkMapper tedTalkMapper;

	@Test
	@SneakyThrows
	public void test_process_returns_whatever_is_recevied_from_mapper() {

		TedTalkFromBatch tedTalkFromBatch = new TedTalkFromBatch();
		TedTalk tedTalkExpected = new TedTalk();
		when(tedTalkMapper.mapTedTalkFromBatchToTedTalk(tedTalkFromBatch)).thenReturn(tedTalkExpected);

		TedTalk tedTalkActual = classUnderTest.process(tedTalkFromBatch);

		assertThat(tedTalkActual).isEqualTo(tedTalkExpected);
	}
}

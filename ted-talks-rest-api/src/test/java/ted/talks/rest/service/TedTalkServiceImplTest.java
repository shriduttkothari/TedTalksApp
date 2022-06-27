package ted.talks.rest.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.SneakyThrows;
import ted.talks.dao.model.TedTalk;
import ted.talks.dao.repository.TedTalkRepository;
import ted.talks.rest.service.TedTalkServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TedTalkServiceImplTest {

	@InjectMocks
	private TedTalkServiceImpl classUnderTest;

	@Mock
	private TedTalkRepository tedTalkRepository;

	@Test
	@SneakyThrows
	public void test_get_all_ted_talk_returns_whatever_is_recevied_from_ted_talk_repository() {

		List<TedTalk> tedTalkListExpexted = Collections.singletonList(new TedTalk());
		when(tedTalkRepository.getAllTedTalks()).thenReturn(tedTalkListExpexted);

		List<TedTalk> tedTalkListActual = classUnderTest.getAllTedTalks();

		assertThat(tedTalkListActual).isEqualTo(tedTalkListExpexted);
	}
}

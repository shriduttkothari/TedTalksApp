package ted.talks.rest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import lombok.SneakyThrows;
import ted.talks.dao.model.TedTalk;
import ted.talks.rest.service.TedTalksService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TedTalksController.class)
public class TedTalksControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TedTalksService tedTalkService;

	@Test
	@SneakyThrows
	public void test_tedtalks_get_endpoint_returns_valid_body_and_valid_http_status_code() {

		TedTalk tedTalk = new TedTalk();
		tedTalk.setAuthor("test_author");
		
		when(tedTalkService.getTedTalkById(123)).thenReturn(tedTalk);

		RequestBuilder operation = get("/tedtalks/123");
		ResultActions resultActions = this.mockMvc.perform(operation);

		resultActions.andDo(print());
		resultActions.andExpect(status().is(200));
		resultActions.andExpect(jsonPath("$.author").value("test_author"));
	}
}

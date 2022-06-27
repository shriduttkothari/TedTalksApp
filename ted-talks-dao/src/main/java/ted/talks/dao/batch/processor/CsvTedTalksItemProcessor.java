package ted.talks.dao.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ted.talks.dao.batch.model.TedTalkFromBatch;
import ted.talks.dao.mapper.TedTalkMapper;
import ted.talks.dao.model.TedTalk;

@Component
@AllArgsConstructor
public class CsvTedTalksItemProcessor implements ItemProcessor<TedTalkFromBatch, TedTalk> {

	private TedTalkMapper csvTedTalksMapper;

	@Override
	public TedTalk process(final TedTalkFromBatch tedTalksFromBatch) throws Exception {
		return csvTedTalksMapper.mapTedTalkFromBatchToTedTalk(tedTalksFromBatch);
	}

}
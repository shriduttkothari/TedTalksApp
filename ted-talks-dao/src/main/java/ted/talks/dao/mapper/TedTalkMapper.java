package ted.talks.dao.mapper;

import ted.talks.dao.batch.model.TedTalkFromBatch;
import ted.talks.dao.model.TedTalk;

public interface TedTalkMapper {

	public TedTalk mapTedTalkFromBatchToTedTalk(final TedTalkFromBatch tedTalkFromBatch);
}

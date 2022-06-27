package ted.talks.dao.mapper;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ted.talks.dao.batch.model.TedTalkFromBatch;
import ted.talks.dao.model.TedTalk;

@Component
@Slf4j
public class CsvTedTalkMapper implements TedTalkMapper {

	public TedTalk mapTedTalkFromBatchToTedTalk(final TedTalkFromBatch tedTalkFromBatch) {
		if (tedTalkFromBatch == null) {
			return null;
		}
		BigInteger likes = null;
		if (null != tedTalkFromBatch.getLikes()) {
			try {
				likes = new BigInteger(tedTalkFromBatch.getLikes());
			} catch (NumberFormatException e) {
				log.info("Invalid value for Likes: " + tedTalkFromBatch.getLikes());
			}
		}

		BigInteger views = null;
		if (null != tedTalkFromBatch.getViews()) {
			try {
				views = new BigInteger(tedTalkFromBatch.getViews());
			} catch (Exception e) {
				log.info("Invalid value for Views: " + tedTalkFromBatch.getViews());
			}
		}

		final TedTalk transformedTedTalk = TedTalk.builder().title(tedTalkFromBatch.getTitle())
				.author(tedTalkFromBatch.getAuthor()).date(tedTalkFromBatch.getDate()).views(views).likes(likes)
				.link(tedTalkFromBatch.getLink()).build();

		log.info("Converting (" + tedTalkFromBatch + ") into (" + transformedTedTalk + ")");
		return transformedTedTalk;

	}

}

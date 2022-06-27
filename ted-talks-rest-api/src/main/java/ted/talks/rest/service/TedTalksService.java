package ted.talks.rest.service;

import java.math.BigInteger;
import java.util.List;

import ted.talks.dao.model.TedTalk;

public interface TedTalksService {

	public List<TedTalk> getAllTedTalks();

	public TedTalk saveTedTalk(TedTalk tedTalk);

	public List<TedTalk> getTedTalksWithLikesLessThan(BigInteger likes);

	public List<TedTalk> getTedTalksWithLikesGreaterThan(BigInteger likes);

	public TedTalk getTedTalkById(Integer tedTalkId);

	public List<TedTalk> getTedTalksByAuthor(String author);

}

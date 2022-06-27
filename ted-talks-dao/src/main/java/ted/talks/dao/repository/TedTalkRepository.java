package ted.talks.dao.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import ted.talks.dao.model.TedTalk;

public interface TedTalkRepository {

	public List<TedTalk> getAllTedTalks();

	public List<TedTalk> getTedTalksWithLikesLessThan(BigInteger likes);

	public List<TedTalk> getTedTalksWithLikesGreaterThan(BigInteger Likes);

	public TedTalk saveTedTalk(TedTalk tedTalk);

	Optional<TedTalk> getTedTalksById(int id);

	public List<TedTalk> getTedTalksByAuthor(String author);

}

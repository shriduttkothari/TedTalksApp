package ted.talks.dao.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import ted.talks.dao.model.TedTalk;

public interface TedTalkRepository {

	public List<TedTalk> getAllTedTalks();

	public TedTalk saveTedTalk(TedTalk tedTalk);

	public Optional<TedTalk> getTedTalksById(Integer tedTalkId);

	public List<TedTalk> getTedTalksByMultipleFilters(String author, String title, BigInteger viewsLessThan,
			BigInteger viewsMoreThan, BigInteger likesLessThan, BigInteger likesMoreThan);

	public boolean deleteTedTalksById(Integer tedTalkId);

}

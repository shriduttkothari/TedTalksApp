package ted.talks.rest.service;

import java.math.BigInteger;
import java.util.List;

import ted.talks.dao.model.TedTalk;

public interface TedTalksService {

	public List<TedTalk> getAllTedTalks();

	public TedTalk saveTedTalk(TedTalk tedTalk);

	public TedTalk getTedTalkById(Integer tedTalkId);

	public List<TedTalk> getTedTalksByMultipleFilters(String author, String title,
			BigInteger viewsLessThan, BigInteger viewsMoreThan, BigInteger likesLessThan, BigInteger likesMoreThan);

}

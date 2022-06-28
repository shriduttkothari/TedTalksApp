package ted.talks.rest.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ted.talks.dao.model.TedTalk;
import ted.talks.dao.repository.TedTalkRepository;
import ted.talks.rest.exception.NoTedTalkFoundException;

@Service
@AllArgsConstructor
public class TedTalkServiceImpl implements TedTalksService {

	private final TedTalkRepository tedTalkRepository;

	/**
	 * TODO:<br>
	 * <br>
	 * 1. We can convert the List of TedTalk into List of TedTalk DTO class before
	 * returning it to controller <br>
	 * <br>
	 * 2. USe limit and offset as we can not return all the data from the db to user
	 */
	@Override
	public List<TedTalk> getAllTedTalks() {
		return tedTalkRepository.getAllTedTalks();
	}

	@Override
	public TedTalk saveTedTalk(TedTalk tedTalk) {
		return tedTalkRepository.saveTedTalk(tedTalk);
	}

	@Override
	public TedTalk getTedTalkById(Integer tedTalkId) {
		Optional<TedTalk> tedTalkOptional = tedTalkRepository.getTedTalksById(tedTalkId);
		if (tedTalkOptional.isPresent()) {
			return tedTalkOptional.get();
		}
		throw new NoTedTalkFoundException();
	}

	@Override
	public List<TedTalk> getTedTalksByMultipleFilters(String author, String title, BigInteger viewsLessThan,
			BigInteger viewsMoreThan, BigInteger likesLessThan, BigInteger likesMoreThan) {
		List<TedTalk> tedTalkList = tedTalkRepository.getTedTalksByMultipleFilters(author, title, viewsLessThan,
				viewsMoreThan, likesLessThan, likesMoreThan);
		if (!tedTalkList.isEmpty()) {
			return tedTalkList;
		}
		throw new NoTedTalkFoundException();
	}
	
	@Override
	public void deleteTedTalkById(Integer tedTalkId) {
		boolean tedTalkDeleted = tedTalkRepository.deleteTedTalksById(tedTalkId);
		if (!tedTalkDeleted) {
			throw new NoTedTalkFoundException();
		}
		
	}
}

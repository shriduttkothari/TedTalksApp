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

	@Override
	public List<TedTalk> getAllTedTalks() {
		// TODO: We can convert the List of TedTalk into List of TedTalk DTO class before
		// returning it to controller
		return tedTalkRepository.getAllTedTalks();
	}

	@Override
	public List<TedTalk> getTedTalksWithLikesLessThan(BigInteger likes) {
		// TODO: We can convert the List of TedTalk into List of TedTalk DTO class before
		// returning it to controller
		return tedTalkRepository.getTedTalksWithLikesLessThan(likes);
	}

	@Override
	public List<TedTalk> getTedTalksWithLikesGreaterThan(BigInteger likes) {
		// TODO: We can convert the List of TedTalk into List of TedTalk DTO class before
		// returning it to controller
		return tedTalkRepository.getTedTalksWithLikesGreaterThan(likes);
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
	public List<TedTalk> getTedTalksByAuthor(String author) {
		List<TedTalk> tedTalkList = tedTalkRepository.getTedTalksByAuthor(author);
		if (!tedTalkList.isEmpty()) {
			return tedTalkList;
		}
		throw new NoTedTalkFoundException();
	}
}

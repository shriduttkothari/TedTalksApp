package ted.talks.rest.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ted.talks.dao.model.TedTalk;
import ted.talks.rest.service.TedTalksService;

@RestController
@AllArgsConstructor
public class TedTalksController {

	private final TedTalksService tedTalksService;

	@PostMapping(path = "/tedtalks", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TedTalk> saveTedTalk(@RequestBody TedTalk tedTalk) {
		TedTalk tedTalkSaved = tedTalksService.saveTedTalk(tedTalk);
		return ResponseEntity.status(HttpStatus.CREATED).body(tedTalkSaved);
	}

	@GetMapping(path = "/tedtalks/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TedTalk> getTedTalksById(@PathVariable(name = "id") Integer tedTalkId) {
		TedTalk tedTalk = tedTalksService.getTedTalkById(tedTalkId);
		return ResponseEntity.ok(tedTalk);
	}

	@GetMapping(path = "/tedtalks", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TedTalk>> getTedTalksByAuthor(@RequestParam(name = "author") String author) {
		List<TedTalk> tedTalkList = tedTalksService.getTedTalksByAuthor(author);
		return ResponseEntity.ok(tedTalkList);
	}

	@GetMapping(path = "/tedtalks/likeslessthan")
	public ResponseEntity<List<TedTalk>> getTedTalksWithLikesLessThan(@RequestParam(name = "limit") BigInteger limit) {
		List<TedTalk> tedTalkList = tedTalksService.getTedTalksWithLikesLessThan(limit);
		return ResponseEntity.ok(tedTalkList);
	}

	@GetMapping(path = "/tedtalks/likesgreaterthan")
	public ResponseEntity<List<TedTalk>> getTedTalksWithLikesGreaterThan(
			@RequestParam(name = "limit") BigInteger limit) {
		List<TedTalk> tedTalkList = tedTalksService.getTedTalksWithLikesGreaterThan(limit);
		return ResponseEntity.ok(tedTalkList);
	}

}

package ted.talks.rest.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<TedTalk> getTedTalksById(@PathVariable(name = "id", required = true) Integer tedTalkId) {
		TedTalk tedTalk = tedTalksService.getTedTalkById(tedTalkId);
		return ResponseEntity.ok(tedTalk);
	}

	@GetMapping(path = "/tedtalks", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TedTalk>> getTedTalksByAuthor(
			@RequestParam(name = "author", required = false) String author,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "views_less_than", required = false) BigInteger viewsLessThan,
			@RequestParam(name = "views_more_than", required = false) BigInteger viewsMoreThan,
			@RequestParam(name = "likes_less_than", required = false) BigInteger likesLessThan,
			@RequestParam(name = "likes_more_than", required = false) BigInteger likesMoreThan) {
		List<TedTalk> tedTalkList = tedTalksService.getTedTalksByMultipleFilters(author, title, viewsLessThan,
				viewsMoreThan, likesLessThan, likesMoreThan);
		return ResponseEntity.ok(tedTalkList);
	}

	@DeleteMapping(path = "/tedtalks/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteTedTalksById(@PathVariable(name = "id", required = true) Integer tedTalkId) {
		tedTalksService.deleteTedTalkById(tedTalkId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

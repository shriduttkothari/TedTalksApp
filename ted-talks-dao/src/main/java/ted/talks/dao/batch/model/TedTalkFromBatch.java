package ted.talks.dao.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TedTalkFromBatch {

	private String title;
	private String author;
	private String date;
	private String views;
	private String likes;
	private String link;

}

package ted.talks.dao.model;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TedTalk {

	private int id;
	private String title;
	private String author;
	private String date;
	private BigInteger views;
	private BigInteger likes;
	private String link;

}

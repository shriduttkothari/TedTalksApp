package ted.talks.dao.repository;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import ted.talks.dao.model.TedTalk;

@Repository
@AllArgsConstructor
public class TedTalkRepositoryImpl implements TedTalkRepository {

	private static final String TABLE_NAME = "ted_talk";
	private static final String[] COLUMNS_NAMES = new String[] { "title", "author", "date", "views", "likes", "link" };

	private final JdbcTemplate jdbcTemplate;

	@Override
	public List<TedTalk> getAllTedTalks() {
		List<TedTalk> tedTalkList = jdbcTemplate.query("SELECT * FROM " + TABLE_NAME, (resultSet, row) -> {
			return mapResultSetToTedTalk(resultSet);
		});

		return tedTalkList;
	}

	@Override
	public List<TedTalk> getTedTalksWithLikesLessThan(BigInteger likes) {
		List<TedTalk> tedTalkList = jdbcTemplate.query("SELECT * FROM " + TABLE_NAME + " Where likes < " + likes,
				(resultSet, row) -> {
					return mapResultSetToTedTalk(resultSet);
				});

		return tedTalkList;
	}

	@Override
	public List<TedTalk> getTedTalksWithLikesGreaterThan(BigInteger Likes) {
		List<TedTalk> tedTalkList = jdbcTemplate.query("SELECT * FROM " + TABLE_NAME + " Where Likes > " + Likes,
				(resultSet, row) -> {
					return mapResultSetToTedTalk(resultSet);
				});

		return tedTalkList;
	}

	private TedTalk mapResultSetToTedTalk(ResultSet resultSet) throws SQLException {
		BigInteger views = null == resultSet.getString(5) ? null : new BigInteger(resultSet.getString(5));
		BigInteger likes = null == resultSet.getString(5) ? null : new BigInteger(resultSet.getString(6));

		return TedTalk.builder().id(resultSet.getInt(1)).title(resultSet.getString(2)).author(resultSet.getString(3))
				.date(resultSet.getString(4)).views(views).likes(likes).link(resultSet.getString(7)).build();
	}

	@Override
	public Optional<TedTalk> getTedTalksById(int id) {
		List<TedTalk> tedTalkList = jdbcTemplate.query("SELECT * FROM " + TABLE_NAME + " Where ted_talk_id = " + id,
				(resultSet, row) -> {
					return mapResultSetToTedTalk(resultSet);
				});

		return tedTalkList.stream().findFirst();
	}

	private final String INSERT_SQL = "INSERT INTO " + TABLE_NAME + "(" + COLUMNS_NAMES[0] + "," + COLUMNS_NAMES[1]
			+ "," + COLUMNS_NAMES[2] + "," + COLUMNS_NAMES[3] + "," + COLUMNS_NAMES[4] + "," + COLUMNS_NAMES[5]
			+ ") values(?,?,?,?,?,?)";

	@Override
	public TedTalk saveTedTalk(final TedTalk tedTalk) {
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, tedTalk.getTitle());
				ps.setString(2, tedTalk.getAuthor());
				ps.setString(3, tedTalk.getDate());
				ps.setString(4, tedTalk.getViews().toString());
				ps.setString(5, tedTalk.getLikes().toString());
				ps.setString(6, tedTalk.getLink());
				return ps;
			}
		}, holder);
		int newTedTalkId = holder.getKey().intValue();
		tedTalk.setId(newTedTalkId);
		return tedTalk;
	}

	@Override
	public List<TedTalk> getTedTalksByAuthor(String author) {
		List<TedTalk> tedTalkList = jdbcTemplate.query(
				"SELECT * FROM " + TABLE_NAME + " Where " + COLUMNS_NAMES[1] + " LIKE '%" + author +"%'", (resultSet, row) -> {
					return mapResultSetToTedTalk(resultSet);
				});

		return tedTalkList;
	}

}

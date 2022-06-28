package ted.talks.dao.repository;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
	public List<TedTalk> getTedTalksByMultipleFilters(String author, String title, BigInteger viewsLessThan,
			BigInteger viewsMoreThan, BigInteger likesLessThan, BigInteger likesMoreThan) {
		Set<String> filters = new HashSet<>();
		if (null != title) {
			filters.add(" " + COLUMNS_NAMES[0] + " LIKE '%" + title + "%'");
		}

		if (null != author) {
			filters.add(" " + COLUMNS_NAMES[1] + " LIKE '%" + author + "%'");
		}

		if (null != viewsLessThan) {
			filters.add(" " + COLUMNS_NAMES[3] + " < " + viewsLessThan);
		}
		if (null != viewsMoreThan) {
			filters.add(" " + COLUMNS_NAMES[3] + " > " + viewsMoreThan);
		}
		if (null != likesLessThan) {
			filters.add(" " + COLUMNS_NAMES[4] + " < " + likesLessThan);
		}
		if (null != likesMoreThan) {
			filters.add(" " + COLUMNS_NAMES[4] + " > " + likesLessThan);
		}

		if (filters.isEmpty()) {
			throw new IllegalArgumentException("Atleast one filter needed to search the Ted Talk");
		}

		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ");
		queryBuilder.append(TABLE_NAME);
		queryBuilder.append(" Where");
		String combinedFilters = filters.stream().collect(Collectors.joining(" AND "));
		queryBuilder.append(combinedFilters);

		List<TedTalk> tedTalkList = jdbcTemplate.query(queryBuilder.toString(), (resultSet, row) -> {
			return mapResultSetToTedTalk(resultSet);
		});

		return tedTalkList;
	}

}

package com.promineotech.art.dao.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import com.promineotech.art.entity.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserAddUserDao implements UserAddUserDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public User addUser(String user_name, String password, String first_name, String last_name,
      String email) {
    log.info(
        "User is being created in Dao. Parameters: "
        + "user_name = {}, password = {}, first_name = {}, last_name = {}, email = {}",
        user_name, password, first_name, last_name, email);
    SqlParams params = insertSql(user_name, password, first_name, last_name, email);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    int user_id = keyHolder.getKey().intValue();


    return User.builder().user_id(user_id).user_name(user_name).password(password)
        .first_name(first_name).last_name(last_name).email(email).build();
  }

  private SqlParams insertSql(String user_name, String password, String first_name,
      String last_name, String email) {
    SqlParams params = new SqlParams();

    params.sql ="" 
            + "INSERT INTO users (" + "user_name, user_password, first_name, last_name, email"
            + ") VALUES (" + ":user_name, :user_password, :first_name, :last_name, :email" + ")";

    params.source.addValue("user_name", user_name);
    params.source.addValue("user_password", password);
    params.source.addValue("first_name", first_name);
    params.source.addValue("last_name", last_name);
    params.source.addValue("email", email);

    return params;
  }


  @Override
  public List<User> fetchUsers() {
    log.info("Users are being fetched in Dao");

    String sql = "" 
        + "SELECT user_name, email " 
        + "FROM users";

    Map<String, Object> params = new HashMap<>();

    return jdbcTemplate.query(sql, params, new RowMapper<User>() {

      public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        return User.builder().user_name(rs.getString("user_name")).email(rs.getString("email"))
            .build();
      }
    });
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}

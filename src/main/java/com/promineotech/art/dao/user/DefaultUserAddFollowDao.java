package com.promineotech.art.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.art.entity.Follow;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultUserAddFollowDao implements UserAddFollowDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Follow createFollow(int user_id, int seller_id) {
    log.info("Dao: Creating follow. user_id = {} with seller_id = {}", user_id, seller_id);
    
    SqlParams params = insertSql(user_id, seller_id);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
   
    int follow_id = keyHolder.getKey().intValue();
   
    
    return Follow.builder()
        .follow_id(follow_id)
        .user_id(user_id)
        .seller_id(seller_id)
        .build();
  }

  private SqlParams insertSql( int user_id, int seller_id) {
    SqlParams params = new SqlParams();

    params.sql = ""
        + "INSERT INTO follow ("
        + "user_id, seller_id"
        + ") VALUES ("
        + ":user_id, :seller_id"
        + ")";

    params.source.addValue("user_id", user_id);
    params.source.addValue("seller_id",seller_id);

    return params;
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}

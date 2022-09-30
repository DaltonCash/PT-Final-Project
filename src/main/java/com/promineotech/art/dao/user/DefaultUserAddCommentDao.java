package com.promineotech.art.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.promineotech.art.entity.ArtComment;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultUserAddCommentDao implements UserAddCommentDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public ArtComment createArtComment(int art_id, int user_id, String message, int stars, String post_date) {
    log.info(
        "User is being created in Dao. Parameters: "
        + "art_id = {}, user_id = {}, message = {}, stars = {}",
        art_id, user_id, message, stars);
    SqlParams params = insertSql(art_id, user_id, message, stars);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    int comment_id = keyHolder.getKey().intValue();

    return ArtComment.builder()
        .comment_id(comment_id)
        .art_id(art_id)
        .user_id(user_id)
        .message(message)
        .post_date(post_date)
        .likes(0)
        .stars(stars)
        .build();
  }

  private SqlParams insertSql(int art_id, int user_id, String message, int stars) {
    SqlParams params = new SqlParams();

    params.sql ="" 
            + "INSERT INTO art_comment (" + "art_id, user_id, message, stars"
            + ") VALUES (:art_id, :user_id, :message, :stars)";

    params.source.addValue("art_id", art_id);
    params.source.addValue("user_id", user_id);
    params.source.addValue("message", message);
    params.source.addValue("stars", stars);

    return params;
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}
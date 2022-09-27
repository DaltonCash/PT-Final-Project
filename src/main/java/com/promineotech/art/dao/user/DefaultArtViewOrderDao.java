package com.promineotech.art.dao.user;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtViewOrderDao implements ArtViewOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Art> fetchArtFromOrder(int order_id) {

    log.info("DAO: order_id = {}", order_id);

    String sql = ""
        + "SELECT art.art_id, "
        + "art.title, "
        + "art.artist_name, "
        + "art.art_period, "
        + "art.art_medium, "
        + "art.creation_year, "
        + "art.art_stock, "
        + "art.price "
        + "FROM art "
        + "INNER JOIN "
        + "orders_art "
        + "ON orders_art.art_id=art.art_id "
        + "WHERE order_id = :order_id;";

    Map<String, Object> params = new HashMap<>();
    params.put("order_id", order_id);

    return jdbcTemplate.query(sql, params,new RowMapper<Art>(){

      @Override
      public Art mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Art.builder()
            .art_id(rs.getInt("art_id"))
            .art_medium(rs.getString("art_medium"))
            .art_period(rs.getString("art_period"))
            .art_stock(rs.getInt("art_stock"))
            .artist_name(rs.getString("artist_name"))
            .price(new BigDecimal(rs.getInt("price")))
            .title(rs.getString("title"))
            .creation_year(rs.getInt("creation_year"))
            .build();
      }
    });
  }
}
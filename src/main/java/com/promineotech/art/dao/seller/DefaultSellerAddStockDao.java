package com.promineotech.art.dao.seller;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultSellerAddStockDao implements SellerAddStockDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Art addStock(Art art, int stock_to_add) {
    log.info("Dao: Returning Art.");

    SqlParams params = insertSql(art, stock_to_add);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    return Art.builder()
        .art_id(art.getArt_id())
        .seller_id(art.getSeller_id())
        .art_medium(art.getArt_medium())
        .art_period(art.getArt_period())
        .art_stock(art.getArt_stock() + stock_to_add)
        .artist_name(art.getArtist_name())
        .price(art.getPrice())
        .title(art.getTitle())
        .creation_year(art.getCreation_year())
        .build();
  }

  private SqlParams insertSql(Art art, int stock_to_add) {
    SqlParams params = new SqlParams();


    params.sql = ""
        + "UPDATE art "
        + "SET art_stock = art_stock + :stock_to_add "
        + "WHERE art_id = :art_id;";


    params.source.addValue("art_id", art.getArt_id());
    params.source.addValue("stock_to_add", stock_to_add);

    return params;
  }

  @Override
  public Art fetchArt(int art_id) {
    log.info("DAO: Fetching art: art_id = {}", art_id);

    String sql = ""
        + "SELECT * "
        + "FROM art "
        + "WHERE art_id = :art_id";

    Map<String, Object> params = new HashMap<>();
    params.put("art_id", art_id);


    return jdbcTemplate.query(sql, params, new ArtResultSetExtractor());
  }

  class ArtResultSetExtractor implements ResultSetExtractor<Art> {
    @Override
    public Art extractData(ResultSet rs) throws SQLException {
      rs.next();
      return Art.builder()
          .art_id(rs.getInt("art_id"))
          .seller_id(rs.getInt("seller_id"))
          .art_medium(rs.getString("art_medium"))
          .art_period(rs.getString("art_period"))
          .art_stock(rs.getInt("art_stock"))
          .artist_name(rs.getString("artist_name"))
          .price(new BigDecimal(rs.getInt("price")))
          .title(rs.getString("title"))
          .creation_year(rs.getInt("creation_year"))
          .build();
    }
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}

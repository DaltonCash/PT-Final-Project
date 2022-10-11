package com.promineotech.art.dao.seller;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultSellerAddArtDao implements SellerAddArtDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Art addArt(int seller_id, String title, String artist_name, String art_period,
      String art_medium, int creation_year, int art_stock, double price) {
    
    log.info("Dao: A request has been made to add art. Parameters: "
        + "seller_id = {}, title = {}, artist_name = {}, art_period = {}, art_medium = {}, "
        + "creation_year = {}, art_stock = {}, price = {}.", 
        seller_id, title, artist_name, art_period, art_medium, creation_year, art_stock, price);
    
    SqlParams params = insertSql(seller_id, title, artist_name, art_period, art_medium, creation_year, art_stock, price);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    int art_id = keyHolder.getKey().intValue();


    return Art.builder()
        .art_id(art_id)
        .seller_id(seller_id)
        .title(title)
        .artist_name(artist_name)
        .art_period(art_period)
        .art_medium(art_medium)
        .creation_year(creation_year)
        .art_stock(art_stock)
        .price(new BigDecimal(price))
        .build();
  }

  private SqlParams insertSql(int seller_id, String title, String artist_name, String art_period,
      String art_medium, int creation_year, int art_stock, double price) {
    
    SqlParams params = new SqlParams();

    params.sql =
        "" + "INSERT INTO art (" + "seller_id, title, artist_name, art_period, art_medium, "
            + "creation_year, art_stock, price"
            + ") VALUES (" + ":seller_id, :title, :artist_name, :art_period, :art_medium, "
                + ":creation_year, :art_stock, :price)";

    params.source.addValue("seller_id", seller_id);
    params.source.addValue("title", title);
    params.source.addValue("artist_name", artist_name);
    params.source.addValue("art_period", art_period);
    params.source.addValue("art_medium", art_medium);
    params.source.addValue("creation_year", creation_year);
    params.source.addValue("art_stock", art_stock);
    params.source.addValue("price", price);

    return params;
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}

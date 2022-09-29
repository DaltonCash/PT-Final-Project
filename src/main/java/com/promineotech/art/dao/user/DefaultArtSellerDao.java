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
import com.promineotech.art.entity.Seller;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtSellerDao implements ArtSellerDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Art> fetchArtBySellerId(List<Seller> seller_ids) {
    log.info("DAO: seller_id = {}", seller_ids);

    String sql = ""
        + "SELECT * "
        + "FROM art "
        + "WHERE seller_id "
        + "= :seller_id0 ";
    for(int i = 1; i < seller_ids.size(); i++) {
      sql += "OR seller_id = :seller_id" + i;
    }
         

    Map<String, Object> params = new HashMap<>();
    for(int i = 0; i < seller_ids.size(); i++) {
      params.put("seller_id" + i, seller_ids.get(i).getSeller_id());
    }

    return jdbcTemplate.query(sql, params,new RowMapper<Art>(){

      @Override
      public Art mapRow(ResultSet rs, int rowNum) throws SQLException {

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
    });
  }

  @Override
  public List<Seller> fetchSellerIdBySellerName(String seller) {
    log.info("DAO: seller = {}", seller);
    seller = "%" + seller + "%";
    

    String sql = ""
        + "SELECT seller_id "
        + "FROM sellers "
        + "WHERE seller_name "
        + "LIKE :seller";

    Map<String, Object> params = new HashMap<>();
    params.put("seller", seller);

    return jdbcTemplate.query(sql, params, new RowMapper<Seller>(){

      @Override
      public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Seller.builder()
            .seller_id(rs.getInt("seller_id"))
            .build();
      }
    });
  }
}
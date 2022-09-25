package com.promineotech.art.dao.user;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtBrowseDao implements ArtBrowseDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Art> fetchArt() {
   
    log.info("All art has been requested in DAO Layer");
    
    String sql = "SELECT * FROM art"; 
  
    return jdbcTemplate.query(sql, new RowMapper<Art>(){

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

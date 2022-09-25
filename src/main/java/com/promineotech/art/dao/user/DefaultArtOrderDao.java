package com.promineotech.art.dao.user;

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
import org.springframework.stereotype.Component;
import com.promineotech.art.entity.Art;
import com.promineotech.art.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultArtOrderDao implements ArtOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Order saveOrder(int art_id, int user_id, BigDecimal price, String order_date, String defaultMessage) {
    log.info("A new order has been requested for creation by user user_id = {} with art art_id = {}", user_id, art_id );
    SqlParams params = insertSql(art_id,user_id, price);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    int order_id = keyHolder.getKey().intValue();
    
    
    createNewOrdersArt(art_id, order_id);
    
    return Order.builder()
        .order_id(order_id)
        .user_id(user_id)
        .price(price)
        .order_date(order_date)
        .message(defaultMessage)
        .build();
  }
  
  private SqlParams insertSql(int art_id, int user_id, BigDecimal price) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO orders ("
        + "user_id, price"
        + ") VALUES ("
        + ":user_id, :price"
        + ")";
 
    params.source.addValue("user_id", user_id);
    params.source.addValue("price",price);
    
    return params;
  }
  
  @Override
  public Art fetchPrice(int art_id) {
    
    String sql = "" 
        + "SELECT price " 
        + "FROM art " 
        + "WHERE art_id = :art_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("art_id", art_id);

    return jdbcTemplate.query(sql, params, new PriceResultSetExtractor());
  }
  
  class PriceResultSetExtractor implements ResultSetExtractor<Art> {
    @Override
    public Art extractData(ResultSet rs) throws SQLException {
      rs.next();

      return Art.builder().price(new BigDecimal(rs.getInt("price"))).build();
    }
  }
 
  @Override
  public void reduceStock(int art_id) {
    String sql = ""
        + "UPDATE art " 
        + "SET art_stock = art_stock - 1 " 
        + "WHERE art_id = :art_id;";
    
    Map<String, Object> params = new HashMap<>();
    params.put("art_id", art_id);
    
    jdbcTemplate.update(sql, params);
  }
  
  @Override
  public Art fetchStock(int art_id) {
    String sql = "" 
        + "SELECT art_stock " 
        + "FROM art " 
        + "WHERE art_id = :art_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("art_id", art_id);
    
    return jdbcTemplate.query(sql, params, new StockResultSetExtractor());
  }
  
  class StockResultSetExtractor implements ResultSetExtractor<Art> {
    @Override
    public Art extractData(ResultSet rs) throws SQLException {
      rs.next();

      return Art.builder().art_stock(rs.getInt("art_stock")).build();
    }
  } 
  
  @Override
  public void createNewOrdersArt(int art_id, int order_id) {
    String sql = ""
        + "INSERT INTO orders_art "
        + "(art_id, order_id) "
        + "VALUES (:art_id, :order_id)";
    
    Map<String, Object> params = new HashMap<>();
    params.put("art_id", art_id);
    params.put("order_id", order_id);
    
    jdbcTemplate.update(sql, params);
    
  }
  
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}

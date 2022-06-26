package com.promineotech.art.dao;

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
public class DefaultAddToOrderDao implements ArtAddToOrderDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Order addToOrder(int art_id, int user_id, BigDecimal price, int order_id, Order order){
    log.info("user_id = {} has requested to add art_id = {} to order_id = {}", user_id, art_id, order_id);
    
    SqlParams params = insertSql(order_id, price);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    
    createNewOrdersArt(art_id, order_id);
    
    return Order.builder()
        .order_id(order_id)
        .user_id(user_id)
        .price(order.getPrice().add(price))
        .order_date(order.getOrder_date())
        .message(order.getMessage())
        .build();
  }
  
  private SqlParams insertSql(int order_id, BigDecimal price) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "UPDATE orders "
        + "SET price = price + :price "
        + "WHERE order_id = :order_id;";
 
    params.source.addValue("price", price);
    params.source.addValue("order_id", order_id);
    
    return params;
  }
  
  
  @Override
  public Order fetchOrder(int order_id) {
    
    String sql = "" 
        + "SELECT * " 
        + "FROM orders " 
        + "WHERE order_id = :order_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("order_id", order_id);

    return jdbcTemplate.query(sql, params, new OrderResultSetExtractor());
  }
  
  class OrderResultSetExtractor implements ResultSetExtractor<Order> {
    @Override
    public Order extractData(ResultSet rs) throws SQLException {
      rs.next();

      return Order.builder()
          .order_id(rs.getInt("order_id"))
          .user_id(rs.getInt("user_id"))
          .price(rs.getBigDecimal("price"))
          .order_date(rs.getString("order_date"))
          .message(rs.getString("message"))
          .build();
    }
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

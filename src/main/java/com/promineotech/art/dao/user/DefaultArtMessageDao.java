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
import com.promineotech.art.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultArtMessageDao implements ArtMessageDao{

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Order changeMessage(int order_id, String message) {
    log.info("Message change on order order_id = {} has been requested with message: {}",order_id, message);

    SqlParams params = insertSql(order_id, message);
    Order order = fetchOrder(order_id);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    return Order.builder()
        .order_id(order_id)
        .user_id(order.getUser_id())
        .price(order.getPrice())
        .order_date(order.getOrder_date())
        .message(message)
        .build();
  }

  private SqlParams insertSql(int order_id, String message) {
    SqlParams params = new SqlParams();


    params.sql = ""
        + "UPDATE orders "
        + "SET message = :message "
        + "WHERE order_id = :order_id;";


    params.source.addValue("order_id", order_id);
    params.source.addValue("message", message);

    return params;
  }

  @Override
  public Order fetchOrder(int order_id) {
    log.info("DAO: order_id = {}", order_id);

    String sql = ""
        + "SELECT * "
        + "FROM orders "
        + "WHERE order_id = :order_id";

    Map<String, Object> params = new HashMap<>();
    params.put("order_id", order_id);


    return jdbcTemplate.query(sql, params, new StockResultSetExtractor());
  }

  class StockResultSetExtractor implements ResultSetExtractor<Order> {
    @Override
    public Order extractData(ResultSet rs) throws SQLException {
      rs.next();
      return Order.builder()
          .order_id(rs.getInt("order_id"))
          .user_id(rs.getInt("user_id"))
          .price(new BigDecimal(rs.getInt("price")))
          .order_date(rs.getString("order_date"))
          .build();
    }
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}

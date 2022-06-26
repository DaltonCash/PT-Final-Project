package com.promineotech.art.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.art.entity.Users;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtUserDao implements ArtUserDao {
 
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public Users fetchUserInfo(String user_name, String password) {
    log.info("A user is requesting to log in with credentials user_name = {} and password = {}", user_name, password);
    
    String sql = "" 
        + "SELECT * "
        + "FROM users "
        + "WHERE user_name = :user_name "
        + "AND user_password = :user_password";
    
    Map<String, Object> params = new HashMap<>();
    
    params.put("user_name", user_name);
    params.put("user_password", password);
    
    return jdbcTemplate.query(sql, params, new PriceResultSetExtractor());
  }
  
  class PriceResultSetExtractor implements ResultSetExtractor<Users> {
    @Override
    public Users extractData(ResultSet rs) throws SQLException {
      rs.next();

      return Users.builder()
                  .user_id(rs.getInt("user_id"))
                  .user_name(rs.getString("user_name"))
                  .password(rs.getString("user_password"))
                  .first_name(rs.getString("first_name"))
                  .last_name(rs.getString("last_name"))
                  .email(rs.getString("email"))
                  .build();
    }
  }
}

package com.promineotech.art.dao.seller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.seller.DefaultSellerAddSellerDao;
import com.promineotech.art.entity.Seller;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultSellerAddSellerDao implements SellerAddSellerDao {

  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Seller addSeller(String seller_name, String password, String first_name, String last_name,
      String email) {
    log.info(
        "Seller is being created in Dao. Parameters: "
            + "seller_name = {}, password = {}, first_name = {}, last_name = {}, email = {}",
        seller_name, password, first_name, last_name, email);
    SqlParams params = insertSql(seller_name, password, first_name, last_name, email);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);

    int seller_id = keyHolder.getKey().intValue();


    return Seller.builder().seller_id(seller_id).seller_name(seller_name).password(password)
        .first_name(first_name).last_name(last_name).email(email).build();
  }

  private SqlParams insertSql(String seller_name, String password, String first_name,
      String last_name, String email) {
    SqlParams params = new SqlParams();

    params.sql =
        "" + "INSERT INTO sellers (" + "seller_name, seller_password, first_name, last_name, email"
            + ") VALUES (" + ":seller_name, :seller_password, :first_name, :last_name, :email" + ")";

    params.source.addValue("seller_name", seller_name);
    params.source.addValue("seller_password", password);
    params.source.addValue("first_name", first_name);
    params.source.addValue("last_name", last_name);
    params.source.addValue("email", email);

    return params;
  }


  @Override
  public List<Seller> fetchSellers() {
    log.info("Sellers are being fetched in Dao");

    String sql = "" + "SELECT seller_name, email " + "FROM sellers";

    Map<String, Object> params = new HashMap<>();

    return jdbcTemplate.query(sql, params, new RowMapper<Seller>() {

      public Seller mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Seller.builder().seller_name(rs.getString("seller_name")).email(rs.getString("email"))
            .build();
      }
    });
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
}

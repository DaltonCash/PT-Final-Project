package com.promineotech.art.dao;

import java.math.BigDecimal;
import com.promineotech.art.entity.Art;
import com.promineotech.art.entity.Order;

public interface ArtOrderDao {
  
  void reduceStock(int art_id);
 
  Order saveOrder(int art_id, int user_id, BigDecimal price, String order_date, String defaultMessage);

  Art fetchPrice(int art_id);

  Art fetchStock(int art_id);

  void createNewOrdersArt(int art_id, int order_id);
  
}

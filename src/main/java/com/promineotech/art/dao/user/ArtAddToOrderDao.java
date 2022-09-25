package com.promineotech.art.dao.user;

import java.math.BigDecimal;
import com.promineotech.art.entity.Art;
import com.promineotech.art.entity.Order;

public interface ArtAddToOrderDao {

  void reduceStock(int art_id);
  
  Order addToOrder(int art_id, int user_id, BigDecimal price, int order_id, Order order);

  Art fetchPrice(int art_id);

  Art fetchStock(int art_id);

  Order fetchOrder(int order_id);

  void createNewOrdersArt(int art_id, int order_id);
  
}

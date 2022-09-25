package com.promineotech.art.service.user;

import com.promineotech.art.entity.Order;

public interface ArtAddToOrderService {

  Order addToOrder(int art_id, int user_id, int order_id);

  boolean checkStock(int art_id);
  
}

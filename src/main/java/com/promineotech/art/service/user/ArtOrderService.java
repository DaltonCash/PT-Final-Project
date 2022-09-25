package com.promineotech.art.service.user;

import com.promineotech.art.entity.Order;

public interface ArtOrderService {
  
  Order createOrder(int art_id, int user_id);

  boolean checkStock(int art_id);
  
}

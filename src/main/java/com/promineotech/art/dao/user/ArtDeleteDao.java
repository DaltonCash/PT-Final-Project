package com.promineotech.art.dao.user;

import com.promineotech.art.entity.Order;

public interface ArtDeleteDao {

  Order deleteOrder(int order_id, Order order);

  Order fetchOrder(int order_id);

}

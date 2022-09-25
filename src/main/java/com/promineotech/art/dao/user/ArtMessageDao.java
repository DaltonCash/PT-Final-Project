package com.promineotech.art.dao.user;

import com.promineotech.art.entity.Order;

public interface ArtMessageDao {

  Order changeMessage(int order_id, String message);

  Order fetchOrder(int order_id);

}

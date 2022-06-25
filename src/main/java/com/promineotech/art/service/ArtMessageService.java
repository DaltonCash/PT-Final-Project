package com.promineotech.art.service;

import com.promineotech.art.entity.Order;

public interface ArtMessageService {

  Order changeMessage(int order_id, String message);

}

package com.promineotech.art.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.user.ArtDeleteDao;
import com.promineotech.art.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtDeleteService implements ArtDeleteService {

  @Autowired
  private ArtDeleteDao artDeleteDao;
  
  @Override
  public Order deleteOrder(int order_id) {
    log.info("Service has picked up request to delete order with order_id: {}",order_id);
    Order order = artDeleteDao.fetchOrder(order_id);
    
    return artDeleteDao.deleteOrder(order_id, order);
  }
}

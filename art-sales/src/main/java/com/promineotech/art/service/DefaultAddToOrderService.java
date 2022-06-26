package com.promineotech.art.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.art.dao.ArtAddToOrderDao;
import com.promineotech.art.entity.Art;
import com.promineotech.art.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultAddToOrderService implements ArtAddToOrderService {

  @Autowired
  private ArtAddToOrderDao artAddToOrderDao;
  
  @Override
  public boolean checkStock(int art_id) {
    return artAddToOrderDao.fetchStock(art_id).getArt_stock() > 0;
  }
  
  @Transactional
  public Order addToOrder(int art_id, int user_id, int order_id) {
    log.info("user_id = {} has requested to add art art_id = {} to order order_id = {}", user_id, art_id, order_id);
    
    Art art = artAddToOrderDao.fetchPrice(art_id);
    BigDecimal price = art.getPrice();
    
    artAddToOrderDao.reduceStock(art_id);
    
    Order order = artAddToOrderDao.fetchOrder(order_id);
    
    return artAddToOrderDao.addToOrder(art_id, user_id, price, order_id, order);
   
  } 
}


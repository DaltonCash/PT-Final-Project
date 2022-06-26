package com.promineotech.art.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.art.dao.ArtOrderDao;
import com.promineotech.art.entity.Art;
import com.promineotech.art.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtOrderService implements ArtOrderService {
  
  @Autowired
  private ArtOrderDao artOrderDao;
  
  @Override
  public boolean checkStock(int art_id) {
    return artOrderDao.fetchStock(art_id).getArt_stock() > 0;
  }
  
  @Transactional
  public Order createOrder(int art_id, int user_id) {
    log.info("Order with art_id = {} has been requested by {} in service.",art_id, user_id);
    
    Art art = artOrderDao.fetchPrice(art_id);
    BigDecimal price = art.getPrice();
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String order_date = LocalDateTime.now().format(formatter);
    
    artOrderDao.reduceStock(art_id);
    
    String defaultMessage = "Default Message";
        
    return artOrderDao.saveOrder(art_id, user_id, price, order_date, defaultMessage);
  } 
}

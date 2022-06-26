package com.promineotech.art.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.dao.ArtMessageDao;
import com.promineotech.art.entity.Order;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtMessageService implements ArtMessageService {
  
  @Autowired
  private ArtMessageDao artMessageDao;
  
  @Transactional
  public Order changeMessage(int order_id, String message) {
    log.info("A request to change message for order_id = {} has been made with message: {}",order_id, message);
    try {
      return artMessageDao.changeMessage(order_id, message);
    }catch(TransientDataAccessResourceException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No order was found with supplied order_id");
    }
   
  }
}

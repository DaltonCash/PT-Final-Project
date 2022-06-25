package com.promineotech.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.entity.Order;
import com.promineotech.art.service.ArtOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtOrderController implements ArtOrderController {

  @Autowired
  private ArtOrderService artOrderService;

  @Override
  public Order createOrder(int art_id, int user_id) {
    
    log.info("An Order Request has been made for Art: art_id = {} has been requested by user_id = {}.",art_id, user_id);
    log.info("Checking to see if art_id: {} has a stock higher than 0",art_id);
    
    try {
      if(artOrderService.checkStock(art_id)) {
        log.info("art_id: {} is in stock! creating order...", art_id);
        
        return artOrderService.createOrder(art_id, user_id);
      }else {
        log.info("art_id: {} is out of stock.",art_id);
        
         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Art is out of stock, try again later!");  
      }
    }catch(TransientDataAccessResourceException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Either the supplied art_id or the user_id is incorrect.");
    }
  }
}

package com.promineotech.art.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.entity.Order;
import com.promineotech.art.service.ArtAddToOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtAddToOrderController implements ArtAddToOrderController {

  @Autowired
  private ArtAddToOrderService artAddToOrderService;

  @Override
  public Order addToOrder(int art_id, int user_id, int order_id) {
    
    log.info("An Order Request has been made to add art. Art: art_id = {} has been requested by user_id = {} for order = {}.",art_id, user_id, order_id);
    log.info("Checking to see if art_id: {} has a stock higher than 0",art_id);
 try {
   if(artAddToOrderService.checkStock(art_id)) {
     log.info("art_id: {} is in stock! creating order...", art_id);
     
     return artAddToOrderService.addToOrder(art_id, user_id, order_id);
   }else {
     log.info("art_id: {} is out of stock.",art_id);
     
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Art is out of stock, try again later!");  
   }
    }catch(TransientDataAccessResourceException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The supplied art_id, user_id, or order_id does not exist.");
    }
    
  }
}
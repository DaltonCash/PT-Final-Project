package com.promineotech.art.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.entity.Order;
import com.promineotech.art.service.user.ArtDeleteService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtDeleteController implements ArtDeleteController{

  @Autowired
  private ArtDeleteService artDeleteService;
  
  @Override
  public Order deleteOrder(int order_id) {
    log.info("A request to delete order with order_id = {} has been made",order_id);
    try{
      return artDeleteService.deleteOrder(order_id);
    }catch(TransientDataAccessResourceException e){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested order does not exist.");
    }
  }
}

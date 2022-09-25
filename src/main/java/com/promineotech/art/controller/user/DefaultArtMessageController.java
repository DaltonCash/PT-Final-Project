package com.promineotech.art.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Order;
import com.promineotech.art.service.user.ArtMessageService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtMessageController implements ArtMessageController{

  @Autowired
  private ArtMessageService artMessageService;

  @Override
  public Order changeMessage(int order_id, String message) {
    log.info("A message update has been requested on order_id = {}, with the message: {}",order_id, message);
    return artMessageService.changeMessage(order_id, message);
  }
}

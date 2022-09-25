package com.promineotech.art.controller.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Art;
import com.promineotech.art.service.user.ArtViewOrderService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtViewOrderController implements ArtViewOrderController {
  
  @Autowired
  private ArtViewOrderService artViewOrderService;
  
  @Override
  public List<Art> fetchArtFromOrder(int order_id) {
    log.info("order_id = {}", order_id);
        return artViewOrderService.fetchArtFromOrder(order_id);
  }
}

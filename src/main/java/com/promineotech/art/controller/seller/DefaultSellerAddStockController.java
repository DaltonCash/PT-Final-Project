package com.promineotech.art.controller.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Art;
import com.promineotech.art.service.seller.SellerAddStockService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultSellerAddStockController implements SellerAddStockController {

  @Autowired
  private SellerAddStockService sellerAddStockService;

  @Override
  public Art addStock(int art_id, int stock_to_add) {
    log.info("Controller: A request has been made to add stock to art. "
        + "Parameters: , art_id = {}, stock_to_add = {}", art_id, stock_to_add);
    return sellerAddStockService.addStock(art_id, stock_to_add);
  }
}
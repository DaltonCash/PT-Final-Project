package com.promineotech.art.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.seller.SellerAddStockDao;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultSellerAddStockService implements SellerAddStockService{

  @Autowired
  private SellerAddStockDao sellerAddStockDao;
  
  @Override
  public Art addStock(int art_id, int stock_to_add) {
    log.info("Service: A request has been made to add stock to art. "
        + "Parameters: , art_id = {}, stock_to_add = {}", art_id, stock_to_add);
    log.info("Service: Fetching art. art_id = {}", art_id);
    Art art = sellerAddStockDao.fetchArt(art_id);
    return sellerAddStockDao.addStock(art, stock_to_add);
  }

}

package com.promineotech.art.controller.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Art;
import com.promineotech.art.service.seller.SellerAddArtService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultSellerAddArtController implements SellerAddArtController {

  @Autowired
  private SellerAddArtService sellerAddArtService;

  @Override
  public Art addArt(int seller_id, String title, String artist_name, String art_period,
      String art_medium, int creation_year, int art_stock, double price) {
    
      log.info("Controller: A request has been made to add art. Parameters: "
          + "seller_id = {}, title = {}, artist_name = {}, art_period = {}, art_medium = {}, "
          + "creation_year = {}, art_stock = {}, price = {}.", 
          seller_id, title, artist_name, art_period, art_medium, creation_year, art_stock, price);
      
    return sellerAddArtService.addArt(seller_id, title, artist_name, art_period,
         art_medium, creation_year, art_stock, price);
  }
}

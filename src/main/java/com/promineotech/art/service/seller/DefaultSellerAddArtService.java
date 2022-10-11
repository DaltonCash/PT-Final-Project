package com.promineotech.art.service.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.seller.SellerAddArtDao;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultSellerAddArtService implements SellerAddArtService{

  @Autowired
  private SellerAddArtDao sellerAddArtDao;

  @Override
  public Art addArt(int seller_id, String title, String artist_name, String art_period,
      String art_medium, int creation_year, int art_stock, double price) {
    
      log.info("Service: A request has been made to add art. Parameters: "
        + "seller_id = {}, title = {}, artist_name = {}, art_period = {}, art_medium = {}, "
        + "creation_year = {}, art_stock = {}, price = {}.", 
        seller_id, title, artist_name, art_period, art_medium, creation_year, art_stock, price);
    
  return sellerAddArtDao.addArt(seller_id, title, artist_name, art_period,
       art_medium, creation_year, art_stock, price);
  }

}
package com.promineotech.art.service.seller;

import com.promineotech.art.entity.Art;

public interface SellerAddArtService {

  Art addArt(int seller_id, String title, String artist_name, String art_period,
      String art_medium, int creation_year, int art_stock, double price);

}

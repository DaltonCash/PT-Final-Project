package com.promineotech.art.dao.seller;

import com.promineotech.art.entity.Art;

public interface SellerAddArtDao {

  Art addArt(int seller_id, String title, String artist_name, String art_period,
      String art_medium, int creation_year, int art_stock, double price);

}

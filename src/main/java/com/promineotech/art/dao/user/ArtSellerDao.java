package com.promineotech.art.dao.user;

import java.util.List;
import com.promineotech.art.entity.Art;
import com.promineotech.art.entity.Seller;

public interface ArtSellerDao {

  List<Art> fetchArtBySellerId(List<Seller> seller_ids);

  List<Seller> fetchSellerIdBySellerName(String seller);
  
}

package com.promineotech.art.dao.seller;

import java.util.List;
import com.promineotech.art.entity.Seller;

public interface SellerAddSellerDao {

  List<Seller> fetchSellers();

  Seller addSeller(String seller_name, String password, String first_name, String last_name,
      String email);
  
}

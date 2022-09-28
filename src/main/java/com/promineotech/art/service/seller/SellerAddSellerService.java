package com.promineotech.art.service.seller;

import com.promineotech.art.entity.Seller;

public interface SellerAddSellerService {

  Seller addSeller(String seller_name, String password, String first_name, String last_name, String email);

  boolean checkEmailAndUsername(String email, String user_name);

}

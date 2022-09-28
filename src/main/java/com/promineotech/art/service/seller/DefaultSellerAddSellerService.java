package com.promineotech.art.service.seller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.dao.seller.SellerAddSellerDao;
import com.promineotech.art.entity.Seller;
import com.promineotech.art.service.seller.DefaultSellerAddSellerService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultSellerAddSellerService implements SellerAddSellerService {

  @Autowired
  private SellerAddSellerDao sellerAddSellerDao;

  @Override
  public Seller addSeller(String seller_name, String password, String first_name, String last_name,
      String email) {
    log.info("Adding new seller to database in service.Parameters: "
        + "seller_name = {}, password = {}, first_name = {}, last_name = {}, email = {}",
        seller_name, password, first_name, last_name, email);
    
    return sellerAddSellerDao.addSeller(seller_name, password, first_name, last_name, email);
  }

  @Override
  public boolean checkEmailAndUsername(String email, String seller_name) {
    List<Seller> sellerList = sellerAddSellerDao.fetchSellers();
    for (Seller seller : sellerList) {
      if (seller.getEmail().equals(email)) {
        log.info("Email, \"{}\", is already in use.", email);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "The email entered is currently in use.");
      }
      if (seller.getSeller_name().equals(seller_name)) {
        log.info("Seller's name, \"{}\", is already in use.", seller_name);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "The seller's username entered is currently in use.");
      }
    }
    return false;
  }
}
package com.promineotech.art.controller.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.entity.Seller;
import com.promineotech.art.service.seller.SellerAddSellerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultSellerAddSellerController implements SellerAddSellerController {

  @Autowired
  private SellerAddSellerService sellerAddSellerService;

  @Override
  public Seller addSeller(String seller_name, String password, String first_name, String last_name,
      String email) {
    log.info(
        "A request has been made to add seller. Parameters: seller_name = {}, seller_password = {}, first_name = {}, last_name = {}, email = {}",
        seller_name, password, first_name, last_name, email);
    log.info("Checking whether seller_name and email are currently in use. seller_name = {}, email = {}");

      if (!sellerAddSellerService.checkEmailAndUsername(email, seller_name)) {
        return sellerAddSellerService.addSeller(seller_name, password, first_name, last_name, email);
      } else {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "The email or seller's name entered is currently in use.");
    }
  }
}

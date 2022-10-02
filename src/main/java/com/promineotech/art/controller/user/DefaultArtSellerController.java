package com.promineotech.art.controller.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Art;
import com.promineotech.art.service.user.ArtSellerService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtSellerController implements ArtSellerController {

  @Autowired
  private ArtSellerService artSellerService;

  @Override
  public List<Art> fetchArtBySeller(String seller) {
    log.info("Controller: The fetchArtBySeller method was called with argument: (seller = {})", seller);
        return artSellerService.fetchArtBySeller(seller);
  }
}
package com.promineotech.art.service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.dao.user.ArtSellerDao;
import com.promineotech.art.entity.Art;
import com.promineotech.art.entity.Seller;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtSellerService implements ArtSellerService {

  @Autowired
  private ArtSellerDao artSellerDao;

  @Override
  public List<Art> fetchArtBySeller(String seller){
    log.info("The fetchArtBySeller method was called with argument: (seller = {})", seller);
    
    List<Seller> seller_ids = artSellerDao.fetchSellerIdBySellerName(seller);
    if(seller_ids.size() == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Sellers were found with the supplied query.");
    }
    
    return artSellerDao.fetchArtBySellerId(seller_ids);
  }
}

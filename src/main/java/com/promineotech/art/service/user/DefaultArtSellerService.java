package com.promineotech.art.service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.user.ArtSellerDao;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtSellerService implements ArtSellerService {

  @Autowired
  private ArtSellerDao artSellerDao;

  @Override
  public List<Art> fetchArtBySeller(String seller){
    log.info("Service: The fetchArtBySeller method was called with argument: (seller = {})", seller);
    return artSellerDao.fetchArtBySeller(seller);
  }
}

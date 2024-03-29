package com.promineotech.art.service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.user.ArtViewOrderDao;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtViewOrderService implements ArtViewOrderService {

  @Autowired
  private ArtViewOrderDao artViewOrderDao;

  @Override
  public List<Art> fetchArtFromOrder(int order_id){
    log.info("The fetchArtFromOrder method was called with argument: (order_id = {})", order_id);

    return artViewOrderDao.fetchArtFromOrder(order_id);
  }
}


package com.promineotech.art.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.ArtPeriodDao;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtPeriodService implements ArtPeriodService {
  
  @Autowired
  private ArtPeriodDao artPeriodDao;
  
  public List<Art> fetchArtByPeriod(String period){
    log.info("The fetchArtByPeriod method was called with argument: (period = {})", period);
    
    return artPeriodDao.fetchArtByPeriod(period);
  }
}

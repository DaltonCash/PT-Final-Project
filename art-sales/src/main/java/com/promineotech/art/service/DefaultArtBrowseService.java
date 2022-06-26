package com.promineotech.art.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.ArtBrowseDao;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtBrowseService implements ArtBrowseService {
  
  @Autowired
  private ArtBrowseDao artBrowseDao;
  
  public List<Art> fetchArt(){
    log.info("All art has been requested in the Service Layer");
    
    return artBrowseDao.fetchArt();
  }
}

package com.promineotech.art.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.ArtTitleDao;
import com.promineotech.art.entity.Art;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtTitleService implements ArtTitleService {
  
  @Autowired
  private ArtTitleDao artTitleDao;
  
  public List<Art> fetchArtByTitle(String title){
    log.info("The fetchArtByTitle method was called with argument: (title = {})", title);
    
    return artTitleDao.fetchArtByTitle(title);
  }
}

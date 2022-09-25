package com.promineotech.art.service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.user.ArtArtistDao;
import com.promineotech.art.entity.Art;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtArtistService implements ArtArtistService {
  
  @Autowired
  private ArtArtistDao artArtistDao;
  
  public List<Art> fetchArtByArtist(String artist){
    log.info("The fetchArtByArtist method was called with argument: (artist = {})", artist);
    
    return artArtistDao.fetchArtByArtist(artist);
  }
}

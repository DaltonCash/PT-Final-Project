package com.promineotech.art.controller.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Art;
import com.promineotech.art.service.user.ArtArtistService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtArtistController implements ArtArtistController {

  @Autowired
  private ArtArtistService artArtistService;

  @Override
  public List<Art> fetchArtByArtist(String artist) {
    log.info("Art artist = {}", artist);
        return artArtistService.fetchArtByArtist(artist);
  }
}

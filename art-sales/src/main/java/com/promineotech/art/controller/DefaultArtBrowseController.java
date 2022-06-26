package com.promineotech.art.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Art;
import com.promineotech.art.service.ArtBrowseService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtBrowseController implements ArtBrowseController {

  @Autowired
  private ArtBrowseService artBrowseService;
  
  @Override
  public List<Art> fetchArt() {
    log.info("All art has been requested in controller");
        return artBrowseService.fetchArt();
  }
}

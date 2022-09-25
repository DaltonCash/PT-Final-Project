package com.promineotech.art.controller.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Art;
import com.promineotech.art.service.user.ArtTitleService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtTitleController implements ArtTitleController {

  @Autowired
  private ArtTitleService artTitleService;
  
  @Override
  public List<Art> fetchArtByTitle(String title) {
    log.info("Art title = {}", title);
        return artTitleService.fetchArtByTitle(title);
  }
}

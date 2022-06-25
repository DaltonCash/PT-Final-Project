package com.promineotech.art.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Art;
import com.promineotech.art.service.ArtPeriodService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtPeriodController implements ArtPeriodController {

  @Autowired
  private ArtPeriodService artPeriodService;
  
  @Override
  public List<Art> fetchArtByPeriod(String period) {
    log.info("Art period = {}", period);
        return artPeriodService.fetchArtByPeriod(period);
  }
}

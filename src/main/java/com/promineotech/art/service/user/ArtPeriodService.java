package com.promineotech.art.service.user;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtPeriodService {

  List<Art> fetchArtByPeriod(String period);

}

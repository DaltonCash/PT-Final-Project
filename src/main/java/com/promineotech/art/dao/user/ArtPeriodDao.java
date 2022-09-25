package com.promineotech.art.dao.user;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtPeriodDao {

  List<Art> fetchArtByPeriod(String period);

}

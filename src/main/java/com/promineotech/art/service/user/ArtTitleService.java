package com.promineotech.art.service.user;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtTitleService {

  List<Art> fetchArtByTitle(String title);
  
}

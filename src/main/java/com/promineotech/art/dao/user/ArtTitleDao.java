package com.promineotech.art.dao.user;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtTitleDao {

  List<Art> fetchArtByTitle(String title);

}

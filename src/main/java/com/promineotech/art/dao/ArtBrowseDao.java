package com.promineotech.art.dao;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtBrowseDao {

  List<Art> fetchArt();
  
}

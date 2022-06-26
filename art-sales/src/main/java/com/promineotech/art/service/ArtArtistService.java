package com.promineotech.art.service;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtArtistService {

  List<Art> fetchArtByArtist(String artist);
  
}

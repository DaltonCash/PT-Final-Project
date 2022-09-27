package com.promineotech.art.service.user;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtArtistService {

  List<Art> fetchArtByArtist(String artist);

}

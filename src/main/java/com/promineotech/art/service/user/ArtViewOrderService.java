package com.promineotech.art.service.user;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtViewOrderService {

  List<Art> fetchArtFromOrder(int order_id);
  
}

package com.promineotech.art.dao.user;

import java.util.List;
import com.promineotech.art.entity.Art;

public interface ArtViewOrderDao {

  List<Art> fetchArtFromOrder(int order_id);

}

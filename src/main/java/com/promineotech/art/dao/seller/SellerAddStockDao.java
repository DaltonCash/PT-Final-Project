package com.promineotech.art.dao.seller;

import com.promineotech.art.entity.Art;

public interface SellerAddStockDao {

  Art addStock(Art art, int stock_to_add);

  Art fetchArt(int art_id);

}

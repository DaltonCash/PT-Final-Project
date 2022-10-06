package com.promineotech.art.service.seller;

import com.promineotech.art.entity.Art;

public interface SellerAddStockService {

  Art addStock(int art_id, int stock_to_add);

}

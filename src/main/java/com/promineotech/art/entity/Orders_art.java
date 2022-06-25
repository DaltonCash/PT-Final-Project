package com.promineotech.art.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Orders_art {
  
  private int orders_art_id;
  private int art_id;
  private int order_id;

}

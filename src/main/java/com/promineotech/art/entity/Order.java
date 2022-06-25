package com.promineotech.art.entity;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class Order {
  
  
  private int order_id;
  private int user_id;
  private BigDecimal price; 
  private final String order_date;
  private String message;
  

}

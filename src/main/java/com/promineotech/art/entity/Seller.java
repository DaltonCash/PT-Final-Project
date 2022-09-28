package com.promineotech.art.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Seller {

  private int seller_id;
  private String seller_name;
  private String password;
  private String first_name;
  private String last_name;
  private String email;

}

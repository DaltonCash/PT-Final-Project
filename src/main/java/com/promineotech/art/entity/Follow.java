package com.promineotech.art.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Follow {

  private int follow_id;
  private int user_id;
  private int seller_id;

}

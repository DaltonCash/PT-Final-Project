package com.promineotech.art.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  
  private int user_id;
  private String user_name;
  private String password;
  private String first_name;
  private String last_name;
  private String email;
  
}

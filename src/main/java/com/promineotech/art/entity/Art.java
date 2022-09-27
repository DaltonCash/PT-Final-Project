package com.promineotech.art.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Art {

  int art_id;
  String title;
  String artist_name;
  String art_period;
  String art_medium;
  int creation_year;
  int art_stock;
  BigDecimal price;

}

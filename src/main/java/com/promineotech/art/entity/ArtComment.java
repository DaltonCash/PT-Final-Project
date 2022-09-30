package com.promineotech.art.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtComment {

  private int comment_id;
  private int art_id;
  private int user_id;
  private final String post_date;
  private String message;
  private int likes;
  private int stars;

}
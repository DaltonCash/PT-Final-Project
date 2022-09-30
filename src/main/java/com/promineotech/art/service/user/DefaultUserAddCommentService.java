package com.promineotech.art.service.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.user.UserAddCommentDao;
import com.promineotech.art.entity.ArtComment;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserAddCommentService implements UserAddCommentService {

  @Autowired
  private UserAddCommentDao userAddCommentDao;

  @Override
  public ArtComment createArtComment(int art_id, int user_id, String message, int stars) {
    log.info("Service: art_id = {}, user_id = {}, message = {}, stars = {}", art_id, user_id, message, stars);
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    String post_date = LocalDateTime.now().format(formatter);
    
    return userAddCommentDao.createArtComment(art_id, user_id, message, stars, post_date);
  }
}
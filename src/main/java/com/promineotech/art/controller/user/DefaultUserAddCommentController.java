package com.promineotech.art.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.ArtComment;
import com.promineotech.art.service.user.UserAddCommentService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultUserAddCommentController implements UserAddCommentController {

  @Autowired
  private UserAddCommentService userAddCommentService;

  @Override
  public ArtComment createArtComment(int art_id, int user_id, String message, int stars) {
    log.info("Controller: art_id = {}, user_id = {}, message = {}, stars = {}", art_id, user_id, message, stars);
    return userAddCommentService.createArtComment(art_id, user_id, message, stars);
  }
}
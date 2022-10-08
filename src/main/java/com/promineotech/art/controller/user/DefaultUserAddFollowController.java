package com.promineotech.art.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.promineotech.art.entity.Follow;
import com.promineotech.art.service.user.UserAddFollowService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultUserAddFollowController implements UserAddFollowController {

  @Autowired
  private UserAddFollowService userAddFollowService;

  @Override
  public Follow createFollow(int user_id, int seller_id) {
    log.info("Controller: Creating Follow. user_id = {}, seller_id = {}.",user_id, seller_id);

    return userAddFollowService.createFollow(user_id, seller_id);
     
  }
}
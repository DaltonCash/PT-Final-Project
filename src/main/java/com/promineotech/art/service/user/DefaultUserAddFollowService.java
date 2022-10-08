package com.promineotech.art.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.art.dao.user.UserAddFollowDao;
import com.promineotech.art.entity.Follow;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserAddFollowService implements UserAddFollowService {

  @Autowired
  private UserAddFollowDao userAddFollowDao;

  @Override
  public Follow createFollow(int user_id, int seller_id) {
    log.info("Service: Creating Follow. user_id = {}, seller_id = {}",user_id, seller_id);

    return userAddFollowDao.createFollow(user_id, seller_id);
  }
}

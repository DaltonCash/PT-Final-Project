package com.promineotech.art.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.art.dao.user.ArtUserDao;
import com.promineotech.art.entity.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultArtUserService implements ArtUserService{

  @Autowired
  private ArtUserDao artUserDao;

  @Override
  @Transactional
  public User fetchUserInfo(String user_name, String password) {
    log.info("The fetchUserInfo method was called with arguments: (user_name = {}, password = {})", user_name, password);

    return artUserDao.fetchUserInfo(user_name, password);
  }
}

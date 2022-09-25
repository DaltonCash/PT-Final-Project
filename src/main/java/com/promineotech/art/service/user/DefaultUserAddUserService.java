package com.promineotech.art.service.user;

import org.springframework.stereotype.Service;
import com.promineotech.art.entity.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserAddUserService implements UserAddUserService {

  @Override
  public User addUser(String user_name, String password, String first_name, String last_name,
      String email) {
    return null;
  }

  @Override
  public boolean checkUserName(String user_name) {
    return false;
  }

  @Override
  public boolean checkEmail(String email) {
    return false;
  }
}

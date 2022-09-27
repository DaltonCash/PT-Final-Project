package com.promineotech.art.service.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.dao.user.UserAddUserDao;
import com.promineotech.art.entity.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultUserAddUserService implements UserAddUserService {

  @Autowired
  private UserAddUserDao userAddUserDao;

  @Override
  public User addUser(String user_name, String password, String first_name, String last_name,
      String email) {
    log.info("Adding new user to database in service.Parameters: "
        + "user_name = {}, password = {}, first_name = {}, last_name = {}, email = {}",
        user_name, password, first_name, last_name, email);
    
    return userAddUserDao.addUser(user_name, password, first_name, last_name, email);
  }

  @Override
  public boolean checkEmailAndUsername(String email, String user_name) {
    List<User> userList = userAddUserDao.fetchUsers();
    for (User user : userList) {
      if (user.getEmail().equals(email)) {
        log.info("Email \"{}\" is already in use.", email);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "The email entered is currently in use.");
      }
      if (user.getUser_name().equals(user_name)) {
        log.info("Username \"{}\" is already in use.", user_name);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "The username entered is currently in use.");
      }
    }
    return false;
  }
}

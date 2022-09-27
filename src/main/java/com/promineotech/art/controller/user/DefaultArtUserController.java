package com.promineotech.art.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.promineotech.art.entity.User;
import com.promineotech.art.service.user.ArtUserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultArtUserController implements ArtUserController {

  @Autowired
  private ArtUserService artUserService;

  @Override
  public User fetchUserInfo(String user_name, String password) {
    log.info("User has requested to log in with credentials: user_name = {}, password = {}", user_name, password);
    try{
      return artUserService.fetchUserInfo(user_name, password);
    }catch(TransientDataAccessResourceException e){
      log.info("Either the username or password are incorrect");
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Credentials");
    }
  }
}

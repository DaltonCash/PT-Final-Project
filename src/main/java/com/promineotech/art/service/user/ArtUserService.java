package com.promineotech.art.service.user;

import com.promineotech.art.entity.User;

public interface ArtUserService {

  User fetchUserInfo(String user_name, String password);

}

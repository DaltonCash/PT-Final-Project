package com.promineotech.art.dao.user;

import com.promineotech.art.entity.User;

public interface ArtUserDao {

  User fetchUserInfo(String user_name, String password);

}

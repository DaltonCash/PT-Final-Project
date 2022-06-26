package com.promineotech.art.service;

import com.promineotech.art.entity.Users;

public interface ArtUserService {

  Users fetchUserInfo(String user_name, String password);

}

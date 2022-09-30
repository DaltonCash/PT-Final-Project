package com.promineotech.art.service.user;

import com.promineotech.art.entity.ArtComment;

public interface UserAddCommentService {

  ArtComment createArtComment(int art_id, int user_id, String message, int stars);

}

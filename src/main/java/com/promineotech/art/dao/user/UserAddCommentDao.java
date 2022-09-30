package com.promineotech.art.dao.user;

import com.promineotech.art.entity.ArtComment;

public interface UserAddCommentDao {

  ArtComment createArtComment(int art_id, int user_id, String message, int stars, String post_date);

}

package com.promineotech.art.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.art.entity.ArtComment;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/add-art-comment")
public interface UserAddCommentController {


  @Operation(
      summary = "Post a comment",
      description = "Post a comment under art",
      responses = {
          @ApiResponse(responseCode = "201",
              description = "The created art order is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ArtComment.class))),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
            description = "no art was found with the input criteria or the user may not exist",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred.",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "art_id",
            required = true,
            description = "ID of desired Art (i.e., '3')"),
          @Parameter(name = "user_id",
          required = true,
          description = "Your user id (i.e., '57')"),
          @Parameter(name = "message",
          required = true,
          description = "Message to post"),
          @Parameter(name = "stars",
          required = true,
          description = "The amount of stars to rate the art. 1-bad, 5-excellent. "
              + "Leave blank to omit rating (i.e., '4')"),

      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  ArtComment createArtComment(
      @RequestParam int art_id,
      @RequestParam int user_id,
      @RequestParam String message,
      @RequestParam int stars);
}
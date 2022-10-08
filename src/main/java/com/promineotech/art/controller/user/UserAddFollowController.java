package com.promineotech.art.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.art.entity.Follow;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/follow")
public interface UserAddFollowController {


  @Operation(
      summary = "Follow a Seller",
      description = "Follow a Seller using user_id",
      responses = {
          @ApiResponse(responseCode = "201",
              description = "The new follow link between User and Seller is returned.",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Follow.class))),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
            description = "No user or seller was found with the given ids",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred.",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "user_id",
            required = true,
            description = "Your user id (i.e., '3')"),
          @Parameter(name = "seller_id",
          required = true,
          description = "id of seller to follow (i.e., '1')"),

      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Follow createFollow(
      @RequestParam int user_id,
      @RequestParam int seller_id);
}
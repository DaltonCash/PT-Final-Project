package com.promineotech.art.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.art.entity.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/user")
@OpenAPIDefinition(info = @Info(title = "Art Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface ArtUserController {
  
  @Operation(
      summary = "Returns user's info",
      description = "Returns user's info if a valid username and password combination is supplied.",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = "A User has supplied valid credentials and user's info is returned", 
              content = @Content(mediaType = "application/json", 
              schema = @Schema(implementation = User.class))),
          @ApiResponse(responseCode = "401", 
            description = "The user's credentials are invalid", 
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
            description = "An unplanned error occurred", 
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "user_name", 
            required = true, 
            description = "Username associated with your account. (i.e., 'MockUser')"),
          @Parameter(name = "password", 
          required = true, 
          description = "Password associated with your account. (i.e., 'password123')"),
      }
  )
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  User fetchUserInfo(
      @RequestParam String user_name,
      @RequestParam String password); 
}
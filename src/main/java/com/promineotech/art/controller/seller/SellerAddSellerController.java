package com.promineotech.art.controller.seller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.art.entity.Seller;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/addseller")
@OpenAPIDefinition(info = @Info(title = "Art Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface SellerAddSellerController {


  @Operation(
      summary = "Create Seller",
      description = "Create a seller to add art",
      responses = {
          @ApiResponse(responseCode = "201",
              description = "The new seller profile is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Seller.class))),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
            description = "A Seller with the same name or email is already in use.",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred.",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "seller_name",
            required = true,
            description = "desired username for seller (i.e., 'TheArtConnoisseur')"),
          @Parameter(name = "password",
          required = true,
          description = "desired password (i.e., '123password123')"),
          @Parameter(name = "first_name",
          required = true,
          description = "your first name (i.e., 'John')"),
          @Parameter(name = "last_name",
          required = true,
          description = "your last name (i.e., 'Smith')"),
          @Parameter(name = "email",
          required = true,
          description = "email to associate with account (i.e., 'johnsmith@email.com')"),

      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Seller addSeller(
      @RequestParam String Seller_name,
      @RequestParam String password,
      @RequestParam String first_name,
      @RequestParam String last_name,
      @RequestParam String email);
}
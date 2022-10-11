package com.promineotech.art.controller.seller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.art.entity.Art;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/addart")
@OpenAPIDefinition(info = @Info(title = "Art Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface SellerAddArtController {


  @Operation(
      summary = "Post Art",
      description = "Add art to your listings.",
      responses = {
          @ApiResponse(responseCode = "201",
              description = "The posted art is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Art.class))),
          @ApiResponse(responseCode = "400",
              description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
              description = "The Seller does not exist",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
              description = "An unplanned error occurred.",
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "seller_id",
          required = true,
          description = "id of seller (i.e., '2')"),
          @Parameter(name = "title",
          required = true,
          description = "title of art (i.e., 'Mona Lisa')"),
          @Parameter(name = "artist_name",
          required = true,
          description = "the name of artist (i.e., 'Leonardo Da Vinci')"),
          @Parameter(name = "art_period",
          required = true,
          description = "the defining period of the art (i.e., 'Renaissance')"),
          @Parameter(name = "art_medium",
          required = true,
          description = "the medium of the art (i.e., 'oil on canvas')"),
          @Parameter(name = "creation_year",
          required = true,
          description = "the year the art was created in (i.e., '1977')"),
          @Parameter(name = "art_stock",
          required = true,
          description = "how much of the art is in stock (i.e., '3')"),
          @Parameter(name = "price",
          required = true,
          description = "the price the art is being sold at (i.e., '109.33')"),

      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Art addArt(
      @RequestParam int seller_id,
      @RequestParam String title,
      @RequestParam String artist_name,
      @RequestParam String art_period,
      @RequestParam String art_medium,
      @RequestParam int creation_year,
      @RequestParam int art_stock,
      @RequestParam double price);
}

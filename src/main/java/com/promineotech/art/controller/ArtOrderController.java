package com.promineotech.art.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.art.entity.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/orders")
public interface ArtOrderController {
  
  
  @Operation(
      summary = "Create an order for art",
      description = "Returns the order",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = "The created art order is returned", 
              content = @Content(mediaType = "application/json", 
              schema = @Schema(implementation = Order.class))),
          @ApiResponse(responseCode = "400", 
            description = "The request parameters are invalid", 
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
            description = "no art was found with the input criteria or the art may be out of stock", 
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
            description = "An unplanned error occurred.", 
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "art_id",
            required = true, 
            description = "ID of desired Art (i.e., '1')"),
          @Parameter(name = "user_id",
          required = true, 
          description = "Your user id (i.e., '1')"),
          
      }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Order createOrder(
      @RequestParam int art_id,
      @RequestParam int user_id);
}

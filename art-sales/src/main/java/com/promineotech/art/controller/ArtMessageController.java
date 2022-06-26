package com.promineotech.art.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.art.entity.Order;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/message")
@OpenAPIDefinition(info = @Info(title = "Art Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface ArtMessageController {
  
  @Operation(
      summary = "Change an order's message",
      description = "Returns the order along with the updated message.",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = "The order's message has been successfully updated!", 
              content = @Content(mediaType = "application/json", 
              schema = @Schema(implementation = Order.class))),
          @ApiResponse(responseCode = "400", 
            description = "The request parameters are invalid", 
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404", 
            description = "No order was found with the supplied order_id", 
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
            description = "An unplanned error occurred.", 
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "order_id",
            required = true, 
            description = "ID of Order (i.e., '1')"),
          @Parameter(name = "message",
          required = true, 
          description = "New message for order (i.e., 'New paintings for Jane's living room.')"),
          
      }
  )
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Order changeMessage( 
      @RequestParam int order_id,
      @RequestParam String message);
}
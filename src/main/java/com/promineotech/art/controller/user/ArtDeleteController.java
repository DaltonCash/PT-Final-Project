package com.promineotech.art.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@RequestMapping("/delete")
@OpenAPIDefinition(info = @Info(title = "Art Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface ArtDeleteController {

  @Operation(
      summary = "Delete an Order",
      description = "Deletes the order of a specified order_id",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "Order deleted successfully",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Order.class))),
          @ApiResponse(responseCode = "404",
            description = "Supplied order_id does not exist.",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred.",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "order_id",
            required = true,
            description = "ID of Order (i.e., '1')"),

      }
  )
  @DeleteMapping
  @ResponseStatus(code = HttpStatus.OK)
  Order deleteOrder(@RequestParam int order_id);
}
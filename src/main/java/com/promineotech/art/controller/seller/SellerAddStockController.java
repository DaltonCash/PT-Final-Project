package com.promineotech.art.controller.seller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
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

@RequestMapping("/addstock")
@OpenAPIDefinition(info = @Info(title = "Art Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface SellerAddStockController {


  @Operation(
      summary = "Add Stock",
      description = "Add Stock to an art posting",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "The updated art stock is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Art.class))),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
            description = "The art was not found",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred.",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "art_id",
            required = true,
            description = "art_id of art to add stock (i.e., '5')"),
          @Parameter(name = "stock_to_add",
          required = true,
          description = "How much stock to add to art (i.e., '3')")

      }
  )
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Art addStock(
      @RequestParam int art_id,
      @RequestParam int stock_to_add);
}

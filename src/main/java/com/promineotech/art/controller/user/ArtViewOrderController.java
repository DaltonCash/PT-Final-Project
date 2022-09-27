package com.promineotech.art.controller.user;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

@RequestMapping("/vieworder")
@OpenAPIDefinition(info = @Info(title = "Art Sales Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface ArtViewOrderController {

  @Operation(
      summary = "Returns Art",
      description = "Returns all art included in order",
      responses = {
          @ApiResponse(responseCode = "200",
              description = "A list of Art is returned",
              content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Art.class))),
          @ApiResponse(responseCode = "400",
            description = "The request parameters are invalid",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
            description = "No art was found with the input criteria",
            content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500",
            description = "An unplanned error occurred",
            content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "order_id",
            allowEmptyValue = false,
            required = true,
            description = "the id associated with the order. (i.e., '1')"),
      }
  )

  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Art> fetchArtFromOrder(@RequestParam int order_id);
}

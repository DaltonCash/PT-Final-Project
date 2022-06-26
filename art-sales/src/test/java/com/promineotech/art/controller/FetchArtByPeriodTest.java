package com.promineotech.art.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.promineotech.art.entity.Art;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/ArtSalesSchema.sql",
    "classpath:flyway/migrations/ArtSchema.sql"}, 
    config = @SqlConfig(encoding = "utf-8"))

class FetchArtByPeriodTest {
  
  @Autowired
  private TestRestTemplate restTemplate;
  
  @LocalServerPort
  private int serverPort;

  @Test
  void testThatArtIsReturnedWhenPeriodIsSupplied() {

    String period = "Academic art/Academicism";
    String uri = String.format("http://localhost:%d/period?period=%s", serverPort, period);

    ResponseEntity<List<Art>> response = 
        restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Art>>() {});
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
    List<Art> expected = artExpected();
    assertThat(response.getBody()).isEqualTo(expected);
  }

  List<Art> artExpected(){
    List<Art> list = new LinkedList<>();
    
    list.add(Art.builder()
        .art_id(1)
        .artist_name("Alexandre Cabanel")
        .art_period("Academic art/Academicism")
        .art_medium("Oil on Canvas")
        .creation_year(1874)
        .art_stock(1)
        .price(new BigDecimal("10"))
        .title("The Fallen Angel")
        .build());
    
    return list;
  }
}

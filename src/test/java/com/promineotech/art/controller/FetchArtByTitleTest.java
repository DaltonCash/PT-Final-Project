package com.promineotech.art.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.validation.annotation.Validated;
import com.promineotech.art.entity.Art;


@Validated
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/ArtSalesSchema.sql",
    "classpath:flyway/migrations/ArtSchema.sql"},
    config = @SqlConfig(encoding = "utf-8"))

public class FetchArtByTitleTest {

  @LocalServerPort
  private int serverPort;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void testThatArtIsReturnedWhenNameIsSupplied() {

    String body = createArtBody();
    String uri = String.format("http://localhost:%d/art", serverPort);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
    ResponseEntity<Art> response = restTemplate.exchange(uri,HttpMethod.POST, bodyEntity, Art.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    assertThat(response.getBody()).isNotNull();

    Art art = response.getBody();
    assertThat(art.getArt_id()).isEqualTo(10000);
    assertThat(art.getArt_medium()).isEqualTo("Oil on Canvas");
    assertThat(art.getArt_period()).isEqualTo("Academic art/Academicism");
    assertThat(art.getArt_stock()).isEqualTo(1);
    assertThat(art.getPrice()).isEqualTo(10.00);
    assertThat(art.getTitle()).isEqualTo("The Fallen Angel");
    assertThat(art.getCreation_year()).isEqualTo(1874);
  }

  String createArtBody() {
    return "{\n"
        + "  \"artist_name\":\"ALEXANDRE CABANEL\",\n"
        + "  \"art_period\":\"ACADEMIC ART/ACADEMICISM\",\n"
        + "  \"art_medium\":\"OIL ON CANVAS\",\n"
        + "  \"creation_year\":1874,\n"
        + "  \"art_stock\":\"1\",\n"
        + "  \"title\":\"THE FALLEN ANGEL\",\n"
        + "  \"price\":\"10.00\",\n"
        + "  \n"
        + "}\n"
        + "";
  }
}
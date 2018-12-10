package com.n2;

import static org.assertj.core.api.Assertions.assertThat;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactSpecVersion;
import au.com.dius.pact.model.RequestResponsePact;
import java.io.File;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Consumer {

  @Rule
  public PactProviderRuleMk2 mockProvider
      = new PactProviderRuleMk2("test_provider", "localhost", 8080, this);

  @Pact(consumer = "test_consumer")
  public RequestResponsePact createPact(PactDslWithProvider builder) {

    RequestResponsePact requestResponsePact = builder
        .given("a person named John")
        .uponReceiving("a GET REQUEST for a greeting")
        .path("/greet/john")
        .method("GET")
        .willRespondWith()
        .status(200)
        .body("Hello John")
        .toPact();
      File file = new File(".");
      System.out.println("ABCDE = "+file.getAbsolutePath());
      requestResponsePact.write("src/test/resources/pacts/", PactSpecVersion.V3);
      return requestResponsePact;
  }

  @Test
  @PactVerification("test_provider")
  public void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody() {

    // when
    String url = mockProvider.getUrl() + "/greet/john";
    ResponseEntity<String> response = new RestTemplate()
        .getForEntity(url, String.class);

    // then
    assertThat(response.getStatusCode().value()).isEqualTo(200);
    assertThat(response.getBody()).isEqualTo("Hello John");
  }

}

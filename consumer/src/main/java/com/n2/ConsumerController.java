package com.n2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {


  private RestTemplate restTemplate = new RestTemplate();

  @GetMapping(value = "/greet/{name}")
  public String testMe(@PathVariable String name) {

    String url = "http://localhost:8080/greet/" + name;

    return restTemplate.getForEntity(url, String.class).getBody();
  }

}

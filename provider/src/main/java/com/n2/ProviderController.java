package com.n2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

  @GetMapping(value = "/greet/{name}")
  public String testMe(@PathVariable String name) {
    return "Hello " + name;
  }

}

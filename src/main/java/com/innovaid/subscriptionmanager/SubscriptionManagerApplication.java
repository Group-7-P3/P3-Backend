package com.innovaid.subscriptionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SubscriptionManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SubscriptionManagerApplication.class, args);
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }

}

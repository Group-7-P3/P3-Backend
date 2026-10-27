package com.innovaid.subscriptionmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SubscriptionManagerApplication {

  final static String DB_URL = "jdbc:postgresql://localhost:5432/local";

  public static void main(String[] args) throws SQLException {
    Connection conn;
    try {
      conn = DriverManager.getConnection(DB_URL, "root", "mysecretpassword");

      SpringApplication.run(SubscriptionManagerApplication.class, args);

      conn.close();
    } catch (SQLException e) {
      throw e;
    }
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }

}

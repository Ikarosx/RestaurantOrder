package cn.ikarosx.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Ikarosx
 * @date 2020/7/6 23:45
 */
@SpringBootApplication
@EnableJpaAuditing
public class RestaurantApplication {
  public static void main(String[] args) {
    SpringApplication.run(RestaurantApplication.class, args);
  }
}

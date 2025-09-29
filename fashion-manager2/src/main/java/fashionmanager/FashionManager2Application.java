package fashionmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FashionManager2Application {

    public static void main(String[] args) {
        SpringApplication.run(FashionManager2Application.class, args);
    }

}
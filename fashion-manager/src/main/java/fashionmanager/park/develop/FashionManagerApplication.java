package fashionmanager.park.develop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("fashionmanager.park.develop.mapper")
public class FashionManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FashionManagerApplication.class, args);
    }
}

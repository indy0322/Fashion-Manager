package fashionmanager.park.develop.configuration;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(basePackages = "fashionmanager.park.develop", annotationClass = Mapper.class)
public class MybatisConfiguration {




}
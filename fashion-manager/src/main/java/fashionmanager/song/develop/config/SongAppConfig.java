package fashionmanager.song.develop.config;

import org.apache.ibatis.annotations.Mapper;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "fashionmanager.song.develop", annotationClass = Mapper.class)
public class SongAppConfig {

    //DTO <-> Entity 변환을 자동으로 도와주는 라이브러리
//    @Bean
//    public ModelMapper modelMapper() {
//        return new ModelMapper();
//    }
}
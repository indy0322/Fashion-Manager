package fashionmanager.park.develop.config;

import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@MapperScan("fashionmanager.park.develop.mapper")
public class AppConfig {

    /* 설명. 미리 build.gradle에 dependency로 추가해 둘 것 */
    /* 설명. DTO <-> Entity 매핑을 위한 modelmapper 라이브러리 bean 추가 */
    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}

    /* 설명. BCrypt 단방향 암호화를 위해 bean 등록 */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
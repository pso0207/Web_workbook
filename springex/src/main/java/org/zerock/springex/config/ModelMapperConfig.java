package org.zerock.springex.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //빈을 직접 등록할 경우 @Bean + @Configuration 조합으로 함.
public class ModelMapperConfig {

    @Bean // 해당 메소드의 실행 결과로 반환된 객체를 스프링의 빈으로 등록시키는 역할을 함.
    public ModelMapper getMapper() { //필요할때마다 new로 안 만들고 그냥 bean에 넣어서 꺼내쓰면 됨

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;

    }
}

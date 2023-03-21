package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    // 많이 사용하지는 않는다.
    // Http 통신 기록, 메소드랑 uri 기타 등등
    // 개발 단계에서만 사용하자
    // 실제 운영서비스에서는 모니터링 툴이나 핀포인트, Zipkin같은 기술을 쓰자
    @Bean
    public InMemoryHttpExchangeRepository inMemoryHttpExchangeRepository(){
        return new InMemoryHttpExchangeRepository();
    }

}

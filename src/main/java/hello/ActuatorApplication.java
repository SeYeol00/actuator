package hello;

import hello.order.gauge.StockConfigV1;
import hello.order.gauge.StockConfigV2;
import hello.order.v0.OrderConfigV0;
import hello.order.v1.OrderConfigV1;
import hello.order.v2.OrderConfigV2;
import hello.order.v3.OrderConfigV3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

// 수동으로 빈 등록
//@Import(OrderConfigV0.class)
//@Import(OrderConfigV1.class)
//@Import(OrderConfigV2.class)
//@Import(OrderConfigV3.class)
//@Import({OrderConfigV3.class,StockConfigV1.class})
@Import({OrderConfigV3.class, StockConfigV2.class})
// 컨트롤러만 컴포넌트 스캔, 즉 자동 빈 등록을 사용
@SpringBootApplication(scanBasePackages = "hello.controller")
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

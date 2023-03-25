package hello.order.v2;


import hello.order.OrderService;
import hello.order.v1.OrderServiceV1;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV2 {

    // 최초 한 번은 호출해야 생성된다.
    @Bean
    OrderService orderService(){
        return new OrderServiceV2();
    }

    // Aop 적용용 클래스
    // 이걸 빈으로 등록하지 않으면 @Counted가 동작 안 한다.
    @Bean
    public CountedAspect countedAspect(MeterRegistry meterRegistry){
        return new CountedAspect(meterRegistry);
    }
}

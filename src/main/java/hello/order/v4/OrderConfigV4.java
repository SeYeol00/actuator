package hello.order.v4;


import hello.order.OrderService;
import hello.order.v3.OrderServiceV3;
import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfigV4 {

    @Bean
    public OrderService orderService(){
        return new OrderServiceV4();
    }

    // Aop 적용용 클래스
    // 이걸 빈으로 등록하지 않으면 @Counted가 동작 안 한다.
    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry){
        return new TimedAspect(meterRegistry);
    }
}

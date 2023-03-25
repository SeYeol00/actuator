package hello.order.gauge;


import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class StockConfigV1 {

    @Bean
    public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry meterRegistry){
        return new MyStockMetric(orderService,meterRegistry);
    }

    @Slf4j
    static class MyStockMetric{
        private OrderService orderService;

        private MeterRegistry meterRegistry;

        public MyStockMetric(OrderService orderService, MeterRegistry meterRegistry) {
            this.orderService = orderService;
            this.meterRegistry = meterRegistry;
        }

        // 매트릭을 확인할 때마다 함수 호출
        @PostConstruct
        public void init(){
            Gauge.builder("my.stock",orderService,service -> {
                log.info("stock gauge call");
                return orderService.getStock().get();
            }).register(meterRegistry);
    }


    }

}

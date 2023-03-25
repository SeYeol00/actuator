package hello.order.v1;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class OrderServiceV1 implements OrderService {

    // 마이크로미터에서 제공하는 카운터용 레지스트리
    // 이걸로 카운터를 설정한다.
    private final MeterRegistry meterRegistry;

    public OrderServiceV1(MeterRegistry meterRegistry){
        this.meterRegistry = meterRegistry;
    }



    private AtomicInteger stock = new AtomicInteger(100);
    @Override
    public void order() {
        log.info("주문");
        // 감소
        stock.decrementAndGet();

        // 카운터 설정
        Counter.builder("my.order")
                .tag("class",this.getClass().getName())
                .tag("method","order")
                .description("order")
                .register(meterRegistry).increment();
    }

    @Override
    public void cancel() {
        log.info("취소");
        // 증가
        stock.incrementAndGet();

        // 카운터 설정
        Counter.builder("my.order")
                .tag("class",this.getClass().getName())
                .tag("method","cancel")
                .description("cancel")
                .register(meterRegistry).increment();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }
}

package hello.order.v3;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class OrderServiceV3 implements OrderService {

    // 마이크로미터에서 제공하는 카운터용 레지스트리
    // 이걸로 카운터를 설정한다.
    private final MeterRegistry meterRegistry;

    private AtomicInteger stock = new AtomicInteger(100);

    public OrderServiceV3(MeterRegistry meterRegistry){
        this.meterRegistry = meterRegistry;
    }
    @Override
    public void order() {
        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(meterRegistry);
        timer.record(()->{
            log.info("주문");
            // 감소
            stock.decrementAndGet();
            sleep(500);
        });

    }

    @Override
    public void cancel() {

        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("cancel")
                .register(meterRegistry);
        timer.record(()->{
            log.info("취소");
            // 증가
            stock.incrementAndGet();
            sleep(500);
        });
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }


    private static void sleep(int l){
        try{
            Thread.sleep(l+new Random().nextInt(200));
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}

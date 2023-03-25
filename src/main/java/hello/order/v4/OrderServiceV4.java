package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


// Aop 적용, 어노테이션 카운터와 똑같은 적용
@Timed
@Slf4j
public class OrderServiceV4 implements OrderService {


    private AtomicInteger stock = new AtomicInteger(100);

    @Override
    public void order() {
            log.info("주문");
            // 감소
            stock.decrementAndGet();
            sleep(500);

    }

    @Override
    public void cancel() {
            log.info("취소");
            // 증가
            stock.incrementAndGet();
            sleep(500);
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

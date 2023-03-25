package hello.order;

import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {
    void order();
    void cancel();

    // 멀티 스레드 상황에서 안전하게 값 증가시키는 동시성 이슈 문제 해결용 Integer
    AtomicInteger getStock();
}

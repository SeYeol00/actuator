package hello.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {

    @GetMapping("/log")
    public String log(){
        // 내려갈 수록 위험한 레벨
        log.trace("trace log");
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
        return "ok";
    }
    
    // 프로메테우스 쿼리 정리
    // 프로메테우스 설명한 김영한 pdf 확인하기
}

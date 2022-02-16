package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP 사용 선언
@Component // 자동 빈 등록
public class TimeTraceAop {

    // @Around : 공통 관심 사항을 적용할 패키지 범위를 지정할 수 있다.
    @Around("execution(* hello.hellospring..*(..))") // hello.hellospring 하위 패키지 모두 적용
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed(); // Aop가 적용된 메소드를 전체 수행하고 리턴되는 데이터를 받는 메소드

        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}

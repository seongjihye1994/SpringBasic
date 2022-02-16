package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	// 이 main 메소드로부터 프로젝트가 시작된다.
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}

// @SpringBootApplication 어노테이션이 디폴트로 붙은 스프링 부트 프로젝트
// 스프링 부트 애플리케이션은 기본적으로 톰캣(웹서버)을 내장하고 있다.
// 그래서 프로젝트를 띄우면서 톰캣도 같이 띄운다.

// file -> settings 에서 gradle 설정에 들어간 후 build and run using과 build test를 gradle이 아닌 intellij로 바꿔준다.
// 그렇게 하면 run 할때 gradle를 통하지 않고 빠르게 서버를 구동할 수 있음.
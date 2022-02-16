package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // @RequestParam(변수) : url 쿼리 스트링에서 ?name=값 으로 요청한 값을 받아온다.
        // 만약 @RequestParam(value = "변수", required = false)로 하면 값이 없어도 에러가 나지 않음.
        model.addAttribute("name", name);

        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // ResponseBody 사용시 뷰 리졸버 사용 x
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;

        // ResponseBody 의 의미는?
        // HTTP의 구조 중 응답 Body 부분에 "hello " + name 이 데이터를 직접 넣어주겠다는 의미
        // 그래서 뷰 리졸버를 사용하지 않는다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {

        // Hello 객체 생성
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }

    // Hello 클래스 생성
    static class Hello {
        // 참고로 static으로 클래스를 만들면 같은 파일 내에 있는 다른 클래스가 가져다 쓸 수 있음.
        // ex) HelloController.Hello

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


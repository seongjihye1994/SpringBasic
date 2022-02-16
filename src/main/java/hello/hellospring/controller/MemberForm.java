package hello.hellospring.controller;

public class MemberForm {

    private String name; // HTML 태그의 name 값과 일치해야 함

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

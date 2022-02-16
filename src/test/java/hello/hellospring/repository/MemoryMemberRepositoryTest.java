package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest { // MemoryMemberRepository 테스트를 위한 클래스

    // 테스트는 굳이 public으로 만들지 않아도 된다.
    // 외부에서 가져다 쓸 일이 없기 때문.

    // MemoryMemberRepository 테스트를 위한 객체 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // Test의 정확한 검증을 위해 db를 clear 해줘야 한다.
    // 그래서 test 메소드가 끝나고 호출되는 afterEach 메소드로 db를 clear 해보자.
    public void afterEach() {
        repository.clearStore(); // db clear
    }

    @Test
    public void save() { // MemoryMemberRepository 클래스에 있는 save() 메소드 test
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 마지막 get()의 의미?
        // Optional에서 값을 꺼낼 때 .get()으로 쓴다.
        
        // 검증 (assert)
        // 일치하는지 확인
        assertThat(member).isEqualTo(result);

    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}

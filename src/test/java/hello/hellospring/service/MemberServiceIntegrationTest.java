package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // spring boot가 제공하는 test. 기존 테스트와 달리 실제 스프링이 구동되고 db 연결까지 된다.
@Transactional // 실제 db 연결까지 되는 테스트다 보니, 테스트 하고 다시 이전으로 원복해야 하는 경우 사용
class MemberServiceIntegrationTest {

    // 테스트는 외부에서 가져다 쓸 일이 없으므로 가장 편리한 필드 인젝션을 보통 사용한다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    // @Commit // 커밋하면 실제 디비에 반영된다. Transactional이 먹히지 않음.
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}
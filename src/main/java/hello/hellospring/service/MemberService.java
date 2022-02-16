package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // JPA를 사용하기 위해서는 필수, JPA는 모든 데이터 변경이 트랜잭션 안에서 실행되어야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     * */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member); // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent: Optional 이 null이 아니면 동작함
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
    * 회원 한명 조회
    * */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

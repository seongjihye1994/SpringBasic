package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository<Member 타입, 식별자 id>
// JpaRepository를 extends 하면
// SpringDataJpaMemberRepository의 구현체를 스프링 데이터 JPA가 직접 생성해서 스프링 컨테이너에 등록한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
    // 이 메소드를 적어준 이유는?
    // Jpa 가 단순 기본적인 메소드는 처리해 준다고 하지만,
    // 복잡한 로직을 처리해야 하는 쿼리 또는 서비스마다 다른 규칙
    // ex. 우리 서비스는 이름으로 조회한다. 와 같은 룰
    // 은 JpaRepository 에서 제공하지 않는다.
    // 너무 당연한 말 같지만, 이런 것들은 위 메소드처럼
    // 따로 적어줘야 한다.
    // 하지만 이런 상황을 Jpa 는 어느정도 예상하고 규칙을 정했다.
    // findBy~ 처럼 어떤 정형화 된 틀을 제공한다는 의미이다.
    // findBy로 시작해서 NameAndId 로 메소드 명을 적어주면
    // JpaRepository의 정형화된 틀에 의해 이런 커스텀 메소드도
    // 자동으로 구현체를 생성해서 스프링 빈으로 등록해준다는 뜻.
}

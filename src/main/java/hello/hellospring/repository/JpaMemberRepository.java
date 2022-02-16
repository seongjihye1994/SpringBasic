package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA는 EntityManager에 의존한다.
    // Gradle에 JPA 라이브러리를 다운받으면, 스프링 부트가 자동으로 EntityManager를 생성한다.
    // properties에 세팅한 정보들(db 연결정보, sql문 보기, 테이블 생성x 등등)을 가지고
    // 스프링 부트가 EntityManager에 집어 넣는다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 유니크 id 조회, 조회할 타입, 식별자 파라미터
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name", name) // name 으로 조회
                .getResultList(); // 리스트로 결과 리턴

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member as m", Member.class)
                .getResultList(); // findAll 이니 setParameter 조건 필요 x, 리스트로 결과 리턴턴
    }

   // ** JPQL 쿼리언어
    // 객체(Entity)를 대상으로 쿼리문을 날린다.
    // 그럼 sql로 번역이 된다.
    // 기존 sql 문은 select * 또는 m.id 처럼 컬럼을 기준으로 조회했다면,
    // JPQL은 객체 자체(m)를 조회한다.

    // 저장, id 조회, 업데이트는 JPQL 필요 x

}

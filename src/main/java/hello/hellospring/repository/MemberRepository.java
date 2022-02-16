package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // Optional 이란?
    // findById 또는 findByName 으로 조회할 때 만약 데이터가 없어서 Null 이 리턴된다면
    // Optional 로 Null을 감싸서 리턴하게 한다.
    List<Member> findAll();
}

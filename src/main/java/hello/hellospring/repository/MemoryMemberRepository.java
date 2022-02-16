package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    // 현재 연결된 디비가 없으니 간단하게 Map 에 저장해보자.
    // Map<키, 값> 형태이고 여기서는 회원을 식별할 고유값인 id를 키로
    // 그 키의 값인 Member 를 값으로 두었다.
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 회원 가입시마다 시퀀스 ++1 해서 Id에 저장
        store.put(member.getId(), member); // 저장한 시퀀스 Id와 넘어온 member 객체 DB에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // 조회시 만약 데이터가 없어서 Null 이 리턴된다면
        // Optional 로 Null 을 감싸서 리턴하게 한다.
        // 이렇게 되면 클라이언트에서 무언가를 처리할 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
                // filter: 스트림내 요소에 대해서 필터링하는 작업
                // filter를 사용하기 위해서는 stream을 열어야 한다.
                // 스트림을 열고 필터를 실행하는데, 파리미터로 들어오는 변수에
                // store.value에 있는 값들이 차례대로 담긴다.
                // 이 변수에서 .getName을 해서 클라이언트로부터 넘어오는 name과
                // 일치하는지 확인한다.
                // findAny는 루프를 돌면서 찾다가 원하는 값이 찾아지면 바로 리턴한다.
                // 만약 끝까지 찾았는데도 없다? -> Null을 리턴한다.
                // Null 로 리턴되면 Optional에 감싸진다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // store는 Map 형태로 되어있는데, findAll의 리턴값 형태는 List이다.
        // 그래서 ArrayList 객체를 생성해서 파라미터로 store.values()를 던져준다.
        // store.values() 는 Members 이다.
    }

    public void clearStore() {
        store.clear(); // db clear
    }
}

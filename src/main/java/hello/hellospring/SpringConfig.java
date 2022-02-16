package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링이 실행될 때 @Configuration 어노테이션을 읽고
// @Bean 어노테이션을 보고 스프링 빈을 생성해서 스프링 컨테이너에 넣는다.
@Configuration
public class SpringConfig {

    // private DataSource dataSource;
    // 스프링이 application.properties의 DB 설정 정보를 보고
    // 스프링 빈으로 등록한다.

    /*@Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

/*    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    // 스프링 데이터 JPA가 자동으로 만들어 놓은 구현체가 자동으로 인젝션 된다.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // @Bean
    // public MemberRepository memberRepository() {
        // return new MemoryMemberRepository(); // Map 에 저장 (메모리 db)
        // return new JdbcMemberRepository(dataSource); // 순수 JDBC 커넥션 사용
        // return new JDBCTemplateMemberRepository(dataSource); // 스프링 JDBCTemplate 사용
        // return new JpaMemberRepository(em); // JPA 사용
    // }
}

package jiwon.board;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class Init {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init(){
        initMemberService.init();
    }

    @Component
    static class InitMemberService{
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            Member memberA = new Member("memberA", "aaa", "aaa", "aaa@aaa.com", "12334");
            Member memberB = new Member("memberB", "aaa", "bbb", "aaa@aaa.com", "12334");
            em.persist(memberB);
            em.persist(memberA);

            for (int i = 0; i < 100; i++) {
                Member selectedMember = i % 2 == 0 ? memberA : memberB;
                Board board = new Board("test" + i, "contents" + i, selectedMember);
                em.persist(board);
            }
        }
    }
}

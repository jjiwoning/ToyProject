package jiwon.board.repository;

import jiwon.board.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("저장 기능 테스트")
    void save(){
        Member member = new Member("aaa", "12345", "userA", "aaa@aaa.com", "010101010");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember).isEqualTo(member);
    }

    @Test
    @DisplayName("조회 테스트")
    void find(){
        Member member1 = new Member("aaa", "12345", "userA", "aaa@aaa.com", "010101010");
        Member member2 = new Member("bbb", "12345", "userA", "aaa@aaa.com", "010101010");

        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);

        //단건 조회
        Member findMember1 = memberRepository.findById(savedMember1.getId()).get();
        assertThat(findMember1).isEqualTo(savedMember1);

        //리스트 조회
        List<Member> members = memberRepository.findAll();
        assertThat(members).contains(savedMember1, savedMember2);

    }

}
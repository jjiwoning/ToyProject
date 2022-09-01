package jiwon.board.repository;

import jiwon.board.controller.MemberForm;
import jiwon.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //로그인 아이디로 유저 찾기
    Optional<Member> findByLoginId(String LoginId);
}

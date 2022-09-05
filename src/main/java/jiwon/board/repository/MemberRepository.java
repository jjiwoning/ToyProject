package jiwon.board.repository;

import jiwon.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //로그인 아이디로 유저 찾고 가져와야되는 경우 -> 회원 수정
    Optional<Member> findByLoginId(String loginId);

    //로그인 아이디로 유저만 찾으면 되는 경우 -> 로그인 아이디 중복 검사
    boolean existsByLoginId(String loginId);
}

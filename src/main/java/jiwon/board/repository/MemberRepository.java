package jiwon.board.repository;

import jiwon.board.controller.MemberForm;
import jiwon.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

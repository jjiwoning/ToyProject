package jiwon.board.repository;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    boolean existsByMember(Member member);
}

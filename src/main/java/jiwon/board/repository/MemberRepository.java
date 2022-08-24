package jiwon.board.repository;

import jiwon.board.controller.MemberForm;
import jiwon.board.domain.Member;

public interface MemberRepository {

    Long save(Member member);

    void updateMember(Long memberId, MemberForm updateParam);

}

package org.sungsung.youthpolicy.service.member;

import org.sungsung.youthpolicy.domain.vo.member.MemberVO;

import java.util.Optional;


public interface MemberService {
    void insert(MemberVO memberVO);

    Optional<MemberVO> findMemberById(Long id);

}

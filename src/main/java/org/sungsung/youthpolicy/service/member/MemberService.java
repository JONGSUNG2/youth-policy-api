package org.sungsung.youthpolicy.service.member;

import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberDetailDTO;
import org.sungsung.youthpolicy.domain.dto.member.MemberPlusDTO;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;

import java.util.Optional;


public interface MemberService {
    void insert(MemberVO memberVO);
    LoginDTO login(LoginDTO loginDTO);
    MemberDetailDTO findMemberByLoginId(String id);
<<<<<<< feature/Ai-recommand
=======

    public MemberPlusDTO checkMemberPlus(String memberId);

    void insertMemberPlus(MemberPlusDTO memberPlusDTO, String memberLoginId);



>>>>>>> develop
}

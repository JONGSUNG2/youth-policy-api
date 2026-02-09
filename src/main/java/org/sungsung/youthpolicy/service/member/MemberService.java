package org.sungsung.youthpolicy.service.member;

import org.sungsung.youthpolicy.domain.dto.member.LoginDTO;
import org.sungsung.youthpolicy.domain.vo.MemberVO;

public interface MemberService {
    public void insert(MemberVO memberVO);
    public boolean logincheck(LoginDTO loginDTO);
}

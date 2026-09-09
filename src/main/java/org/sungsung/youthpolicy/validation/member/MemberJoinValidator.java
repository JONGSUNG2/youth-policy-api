package org.sungsung.youthpolicy.validation.member;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.sungsung.youthpolicy.domain.vo.member.MemberVO;

@Service
public class MemberJoinValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberVO member = (MemberVO) target;

        //memberId: 길이 5~15자, 영문/숫자만 가능
        if (member.getLoginId() == null || member.getLoginId().isBlank()) {
            errors.rejectValue("loginId", "memberVO.loginId.notBlank");
        } else if (member.getLoginId().length() < 5 || member.getLoginId().length() > 15) {
            errors.rejectValue("loginId", "memberVO.loginId.size");
        } else if (!member.getLoginId().matches("^[a-zA-Z0-9]+$")) {
            errors.rejectValue("loginId", "memberVO.loginId.pattern");
        }

        //memberPassword: 길이 8~20자, 영문/숫자/특수문자 포함
        if (member.getPassword() == null || member.getPassword().isBlank()) {
            errors.rejectValue("password", "memberVO.password.notBlank");
        }else if (!member.getPassword().matches("^(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+{};:,<.>])(?=.*[A-Za-z])[A-Za-z0-9!@#$%^&*()\\-_=+{};:,<.>]{8,20}$")) {
            errors.rejectValue("password", "memberVO.password.pattern");
        }

        //memberName: 길이 2~20자
        if (member.getName() == null || member.getName().isBlank()) {
            errors.rejectValue("name", "memberVO.name.notBlank");
        }else if (member.getName().length() < 2 || member.getName().length() > 20) {
            errors.rejectValue("name", "memberVO.name.size");
        }    }
}

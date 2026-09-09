package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class PolicyConditionVO {
    private Long id;
    private String loginId;
    private Integer age;
    private String region;
    private String mainCategory;
    private String conditionHash;
    private LocalDateTime createDate;
    private String customCondition;


    public String formatCreateDate(){
        LocalDateTime createDate = this.createDate;
        return createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
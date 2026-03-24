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
    private String createDate;


    public String formatCreateDate(){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
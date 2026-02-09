package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyMasterVO {
    private Long id;                // PK
    private String policyId;          // 정책 고유 ID
    private String policyName;      // 정책명
    private String keyword;         // 정책 키워드
    private String description;     // 정책 설명
    private String mainCategory;    // 대분류
    private String subCategory;     // 중분류
    private String addressCode;     // 우편번호
    private String region;          // 지역
    private String adminName;       // 관리자 이름

}

package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyEligibilityVO {
    private Long id;                       // PK
    private String policyId;                 // 정책 고유 ID
    private Integer minAge;                // 최소 나이
    private Integer maxAge;                // 최대 나이
    private String ageLimitFlag;          // 나이 제한 여부
    private String marryStatusCode;        // 결혼 상태 코드
    private String incomeConditionCode;    // 소득 조건 코드
    private String additionalQualification;// 추가 자격요건
    private String participantTargetText;  // 참여 대상 상세설명
}

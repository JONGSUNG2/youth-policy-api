package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyBusinessPeriodVO {
    private Long id;                     // PK
    private String policyId;               // 정책 고유 ID
    private String businessPeriodCode;   // 사업 기간 유형 코드
    private String applyPeriodCode;      // 신청 기간 유형 코드
    private String businessStartDate;    // 정책 시작일
    private String businessEndDate;      // 정책 종료일
    private String businessPeriodType;  // 연중 모집 여부
}

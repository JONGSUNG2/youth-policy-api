package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicySupportVO {
    private Long id;                     // PK
    private String policyId;               // 정책 고유 ID
    private String supportSummary;       // 지원 내용 요약
    private String supportMethodCode;    // 지원 방식 코드
    private String approvalStatusCode;   // 승인 상태 코드
    private String supportLimitFlag;    // 지원 규모 제한 여부
    private Integer supportCount;        // 지원 인원수
    private String firstComeFlag;       // 선착순 여부
}

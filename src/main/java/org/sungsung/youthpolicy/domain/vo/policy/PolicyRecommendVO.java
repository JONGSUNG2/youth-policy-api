package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.Data;

@Data
public class PolicyRecommendVO {
    private Long id;
    private String loginId;
    private String policyId;
    private String reason;
    private String effect;
    private Long policyConditionId;
}
package org.sungsung.youthpolicy.domain.dto.policy;

import lombok.Data;

@Data
public class PolicyRecommendRequestDTO {
    private Integer age;
    private String region;
    private String mainCategory;
}

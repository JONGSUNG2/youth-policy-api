package org.sungsung.youthpolicy.domain.dto.api;

import lombok.Data;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyRecommendVO;

import java.util.List;

@Data
public class RecommendWrapper {
    private List<PolicyRecommendVO> recommendList;
}
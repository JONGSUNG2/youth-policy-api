package org.sungsung.youthpolicy.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.sungsung.youthpolicy.domain.vo.policy.*;
import org.sungsung.youthpolicy.mapper.PublicDataMapper;

@Repository
@RequiredArgsConstructor
public class PublicDataDAO {
    private final PublicDataMapper publicDataMapper;

    public void insertToMaster(PolicyMasterVO policyMasterVO){
        publicDataMapper.insertToMaster(policyMasterVO);
    }
    public void insertToSupport(PolicySupportVO policySupportVO){
        publicDataMapper.insertToSupport(policySupportVO);
    }
    public void insertToBusinessPeriod(PolicyBusinessPeriodVO policyBusinessPeriodVO){
        publicDataMapper.insertToBusinessPeriod(policyBusinessPeriodVO);
    }
    public void insertToApplication(PolicyApplicationVO policyApplicationVO){
        publicDataMapper.insertToApplication(policyApplicationVO);
    }
    public void insertToEligibility(PolicyEligibilityVO policyEligibilityVO){
        publicDataMapper.insertToEligibility(policyEligibilityVO);
    }


}

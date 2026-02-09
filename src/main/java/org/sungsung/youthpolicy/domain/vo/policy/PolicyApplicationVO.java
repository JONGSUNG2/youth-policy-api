package org.sungsung.youthpolicy.domain.vo.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyApplicationVO {
    private Long id;                      // PK
    private String policyId;                // 정책 고유 ID
    private String applyMethod;           // 신청 방법
    private String applyUrl;              // 신청 URL
    private String submissionDocuments;   // 제출 서류
    private String etcNotes;              // 기타 유의사항
    private String referenceUrl1;         // 참고 주소1
    private String referenceUrl2;         // 참고 주소2
}

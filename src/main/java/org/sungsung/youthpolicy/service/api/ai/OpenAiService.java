package org.sungsung.youthpolicy.service.api.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.sungsung.youthpolicy.domain.dto.api.ChatRequest;
import org.sungsung.youthpolicy.domain.dto.api.ChatResponse;
import org.sungsung.youthpolicy.domain.dto.api.RecommendWrapper;
import org.sungsung.youthpolicy.domain.vo.policy.PolicyRecommendVO;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OpenAiService {
    private final WebClient openAiWebClient;
    private final ObjectMapper objectMapper;

    public List<PolicyRecommendVO> recommendPolicyByAi(List<PolicyDTO> filteredPolicies,String customCondition,String memberLoginId) {

        String jsonPolicies = "";
        try {
            jsonPolicies = objectMapper.writeValueAsString(filteredPolicies);
        } catch (Exception e) {
            throw new RuntimeException("JSON 반환 오류");
        }

        String prompt = """
        아래는 이미 사용자 조건으로 필터링된 정책 리스트(JSON)입니다.
        이 정책들만 분석하여 사용자에게 가장 적합한 정책을 추천하세요.
        
        정책 목록(JSON):
        %s
        
        로그인 아이디:
        %s
        
        사용자입력 세부조건:
        %s
        
        요구 사항:
        1. JSON에 포함된 정책만 추천하세요. 임의 정책 추가 금지.
        2. 각 정책에 대해 아래 항목을 반드시 포함하세요:
           - policyId
           - policyName
           - region
           - reason
           - effect
        
        3. reason과 effect 생성 규칙:
           - 반드시 **완전한 문장 단위**로 작성할 것
           - 각 문장은 마침표로 끝낼 것
           - 문장 사이에는 공백을 1칸 이상 넣을 것
           - 필요한 경우 **줄바꿈(\n)** 을 사용해도 됨
           - 최소 2문장 이상 작성
           - 사용자 입력 세부조건(%s)을 최우선 반영하여 구체적으로 설명할 것
           - "로그인 아이디님께 추천하는 이유는 ~ 때문입니다." 형식을 포함할 것
        
        4. 전체 응답은 반드시 아래 JSON 형식만 사용:
        {
          "recommendList": [
            {
              "policyId": "",
              "policyName": "",
              "region": "",
              "reason": "",
              "effect": ""
            }
          ]
        }
        
        5. JSON 외의 다른 설명, 텍스트, 예시, 문장 출력 금지."""
                .formatted(jsonPolicies, memberLoginId, customCondition, customCondition);
        ChatRequest request = new ChatRequest("gpt-5-mini", List.of(new ChatRequest.Message("user", prompt)));

        // ---------- WebClient 호출 ----------
        String aiResultJson;
        try {

             aiResultJson=  openAiWebClient.post()
                    .uri("/v1/chat/completions")
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,response->{
                        if (response.statusCode() == HttpStatus.UNAUTHORIZED){
                            return Mono.error(new RuntimeException("AI api Key 만료"));
                        }
                        return  Mono.error(new RuntimeException("AI 호출 오류"));
                    })
                    .bodyToMono(String.class)
                    .block(); // 동기 처리
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        ChatResponse aiResponse;
        try {
            aiResponse = objectMapper.readValue(aiResultJson, ChatResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("AI JSON->DTO 변환 오류", e);
        }
//        AI응답에서 필요한 부분인 content부분만 추출
        String contentJson = aiResponse.getChoices().get(0).getMessage().getContent();

        RecommendWrapper wrapper;
        try {
//            추출한 content를  PolicyRecommendVO 의 리스트형식으로 "recommendList" 가져옴
            wrapper = objectMapper.readValue(contentJson, RecommendWrapper.class);
        } catch (Exception e) {
            throw new RuntimeException("OpenAI 응답 파싱 오류 (2차: recommendList)", e);
        }

//        받아온 리스트를 리턴
        return wrapper.getRecommendList();
    }
}

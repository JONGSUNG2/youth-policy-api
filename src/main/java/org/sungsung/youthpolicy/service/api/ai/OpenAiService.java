package org.sungsung.youthpolicy.service.api.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.sungsung.youthpolicy.domain.dto.api.ChatRequest;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OpenAiService {

    @Value("${openai.api-key}")
    private String apiKey;

    private final WebClient openAiWebClient;   // 💡 여기 자동 주입됨
    private final ObjectMapper objectMapper;

    public String recommendPolicyToAi(List<PolicyDTO> filteredPolicies) {

        String jsonPolicies = "";
        try {
            jsonPolicies = objectMapper.writeValueAsString(filteredPolicies);
        } catch (Exception e) {
            throw new RuntimeException("JSON 반환 오류");
        }

        String prompt = """
        아래는 이미 사용자 조건으로 필터링된 정책 리스트입니다.
        이 정책들(JSON)만 분석해서 가장 적합한 정책 3개를 추천하세요.

        정책 목록(JSON):
        %s

        요구 사항:
        1. JSON에 포함된 정책을 모두 추천하세요.
        2. 각 정책에 대해:
           - policyId
           - policyName
           - region
           - 추천 이유
           - 기대 효과

        반드시 아래 JSON 형식으로만 응답하세요:

        {
          "recommendations": [
            {
              "policyId": "",
              "policyName": "",
              "region": "",
              "reason": "",
              "effect": ""
            }
          ]
        }

        예외 문장, 설명, 불필요 텍스트 절대 포함하지 마세요.
        """.formatted(jsonPolicies);
        ChatRequest request = new ChatRequest("gpt-5-mini", List.of(new ChatRequest.Message("user", prompt)));

        // ---------- WebClient 호출 ----------
        String aiResultJson=  openAiWebClient.post()
                .uri("/v1/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 동기 처리

        String aiContent;
        try {
            JsonNode root = objectMapper.readTree(aiResultJson);
            aiContent = root.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            throw new RuntimeException("AI 응답 파싱 중 오류 발생", e);
        }
        return aiContent;
    }
}


package org.sungsung.youthpolicy.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyListResponseDTO;
import org.sungsung.youthpolicy.domain.dto.policy.PolicyRecommendRequestDTO;
import org.sungsung.youthpolicy.domain.dto.policy.config.MainCategory;
import org.sungsung.youthpolicy.domain.dto.policy.config.Region;
import org.sungsung.youthpolicy.service.policy.PolicyService;

import java.util.List;


@Controller
@RequestMapping("/policy/*")
@RequiredArgsConstructor
@Slf4j
public class PolicyController {

    private final PolicyService policyService;
    @GetMapping("/detail/{policyId}")
    public String policyDetailPage(@PathVariable("policyId")String policyId, Model model){
        model.addAttribute("policy", policyService.policyDetail(policyId));
        return "policy/policyDetail";
    }
    @GetMapping("policyList")
    public String policyListPage(Model model, PolicyListRequestDTO policyListRequestDTO){

        List<PolicyListResponseDTO> policyList = policyService.policyList(policyListRequestDTO);
        model.addAttribute("regions", Region.values());
        model.addAttribute("mainCategories", MainCategory.values());

        model.addAttribute("policyList",policyList);
        model.addAttribute("policyListRequestDTO", policyListRequestDTO);
        return "policy/policyList";
    }
    @GetMapping("/policy/condition")
    public String policyConditionPage(Model model, PolicyRecommendRequestDTO policyRecommendRequestDTO){
        model.addAttribute("regions", Region.values());
        model.addAttribute("mainCategories", MainCategory.values());
        model.addAttribute("policyRecommendRequestDTO", policyRecommendRequestDTO);
        return "policy/policyCondition";
    }
    @PostMapping("/policy/condition")
    public String policyRecommendProcess(PolicyRecommendRequestDTO policyRecommendRequestDTO, Model model){

//       서버에 회원 조건 TBL_POLICY_CONDITION 에 저장한 후에, 로딩창으로 이동
        return "redirect:/policy/policyLoading";
    }
    @GetMapping("/policy/policyLoading")
    public String policyRecommendListPage(){
//        회원 조건을 불러옴
//        불러온 회원조건을 통해 필터링된 정책 목록 가져오기
//        필터링된 정책을 ai 프롬프트에 맞게 넣어준다
//        fetch를 사용해 비동기 서버통신을 사용해서 javaScript await를 사용해 ai의 작업이 끝나야만 이동하게 한다 (JS)
//        받아온 ai 의 json값을 나의 db 서버 TBL_RECOMMEND_POLICY_LIST 에 넣는다
//        /policy/policyRecommendList로 이동
        return "policy/policyLoading";
    }
    @GetMapping("/policy/policyRecommendList")
    public String policyRecommendListPage(Model model){
//        여기로 이동한 후에 서버에 저장된 추천 정책 목록을 볼수있다
        return "policy/policyRecommendList";
    }
}
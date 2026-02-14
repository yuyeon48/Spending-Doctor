package com.example.demo.controller;

import com.example.demo.entity.SiteUser;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("transactions", analysisService.getMyTransactions(username));
            model.addAttribute("advice", analysisService.generateAdvice(username));
        } else {
            model.addAttribute("transactions", null);
            model.addAttribute("advice", "로그인 후 나만의 소비 진단을 받아보세요! 🩺");
        }
        return "index";
    }

    @PostMapping("/add")
    public String addTransaction(String storeName, Long amount, String category, Principal principal) {
        // 1. 현재 로그인한 유저 정보 가져오기
        SiteUser user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 2. 새로운 지출 객체 생성 및 유저(author) 연결
        // 에러 해결: amount 타입을 Long으로 일치시켰습니다.
        Transaction tx = Transaction.builder()
                .storeName(storeName)
                .amount(amount)
                .category(category)
                .author(user)
                .build();

        // 3. 서비스 호출하여 저장
        analysisService.save(tx);

        return "redirect:/";
    }
}
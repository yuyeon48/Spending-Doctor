package com.example.demo.service;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final TransactionRepository repository;

    // 1. [에러 해결] 컨트롤러에서 호출하던 메서드명을 유지하거나 추가해야 합니다.
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    // 2. 특정 사용자의 데이터만 가져오는 로직
    public List<Transaction> getMyTransactions(String username) {
        return repository.findByAuthorUsername(username);
    }

    // 3. [개선] 전체가 아닌 '특정 유저'의 지출을 분석하여 조언 생성
    public String generateAdvice(String username) {
        // 모든 데이터가 아닌 로그인한 유저의 데이터만 가져옴
        List<Transaction> transactions = repository.findByAuthorUsername(username);

        if (transactions.isEmpty()) {
            return "아직 분석할 데이터가 없어요. 첫 지출을 등록해보세요!";
        }

        long totalAmount = transactions.stream().mapToLong(Transaction::getAmount).sum();

        if (totalAmount >= 500000) {
            return String.format("현재까지 %s원 지출하셨네요. 조금 아껴쓰셔야겠어요! ⚠️",
                    String.format("%,d", totalAmount));
        } else {
            return String.format("현재까지 %s원 지출하셨네요. 아주 훌륭한 소비 습관입니다! 👍",
                    String.format("%,d", totalAmount));
        }
    }

    public void save(Transaction transaction) {
        repository.save(transaction);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
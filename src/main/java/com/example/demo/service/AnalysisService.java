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

  
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public List<Transaction> getMyTransactions(String username) {
        return repository.findByAuthorUsername(username);
    }


    public String generateAdvice(String username) {
  
        List<Transaction> transactions = repository.findByAuthorUsername(username);

        if (transactions.isEmpty()) {
            return "아직 분석할 데이터가 없어요. 첫 지출을 등록해보세요";
        }

        long totalAmount = transactions.stream().mapToLong(Transaction::getAmount).sum();

        if (totalAmount >= 500000) {
            return String.format("현재까지 %s원 지출하셨네요. 조금 아껴쓰셔야겠어요",
                    String.format("%,d", totalAmount));
        } else {
            return String.format("현재까지 %s원 지출하셨네요. 아주 훌륭한 소비 습관입니다",
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

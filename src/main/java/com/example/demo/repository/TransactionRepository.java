package com.example.demo.repository;
import java.util.List;
import com.example.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 이 인터페이스가 DB 접근 객체임을 스프링에게 알려줍니다.
// TransactionRepository.java
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAuthorUsername(String username);
}
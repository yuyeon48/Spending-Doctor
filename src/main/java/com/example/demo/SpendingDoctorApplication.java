package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.time.LocalDate;

@SpringBootApplication
public class SpendingDoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpendingDoctorApplication.class, args);
    }

   /* @Bean
    CommandLineRunner initData(TransactionRepository repository) {
        return args -> {
            repository.save(new Transaction(null, LocalDate.now(), "스타벅스", 5900L, "카페"));
            repository.save(new Transaction(null, LocalDate.now(), "배달의민족", 25000L, "식비"));
            repository.save(new Transaction(null, LocalDate.now(), "무신사", 89000L, "쇼핑"));
            repository.save(new Transaction(null, LocalDate.now(), "택시", 12000L, "교통"));
            repository.save(new Transaction(null, LocalDate.now(), "이마트", 45000L, "식비"));

            System.out.println("샘플 데이터 주입 완료! 이제 브라우저를 새로고침해보세요.");
        };
    } // Bean 메서드 닫기*/
} // 클래스 전체 닫기

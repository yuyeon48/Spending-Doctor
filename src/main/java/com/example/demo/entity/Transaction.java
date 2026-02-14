package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;      // 거래 일자
    private String storeName;    // 상호명
    private Long amount;         // 금액
    private String category;     // 카테고리 (식비, 카페 등)
    // Transaction.java 에 추가
    @ManyToOne // 여러 지출은 한 명의 유저에게 속함
    @JoinColumn(name = "user_id")
    private SiteUser author; // 작성자 필드 추가
}
package com.example.demo.repository;

import com.example.demo.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    // 아이디로 사용자를 찾는 메서드 추가
    Optional<SiteUser> findByUsername(String username);
}
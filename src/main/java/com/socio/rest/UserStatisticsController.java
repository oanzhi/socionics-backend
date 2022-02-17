package com.socio.rest;

import com.socio.model.UserStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserStatisticsController {

    @GetMapping
    public ResponseEntity<UserStatistics> getUsersStatistics() {
        log.info("Getting user statistics");
        return ResponseEntity.ok(new UserStatistics());
    }

    @PostMapping
    public ResponseEntity<UserStatistics> createUserStatistics() {
        log.info("Creating new user statistics");
        return ResponseEntity.ok(new UserStatistics());
    }
}
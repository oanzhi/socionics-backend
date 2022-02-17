package com.socio.repository;

import com.socio.model.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatisticsRepository extends JpaRepository<UserStatistics, String> {
}
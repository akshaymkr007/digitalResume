package com.digitalresume.resume;

import com.digitalresume.user.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {
    Optional<ResumeEntity> findTopByUserOrderByUpdatedAtDesc(UserEntity user);
}

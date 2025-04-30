package ac.kr.hufs.wider.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.SessionLog;

@Repository
public interface SessionRepository extends JpaRepository<SessionLog, String> {
    List<SessionLog> findByUserId(String userId);
} 
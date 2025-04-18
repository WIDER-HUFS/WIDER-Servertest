package ac.kr.hufs.wider.model.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, String>{
    List<Report> findByUser_UserId(String userId);
    Optional<Report> findBySession_SessionId(String sessionId);
}

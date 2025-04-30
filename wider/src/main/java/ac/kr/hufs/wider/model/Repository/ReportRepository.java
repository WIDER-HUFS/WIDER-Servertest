package ac.kr.hufs.wider.model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.kr.hufs.wider.model.Entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, String> {
    List<Report> findBySessionId(String sessionId);
    List<Report> findByUserId(String userId);
}

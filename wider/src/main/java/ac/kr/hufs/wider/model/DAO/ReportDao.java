package ac.kr.hufs.wider.model.DAO;

import java.util.List;
import java.util.Optional;

import ac.kr.hufs.wider.model.Entity.Report;

public interface ReportDao {
    List<Report> findByUserId(String userId);
    Optional<Report> findBySessionId(String sessionId);
    Report save(Report report);
    void deleteById(String reportId);
}

package ac.kr.hufs.wider.model.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ac.kr.hufs.wider.model.Entity.Report;
import ac.kr.hufs.wider.model.Repository.ReportRepository;
import ac.kr.hufs.wider.model.Service.ReportService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Optional<Report> getReportById(String reportId) {
        return reportRepository.findById(reportId);
    }

    @Override
    public List<Report> getReportsBySessionId(String sessionId) {
        return reportRepository.findBySessionId(sessionId);
    }

    @Override
    public List<Report> getReportsByUserId(String userId) {
        return reportRepository.findByUserId(userId);
    }

    @Override
    public Report updateReport(Report report) {
        if (!reportRepository.existsById(report.getReportId())) {
            throw new IllegalArgumentException("Report not found with id: " + report.getReportId());
        }
        return reportRepository.save(report);
    }

    @Override
    public void deleteReport(String reportId) {
        reportRepository.deleteById(reportId);
    }

    @Override
    public void deleteReportsBySessionId(String sessionId) {
        List<Report> reports = getReportsBySessionId(sessionId);
        reportRepository.deleteAll(reports);
    }
} 
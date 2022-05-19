package com.mycompany.dailyreport.repository;

import com.mycompany.dailyreport.domain.Report;
import com.mycompany.dailyreport.service.DateTimePeriod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRepository {

    private final EntityManager em;

    public void save(Report report) {
        em.persist(report);
    }

    public Report findOne(Long id) {
        return em.find(Report.class, id);
    }

    public List<Report> findAll() {
        return em.createQuery("select r from Report", Report.class)
                .getResultList();
    }

    public List<Report> findWithPeriod(DateTimePeriod dateTimePeriod) {
        return em.createQuery("select r from Report r where inputDate between :startDate and :endDate", Report.class)
                .setParameter("startDate", dateTimePeriod.getStartDate())
                .setParameter("endDate", dateTimePeriod.getEndDate())
                .getResultList();
    }
}

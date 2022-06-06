package com.mycompany.dailyreport.repository;

import com.mycompany.dailyreport.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

    private final EntityManager em;

    public List<Member> login(String accountId) {
        return em.createQuery(
                "select m " +
                        "from Member m " +
                        "where accountId = :accountId ", Member.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }
}

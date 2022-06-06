package com.mycompany.dailyreport.repository;

import com.mycompany.dailyreport.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LoginRepository {

    private final EntityManager em;

    public Member Login(String accountId, String password) {
        return em.createQuery(
                "select m " +
                        "from Member m " +
                        "where accountId = :accountId " +
                        "and password = :password ", Member.class)
                .setParameter("accountId", accountId)
                .setParameter("password", password)
                .getSingleResult();
    }
}

package com.mycompany.dailyreport.repository;

import com.mycompany.dailyreport.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByAccountId(String accountId) {
        return em.createQuery(
                "select m " +
                        "from Member m " +
                        "where m.accountId = :accountId", Member.class)
                .setParameter("accountId", accountId)
                .getResultList();
    }
}

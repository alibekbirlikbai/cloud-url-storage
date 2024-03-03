package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.model.TextBin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ServiceHibernateUtils {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public TextBin saveOrUpdateEntity(TextBin textBin) {
        TextBin savedEntity = entityManager.merge(textBin);
        entityManager.flush();
        return savedEntity;
    }
}

package com.example.pastebinproject.service.implementation.serviceUtils;

import com.example.pastebinproject.model.Bin;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ServiceHibernateUtils {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Bin saveOrUpdateEntity(Bin bin) {
        Bin savedEntity = entityManager.merge(bin);
        entityManager.flush();

        savedEntity.setContent(bin.getContent());

        return savedEntity;
    }
}


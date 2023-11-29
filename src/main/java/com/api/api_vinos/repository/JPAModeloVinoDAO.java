package com.api.api_vinos.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.api_vinos.entity.ModeloVino;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository(value = "modeloVinoDao")
public class JPAModeloVinoDAO implements ModeloVinoDAO {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<ModeloVino> getModeloVino() {
        return em.createQuery("select v from Vino v order by v.id").getResultList();
    }

    @Transactional(readOnly = false)
    public void guardaVino(ModeloVinoDAO modelovino) {
        em.merge(modelovino);
    }

}
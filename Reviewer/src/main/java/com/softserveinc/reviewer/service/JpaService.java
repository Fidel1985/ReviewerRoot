package com.softserveinc.reviewer.service;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.softserveinc.reviewer.jpa.Person;

public class JpaService {
    @Inject
    private EntityManager em;

    @Transactional
    public Person createNewPerson() {
        Person person = new Person();
        person.setName("test");
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        return person;
    }
}

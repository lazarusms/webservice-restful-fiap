package com.fiap.webservices.service;

import com.fiap.webservices.domain.Parent;
import com.fiap.webservices.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


public interface ParentService {
    Parent create(Parent parent);
    Collection<Parent> getAllParents();
    Parent getParentById(int id);
    Parent update(int id, Parent parent);
    void deleteParent(int id);
}

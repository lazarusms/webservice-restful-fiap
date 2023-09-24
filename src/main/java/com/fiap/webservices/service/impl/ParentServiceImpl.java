package com.fiap.webservices.service.impl;

import com.fiap.webservices.domain.Parent;
import com.fiap.webservices.repository.ParentRepository;
import com.fiap.webservices.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Autowired
    public ParentServiceImpl(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent create(Parent parent) {
        return parentRepository.save(parent);
    }
    @Override
    public Collection<Parent> getAllParents() {
        return parentRepository.findAll();
    }

    @Override
    public Parent getParentById(int id) {
        return parentRepository.findById(id).get();
    }

    @Override
    public Parent update(int id, Parent updateParent) {
       Parent parent = parentRepository.findById(id).orElseThrow(() -> new IllegalStateException("Parente does not existe"));
        updateEmail(parent, updateParent.getEmail());
        updatePhoneNumber(parent, updateParent.getPhoneNumber());
        return parent;
    }

    @Override
    public void deleteParent(int id) {
        parentRepository.deleteById(id);

    }

    public void updateEmail(Parent parent, String email) {
        if (email != null && !email.equals(parent.getEmail())) {
            parent.setEmail(email);
        }
    }

    public void updatePhoneNumber(Parent parent, String phoneNumber) {
        if(phoneNumber != null && !phoneNumber.equals(parent.getPhoneNumber())) {
            parent.setPhoneNumber(phoneNumber);
        }
    }



}

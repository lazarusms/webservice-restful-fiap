package com.fiap.webservices.resource;

import com.fiap.webservices.domain.Parent;
import com.fiap.webservices.domain.Student;
import com.fiap.webservices.service.impl.ParentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/parent")
public class ParentResource {
    private final ParentServiceImpl parentService;

    @Autowired
    public ParentResource(ParentServiceImpl parentService) {
        this.parentService = parentService;
    }

    @PostMapping()
    public ResponseEntity<Parent> registerParent(@RequestBody Parent parent) {
        Parent registerParent = parentService.create(parent);
        return ResponseEntity.ok(registerParent);
    }

    @GetMapping()
    public ResponseEntity<Collection<Parent>> getAllParentsResc() {
       Collection<Parent> allParents = parentService.getAllParents();
        return ResponseEntity.ok(allParents);
    }
    @GetMapping(path = "/{parentId}")
    public ResponseEntity<Parent> getParentById(@PathVariable int parentId) {
        Parent parentById = parentService.getParentById(parentId);
        return ResponseEntity.ok(parentById);
    }

    @DeleteMapping(path = "/{parentId}")
    public ResponseEntity<String> deleteParent(@PathVariable int parentId) {
        parentService.deleteParent(parentId);
        return ResponseEntity.ok("O tutor informado foi deletado com sucesso");
    }
    @PutMapping(path = "/{parentId}")
    public ResponseEntity<Parent> parentUpdate(
            @PathVariable("parentId") int parentId,
            @RequestBody Parent updatedParent) {
        Parent updated = parentService.update(parentId, updatedParent);
        return ResponseEntity.ok(updated);
    }

}

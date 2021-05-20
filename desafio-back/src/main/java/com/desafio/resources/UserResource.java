package com.desafio.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.domain.User;
import com.desafio.services.UserService;
import com.desafio.services.dto.UserDTO;
import com.desafio.services.dto.UserNewDTO;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @PreAuthorize("hasAnyRole('ADMIN')")    
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAllDTO());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")    
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.findByIdDTO(id));
    }
    

    
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody UserNewDTO objDTO) {
        UserDTO obj = service.insertDTO(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @PathVariable Integer id, @RequestBody UserNewDTO objDTO) {
        service.update(objDTO, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

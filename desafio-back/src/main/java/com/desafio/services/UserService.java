package com.desafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.domain.User;
import com.desafio.repositoties.UserRepository;
import com.desafio.services.dto.UserDTO;
import com.desafio.services.dto.UserNewDTO;
import com.desafio.services.exceptions.ObjectNotFoundException;
import com.desafio.services.mapper.UserMapper;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder bc;

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll() {
        return repository.findAll();
    }

    public List<UserDTO> findAllDTO() {
        return userMapper.toDto(findAll());
    }

    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public UserDTO findByIdDTO(Integer id) {
        return userMapper.toDto(findById(id));
    }

    public User insert(User obj) {
        return repository.saveAndFlush(obj);
    }

    public UserDTO insertDTO(UserNewDTO obj) {
        User user = insert(fromDTO(obj));
        return userMapper.toDto(user);
    }

    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User obj) {
        return repository.saveAndFlush(obj);
    }

    public UserDTO update(UserNewDTO objNew, Integer id) {
        User objOld = findById(id);
        updateData(objOld, objNew);
        return userMapper.toDto(update(objOld));
    }

    private void updateData(User objOld, UserNewDTO objNew) {
        if (objNew.getUsuario() != null) {
            objOld.setUsuario(objNew.getUsuario());
        }
        if (objNew.getSenha() != null) {
            objOld.setSenha(bc.encode(objNew.getSenha()));
        }
    }

    public User fromDTO(UserNewDTO objDTO) {
        return User.builder()
                .usuario(objDTO.getUsuario())
                .senha(objDTO.getSenha())
                .build();
    }
}

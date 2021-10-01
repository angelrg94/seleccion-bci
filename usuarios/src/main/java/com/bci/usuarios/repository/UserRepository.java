package com.bci.usuarios.repository;

import com.bci.usuarios.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User,String>{

    public int countByEmail(String email);
    public int countByName(String name);
}
package com.rpggame.world.users.repository;

import com.rpggame.world.common.repository.CustomInsert;
import com.rpggame.world.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>, CustomInsert<User> {

}

package com.example.healthresponse;

import org.springframework.data.repository.ListCrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface RegisterRepository extends ListCrudRepository<User, Integer> {

}

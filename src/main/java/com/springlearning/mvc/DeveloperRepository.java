package com.springlearning.mvc;

import org.springframework.data.repository.CrudRepository;


public interface DeveloperRepository extends CrudRepository<Developer, Long> {

}
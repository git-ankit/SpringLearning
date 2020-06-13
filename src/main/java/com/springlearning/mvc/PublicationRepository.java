package com.springlearning.mvc;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PublicationRepository extends CrudRepository<Publication, Long> {
}

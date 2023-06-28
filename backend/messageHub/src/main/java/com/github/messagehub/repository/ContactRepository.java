package com.github.messagehub.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.messagehub.model.Contacts;

@Repository
public interface ContactRepository extends CrudRepository<Contacts, Integer> {
	List<Contacts> findById1(int id);
}

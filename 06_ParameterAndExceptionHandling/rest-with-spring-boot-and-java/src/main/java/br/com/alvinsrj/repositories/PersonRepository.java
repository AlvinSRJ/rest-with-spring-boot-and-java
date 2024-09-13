package br.com.alvinsrj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alvinsrj.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{}

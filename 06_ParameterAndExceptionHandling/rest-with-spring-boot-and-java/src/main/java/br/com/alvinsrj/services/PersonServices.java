package br.com.alvinsrj.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alvinsrj.exceptions.ResourceNotFoundException;
import br.com.alvinsrj.model.Person;
import br.com.alvinsrj.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {

		logger.info("Finding all people!");		
		
		return repository.findAll();
		
	}
	
	public Person findById(Long id) {
		
		logger.info("Finding one person!");		
		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID!"));
	}	
	
	public Person create(Person person) {
		
		logger.info("Creating one Person!");		

		return repository.save(person);
	}
	
	public Person update(Person person) {
		
		logger.info("Updating one Person!");		
		
		var entity = repository.findById(person.getId())
			.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID!"));

		entity.setFirstname(person.getFirstname());
		entity.setLastname(person.getLastname());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());		
		
		return repository.save(entity);

	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one Person!");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID!"));

		repository.delete(entity);
		
		
	}
	
}

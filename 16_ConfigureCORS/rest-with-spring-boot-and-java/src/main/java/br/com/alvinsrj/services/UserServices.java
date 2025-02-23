package br.com.alvinsrj.services;

 import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alvinsrj.controllers.PersonController;
import br.com.alvinsrj.data.vo.v1.PersonVO;
import br.com.alvinsrj.exceptions.ResourceNotFoundException;
import br.com.alvinsrj.mapper.DozerMapper;
import br.com.alvinsrj.repositories.UserRepository;

@Service
public class UserServices implements UserDetailsService {

	private Logger logger = Logger.getLogger(UserServices.class.getName());
	
	@Autowired
	UserRepository repository;
	
	public UserServices(UserRepository repository) {
		this.repository = repository;
	}

	public PersonVO findById(Long id) {
		
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID!"));
		
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding one user by name " + username + "!");		
		var user = repository.findByUsername(username);
		if (user != null) {
			return user;			
		} else {
			throw new UsernameNotFoundException("Username "  + username + " not found!");
		}				
	}		
	
}

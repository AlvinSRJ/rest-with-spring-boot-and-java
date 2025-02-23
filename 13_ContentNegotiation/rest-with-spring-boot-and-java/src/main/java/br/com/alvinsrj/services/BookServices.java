package br.com.alvinsrj.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alvinsrj.controllers.BookController;
import br.com.alvinsrj.data.vo.v1.BookVO;
import br.com.alvinsrj.exceptions.RequiredObjectIsNullException;
import br.com.alvinsrj.exceptions.ResourceNotFoundException;
import br.com.alvinsrj.mapper.DozerMapper;
import br.com.alvinsrj.mapper.custom.BookMapper;
import br.com.alvinsrj.model.Book;
import br.com.alvinsrj.repositories.BookRepository;

@Service
public class BookServices {
	
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;
	
	@Autowired
	BookMapper mapper;
	
	public List<BookVO> findAll() {

		logger.info("Finding all books!");		
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class) ;
		books
			.stream()
			.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		return books;
		
	}
	
	
	public BookVO findById(Long id) {
		
		logger.info("Finding one Book!");		
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID!"));
		
		BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}		
	
	
	public BookVO create(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException(); 		
		logger.info("Creating one BookVO!");
		
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}	
	
	public BookVO update(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException(); 		
		logger.info("Updating one BookVO!");		
		
		var entity = repository.findById(book.getKey())
			.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID!"));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());		
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;

	}
	
	public void delete(Long id) {
		
		logger.info("Deleting one BookVO!");
		
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("No Records found for this ID!"));

		repository.delete(entity);
		
		
	}

}

package br.com.alvinsrj.mapper.custom;

import org.springframework.stereotype.Service;

import br.com.alvinsrj.data.vo.v1.BookVO;
import br.com.alvinsrj.model.Book;

@Service
public class BookMapper {
	
	public BookVO convertEntityToVO(Book book) {
		BookVO vo = new BookVO();
		vo.setKey(book.getId());
		vo.setAuthor(book.getAuthor());
		vo.setLaunchDate(book.getLaunchDate());
		vo.setPrice(book.getPrice());
		vo.setTitle(book.getTitle());
		return vo;
		
	}
	
	public Book convertVOToEntity(BookVO vo) {
		Book book = new Book();
		book.setId(vo.getKey());
		book.setAuthor(vo.getAuthor());
		book.setLaunchDate(vo.getLaunchDate());
		book.setPrice(vo.getPrice());
		book.setTitle(vo.getTitle());
		return book;
	}

}

package com.javastreets.ee.microprofile.endpoints;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.javastreets.ee.microprofile.entities.Book;
import com.javastreets.ee.microprofile.services.BookService;

@ApplicationScoped
@Path("/books")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class BookServiceEndpoint {
	
	@Inject
	@ConfigProperty(name="default.record.count")
	int recordCount;
	
	@Inject
	private BookService bookService;
	
	@GET
	public List<Book> listAll() {

		System.out.println("Count: " + recordCount);

		return bookService.listAll();
	}
	
	@GET
	@Path("/{isbn}")
	public Book getBook(@PathParam("isbn") String isbn) {
		return bookService.getBook(isbn);
	}
	
	@POST
	public Response addBook(Book book) {
		System.out.println("Saving book - " + book);
		if(getBook(book.getIsbn()) != null) {
			return Response.status(Status.CONFLICT).header("x-reason", "ISBN already exists").build();
		}
		if (bookService.addBook(book)) {
			return Response.created(UriBuilder.fromMethod(BookServiceEndpoint.class, "getBook")
															.build(book.getIsbn())).build();
		} else {
			return Response.serverError().build();
		}
	}

}

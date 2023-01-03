package se.yrgo.spring.data;

import se.yrgo.spring.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookDaoJpaImpl implements BookDao{

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Book> allBooks() {
        return em.createQuery("SELECT BOOK FROM BOOK AS BOOK").getResultList();
    }

    @Override
    public Book findByIsbn(String isbn) throws BookNotFoundException {
        try {
            return (Book) em.createQuery("SELECT DISTINCT BOOK FROM BOOK AS BOOK WHERE BOOK.ISBN=:isbn")
                    .setParameter("isbn", isbn).getSingleResult();
        }catch (javax.persistence.NoResultException e){
            throw new BookNotFoundException();
        }
    }

    @Override
    public void create(Book newBook) {
        System.out.println("using jpa");
        em.persist(newBook);
    }

    //This should use Id instead of isbn but my database isn't designed to do that currently
    @Override
    public void delete(Book redundantBook) {
        Book book = em.find(Book.class, redundantBook.getIsbn());
        em.remove(book);
    }

    @Override
    public List<Book> findBooksByAuthor(String author) {
        return em.createQuery("SELECT BOOK FROM BOOK AS BOOK WHERE BOOK.AUTHOR =:author")
                .setParameter("author", author).getResultList();
    }
}

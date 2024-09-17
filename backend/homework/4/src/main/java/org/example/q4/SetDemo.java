package org.example.q4;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

public class SetDemo {

    private static final Logger logger = Logger.getLogger(SetDemo.class.getName());

    public static void treeSetDemo(Comparator<Book> comparator) {
        Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
        Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
        Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
        Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);

        Set<Book> books = new TreeSet<>(comparator);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        for (Book book : books) {
            logger.info(String.format("Book [title=%s, author=%s, year=%d]", book.getTitle(), book.getAuthor(), book.getYear()));
        }
    }

    public static void main(String[] args) {
        logger.info("\nBooks in Normal Order");
        treeSetDemo(null);
        logger.info("\nBooks in Ascending Order");
        treeSetDemo(new PubDataAscComparator());
        logger.info("\nBooks in Descending Order");
        treeSetDemo(new PubDataDscComparator());
    }
}

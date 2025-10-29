import java.util.ArrayList;

public class Main {
    static ArrayList<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
        Author dostoevsky = new Author("Dostoevsky", "Russia");
        Author nietzsche = new Author("Nietzsche", "German");
        Author camus = new Author("Camus", "France");
        books.add(createBook("Crime and Punishment", dostoevsky, "Psychological Fiction"));
        books.add(createBook("The Brothers Karamazov", dostoevsky, "Philosophy"));
        books.add(createBook("Thus Spoke Zarathustra", nietzsche, "Philosophy"));
        books.add(createBook("The Stranger", camus, "Existential Fiction"));
        books.add(createBook("Notes from Underground", dostoevsky, "Psychological Fiction"));

        dostoevsky.toStringAuthor();



        nietzsche.toStringAuthor();


        camus.toStringAuthor();


        for (Book book : books) {
            book.toStringBook();
        }

        findBooksByAuthor(dostoevsky);


    }

    static Book createBook(String title, Author author, String genre) {
        Book book = new Book(title, author, genre);
        return book;
    }


    static void findBooksByAuthor(Author author) {
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                book.toStringBook();
                System.out.println();
            }
        }
    }


}

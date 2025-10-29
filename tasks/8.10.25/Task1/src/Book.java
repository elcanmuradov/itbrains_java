public class Book {
     private static int id = 1;
     private String title;
     private Author author;
     private String genre;
     private int bookId;
     public Book(String title, Author author, String genre) {
         this.title = title;
         this.author = author;
         this.genre = genre;
         this.bookId = id;
         id++;
     }
     public Author getAuthor() {
         return author;

     }
     public void toStringBook(){
         System.out.println("\n-------------------");
         System.out.println("BookId: " + bookId);
         System.out.println("Author: " + author.getName());
         System.out.println("Title: " + title);
         System.out.println("Genre: " + genre);
         System.out.println("\n-------------------");
     }
}

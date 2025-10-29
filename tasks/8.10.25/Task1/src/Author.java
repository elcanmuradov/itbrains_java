public class Author {
    private static int id = 1;
    private int authorId;
    private String name;
    private String country;

    public Author(String name, String country) {
        this.authorId = id;
        this.name = name;
        this.country = country;
        id++;
    }

    public String getName() {
        return name;
    }
    public void toStringAuthor(){
        System.out.println("\n-------------------");
        System.out.println("Author: " + authorId);
        System.out.println("Name: " + name);
        System.out.println("Country: " + country);
        System.out.println("\n-------------------");
    }

}

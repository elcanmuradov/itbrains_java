public class Restaurant {
    private static int id = 1;
    private String name;
    private String city;
    private int restaurantId;
    public Restaurant(String name, String city) {
        this.name = name;
        this.city = city;
        this.restaurantId = id;
        id++;
    }
    public void toStringRestaurant(){
        System.out.println("\n-------------------");
        System.out.println("Restaurant Id: " + this.restaurantId);
        System.out.println("Name: " + this.name);
        System.out.println("City: " + this.city);
        System.out.println("\n-------------------");
    }
    public String getName() {
        return name;
    }
}

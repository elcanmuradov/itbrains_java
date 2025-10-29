import java.awt.*;

public class MenuItem {
    private static int id = 1;
    private String name;
    private double price;
    private int menuItemId;
    private Restaurant restaurant;

    public MenuItem(String name, double price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.menuItemId = id;
        this.restaurant = restaurant;
        id++;
    }

    public void toStringMenu() {
        if (this.price >= 10) {
            System.out.println("\n---------!! PREMIUM MENU !!----------");
        } else {
            System.out.println("\n-------------------");
        }
        System.out.println("Menu Id: " + this.menuItemId);
        System.out.println("Name: " + this.name);
        System.out.println("Restaurant: " + this.restaurant.getName());
        System.out.println("Price: " + this.price);
        System.out.println("\n-------------------");
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}

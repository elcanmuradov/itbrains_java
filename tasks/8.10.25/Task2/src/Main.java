import java.util.ArrayList;

public class Main {
    static ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();

    public static void main(String[] args) {
        Restaurant restaurant1 = new Restaurant("First Restaurant", "Ganja");
        Restaurant restaurant2 = new Restaurant("Second Restaurant", "Sumqayit");
        Restaurant restaurant3 = new Restaurant("Third Restaurant", "Baku");

        menuList.add(createMenu("Pizza1",9.9,restaurant1));
        menuList.add(createMenu("Pizza2",19.9,restaurant2));
        menuList.add(createMenu("Pizza3",29.9,restaurant2));
        menuList.add(createMenu("Pizza4",39.9,restaurant3));
        menuList.add(createMenu("Burger2",8.9,restaurant2));
        menuList.add(createMenu("Burger3",24.9,restaurant3));
        menuList.add(createMenu("Burger4",29.9,restaurant3));
        menuList.add(createMenu("Doner",9.9,restaurant1));

        restaurant1.toStringRestaurant();
        restaurant2.toStringRestaurant();
        restaurant3.toStringRestaurant();

        for (MenuItem menuItem : menuList) {
            menuItem.toStringMenu();
        }

        findItemsByRestaurant("Third Restaurant");



    }
    static void findItemsByRestaurant(String restaurantName){
        for (MenuItem item : menuList){
            if (item.getRestaurant().getName().equals(restaurantName)){
                 item.toStringMenu();
            }
        }
    }
    static MenuItem createMenu(String name,double price,Restaurant restaurant) {
        MenuItem menu = new MenuItem(name,price,restaurant);
        return menu;
    }
}

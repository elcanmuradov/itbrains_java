public class Footballer {
    private String name;
    private String surname;
    private String country;
    private Club club;
    private static int id = 0;

    Footballer() {
        id = id++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClub() {
        return club;

    }

    public void setClub(String club) {
        this.club = club;
    }

}


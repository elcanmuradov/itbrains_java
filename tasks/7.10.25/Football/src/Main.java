import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Club> clubs;
        int id = 0;
    }

    private static Club createClub(int id,String clubName, String country) {
        Club Qarabag = new Club();
        ArrayList<Footballer> footballers = new ArrayList<>();
        footballers.add(createFootballer(Qarabag,"Elcan","Muradov","Azerbaijan"));
        footballers.add(createFootballer("Qarabag","Qurban","Qurbanov","Azerbaijan"));
        footballers.add(createFootballer("Qarabag","Kady","Borges","Brazilia"));
        footballers.add(createFootballer("Qarabag","name4","sname4","Azerbaijan"));
        footballers.add(createFootballer("Qarabag","name5","sname5","Azerbaijan"));

        Qarabag.setId(0);
        Qarabag.setName(clubName);
        Qarabag.setCountry(country);
        Qarabag.setFootballer(footballers);
        return Qarabag;
    }
    private static Footballer createFootballer(Club clubName,String name, String surname,String country){
            Footballer footballer = new Footballer();
            footballer.setName(surname);
            footballer.setCountry(country);
            footballer.setSurname(surname);
            footballer.setClub(clubName);
            return footballer;
    }
}

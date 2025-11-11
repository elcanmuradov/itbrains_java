package Services;


import DTO.Genre;
import DTO.MovieDTO;
import Exceptions.DuplicateMovieException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieService {
    static ArrayList<MovieDTO> movies;
    FileService fs;
    Scanner sc = new Scanner(System.in);

    public MovieService(FileService fs) {
        this.fs = fs;
    }

    void addMovie() throws IOException {
        MovieDTO movie = new MovieDTO();
        Long id = Long.parseLong(fs.readMovie().size() + 1 + "");
        movie.setId(id);

        System.out.println("Please enter the title of the movie");
        String title = sc.nextLine();
        movie.setTitle(title);
        movies = fs.readMovie();
        for (MovieDTO m : movies) {
            if(m.getTitle().equals(title)){
                throw new DuplicateMovieException("Title already exists");
            }
        }

        System.out.println("Please enter the genre of the movie");
        System.out.println("\t1. Action\n\t2. Comedy\n\t3. Drama\n\t4. Horror\n\t5. Animation");
        int genre = sc.nextInt();
        while (genre < 1 || genre > 5) {
            System.out.println("Please enter a valid genre");
            genre = sc.nextInt();
        }
        switch (genre) {
            case 1:
                movie.setGenre(Genre.ACTION);
                break;
            case 2:
                movie.setGenre(Genre.COMEDY);
                break;
            case 3:
                movie.setGenre(Genre.DRAMA);
                break;
            case 4:
                movie.setGenre(Genre.HORROR);
                break;
            case 5:
                movie.setGenre(Genre.ANIMATION);
                break;
            default:
                movie.setGenre(null);
        }

        System.out.println("Please enter the duration of the movie");
        int duration = sc.nextInt();
        movie.setDurationMinutes(duration);

        System.out.println("Please enter the ratings of the movie");
        Double rating = sc.nextDouble();
        movie.setRating(rating);
        System.out.println("Please enter the availability of the movie");
        int availability = sc.nextInt();
        movie.setAvailableSeats(availability);
        System.out.println("Please enter the price of the movie");
        Double price = sc.nextDouble();
        movie.setPrice(price);

        movies = fs.readMovie();
        movies.add(movie);
        fs.writeMovie(movies);
    }

    void viewMovies() throws IOException {
        ArrayList<MovieDTO> movies = fs.readMovie();
        for (MovieDTO movie : movies) {

            System.out.println("==========  " + movie.getId() + "  ==========");
            System.out.println(movie.getTitle());
            System.out.println(movie.getGenre());
            System.out.println(movie.getDurationMinutes());
            System.out.println(movie.getRating());
            System.out.println(movie.getAvailableSeats());
            System.out.println(movie.getPrice());
            System.out.println("========== -- ==========\n");

        }
    }
}

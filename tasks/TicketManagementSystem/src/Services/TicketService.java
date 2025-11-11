package Services;

import DTO.*;
import Exceptions.*;

import java.io.IOException;
import java.io.NotActiveException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TicketService {
    FileService fs;
    Scanner sc = new Scanner(System.in);
    ArrayList<TicketDTO> tickets;

    public TicketService(FileService fs) {
        this.fs = fs;
    }

    void purchaseTicket() throws IOException {
        System.out.println("Enter Customer ID");
        int customerID = sc.nextInt();
        if (customerID > fs.readCustomers().size()) {
            throw new NotFoundException("Customer cannot be found");
        }
        System.out.println("Enter Movie ID");
        int movieID = sc.nextInt();
        if (movieID > fs.readMovie().size()) {
            throw new NotFoundException("Movie cannot be found");
        }

        CustomerDTO customer = fs.readCustomers().get(customerID - 1);
        ArrayList<MovieDTO> movies = fs.readMovie();
        MovieDTO movie = movies.get(movieID - 1);
        tickets = fs.readTickets();
        for (TicketDTO ticket : tickets) {
            if (ticket.getCustomerID() == customerID && ticket.getMovieID() == movieID) {
                throw new DuplicateTicketException("Ticket already exists");
            }
        }
        if (movie.getAvailableSeats() == 0) {
            throw new NoSeatsAvailableException();
        }
        TicketDTO ticket = new TicketDTO();
        ticket.setTicketID(fs.readTickets().size() + 1);
        ticket.setCustomerID(customerID);
        ticket.setMovieID(movieID);
        ticket.setSeatNumber(movie.getAvailableSeats());
        movie.setAvailableSeats(movie.getAvailableSeats() - 1);
        ticket.setPrice(movie.getPrice());
        ticket.setPurchaseTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        ticket.setStatus(Status.PURCHASED);

        System.out.println(customer.getNotificationChannel() + ": Sent to " + (customer.getNotificationChannel().equals(NotificationChannel.SMS) ? customer.getPhone() : customer.getEmail()));
        System.out.println("Ticket " + ticket.getTicketID() + " was successfully purchased");


        fs.writeMovie(movies);
        tickets = fs.readTickets();
        tickets.add(ticket);
        fs.writeTicket(tickets);
    }

    void cancelTicket() throws IOException {
        System.out.println("Enter Ticket ID");
        int ticketID = sc.nextInt();
        ArrayList<TicketDTO> tickets = fs.readTickets();
        if (ticketID > tickets.size()) {
            throw new NotFoundException("Ticket cannot be found");
        }
        TicketDTO ticket = tickets.get(ticketID - 1);
        ticket.setStatus(Status.CANCELLED);
        ArrayList<MovieDTO> movies = fs.readMovie();
        MovieDTO movie = movies.get(ticket.getMovieID() - 1);
        movie.setAvailableSeats(movie.getAvailableSeats() + 1);
        fs.writeMovie(movies);
        fs.writeTicket(tickets);
        System.out.println("Ticket " + ticket.getTicketID() + " was successfully cancelled");

    }

    void viewTickets() throws IOException {
        tickets = fs.readTickets();
        for (TicketDTO ticket : tickets) {
            System.out.println("==========  " + ticket.getTicketID()+ "  ==========");
            System.out.println("CustomerID: " + ticket.getCustomerID());
            System.out.println("MovieID: " + ticket.getMovieID());
            System.out.println("SeatNumber: " + ticket.getSeatNumber());
            System.out.println("Ticket Price: " + ticket.getPrice());
            System.out.println("Ticket Status: " + ticket.getStatus());
            System.out.println("Ticket PurchaseTime: " + ticket.getPurchaseTime());
            System.out.println("========== -- ==========\n");

        }
        System.out.println("\n\n");

    }
}

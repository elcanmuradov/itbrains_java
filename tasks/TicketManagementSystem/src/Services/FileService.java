package Services;

import DTO.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileService {
    ObjectMapper mapper = new ObjectMapper();
    File customerFile = new File("src/Files/customers.json");
    File moviesFile = new File("src/Files/movies.json");
    File ticketsFile = new File("src/Files/tickets.json");

    void writeCustomer(ArrayList<CustomerDTO> customers) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(customerFile, customers);

    }

    ArrayList<CustomerDTO> readCustomers() throws IOException {
        try {
            return mapper.readValue(customerFile, new TypeReference<ArrayList<CustomerDTO>>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    void writeMovie(ArrayList<MovieDTO> movies) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(moviesFile, movies);

    }

    ArrayList<MovieDTO> readMovie() throws IOException {
        try {
            return mapper.readValue(moviesFile, new TypeReference<ArrayList<MovieDTO>>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    void writeTicket(ArrayList<TicketDTO> tickets) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(ticketsFile, tickets);
    }

    ArrayList<TicketDTO> readTickets() throws IOException {
        ArrayList<TicketDTO> tickets = new ArrayList<>();
        try {
            return mapper.readValue(ticketsFile, new TypeReference<ArrayList<TicketDTO>>() {
            });
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


}

package com.example.cinema.repository.impl;

import com.example.cinema.configuration.Constants;
import com.example.cinema.model.CinemaRoom;
import com.example.cinema.model.Seat;
import com.example.cinema.model.Ticket;
import com.example.cinema.repository.ICinemaRoomRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class CinemaRoomRepositoryImpl implements ICinemaRoomRepository {
    private Map<String, Ticket> tickets = new ConcurrentHashMap<>();
    private CinemaRoom room = new CinemaRoom();

    @Override
    public List<Seat> getAvailableSeats() {
        return room.getSeats();
    }

    @Override
    public List<Seat> getSoldSeats() {
        List<List<Seat>> seats = new ArrayList<>();
        List<Ticket> listOfTickets = tickets.values().stream().collect(Collectors.toList());
        for (Ticket t: listOfTickets) {
            seats.add(t.getSeats());
        }
        return seats.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Seat> removeFromAvailableSeats(Seat seat) {
        room.getSeats().remove(seat);
        return room.getSeats();
    }

    @Override
    public List<Seat> addToAvailableSeats(Seat seat) {
        room.getSeats().add(seat);
        return room.getSeats();
    }

    @Override
    public Ticket getTicketByToken(String token) {
        return tickets.get(token);
    }

    @Override
    public Map<String, Ticket> getIssuedTickets() {
        return tickets;
    }

    @Override
    public Map<String, Ticket> removeFromIssuedTickets(Ticket ticket) {
        tickets.remove(ticket.getToken().toString());
        return tickets;
    }

    @Override
    public Map<String, Ticket> addToIssuedTickets(Ticket ticket) {
        tickets.put(ticket.getToken().toString(), ticket);
        return tickets;
    }

    @Override
    public CinemaRoom getCinemaRoom() {
        return room;
    }


    // Used to initialize Cinema Room with some data
    // Will be executed only once during the application startup
    @EventListener(ApplicationReadyEvent.class)
    private void initializeCinemaRoom() {
        room.setId(new Random().nextLong());
        room.setRows(Constants.CINEMA_ROOM_ROWS);
        room.setColumns(Constants.CINEMA_ROOM_COLUMNS);
        room.setSeats(getSeats());
    }

    private List<Seat> getSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= Constants.CINEMA_ROOM_ROWS; i++) {
            for (int j = 1; j <= Constants.CINEMA_ROOM_COLUMNS; j++) {
                if (i <= Constants.CINEMA_ROOM_VIP_ROW_LIMIT) {
                    seats.add(
                            new Seat(i + j, i, j, Constants.VIP_SEAT_PRICE));
                } else {
                    seats.add(
                            new Seat(i + j, i, j, Constants.REGULAR_SEAT_RICE));
                }
            }
        }
        return seats;
    }
}

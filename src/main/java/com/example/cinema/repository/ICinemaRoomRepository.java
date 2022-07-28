package com.example.cinema.repository;

import com.example.cinema.model.CinemaRoom;
import com.example.cinema.model.Seat;
import com.example.cinema.model.Ticket;

import java.util.List;
import java.util.Map;

public interface ICinemaRoomRepository {
    List<Seat> getAvailableSeats();
    List<Seat> getSoldSeats();
    List<Seat> removeFromAvailableSeats(Seat seat);
    List<Seat> addToAvailableSeats(Seat seat);

    Ticket getTicketByToken(String token);
    Map<String, Ticket> getIssuedTickets();
    Map<String, Ticket> removeFromIssuedTickets(Ticket ticket);
    Map<String, Ticket> addToIssuedTickets(Ticket ticket);

    CinemaRoom getCinemaRoom();
}

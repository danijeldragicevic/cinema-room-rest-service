package com.example.cinema.service;

import com.example.cinema.model.dto.*;

public interface ICinemaRoomService {
    CinemaRoomDto getCinemaRoomWithAllAvailableSeats();
    StatisticsDto showStatistics(java.lang.String token);
    TicketDto purchaseSeat(SeatDto seatDto);
    ReturnedTicketDto returnTicket(String token);
}

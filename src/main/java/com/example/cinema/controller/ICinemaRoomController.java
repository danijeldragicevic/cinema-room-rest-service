package com.example.cinema.controller;

import com.example.cinema.exception.SeatAlreadyPurchasedException;
import com.example.cinema.exception.WrongPasswordException;
import com.example.cinema.exception.WrongTokenException;
import com.example.cinema.model.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("/api")
public interface ICinemaRoomController {
    @GetMapping("/seats")
    ResponseEntity<CinemaRoomDto> getAvailableSeats();

    @GetMapping("/stats")
    ResponseEntity<StatisticsDto> showStatistics(@RequestParam(value = "password") String param) throws WrongPasswordException;

    @PostMapping("/purchase")
    ResponseEntity<TicketDto> purchaseSeat(@RequestBody @Valid SeatDto payload) throws SeatAlreadyPurchasedException;

    @PostMapping("/return")
    ResponseEntity<ReturnedTicketDto> returnTicket(@RequestBody Map<String, String> payload) throws WrongTokenException;
}

package com.example.cinema.controller.impl;

import com.example.cinema.configuration.Constants;
import com.example.cinema.controller.ICinemaRoomController;
import com.example.cinema.exception.SeatAlreadyPurchasedException;
import com.example.cinema.exception.WrongPasswordException;
import com.example.cinema.exception.WrongTokenException;
import com.example.cinema.model.dto.*;
import com.example.cinema.service.ICinemaRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CinemaRoomControllerImpl implements ICinemaRoomController {
    private final ICinemaRoomService service;

    @Override
    public ResponseEntity<CinemaRoomDto> getAvailableSeats() {
        return ResponseEntity.ok(service.getCinemaRoomWithAllAvailableSeats());
    }

    @Override
    public ResponseEntity<StatisticsDto> showStatistics(String param) throws WrongPasswordException {
        if ("super_secret".equals(param)) {
            return ResponseEntity.ok(service.showStatistics(param));
        } else {
            throw new WrongPasswordException();
        }
    }

    @Override
    public ResponseEntity<TicketDto> purchaseSeat(SeatDto payload) throws SeatAlreadyPurchasedException {
        SeatDto seatDto = new SeatDto();
        seatDto.setRow(payload.getRow());
        seatDto.setColumn(payload.getColumn());
        if (payload.getRow() <= Constants.CINEMA_ROOM_VIP_ROW_LIMIT) {
            seatDto.setPrice(Constants.VIP_SEAT_PRICE);
        } else {
            seatDto.setPrice(Constants.REGULAR_SEAT_RICE);
        }

        if (service.getCinemaRoomWithAllAvailableSeats().getSeats().contains(seatDto)) {
            return ResponseEntity.ok(service.purchaseSeat(seatDto));
        } else {
            throw new SeatAlreadyPurchasedException();
        }
    }

    @Override
    public ResponseEntity<ReturnedTicketDto> returnTicket(Map<String, String> payload) throws WrongTokenException {
        if (!payload.containsKey("token")) {
            throw new WrongTokenException();
        }

        ReturnedTicketDto returnedTicketDto = service.returnTicket(payload.get("token"));
        if (returnedTicketDto != null) {
            return ResponseEntity.ok(returnedTicketDto);
        } else {
            throw new WrongTokenException();
        }
    }
}

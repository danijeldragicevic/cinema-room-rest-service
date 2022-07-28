package com.example.cinema.service.dto;

import com.example.cinema.configuration.Constants;
import com.example.cinema.model.*;
import com.example.cinema.model.dto.*;
import com.example.cinema.repository.ICinemaRoomRepository;
import com.example.cinema.service.ICinemaRoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CinemaRoomServiceImpl implements ICinemaRoomService {
    private final ICinemaRoomRepository repository;
    private final ModelMapper mapper;

    @Override
    public CinemaRoomDto getCinemaRoomWithAllAvailableSeats() {
        CinemaRoom room = repository.getCinemaRoom();
        return mapToDto(room);
    }

    @Override
    public StatisticsDto showStatistics(java.lang.String token) {
        int income = 0;
        for (Seat s: repository.getSoldSeats()) {
            income += s.getPrice();
        }
        int availableSeats = repository.getAvailableSeats().size();
        int soldTickets = repository.getIssuedTickets().size();

        Statistics statistics = new Statistics(income, availableSeats, soldTickets);
        return mapToDto(statistics);
    }

    @Override
    public TicketDto purchaseSeat(SeatDto seatDto) {
        Seat seat = new Seat(
                seatDto.getRow() + seatDto.getColumn(),
                seatDto.getRow(),
                seatDto.getColumn());

        if (seatDto.getRow() <= Constants.CINEMA_ROOM_VIP_ROW_LIMIT) {
            seat.setPrice(Constants.VIP_SEAT_PRICE);
        } else {
            seat.setPrice(Constants.REGULAR_SEAT_RICE);
        }
        repository.removeFromAvailableSeats(seat);

        Ticket ticket = new Ticket(UUID.randomUUID(), List.of(seat));
        repository.addToIssuedTickets(ticket);
        return mapToDto(ticket);
    }

    @Override
    public ReturnedTicketDto returnTicket(String token) {
        Ticket ticket = repository.getTicketByToken(token);
        if (ticket != null) {
            repository.removeFromIssuedTickets(ticket);

            List<Seat> seats = ticket.getSeats();
            for (Seat s: seats) {
                repository.addToAvailableSeats(s);
            }

            ReturnedTicket returnedTicket = new ReturnedTicket(seats);
            return mapToDtp(returnedTicket);
        } else {
            return null;
        }
    }

    // Entity to DTO mappers:
    private CinemaRoomDto mapToDto(CinemaRoom room) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(room, CinemaRoomDto.class);
    }

    private StatisticsDto mapToDto(Statistics statistics) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(statistics, StatisticsDto.class);
    }

    private TicketDto mapToDto(Ticket ticket) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(ticket, TicketDto.class);
    }

    private ReturnedTicketDto mapToDtp(ReturnedTicket returnedTicket) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper.map(returnedTicket, ReturnedTicketDto.class);
    }
}

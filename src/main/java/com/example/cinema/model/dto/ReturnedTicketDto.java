package com.example.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ReturnedTicketDto {
    @JsonProperty("returned_seats")
    List<SeatDto> seats;
}

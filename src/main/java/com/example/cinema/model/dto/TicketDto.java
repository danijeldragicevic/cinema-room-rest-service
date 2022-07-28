package com.example.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TicketDto {
    private UUID token;
    @Size(max = 81)
    @JsonProperty("booked_seats")
    private List<SeatDto> seats;
}

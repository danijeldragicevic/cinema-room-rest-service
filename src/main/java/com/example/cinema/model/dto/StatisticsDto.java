package com.example.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class StatisticsDto {
    @JsonProperty("current_income")
    private int income;

    @JsonProperty("available_seats")
    private int availableSeats;

    @JsonProperty("sold_tickets")
    private int soldTickets;
}

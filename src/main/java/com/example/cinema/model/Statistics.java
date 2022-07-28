package com.example.cinema.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Statistics {
    private int income;
    private int availableSeats;
    private int soldTickets;
}

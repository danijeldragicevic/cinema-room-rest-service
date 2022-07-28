package com.example.cinema.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ReturnedTicket {
    List<Seat> seats;
}

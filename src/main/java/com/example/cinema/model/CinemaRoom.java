package com.example.cinema.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CinemaRoom {
    private long id;
    private int rows;
    private int columns;
    private List<Seat> seats;

    public CinemaRoom(long id, int rows, int columns) {
        this.id = id;
        this.rows = rows;
        this.columns = columns;
    }
}

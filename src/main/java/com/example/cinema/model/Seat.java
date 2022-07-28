package com.example.cinema.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Seat {
    private long id;
    private int row;
    private int column;
    private int price;

    public Seat(long id, int row, int column) {
        this.id = id;
        this.row = row;
        this.column = column;
    }
}

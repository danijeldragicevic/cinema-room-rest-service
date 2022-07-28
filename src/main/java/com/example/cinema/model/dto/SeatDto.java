package com.example.cinema.model.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class SeatDto {
    @Min(value = 1)
    @Max(value = 9)
    private int row;

    @Min(value = 1)
    @Max(value = 9)
    private int column;

    private int price;
}

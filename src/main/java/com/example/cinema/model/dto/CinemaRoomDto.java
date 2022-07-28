package com.example.cinema.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CinemaRoomDto {
    @Min(value = 1)
    @Max(value = 9)
    private int rows;

    @Min(value = 1)
    @Max(value = 9)
    private int columns;

    @JsonProperty("available_seats")
    private List<SeatDto> seats;
}

package org.example.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Builder
@Getter
@Setter
@Jacksonized
public class SongDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Artist cannot be blank")
    private String artist;

    @NotBlank(message = "Album cannot be blank")
    private String album;

    @NotBlank(message = "Length cannot be blank")
    private String length;

    @NotNull(message = "Resource ID cannot be null")
    private Integer resourceId;

    @NotNull(message = "Year cannot be null")
    private Integer year;

}

package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

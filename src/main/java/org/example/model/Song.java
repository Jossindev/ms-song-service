package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Value;

@Entity
@Value
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    String data;

}

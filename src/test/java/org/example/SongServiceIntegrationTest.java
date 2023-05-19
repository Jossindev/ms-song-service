package org.example;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.example.dto.SongDTO;
import org.example.model.Song;
import org.example.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class SongServiceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private SongRepository songRepository;

    @Test
    public void createSongTest() {
        SongDTO songDTO = SongDTO.builder()
            .name("Test Song")
            .artist("Test Artist")
            .album("Test Album")
            .length("3:30")
            .resourceId(1)
            .year(2022)
            .build();

        ResponseEntity<Integer> responseEntity = restTemplate.postForEntity("/songs", songDTO, Integer.class);
        Integer songId = ofNullable(responseEntity.getBody()).orElse(0);
        Song song = songRepository.findById(songId).get();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNotNull(song);
        assertThat(song.getId()).isEqualTo(1);
    }
}


package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.example.controller.SongController;
import org.example.dto.SongDTO;
import org.example.dto.mapper.SongMapper;
import org.example.model.Song;
import org.example.service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class SongControllerTest {

    private SongController songController;
    private SongService songService;
    private SongMapper mapper;

    @BeforeEach
    public void setup() {
        songService = mock(SongService.class);
        mapper = mock(SongMapper.class);
        songController = new SongController(songService, mapper);
    }

    @Test
    public void testCreateSong_ValidSongDTO_ReturnsOk() {
        SongDTO songDTO = SongDTO.builder().build();
        Song song = new Song();
        when(mapper.mapToSong(songDTO)).thenReturn(song);
        when(songService.saveSong(song)).thenReturn(1);

        ResponseEntity<?> responseEntity = songController.createSong(songDTO, mock(BindingResult.class));

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(1);
    }

    @Test
    public void testCreateSong_InvalidSongDTO_ReturnsBadRequest() {
        SongDTO songDTO = SongDTO.builder().build();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        ResponseEntity<?> responseEntity = songController.createSong(songDTO, bindingResult);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
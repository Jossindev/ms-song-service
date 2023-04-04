package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.DeletedIdsResponse;
import org.example.dto.SongDTO;
import org.example.dto.mapper.SongMapper;
import org.example.model.Song;
import org.example.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final SongMapper mapper;

    @PostMapping
    public ResponseEntity<?> createSong(@Valid @RequestBody SongDTO songDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        Integer id = songService.saveSong(mapper.mapToSong(songDTO));
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSong(@PathVariable("id") Integer id) {
        Song song = songService.findSong(id);
        return ResponseEntity.ok(mapper.mapFromSong(song));
    }

    @DeleteMapping
    public ResponseEntity<DeletedIdsResponse> getResource(@RequestParam("id") @Size(max = 200) String id) {
        List<Integer> ids = Stream.of(id.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> deletedIds = songService.deleteSongs(ids);
        DeletedIdsResponse response = new DeletedIdsResponse(deletedIds);
        return ResponseEntity.ok(response);
    }
}

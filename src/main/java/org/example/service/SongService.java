package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.exception.SongNotFoundException;
import org.example.model.Song;
import org.example.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {

    private final SongRepository songRepository;

    public Integer saveSong(Song song) {
        return songRepository.save(song).getId();
    }

    public Song findSong(Integer id) {
        Optional<Song> song = songRepository.findById(id);

        if (song.isEmpty()) {
            throw new SongNotFoundException("Song not found. Id: " + id);
        }

        return song.get();
    }

    public List<Integer> deleteSongs(List<Integer> ids) {
        List<Integer> deletedIds = new ArrayList<>();

        for (Integer id : ids) {
            Optional<Song> song = songRepository.findById(id);
            if (song.isPresent()) {
                songRepository.deleteById(id);
                deletedIds.add(id);
            }
        }
        return deletedIds;
    }
}

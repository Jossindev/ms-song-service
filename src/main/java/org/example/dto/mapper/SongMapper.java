package org.example.dto.mapper;

import lombok.NoArgsConstructor;
import org.example.dto.SongDTO;
import org.example.model.Song;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class SongMapper {

    public Song mapToSong(SongDTO dto) {
        return Song.builder()
                .name(dto.getName())
                .artist(dto.getArtist())
                .album(dto.getAlbum())
                .length(dto.getLength())
                .resourceId(dto.getResourceId())
                .year(dto.getYear())
                .build();
    }

    public SongDTO mapFromSong(Song song) {
        return SongDTO.builder()
                .name(song.getName())
                .artist(song.getArtist())
                .album(song.getAlbum())
                .length(song.getLength())
                .resourceId(song.getResourceId())
                .year(song.getYear())
                .build();
    }
}

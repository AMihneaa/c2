package com.mihnea.springboot3.c2.Video;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.BDDMockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    VideoService service;
    @Mock VideoRepository repository;

    @BeforeEach
    void setUp(){
        this.service = new VideoService(repository);
    }

    @Test
    void getVideosShouldReturnAll(){
        VideoEntity video1 = new VideoEntity("mihnea", "mihnea_numeVideo", "mihnea_descriereVideo");
        VideoEntity video2 = new VideoEntity("stefan", "stefan_numeVideo", "stefan_descriereVideo");

        when(repository.findAll()).thenReturn(List.of(video1, video2));

        List<VideoEntity> videos = service.getVideos();

        Assertions.assertThat(videos).containsExactly(video1, video2);
    }

    @Test
    void creatingANewVideoShouldReturnTheSameData(){
        BDDMockito.given(repository.saveAndFlush(any(VideoEntity.class)))
                .willReturn(new VideoEntity("mihnea", "mihnea_numeVideo", "mihnea_descriereVideo"));

        VideoEntity videoNou = service.create(new NewVideo("mihnea_numeVideo", "mihnea_descriereVideo"), "mihnea");

        Assertions.assertThat(videoNou.getUserName()).isEqualTo("mihnea");
        Assertions.assertThat(videoNou.getName()).isEqualTo("mihnea_numeVideo");
        Assertions.assertThat(videoNou.getDescription()).isEqualTo("mihnea_descriereVideo");
    }

    @Test
    void deletingAVideoShouldWork() {
        VideoEntity entity = new VideoEntity("mihnea", "mihnea_numeVideo", "mihnea_descriereVideo");
        entity.setId(99L);

        when(repository.findById(99L)).thenReturn(Optional.of(entity));

        service.deleteVideo(99L);

        verify(repository).findById(99L);
        verify(repository).delete(entity);
    }



}

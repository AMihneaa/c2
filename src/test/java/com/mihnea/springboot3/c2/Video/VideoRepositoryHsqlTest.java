package com.mihnea.springboot3.c2.Video;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@DataJpaTest
@ContextConfiguration(initializers = VideoRepositoryTestcontainersTest.DataSourceInitializer.class)
public class VideoRepositoryHsqlTest {

    @Autowired VideoRepository videoRepository;

    @BeforeEach
    void setUp(){
        videoRepository.saveAll(List.of(
                new VideoEntity("mihnea", "mihnea_video1", "mihnea_descriere1"),
                new VideoEntity("mihnea", "mihnea_video2", "mihnea_descriere2"),
                new VideoEntity("stefan", "stefan_video1", "stefan_descriere1")
        ));
    }

    @Test
    void findAllShouldProduceAllVideos(){
        List<VideoEntity> videos = videoRepository.findAll();
        Assertions.assertThat(videos).hasSize(3);
    }


    @Test
    void findByNameShouldRetrieveOneEntry(){
        List<VideoEntity> video = videoRepository.findByNameContainsIgnoreCase("mihnea_video1");

        Assertions.assertThat(video).hasSize(1);
        Assertions.assertThat(video).extracting(VideoEntity::getName).containsExactlyInAnyOrder("mihnea_video1");
    }

    @Test
    void findByNameOrDescriptionShouldFindTwo(){
        List<VideoEntity> video = videoRepository.findByNameContainsOrDescriptionContainsAllIgnoreCase("miHnEa", "video");

        Assertions.assertThat(video).hasSize(2);
        Assertions.assertThat(video).extracting(VideoEntity::getName).contains("mihnea_video1", "mihnea_video2");
    }
}

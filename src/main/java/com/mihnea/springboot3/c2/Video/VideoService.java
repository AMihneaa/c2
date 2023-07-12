package com.mihnea.springboot3.c2.Video;

import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Collections;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<VideoEntity> getVideos() {
        return repository.findAll();
    }

    public VideoEntity create(NewVideo newVideo, String userName){
        return repository.saveAndFlush(new VideoEntity(userName, newVideo.name(), newVideo.description()));
    }

    public void deleteVideo(Long id){
        VideoEntity videoEntity = repository.findById(id).orElseThrow( () -> new RuntimeException("Nu s a gasit video cu id " + id));

        repository.delete(videoEntity);
    }

    public List<VideoEntity> search(Search videoSearch) {
        if (StringUtils.hasText(videoSearch.name()) && StringUtils.hasText(videoSearch.description())) {
            return repository.findByNameContainsOrDescriptionContainsAllIgnoreCase(videoSearch.name(), videoSearch.description());
        }

        if (StringUtils.hasText(videoSearch.name())) {
            return repository.findByNameContainsIgnoreCase(videoSearch.name());
        }

        if (StringUtils.hasText(videoSearch.description())) {
            return repository.findByDescriptionContainsIgnoreCase(videoSearch.description());
        }

        return Collections.emptyList();
    }

    public List<VideoEntity> search(UniversalSearch search) {
        VideoEntity probe = new VideoEntity();
        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setDescription(search.value());
        }
        Example<VideoEntity> example = Example.of(probe,
                        ExampleMatcher.matchingAny()
                                .withIgnoreCase()
                                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

    @PostConstruct
    void initDatabase() {
        repository.save(new VideoEntity("mihnea", "Need HELP with your SPRING BOOT 3 App?", "SPRING BOOT 3 will only speed things up and make itsuper SIMPLE to serve templates and raw data."));
        repository.save(new VideoEntity("mihnea", "Don't do THIS to your own CODE!", "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"));
        repository.save(new VideoEntity("stefan", "SECRETS to fix BROKEN CODE!", "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."));
    }
}
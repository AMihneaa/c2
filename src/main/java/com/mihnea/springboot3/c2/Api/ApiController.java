package com.mihnea.springboot3.c2.Api;

import com.mihnea.springboot3.c2.Video.NewVideo;
import com.mihnea.springboot3.c2.Video.VideoEntity;
import com.mihnea.springboot3.c2.Video.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ApiController {

    private @Autowired VideoService videoService;

    public ApiController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/api/videos")
    public List<VideoEntity> all() {
        return videoService.getVideos();
    }

    @PostMapping("/api/videos")
    public VideoEntity newVideo(@RequestBody NewVideo newVideo, Authentication authentication) {
        return videoService.create(newVideo, authentication.getName());
    }
}
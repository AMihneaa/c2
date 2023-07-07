package com.mihnea.springboot3.c2.Home;

import com.mihnea.springboot3.c2.Video.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private @Autowired VideoService videoService;

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("videos", videoService.getVideos());
        model.addAttribute("authentication", authentication);

        return "index";
    }

//    @GetMapping("/react")
//    public String react(){
//        return "react";
//    }

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute NewVideo newVideo, Authentication authentication){
        videoService.create(newVideo, authentication.getName());

        return "redirect:/";
    }

    @PostMapping("/multi-field-search")
    public String multiFieldSearch(@ModelAttribute Search search, Model model) {
        List<VideoEntity> searchResults = videoService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/universal-search")
    public String universalSearch(@ModelAttribute UniversalSearch search, Model model) {
        List<VideoEntity> searchResults = videoService.search(search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/delete/videos/{videoId}")
    public String deleteVideo(@PathVariable Long videoId){
        videoService.delete(videoId);

        return "redirect:/";
    }

}
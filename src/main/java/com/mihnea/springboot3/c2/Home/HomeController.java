package com.mihnea.springboot3.c2.Home;

import com.mihnea.springboot3.c2.Security.AppConfig;
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
    private @Autowired AppConfig appConfig;

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("videos", videoService.getVideos());
        model.addAttribute("authentication", authentication);

        model.addAttribute("header", appConfig.header());
        model.addAttribute("intro", appConfig.intro());

        return "index";
    }
    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute NewVideo newVideo, Authentication authentication){
        videoService.create(newVideo, authentication.getName());

        return "redirect:/";
    }

//    @PostMapping("/search")
//    public String universalSearch(@ModelAttribute UniversalSearch search, Model model) {
//        List<VideoEntity> searchResults = videoService.search(search);
//        model.addAttribute("nameSearch", searchResults);
//        return "index";
//    }
    @PostMapping("/delete/videos/{id}")
    public String deleteVideo(@PathVariable("id") Long id) {
        videoService.deleteVideo(id);

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(){
        return "/logout";
    }

}
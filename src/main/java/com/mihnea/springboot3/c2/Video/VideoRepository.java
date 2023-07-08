package com.mihnea.springboot3.c2.Video;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    List<VideoEntity> findByNameContainsIgnoreCase(String partialName);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String partialDescription);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName, String partialDescription);
    VideoEntity findById(long id);

    @PreAuthorize("#videoEntity.userName == authentication.name || hasAuthority('ROLE_ADMIN')")
    @Override
    void delete(VideoEntity videoEntity);
}

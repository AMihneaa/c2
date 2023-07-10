package com.mihnea.springboot3.c2.Video;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class CoreDomainTest {

    @Test
    void newVideoEntityShouldHaveNullId(){
        VideoEntity videoEntity = new VideoEntity("mihnea", "title", "description");

        Assertions.assertThat(videoEntity.getId()).isNull();
        Assertions.assertThat(videoEntity.getUserName()).isEqualTo("mihnea");
        Assertions.assertThat(videoEntity.getName()).isEqualTo("title");
        Assertions.assertThat(videoEntity.getDescription()).isEqualTo("description");
    }

    @Test
    void toStringShouldAlsoBeTested(){
        VideoEntity videoEntity = new VideoEntity("mihnea", "title", "description");
        Assertions.assertThat(videoEntity.toString()).isEqualTo("VideoEntity{id=null, username='mihnea', name='title', description='description'}");
    }

    @Test
    void settersShouldMutateState(){
        VideoEntity videoEntity = new VideoEntity("mihnea", "title", "description");

        videoEntity.setId(99L);
        videoEntity.setUserName("stefan");
        videoEntity.setName("nume");
        videoEntity.setDescription("descriere");

        Assertions.assertThat(videoEntity.getId()).isEqualTo(99L);
        Assertions.assertThat(videoEntity.getUserName()).isEqualTo("stefan");
        Assertions.assertThat(videoEntity.getName()).isEqualTo("nume");
        Assertions.assertThat(videoEntity.getDescription()).isEqualTo("descriere");


    }
}

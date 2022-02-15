package br.com.example.repository;

import br.com.example.domain.Anime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Test for anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    void savePersistAnimeWhenSuccessful(){
        Anime anime = createAnime();
        Anime saved = this.animeRepository.save(anime);
        Assertions.assertTrue(saved != null);
        Assertions.assertTrue(saved.getId() != null);
        Assertions.assertTrue(saved.getName() == anime.getName());
    }

    private Anime createAnime(){
        return Anime.builder().name("Meninas Superpoderosas").build();
    }

}
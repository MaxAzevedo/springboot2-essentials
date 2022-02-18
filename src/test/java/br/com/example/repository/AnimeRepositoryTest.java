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
    @DisplayName("Save anime when successful")
    void savePersistAnimeWhenSuccessful(){
        Anime anime = createAnime();
        Anime saved = this.animeRepository.save(anime);
        Assertions.assertTrue(saved != null);
        Assertions.assertTrue(saved.getId() != null);
        Assertions.assertTrue(saved.getName() == anime.getName());
    }

    @Test
    @DisplayName("Update anime when successful")
    void saveUpdateAnimeWhenSuccessful(){
        var anime = createAnime();
        var saved = this.animeRepository.save(anime);

        saved.setName("Anime updated");
        var updated = this.animeRepository.save(saved);

        Assertions.assertTrue(saved != null);
        Assertions.assertTrue(saved.getId() != null);
        Assertions.assertEquals(saved.getName(), "Anime updated");
    }

    @Test
    @DisplayName("Delete anime when successful")
    void deleteAnimeWhenSuccessful(){
        var anime = createAnime();
        var saved = this.animeRepository.save(anime);
        this.animeRepository.delete(saved);
        var deleted = animeRepository.findById(saved.getId());
        Assertions.assertTrue(deleted.isEmpty());
    }

    @Test
    @DisplayName("Find anime by name when successful")
    void findByNameWhenSuccessful(){
        var anime = createAnime();
        this.animeRepository.save(anime);
        var animes = animeRepository.findByName("Meninas Superpoderosas");
        Assertions.assertTrue(!animes.isEmpty());
        Assertions.assertTrue(animes.contains(anime));

    }

    @Test
    @DisplayName("Find anime by name when not found")
    void findByNameWhenNotFoundAnime(){
        var anime = createAnime();
        this.animeRepository.save(anime);
        var animes = animeRepository.findByName("Invalid Anime");
        Assertions.assertTrue(animes.isEmpty());

    }

    private Anime createAnime(){
        return Anime.builder().name("Meninas Superpoderosas").build();
    }

}
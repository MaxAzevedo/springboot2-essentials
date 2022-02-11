package br.com.example.service;

import br.com.example.domain.Anime;
import br.com.example.request.AnimePostRequestBody;
import br.com.example.request.AnimePutRequestBody;
import br.com.example.exception.BadRequestException;
import br.com.example.mapper.AnimeMapper;
import br.com.example.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Page<Anime> all(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public Anime findById(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
       return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void remove(Long id) {
        animeRepository.delete(findById(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime animeSaved = findById(animePutRequestBody.getId());
        Anime newAnime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        newAnime.setId(animeSaved.getId());
        animeRepository.save(newAnime);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }
}

package com.aws.example.service.posts;

import com.aws.example.domain.posts.Posts;
import com.aws.example.domain.posts.PostsRepository;
import com.aws.example.web.dto.PostsResponseDto;
import com.aws.example.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public List<PostsResponseDto> findAll() {
        List<Posts> posts = postsRepository.findAll();
        List<PostsResponseDto> result = new ArrayList<>();
        posts.forEach((p) -> result.add(new PostsResponseDto(p)));
        return result;
    }

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto dto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        posts.update(dto.getTitle(), dto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new PostsResponseDto(entity);
    }
}

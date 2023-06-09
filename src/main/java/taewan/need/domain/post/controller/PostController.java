package taewan.need.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import taewan.need.domain.post.dto.PostInfoDto;
import taewan.need.domain.post.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostInfoDto> searchAllWithFilter(Sort sort,
                                                 @RequestParam(required = false, defaultValue = "") String job,
                                                 @RequestParam(required = false, defaultValue = "") String benefit,
                                                 @RequestParam(required = false, defaultValue = "") String sex,
                                                 @RequestParam(required = false, defaultValue = "") String administrativeDistrict,
                                                 @RequestParam(required = false, defaultValue = "") String search,
                                                 @RequestParam(required = false, defaultValue = "0") Long offset) {
        return postService.findAllWithFilter(sort, job, benefit, sex, administrativeDistrict, search, offset);
    }
}

package taewan.need.domain.post.service;

import org.springframework.data.domain.Sort;
import taewan.need.domain.post.dto.PostInfoDto;

import java.util.List;

public interface PostService {

    List<PostInfoDto> findAllWithFilter(Sort sort, String job, String benefit, String sex, String administrativeDistrict,
                                        String search, Long offset);
}

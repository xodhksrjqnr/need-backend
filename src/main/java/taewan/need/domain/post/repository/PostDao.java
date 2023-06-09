package taewan.need.domain.post.repository;

import org.springframework.data.domain.Sort;
import taewan.need.domain.post.entity.Post;

import java.util.List;

public interface PostDao {

    List<Post> findAllWithFilter(Sort sort, String job, String benefit, String sex, String administrativeDistrict,
                                 String search, Long offset);
    long count();
}

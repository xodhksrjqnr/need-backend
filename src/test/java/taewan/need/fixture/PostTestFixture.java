package taewan.need.fixture;

import taewan.need.domain.condition.entity.Benefit;
import taewan.need.domain.condition.entity.Job;
import taewan.need.domain.post.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostTestFixture {

    public static String toString(Post post) {
        return post.getThumbnail() + post.getTitle() + post.getUrl() + post.getJob().getName() +
                post.getBenefit().getName() + post.getStatus() + post.getCreatedDate() +
                post.getLastModifiedDate() + post.getLastModifiedDate();
    }

    public static Post createPost() {
        return createPost(1);
    }

    public static Post createPost(int index) {
        return Post.builder()
                .title("test" + index)
                .thumbnail("https://test.com/test.jpg")
                .url("https://test.com")
                .job(new Job("학생"))
                .benefit(new Benefit("취업"))
                .administrativeDistrict("서울특별시 종로구")
                .sex("남성")
                .status(true)
                .closingDate(LocalDateTime.now().plusDays(1L))
                .build();
    }

    public static List<Post> createPostList() {
        List<Post> list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            list.add(createPost(i));
        }
        return list;
    }
}

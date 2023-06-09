package taewan.need.unit.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import taewan.need.domain.post.dto.PostInfoDto;
import taewan.need.domain.post.entity.Post;
import taewan.need.domain.post.repository.PostDao;
import taewan.need.domain.post.service.PostServiceImpl;
import taewan.need.fixture.PostTestFixture;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock private PostDao postDao;
    @InjectMocks private PostServiceImpl postService;

    @Test
    @DisplayName("필터에 유효한 결과가 존재하는 경우 ")
    void findAllWithFilter() {
        //given
        List<Post> posts = PostTestFixture.createPostList();

        when(postDao.findAllWithFilter(any(), anyString(), anyString(), anyString(), anyString(), anyString(), any()))
                .thenReturn(posts);

        //when
        List<PostInfoDto> found = postService.findAllWithFilter(Sort.by("postId"), "학생", "취업", "남성",
                "서울특별시 종로구", "test", 0L);

        //then
        assertEquals(found.size(), 10);
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            PostInfoDto postInfoDto = found.get(i);
            String[] district = post.getAdministrativeDistrict().split(" ");

            assertEquals(post.getThumbnail(), postInfoDto.getThumbnail());
            assertEquals(post.getTitle(), postInfoDto.getTitle());
            assertEquals(post.getUrl(), postInfoDto.getUrl());
            assertEquals(post.getClosingDate(), postInfoDto.getClosingDate());
            assertThat(postInfoDto.getConditions().contains(post.getSex())).isTrue();
            assertThat(postInfoDto.getConditions().contains(post.getBenefit().getName())).isTrue();
            assertThat(postInfoDto.getConditions().contains(post.getJob().getName())).isTrue();
            for (String d : district) {
                assertThat(postInfoDto.getConditions().contains(d)).isTrue();
            }
        }
        verify(postDao, only())
                .findAllWithFilter(any(), anyString(), anyString(), anyString(), anyString(), anyString(), any());
        verify(postDao, times(1))
                .findAllWithFilter(any(), anyString(), anyString(), anyString(), anyString(), anyString(), any());
    }
}

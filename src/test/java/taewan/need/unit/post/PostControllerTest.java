package taewan.need.unit.post;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import taewan.need.domain.post.controller.PostController;
import taewan.need.domain.post.service.PostService;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    private MockMvc mockMvc;
    @Mock private PostService postService;

    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(new PostController(postService))
                .setCustomArgumentResolvers(new SortHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    @DisplayName("각 필터 조건이 설정되지 않은 경우에도 디폴트 값으로 설정되어 정상적으로 처리")
    void searchAllWithFilter() throws Exception {
        //given
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("sort", "postId,DESC");
        map.add("job", "학생");
        map.add("benefit", "취업");
        map.add("sex", "남성");
        map.add("administrativeDistrict", "서울특별시 종로구");
        map.add("search", "test");
        map.add("offset", "10");

        when(postService.findAllWithFilter(any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(new ArrayList<>());

        //when
        ResultActions result1 = mockMvc.perform(get("/posts")
                .params(map));
        map.remove("job");
        ResultActions result2 = mockMvc.perform(get("/posts")
                .params(map));
        map.add("job", "학생");
        map.remove("benefit");
        ResultActions result3 = mockMvc.perform(get("/posts")
                .params(map));
        map.add("benefit", "취업");
        map.remove("sex");
        ResultActions result4 = mockMvc.perform(get("/posts")
                .params(map));
        map.add("sex", "남성");
        map.remove("administrativeDistrict");
        ResultActions result5 = mockMvc.perform(get("/posts")
                .params(map));
        map.add("administrativeDistrict", "서울특별시 종로구");
        map.remove("search");
        ResultActions result6 = mockMvc.perform(get("/posts")
                .params(map));
        map.add("search", "test");
        map.remove("offset");
        ResultActions result7 = mockMvc.perform(get("/posts")
                .params(map));
        map.add("offset", "10");
        map.remove("sort");
        ResultActions result8 = mockMvc.perform(get("/posts")
                .params(map));

        //then
        result1.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON));
        result2.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON));
        result3.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON));
        result4.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON));
        result5.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON));
        result6.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON));
        result7.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON));
        result8.andExpectAll(status().isOk(), content().contentType(MediaType.APPLICATION_JSON));
    }
}

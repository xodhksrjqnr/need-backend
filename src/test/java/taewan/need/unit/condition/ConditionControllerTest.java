package taewan.need.unit.condition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import taewan.need.domain.condition.controller.ConditionController;
import taewan.need.domain.condition.dto.Condition;
import taewan.need.domain.condition.service.ConditionService;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@ExtendWith(MockitoExtension.class)
@Sql(scripts = "/test-condition-data.sql")
public class ConditionControllerTest {

    private MockMvc mockMvc;
    @Mock private ConditionService conditionService;

    @BeforeEach
    void setup() {
        mockMvc = standaloneSetup(new ConditionController(conditionService))
                .build();
    }

    @Test
    @DisplayName("필터 조건 존체 조회 테스트")
    void searchAll() throws Exception {
        //given
        when(conditionService.findAllCondition())
                .thenReturn(new Condition(new ArrayList<>(), new ArrayList<>()));

        //when //then
        mockMvc.perform(get("/conditions"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

package taewan.need.unit.condition;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import taewan.need.domain.condition.dto.Condition;
import taewan.need.domain.condition.repository.ConditionDao;
import taewan.need.domain.condition.service.ConditionServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ConditionServiceTest {

    @Mock
    private ConditionDao conditionDao;
    @InjectMocks
    private ConditionServiceImpl conditionService;

    private List<String> benefits = List.of("취업", "생활", "교통", "학습");
    private List<String> jobs = List.of("학생", "대학생", "취준생");

    @Test
    @DisplayName("benefit과 job 테이블에 저장된 타입 전체 조회 테스트")
    void findAllCondition() {
        //given
        when(conditionDao.findAllTypeName("Benefit")).thenReturn(benefits);
        when(conditionDao.findAllTypeName("Job")).thenReturn(jobs);

        //when
        Condition condition = conditionService.findAllCondition();

        //then
        assertEquals(condition.getBenefits().size(), 4);
        assertEquals(condition.getJobs().size(), 3);
        verify(conditionDao, times(2)).findAllTypeName(anyString());
    }
}

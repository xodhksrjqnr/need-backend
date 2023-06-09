package taewan.need.domain.condition.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taewan.need.domain.condition.dto.Condition;
import taewan.need.domain.condition.repository.ConditionDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService {

    private final ConditionDao conditionDao;

    @Override
    public Condition findAllCondition() {
        List<String> typeBenefit = conditionDao.findAllTypeName("Benefit");
        List<String> typeJob = conditionDao.findAllTypeName("Job");

        return new Condition(typeBenefit, typeJob);
    }
}

package taewan.need.domain.condition.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taewan.need.domain.condition.dto.Condition;
import taewan.need.domain.condition.service.ConditionService;

@RestController
@RequestMapping("/conditions")
@RequiredArgsConstructor
public class ConditionController {

    private final ConditionService conditionService;

    @GetMapping
    public Condition searchAll() {
        return conditionService.findAllCondition();
    }
}

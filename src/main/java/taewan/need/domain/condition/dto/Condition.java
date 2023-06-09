package taewan.need.domain.condition.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class Condition {

    private List<String> benefits;
    private List<String> jobs;

    public Condition(List<String> benefits, List<String> jobs) {
        this.benefits = benefits;
        this.jobs = jobs;
    }
}

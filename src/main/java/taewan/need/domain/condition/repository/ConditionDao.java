package taewan.need.domain.condition.repository;


import java.util.List;

public interface ConditionDao {

    public List<String> findAllTypeName(String target);
    long count(final String target);
}

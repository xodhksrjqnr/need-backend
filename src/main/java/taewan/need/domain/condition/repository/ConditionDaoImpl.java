package taewan.need.domain.condition.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ConditionDaoImpl implements ConditionDao {

    private final EntityManager em;

    @Override
    public List<String> findAllTypeName(String target) {
        return em.createQuery("select t.name from " + target + " t", String.class)
                .getResultList();
    }

    @Override
    public long count(final String target) {
        String query = "select count(*) from " + target + " t";

        return ((Number) em.createQuery(query)
                .getSingleResult())
                .longValue();
    }
}
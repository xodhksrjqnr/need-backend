package taewan.need.unit.condition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.jdbc.Sql;
import taewan.need.domain.condition.repository.ConditionDao;
import taewan.need.domain.condition.repository.ConditionDaoImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import({ConditionDaoImpl.class, PersistenceExceptionTranslationPostProcessor.class})
@DataJpaTest
@EnableJpaRepositories(basePackages = "taewan.need.domain.condition.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/test-condition-data.sql")
public class ConditionDaoTest {

    @Autowired private ConditionDao conditionDao;

    @Test
    @DisplayName("benefit 전체 조회 테스트")
    void findAllTypeName() {
        //when
        List<String> benefits = conditionDao.findAllTypeName("Benefit");
        List<String> jobs = conditionDao.findAllTypeName("Job");

        //then
        assertEquals(benefits.size(), 4);
        assertEquals(jobs.size(), 3);
    }

    @Test
    @DisplayName("count 테스트")
    void count_benefit() {
        //when
        long benefitCount = conditionDao.count("Benefit");
        long jobCount = conditionDao.count("Job");

        //then
        assertEquals(benefitCount, 4);
        assertEquals(jobCount, 3);
    }
}

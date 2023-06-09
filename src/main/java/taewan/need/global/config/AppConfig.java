package taewan.need.global.config;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import taewan.need.domain.condition.repository.ConditionDao;
import taewan.need.domain.condition.repository.ConditionDaoImpl;
import taewan.need.domain.condition.service.ConditionService;
import taewan.need.domain.condition.service.ConditionServiceImpl;
import taewan.need.domain.post.repository.PostDao;
import taewan.need.domain.post.repository.PostDaoImpl;
import taewan.need.domain.post.service.PostService;
import taewan.need.domain.post.service.PostServiceImpl;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class AppConfig {

    private final EntityManager entityManager;

    @Bean
    public PostDao postDao() {
        return new PostDaoImpl(entityManager);
    }

    @Bean
    public PostService postService() {
        return new PostServiceImpl(postDao());
    }

    @Bean
    public ConditionDao conditionDao() {
        return new ConditionDaoImpl(entityManager);
    }

    @Bean
    public ConditionService conditionService() {
        return new ConditionServiceImpl(conditionDao());
    }
}

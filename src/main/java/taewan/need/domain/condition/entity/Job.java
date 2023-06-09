package taewan.need.domain.condition.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "jobs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Job {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;
    private String name;

    public Job(String name) {
        this.name = name;
    }
}

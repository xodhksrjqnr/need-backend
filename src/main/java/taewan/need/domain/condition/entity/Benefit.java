package taewan.need.domain.condition.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Table(name = "benefits")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Benefit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer benefitId;
    private String name;

    public Benefit(String name) {
        this.name = name;
    }
}

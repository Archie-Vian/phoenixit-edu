package phoenixit.education.services.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.Specification;
import phoenixit.education.models.Education;

import java.util.Set;
import java.util.UUID;

import static org.springframework.data.jpa.domain.Specification.where;
import static phoenixit.education.services.filter.BaseSpecification.in;
import static phoenixit.education.services.filter.BaseSpecification.like;

/**
 * Фильтр направлений образования.
 */
@Getter
@Setter
@ToString
public class EducationFilter implements Filter<Education> {

    /**
     * Идентификаторы направлений образования.
     */
    private Set<UUID> ids;
    /**
     * Наименования направления образования.
     */
    private String name;
    /**
     * Код направления образования.
     */
    private String code;

    @Override
    public Specification<Education> toSpecification() {
        return where(BaseSpecification
                .<Education>equal("code", code))
                .and(like("name", name))
                .and(in("id", ids));
    }
}

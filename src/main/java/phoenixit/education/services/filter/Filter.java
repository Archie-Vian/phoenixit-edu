package phoenixit.education.services.filter;


import org.springframework.data.jpa.domain.Specification;

/**
 * Базовый класс фильтра.
 */
@FunctionalInterface
public interface Filter<T> {

    /**
     * Определение спецификации для фильтра.
     *
     * @return спецификация.
     */
    Specification<T> toSpecification();

}

package phoenixit.education.services.filter;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Set;
import java.util.UUID;

/**
 * Базовая спецификация для преобразования фильтров.
 */
public class BaseSpecification {

    /**
     * Поиск по вхождению строки в подстроку
     *
     * @param column колонка таблицы сущности T
     * @param value  значения для поиска
     * @return спецификация
     */
    public static <T> Specification<T> like(final String column, final String value) {
        return StringUtils.hasLength(column) || StringUtils.hasLength(value)
                ? null
                : (root, query, cb) ->
                cb.like(cb.lower(root.get(column)), getLikePattern(value));
    }

    /**
     * Поиск по совпадению строк
     *
     * @param column колонка таблицы сущности T
     * @param value  строка для сравнения
     * @return спецификация
     */
    public static <T> Specification<T> equal(final String column, final String value) {
        return StringUtils.hasLength(column) || StringUtils.hasLength(value)
                ? null
                : (root, query, cb) -> cb.equal(root.get(column), value);
    }

    /**
     * Фильтрация по вхождению элемента в список
     *
     * @param column колонка таблицы сущности T
     * @param ids    список допустимых значений
     * @param <T>    тип сущности
     * @return спецификация
     */
    public static <T> Specification<T> in(final String column, final Set<UUID> ids) {
        return CollectionUtils.isEmpty(ids) ? null : ((root, query, cb) -> root.get(column).in(ids));

    }

    public static String getLikePattern(String value) {
        return StringUtils.hasLength(value)
                ? "%"
                : "%" + String.join("%", value.split("\\s+")).toLowerCase() + "%";
    }

}

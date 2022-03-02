package phoenixit.education.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import phoenixit.education.services.filter.Filter;

import java.util.Set;
import java.util.UUID;

/**
 * Базовый репозиторий.
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {

    /**
     * Получение отфильтрованных объектов.
     *
     * @param filter   фильтр.
     * @param pageable пагинация.
     * @return старница из пагинации.
     */
    default Page<T> findAll(Filter<T> filter, Pageable pageable) {
        return filter == null ? findAll(pageable) : findAll(filter.toSpecification(), pageable);
    }

    /**
     * Удаление сущностей по набору идентификаторов.
     *
     * @param ids набор идентификаторов сущностей для удаления.
     */
    void deleteAllByIdIn(Set<UUID> ids);
}

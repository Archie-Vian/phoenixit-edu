package phoenixit.education.repositories;

import phoenixit.education.models.Education;

/**
 * Репозиторий справочника "Направления образования"
 */
public interface EducationRepository extends BaseRepository<Education> {

    boolean existsByCode(String code);

    boolean existsByName(String name);

}

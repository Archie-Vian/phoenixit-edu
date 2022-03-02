package phoenixit.education.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenixit.education.exceptions.EntityNotFoundException;
import phoenixit.education.exceptions.EntitySaveException;
import phoenixit.education.exceptions.messages.EntitySaveExceptionMessages;
import phoenixit.education.models.Education;
import phoenixit.education.repositories.EducationRepository;
import phoenixit.education.services.filter.EducationFilter;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Сервис направления образования.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducationService {

    private final EducationRepository educationRepository;

    /**
     * Найти сущности направления образования, удовлетворяющие условиям фильтра.
     *
     * @param filter   Фильтр поиска.
     * @param pageable Условия пагинации, сортировки.
     * @return Страница объектов сущности направления образования.
     */
    public Page<Education> findAll(EducationFilter filter, Pageable pageable) {
        return educationRepository.findAll(filter, pageable);
    }

    /**
     * Поиск сущности направления образования по идентификатору.
     *
     * @param id Идентификатор элемента справочника "Направления образования".
     * @return Объект справочника "Направления образования".
     */
    public Education findOne(UUID id) {
        return educationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Education.class, id));
    }

    /**
     * Сохранение данных направления образования.
     *
     * @param education Объект сущности направления образования.
     * @return Результат сохранения данных направления образования.
     */
    @Transactional
    public Education save(Education education) {
        Education storedEntity;
        Optional<Education> storedEntityOptional = education.getId() != null
                ? educationRepository.findById(education.getId())
                : Optional.empty();

        if (storedEntityOptional.isPresent()) {
            storedEntity = storedEntityOptional.get();
            storedEntity.setName(education.getName());
            storedEntity.setCode(education.getCode());
        } else {
            validateNewEntity(education);
            storedEntity = educationRepository.save(education);
        }

        return storedEntity;
    }

    /**
     * Удаление сущностей направления образования.
     *
     * @param ids Перечень идентификаторов для удаления.
     */
    @Transactional
    public void delete(Set<UUID> ids) {
        educationRepository.deleteAllByIdIn(ids);
    }

    /**
     * Валидация перед добавлением новой записи в БД.
     *
     * @param education Сохраняемая сущность.
     */
    private void validateNewEntity(Education education) {
        if (education.getId() != null) {
            throw new EntityNotFoundException(Education.class, education.getId());
        }
        if (hasCodeDuplicate(education.getCode())) {
            throw new EntitySaveException(
                    String.format(EntitySaveExceptionMessages.EDUCATION_CODE_DUPLICATE, education.getCode()));
        }
        if (hasNameDuplicate(education.getName())) {
            throw new EntitySaveException(
                    String.format(EntitySaveExceptionMessages.EDUCATION_NAME_DUPLICATE, education.getName()));
        }
    }

    /**
     * Существует ли дубликат по коду.
     *
     * @param code Код элемента справочника.
     * @return Да\Нет.
     */
    private boolean hasCodeDuplicate(String code) {
        return educationRepository.existsByCode(code);
    }

    /**
     * Существует ли дубликат по имени.
     *
     * @param name Имя элемента справочника.
     * @return Да\Нет.
     */
    private boolean hasNameDuplicate(String name) {
        return educationRepository.existsByName(name);
    }

}

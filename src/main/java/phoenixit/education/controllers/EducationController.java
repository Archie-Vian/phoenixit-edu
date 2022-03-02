package phoenixit.education.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import phoenixit.education.models.Education;
import phoenixit.education.services.EducationService;
import phoenixit.education.services.filter.EducationFilter;

import java.util.Set;
import java.util.UUID;

/**
 * Контроллер направлений образования.
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    /**
     * Получение постраничной выборки направлений образования.
     *
     * @param filter   Фильтр выборки направлений образования.
     * @param pageable Настройки пагинации.
     * @return Страница направления образования.
     */
    @GetMapping
    public Page<Education> findAll(EducationFilter filter, Pageable pageable) {
        log.info("Получение выборки направлений образования по фильтру: {}", filter);
        return educationService.findAll(filter, pageable);
    }

    /**
     * Получение элемента справочника направления образования по идентификатору.
     *
     * @param id Идентификатор элемента справочника.
     * @return Элемент справочника.
     */
    @GetMapping("/{id}")
    public Education findOne(@PathVariable UUID id) {
        log.info("Получение направления образования с id: {}", id);
        return educationService.findOne(id);
    }

    /**
     * Сохранение элемента справочника направления образования.
     *
     * @param education Объект сущности справочника направления образования.
     * @return Сохраненный элемент справочника.
     */
    @PostMapping
    public Education save(@RequestBody Education education) {
        log.info("Добавление элемента справочника направлений образования: {}", education);
        return educationService.save(education);
    }

    /**
     * Обновление элемента справочника направления образования.
     *
     * @param education Объект сущности справочника направления образования.
     * @return Обновленный элемент справочника.
     */
    @PutMapping
    public Education update(@RequestBody Education education) {
        log.info("Обновление элемента справочника направлений образования с идентификатором {}: {}",
                education.getId(), education);
        return educationService.update(education);
    }

    /**
     * Удаление элементов справочников по перечню идентификаторов.
     *
     * @param ids Перечень идентификаторов удаляемых элементов справочника.
     */
    @DeleteMapping
    public void delete(@RequestBody Set<UUID> ids) {
        log.info("Удаление направлений образования с id: {}", ids);
        educationService.delete(ids);
    }

}

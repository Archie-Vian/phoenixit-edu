package phoenixit.education.exceptions;

import phoenixit.education.models.Identifiable;

import java.util.UUID;

import static java.lang.String.format;

/**
 * Исключение отсутствия записи в базе данных.
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<? extends Identifiable> clazz, UUID id) {
        super(format("Сущность не найдена '%s' '%s'", clazz.getSimpleName(), id.toString()));
    }

}

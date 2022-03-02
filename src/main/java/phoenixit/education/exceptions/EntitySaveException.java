package phoenixit.education.exceptions;

import static java.lang.String.format;

/**
 * Исключение валидации сущности.
 */
public class EntitySaveException extends RuntimeException {

    public EntitySaveException(String eerMsg) {
        super(format("%s", eerMsg));
    }

}

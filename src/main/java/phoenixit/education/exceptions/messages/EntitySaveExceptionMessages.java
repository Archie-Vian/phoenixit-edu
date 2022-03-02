package phoenixit.education.exceptions.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntitySaveExceptionMessages {

    public final static String NON_EMPTY_IDENTIFIER_SAVE =
            "Указан идентификатор записи. Сохранение с непустым идентификатором невозможно.";
    public final static String EMPTY_IDENTIFIER_UPDATE =
            "Для обновления записи необходимо указать идентификатор.";

    public final static String EDUCATION_CODE_DUPLICATE =
            "Элемент справочника \"Направления образования\" с кодом %s уже существует в системе";
    public final static String EDUCATION_NAME_DUPLICATE =
            "Элемент справочника \"Направления образования\" с именем %s уже существует в системе";
    public final static String EDUCATION_CODE_NOT_PRESENT =
            "Код элемента справочника \"Направления образования\" не должен быть пустым";
    public final static String EDUCATION_NAME_NOT_PRESENT =
            "Имя элемента справочника \"Направления образования\" не должно быть пустым";

}

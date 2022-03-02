package phoenixit.education.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import static phoenixit.education.exceptions.messages.EntitySaveExceptionMessages.EDUCATION_CODE_NOT_PRESENT;
import static phoenixit.education.exceptions.messages.EntitySaveExceptionMessages.EDUCATION_NAME_NOT_PRESENT;

/**
 * Направление образования.
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "EDUCATION")
@EqualsAndHashCode(callSuper = true)
public class Education extends Identifiable {

    /**
     * Имя направления образования.
     */
    @NotBlank(message = EDUCATION_NAME_NOT_PRESENT)
    @Column(name = "NAME", nullable = false, unique = true)
    private String name = "";

    /**
     * Код направления образования.
     */
    @NotBlank(message = EDUCATION_CODE_NOT_PRESENT)
    @Column(name = "CODE", nullable = false, unique = true)
    private String code = "";

}

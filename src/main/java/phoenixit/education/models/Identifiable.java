package phoenixit.education.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Базовый класс сущности
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Identifiable {

    /**
     * Идентификатор сущности
     */
    @Id
    @GeneratedValue(generator = "UUIDCustomGenerator")
    @GenericGenerator(name = "UUIDCustomGenerator",
            strategy = "phoenixit.education.config.generator.UUIDCustomGenerator")
    @Type(type = "uuid-char")
    @Column(name = "ID", nullable = false, length = 36, unique = true)
    private UUID id;

}

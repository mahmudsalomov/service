package uz.bank.web.kredit.model;

import lombok.*;
import uz.bank.web.kredit.model.template.EntityUUID;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Passport extends EntityUUID {

    @NotNull
    private String series;
    @NotNull
    private String number;
    @NotNull
    private String name;
    private String surname;
    private String country_code;
    @NotNull
    private String place_of_birth;
    private Date date_of_birth;
    private Date date_of_issue;
    private Date date_of_expiry;

}

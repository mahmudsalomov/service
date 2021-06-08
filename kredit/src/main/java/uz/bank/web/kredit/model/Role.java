package uz.bank.web.kredit.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.bank.web.kredit.model.others.RoleName;
import uz.bank.web.kredit.model.template.EntityShort;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role extends EntityShort implements GrantedAuthority{


    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
    @Override
    public String getAuthority() {
        return roleName.name();
    }
}

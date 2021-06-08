package uz.bank.web.passport.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import uz.bank.web.passport.model.others.RoleName;
import uz.bank.web.passport.model.template.EntityShort;

import javax.persistence.*;

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

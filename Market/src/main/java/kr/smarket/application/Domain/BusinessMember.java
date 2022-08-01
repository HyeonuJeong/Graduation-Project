package kr.smarket.application.Domain;

import kr.smarket.application.Domain.Enum.Category;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class BusinessMember extends Member {

    private String marketName;

    private String storeName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String officeNumber;
}

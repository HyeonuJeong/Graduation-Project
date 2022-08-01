package kr.smarket.application.Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import kr.smarket.application.Domain.Common.LogDateTime;
import kr.smarket.application.Domain.Enum.Category;
import kr.smarket.application.Domain.Enum.Region;
import kr.smarket.application.Domain.Enum.Role;
import kr.smarket.application.Domain.Enum.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends LogDateTime {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable=false, unique=true, length=20)
    private String userName;

    @Column(nullable=false, unique=true, length=20)
    private String userId;

    private String password;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(nullable=false, length=50)
    private String email;

    private String address;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String marketName;

    private String storeName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String officeNumber;
}

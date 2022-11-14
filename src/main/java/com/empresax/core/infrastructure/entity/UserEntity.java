package com.empresax.core.infrastructure.entity;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_x")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private UUID id_user;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String fist_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "phone_number")
    private String phone_number;

    @OneToMany
    @JoinTable(
        name = "user_address", 
        joinColumns = @JoinColumn(name = "fk_user"), 
        inverseJoinColumns = @JoinColumn(name = "fk_address")
    )
    private List<AddressEntity> addresses = Collections.emptyList();

    @Convert(converter = RoleConverter.class)
    private RoleType role = RoleType.USER;

    @Enumerated(EnumType.ORDINAL)
    private StateType state = StateType.ACTIVE;

    @Column(name = "date_entry")
    private Date date_entry = new Date(System.currentTimeMillis());

}

@Converter(autoApply = true)
class RoleConverter implements AttributeConverter<RoleType, String> {

    @Override
    public String convertToDatabaseColumn(RoleType attribute) {
        return attribute.getAuthority();
    }

    @JsonCreator
    @Override
    public RoleType convertToEntityAttribute(@JsonProperty String dbData) {
        return RoleType.fromAuthority(dbData);
    }

}
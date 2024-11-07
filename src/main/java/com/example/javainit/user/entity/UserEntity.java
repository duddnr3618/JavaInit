package com.example.javainit.user.entity;

import com.example.javainit.user.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPhone;

    @Column(nullable = false)
    private LocalDate createAt;

    @Enumerated(EnumType.STRING)
    private Role role;


    // Entity -> DTO 변환 메소드
    public UserDto toUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(this.id);
        userDto.setUserEmail(this.userEmail);
        userDto.setPassword(this.password);
        userDto.setUserName(this.userName);
        userDto.setUserPhone(this.userPhone);
        userDto.setCreateAt(this.createAt);
        userDto.setRole(this.role);
        return userDto;
    }

    // DTO -> Entity 변환 메소드
    public static UserEntity toUserEntity(UserDto dto) {
        return new UserEntity(
                dto.getId(),
                dto.getUserEmail(),
                dto.getPassword(),
                dto.getUserName(),
                dto.getUserPhone(),
                dto.getCreateAt(),
                dto.getRole()
        );
    }

}

package ru.itis.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.trip.entities.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastname;
    private String middlename;
    private String address;
    private String job;
    private Integer age;
    private String photo;
    private String additionalInfo;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .lastname(user.getLastname())
                .middlename(user.getMiddlename())
                .address(user.getAddress())
                .job(user.getJob())
                .age(user.getAge())
                .photo(user.getPhoto())
                .additionalInfo(user.getAdditionalInfo())
                .build();
    }
}

package ru.itis.trip.entities.dto;

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
    Long id;
    String username;
    String email;
    String name;
    String lastname;
    String middlename;
    String address;
    String job;
    Integer age;
    String photo;   //path?
    String additionalInfo;

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

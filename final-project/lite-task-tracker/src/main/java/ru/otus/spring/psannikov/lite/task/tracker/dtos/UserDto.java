package ru.otus.spring.psannikov.lite.task.tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.otus.spring.psannikov.lite.task.tracker.models.User;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class UserDto {
    private long id;

    private String name;

    private String email;

    private String login;

    private String password;

    private long departmentId;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .login(user.getLogin())
                .password(user.getPassword())
                .departmentId(user.getDepartment().getId())
                .build();
    }
}

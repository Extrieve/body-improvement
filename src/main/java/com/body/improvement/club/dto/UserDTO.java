package com.body.improvement.club.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {

        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
//        private String role;
}

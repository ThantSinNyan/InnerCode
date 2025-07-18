package com.inner_code.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String name;
    private String email;
    private String phone;
    private String password;
    private String address;


}

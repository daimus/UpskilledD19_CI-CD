package id.daimus.productservice.infrastructure.data.jpa.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
@EqualsAndHashCode
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="email", unique = true)
    @NotBlank(message = "email is mandatory")
    private String email;
    @Column(name="phone", unique = true)
    @NotBlank(message = "phone is mandatory")
    private String phone;
    @Column(name="password")
    @NotBlank(message = "password is mandatory")
    private String password;
}

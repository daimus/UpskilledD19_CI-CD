package id.daimus.productservice.application.user.usecase;

import id.daimus.productservice.application.user.entity.User;

import java.util.Optional;

public interface GetUserByEmailUseCase {
    Optional<User> getUserByEmail(String email);
}

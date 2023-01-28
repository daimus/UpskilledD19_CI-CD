package id.daimus.productservice.application.user.usecase;

import id.daimus.productservice.application.user.entity.User;

import java.util.Optional;

public interface GetUserByPhoneUseCase {
    Optional<User> getUserByPhone(String phone);
}

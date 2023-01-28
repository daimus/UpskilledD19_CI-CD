package id.daimus.productservice.application.user.usecase;

import id.daimus.productservice.application.user.entity.User;

import java.util.List;

public interface GetAllUserUseCase {
    List<User> getAllUser();
}

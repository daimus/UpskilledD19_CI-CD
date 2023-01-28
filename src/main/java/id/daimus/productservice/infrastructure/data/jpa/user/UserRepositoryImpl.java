package id.daimus.productservice.infrastructure.data.jpa.user;

import id.daimus.productservice.application.user.entity.User;
import id.daimus.productservice.application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;
    @Override
    public Optional<User> getUserById(Long id) {
        Optional<UserEntity> userEntity = jpaUserRepository.findById(id);
        if (userEntity.isPresent()){
            User user = new User();
            BeanUtils.copyProperties(userEntity.get(), user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<UserEntity> userEntity = jpaUserRepository.findByEmail(email);
        if (userEntity.isPresent()){
            User user = new User();
            BeanUtils.copyProperties(userEntity.get(), user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        Optional<UserEntity> userEntity = jpaUserRepository.findByPhone(phone);
        if (userEntity.isPresent()){
            User user = new User();
            BeanUtils.copyProperties(userEntity.get(), user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        UserEntity createdUserEntity = jpaUserRepository.save(userEntity);
        BeanUtils.copyProperties(createdUserEntity, user);
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userEntity.setId(id);
        UserEntity updatedUserEntity = jpaUserRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUserEntity, user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = jpaUserRepository.findAll();
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity: userEntities){
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            users.add(user);
        }
        return users;
    }

    @Override
    public void deleteUser(Long id) {
        jpaUserRepository.deleteById(id);
    }
}

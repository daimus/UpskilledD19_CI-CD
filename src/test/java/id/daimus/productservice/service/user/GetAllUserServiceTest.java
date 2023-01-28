package id.daimus.productservice.service.user;

import id.daimus.productservice.application.user.entity.User;
import id.daimus.productservice.application.user.repository.UserRepository;
import id.daimus.productservice.application.user.service.GetAllUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GetAllUserServiceTest {
    private GetAllUserService getAllUserService;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        getAllUserService = new GetAllUserService(userRepository);
    }
    @Nested
    class GetAllUser {
        @Test
        void GetAllUser_ValidInput_True() {
            // Given
            List<User> users = asList(new User(), new User());
            given(userRepository.getAllUsers()).willReturn(users);
            // When
            List<User> actualUsers = getAllUserService.getAllUser();
            // Then
            assertThat(actualUsers).isEqualTo(users);
        }
    }
}

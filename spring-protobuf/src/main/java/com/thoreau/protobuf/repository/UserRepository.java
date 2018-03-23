package com.thoreau.protobuf.repository;

import com.thoreau.protobuf.generated.vo.User;
import org.springframework.stereotype.Repository;

/**
 * 2018/3/23 13:48.
 *
 * @author zhaozhou
 */
@Repository
public class UserRepository {
    public User getUser() {
        return User.newBuilder()
                   .setFirstName("Jake")
                   .setLastName("Partusch")
                   .setEmailAddress("jakepartusch@abc.com")
                   .setHomeAddress("123 Seasame Street")
                   .addPhoneNumbers(User.PhoneNumber
                           .newBuilder()
                           .setAreaCode(123)
                           .setPhoneNumber(1234567))
                   .build();
    }
}

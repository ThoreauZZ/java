package com.thoreau.protobuf.repository;

import com.thoreau.protobuf.generated.vo.Hobby;
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
                   .setFirstName("thoreau")
                   .setLastName("zz")
                   .setEmailAddress("thoreau@gmail.com")
                   .setHomeAddress("123 xxx Street")
                   .addHobbies(Hobby.newBuilder().setName("basketball").build())
                   .addHobbies(Hobby.newBuilder().setName("football").build())
//                   .setHobbies(1,Hobby.newBuilder().setName("basketball").build())
                   .addSkills(User.Skill.JAVA)
                   .addSkills(User.Skill.GOLANG)
                   .build();
    }
}

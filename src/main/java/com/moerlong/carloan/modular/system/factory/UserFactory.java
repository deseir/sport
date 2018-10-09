package com.moerlong.carloan.modular.system.factory;

import com.moerlong.carloan.modular.system.transfer.UserDto;
import com.moerlong.carloan.common.persistence.model.User;
import org.springframework.beans.BeanUtils;

/**
 * 用户创建工厂
 *
 * @author hwl
 * @date 2017-05-05 22:43
 */
public class UserFactory {

    public static User createUser(UserDto userDto){
        if(userDto == null){
            return null;
        }else{
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            return user;
        }
    }
}

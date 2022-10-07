package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.VirtualUser;


public interface VirtualUserMapper {

    VirtualUser selectUsers(@Param("userName") String userName, @Param("password") String password);

    VirtualUser selectName(@Param("userName") String userName);

    void addNew(VirtualUser user);
}

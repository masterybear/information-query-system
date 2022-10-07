package org.example.service;

import org.example.pojo.VirtualUser;

public interface VUserService {

    VirtualUser login(String userName, String password);

    boolean register(VirtualUser user);

    boolean selectName(String userName);
}

package com.usermanage.service.impl;

import com.usermanage.dao.UserDao;
import com.usermanage.dao.impl.UserDaoImpl;
import com.usermanage.domain.UserBean;
import com.usermanage.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao=new UserDaoImpl();
    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<UserBean> findAll() {
        return userDao.findAll();
    }

    /***
     * 根据id查询用户
     * @return
     * @param id
     */
    @Override
    public UserBean findById(int id) {
        return userDao.findById(id);
    }

    /***
     * 新增用户
     * @param user
     */
    @Override
    public void addUser(UserBean user) {
        userDao.addUser(user);
    }

    /***
     * 用户信息更新
     * @param user
     */
    @Override
    public void updateUser(UserBean user) {
        userDao.updateUser(user);
    }

    /***
     * 删除用户
     * @param id
     */
    @Override
    public void delUser(String id) {
    userDao.delUser(Integer.parseInt(id));
    }
}

package com.usermanage.service.impl;

import com.usermanage.dao.UserDao;
import com.usermanage.dao.impl.UserDaoImpl;
import com.usermanage.domain.UserBean;
import com.usermanage.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private  UserDao userDao=new UserDaoImpl();
    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<UserBean> findAll() {
        return userDao.findAll();
    }

    /**
     * 登录方法
     *
     * @param
     * @return
     */
    @Override
    public UserBean login(String username, String password) {
        UserBean userBean = userDao.findByUsernameAndPassword(username, password);
        return userBean;
    }

    /**
     * 保存User
     *
     * @param user
     */
    @Override
    public void addUser(UserBean user) {
        userDao.addUser(user);
    }

    /**
     * 根据id删除User
     *
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        userDao.delUser(Integer.parseInt(id));
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public UserBean findUserById(String id) {
        return userDao.findById(Integer.parseInt(id));
    }

    /**
     * 修改用户信息
     *
     * @param user
     */
    @Override
    public void updateUser(UserBean user) {
        userDao.updateUser(user);
    }

    /**
     * 批量删除用户
     *
     * @param ids
     */
    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                userDao.delUser(Integer.parseInt(ids[i]));
            }
        }
    }
}

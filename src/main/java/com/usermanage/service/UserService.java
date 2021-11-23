package com.usermanage.service;

import com.usermanage.domain.UserBean;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService {
        /**
         * 查询所有用户信息
         * @return
         */
        public List<UserBean> findAll();

        /**
         * 登录方法
         * @param
         * @return
         */
        UserBean login(String username, String password);

        /**
         * 保存User
         * @param user
         */
        void addUser(UserBean user);

        /**
         * 根据id删除User
         * @param id
         */
        void deleteUser(String id);

        /**
         * 根据id查询
         * @param id
         * @return
         */
        UserBean findUserById(String id);

        /**
         * 修改用户信息
         * @param user
         */
        void updateUser(UserBean user);

        /**
         * 批量删除用户
         * @param ids
         */
        void delSelectedUser(String[] ids);

}

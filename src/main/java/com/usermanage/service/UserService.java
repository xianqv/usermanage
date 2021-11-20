package com.usermanage.service;

import com.usermanage.domain.UserBean;

import java.util.List;

/***
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户
     * @return
     */
    public List<UserBean> findAll();

    /***
     * 根据id查询用户
     * @return
     */
    public UserBean findById(int id );

    /***
     * 新增用户
     * @param user
     */
    public  void addUser(UserBean user);

    /***
     * 用户信息更新
     * @param user
     */
    public void updateUser(UserBean user);

    /***
     * 删除用户
     * @param id
     */
    public void delUser(String id);
}

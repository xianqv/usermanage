package com.usermanage.dao;

import com.usermanage.domain.UserBean;

import java.util.List;
import java.util.Map;

public interface UserDao {
    /***
     * 查训所有用户
     * @return
     */
    public List<UserBean> findAll();
    /***
     * 用户登录
     */
    public UserBean findByUsernameAndPassword(String username,String password);

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
    public void delUser(int id);

}

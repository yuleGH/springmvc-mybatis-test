package com.yule.user.dao;

import com.yule.user.entity.User;

import java.util.List;

/**
 * 用户 Dao 层
 *
 * @author yule
 * @date 2018/8/6 22:06
 */
public interface UserDao {
    /**
     * 查询所有的用户
     * @return
     */
    List<User> queryUserList();

    /**
     * 通过名字查找用户
     * @param name
     * @return
     */
    User queryUserByName(String name);

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    User queryUserById(String id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteUserById(String id);
}

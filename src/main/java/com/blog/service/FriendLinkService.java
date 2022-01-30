package com.blog.service;

import com.blog.entity.FriendLink;

import java.util.List;

/**
 * @author Ryan
 */
public interface FriendLinkService {

    /**
     * 查询所有友链
     * @return 友链列表
     */
    List<FriendLink> listFriendLink();

    /**
     * 友链新增
     * @param friendLink 友链
     * @return 状态值
     */
    int saveFriendLink(FriendLink friendLink);

    /**
     * 根据id查询友链
     * @param id 友链id
     * @return 友链
     */
    FriendLink getFriendLink(Integer id);

    /**
     * 根据网址查询友链
     * @param blogAddress 友链网址
     * @return 友链
     */
    FriendLink getFriendLinkByBlogAddress(String blogAddress);

    /**
     * 编辑修改友链
     * @param friendLink 友链
     * @return 状态值
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 删除友链
     * @param id 友链id
     */
    void deleteFriendLink(Integer id);

}
package com.blog.service;

import com.blog.pojo.FriendLink;

import java.util.List;

/**
 * @author Ryan
 */
public interface FriendLinkService {

    /**
     * 查询所有友链
     * @return
     */
    List<FriendLink> listFriendLink();

    /**
     * 友链新增
     * @param friendLink
     * @return
     */
    int saveFriendLink(FriendLink friendLink);

    /**
     * 根据id查询友链
     * @param id
     * @return
     */
    FriendLink getFriendLink(Long id);

    /**
     * 根据网址查询友链
     * @param blogAddress
     * @return
     */
    FriendLink getFriendLinkByBlogAddress(String blogAddress);

    /**
     * 编辑修改友链
     * @param friendLink
     * @return
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 删除友链
     * @param id
     */
    void deleteFriendLink(Long id);


}
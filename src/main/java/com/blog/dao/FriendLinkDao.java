package com.blog.dao;

import com.blog.entity.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Ryan
 */
@Mapper
@Repository
public interface FriendLinkDao {
    /**
     * 列出友情链接
     * @return 友链列表
     */
    List<FriendLink> listFriendLink();

    /**
     * 保存友情链接
     * @param friendLink 友链
     * @return 状态值
     */
    int saveFriendLink(FriendLink friendLink);

    /**
     * 得到数量
     * @return 数量
     */
    int getCount();

    /**
     * 获取友情链接
     * @param id 友链id
     * @return 友链
     */
    FriendLink getFriendLink(Integer id);

    /**
     * 通过地址得到友情链接
     * @param blogAddress 友链地址
     * @return 友链
     */
    FriendLink getFriendLinkByBlogAddress(String blogAddress);

    /**
     * 更新友情链接
     * @param friendLink 友链
     * @return 状态值
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 删除友情链接
     * @param id 友链id
     */
    void deleteFriendLink(Integer id);

}
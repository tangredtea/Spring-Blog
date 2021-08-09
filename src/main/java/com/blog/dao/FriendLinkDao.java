package com.blog.dao;

import com.blog.pojo.FriendLink;
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
     * @return
     */
    List<FriendLink> listFriendLink();

    /**
     * 保存友情链接
     * @param friendLink
     * @return
     */
    int saveFriendLink(FriendLink friendLink);

    /**
     * 获取友情链接
     * @param id
     * @return
     */
    FriendLink getFriendLink(Long id);

    /**
     * 通过地址得到友情链接
     * @param blogaddress
     * @return
     */
    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    /**
     * 更新友情链接
     * @param friendLink
     * @return
     */
    int updateFriendLink(FriendLink friendLink);

    /**
     * 删除友情链接
     * @param id
     */
    void deleteFriendLink(Long id);

}
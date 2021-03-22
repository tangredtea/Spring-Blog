package com.blog.dao;

import com.blog.pojo.FriendLink;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FriendLinkDao {

    List<FriendLink> listFriendLink();

    int saveFriendLink(FriendLink friendLink);

    FriendLink getFriendLink(Long id);

    FriendLink getFriendLinkByBlogaddress(String blogaddress);

    int updateFriendLink(FriendLink friendLink);

    void deleteFriendLink(Long id);

}
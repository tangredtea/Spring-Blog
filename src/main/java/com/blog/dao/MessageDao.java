package com.blog.dao;

import com.blog.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageDao {

    /**
     * 添加一个评论
     * @param message
     * @return
     */
    int saveMessage(Message message);

    /**
     * 查询评论
     * @param ParentId
     * @return
     */
    List<Message> findByIndexParentId(@Param("ParentId") Long ParentId);

    /**
     * 查询父级评论
     * @param ParentId
     * @return
     */
    List<Message> findByParentIdNull(@Param("ParentId") Long ParentId);

    /**
     * 查询一级回复
     * @param id
     * @return
     */
    List<Message> findByParentIdNotNull(@Param("id") Long id);

    /**
     * 查询二级以及所有子集回复
     * @param childId
     * @return
     */
    List<Message> findByReplayId(@Param("childId") Long childId);

    /**
     * 删除评论
     * @param id
     */
    void deleteMessage(Long id);

}

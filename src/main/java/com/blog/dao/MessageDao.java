package com.blog.dao;

import com.blog.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Ryan
 */
@Mapper
@Repository
public interface MessageDao {

    /**
     * 添加一个留言
     * @param message 留言
     * @return 状态值
     */
    int saveMessage(Message message);

    /**
     * 查询首页推荐留言
     * @param ParentId 留言父id
     * @return 留言列表
     */
    List<Message> findByIndexParentId(@Param("ParentId") Long ParentId);

    /**
     * 查询留言
     * @param ParentId 留言父id
     * @return 留言列表
     */
    List<Message> findByParentIdNull(@Param("ParentId") Long ParentId);

    /**
     * 查询一级回复
     * @param id 留言id
     * @return 留言列表
     */
    List<Message> findByParentIdNotNull(@Param("id") Long id);

    /**
     * 查询二级以及所有子集回复
     * @param childId 留言id
     * @return 留言列表
     */
    List<Message> findByReplayId(@Param("childId") Long childId);

    /**
     * 删除评论
     * @param id 留言id
     */
    void deleteMessage(Long id);

}

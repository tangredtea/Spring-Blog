package com.blog.service;

import com.blog.entity.Message;

import java.util.List;

/**
 * @author Ryan
 */
public interface MessageService {

    /**
     * 查询留言列表
     * @return 留言列表
     */
    List<Message> listMessage();

    /**
     * 列出首页推荐留言
     * @return 留言列表
     */
    List<Message> findByIndexParentId();

    /**
     * 保存留言
     * @param message 留言
     * @return 状态
     */
    int saveMessage(Message message);

    /**
     * 删除留言
     * @param id 留言id
     */
    void deleteMessage(Long id);

}

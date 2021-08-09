package com.blog.service;

import com.blog.pojo.Message;

import java.util.List;

public interface MessageService {

    /**
     * 查询留言列表
     * @return
     */
    List<Message> listMessage();

    List<Message> findByIndexParentId();

    /**
     * 保存留言
     * @param message
     * @return
     */
    int saveMessage(Message message);

    /**
     * 删除留言
     * @param id
     */
    void deleteMessage(Long id);

}

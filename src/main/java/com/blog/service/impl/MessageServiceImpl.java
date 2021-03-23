package com.blog.service.impl;

import com.blog.dao.MessageDao;
import com.blog.pojo.Message;
import com.blog.service.MessageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    //存放迭代找出的所有子代的集合
    private List<Message> tempReplys = new ArrayList<>();

    /**
     * 首页推荐评论
     * @return
     */
    @Override
    public  List<Message> findByIndexParentId(){
        List<Message> messages = messageDao.findByParentIdNull(Long.parseLong("-1"));
        return messages;
    }
    /**
     * @Description: 查询留言
     * @Return: 留言消息
     */
    @Override
    public List<Message> listMessage() {
        //查询出父节点
        List<Message> messages = messageDao.findByParentIdNull(Long.parseLong("-1"));
        for(Message message : messages){
            Long id = message.getId();
            String parentNickname1 = message.getNickname();
            List<Message> childMessages = messageDao.findByParentIdNotNull(id);
            //查询出子留言
            combineChildren(childMessages, parentNickname1);
            message.setReplyMessages(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return messages;
    }


    /**
     * @Description: 查询出子留言
     * @Return:
     */
    private void combineChildren(List<Message> childMessages, String parentNickname1) {
        //判断是否有一级子回复
        if(childMessages.size() > 0){
            //循环找出子留言的id
            for(Message childMessage : childMessages){
                String parentNickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname1);
                tempReplys.add(childMessage);
                Long childId = childMessage.getId();
                //查询二级以及所有子集回复
                recursively(childId, parentNickname);
            }
        }
    }

    /**
     * @Description: 循环迭代找出子集回复
     * @Return:
     */
    private void recursively(Long childId, String parentNickname1) {
        //根据子一级留言的id找到子二级留言
        List<Message> replayMessages = messageDao.findByReplayId(childId);

        if(replayMessages.size() > 0){
            for(Message replayMessage : replayMessages){
                String parentNickname = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickname1);
                Long replayId = replayMessage.getId();
                tempReplys.add(replayMessage);
                //循环迭代找出子集回复
                recursively(replayId,parentNickname);
            }
        }
    }
    @Override
    //存储留言信息
    public int saveMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDao.saveMessage(message);
    }

    //    删除留言
    @Override
    public void deleteMessage(Long id) {
        messageDao.deleteMessage(id);
    }
}
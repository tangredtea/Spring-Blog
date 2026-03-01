package com.blog.service.impl;

import com.blog.dao.MessageDao;
import com.blog.entity.Message;
import com.blog.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tangredtea
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageDao messageDao;

    /**
     * 首页推荐评论
     * @return 留言列表
     */
    @Override
    public  List<Message> findByIndexParentId(){
        return messageDao.findByParentIdNull(Long.parseLong("-1"));
    }

    /**
     * 列出留言
     * @return 留言列表
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
            List<Message> tempReplies = new ArrayList<>();
            combineChildren(tempReplies, childMessages, parentNickname1);
            message.setReplyMessages(tempReplies);
        }
        return messages;
    }


    /**
     * 查询子留言
     * @param tempReplies 存放子留言的集合
     * @param childMessages 子留言
     * @param parentNickname1 父留言名称
     */
    private void combineChildren(List<Message> tempReplies, List<Message> childMessages, String parentNickname1) {
        if(!childMessages.isEmpty()){
            for(Message childMessage : childMessages){
                String parentNickname = childMessage.getNickname();
                childMessage.setParentNickname(parentNickname1);
                tempReplies.add(childMessage);
                Long childId = childMessage.getId();
                recursively(tempReplies, childId, parentNickname);
            }
        }
    }

    /**
     * 循环迭代找出子集回复
     * @param tempReplies 存放子留言的集合
     * @param childId 子集id
     * @param parentNickname1 父名称
     */
    private void recursively(List<Message> tempReplies, Long childId, String parentNickname1) {
        List<Message> replayMessages = messageDao.findByReplayId(childId);
        if(!replayMessages.isEmpty()){
            for(Message replayMessage : replayMessages){
                String parentNickname = replayMessage.getNickname();
                replayMessage.setParentNickname(parentNickname1);
                Long replayId = replayMessage.getId();
                tempReplies.add(replayMessage);
                recursively(tempReplies, replayId, parentNickname);
            }
        }
    }

    /**
     * 保存留言
     * @param message 留言
     * @return 状态
     */
    @Override
    public int saveMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDao.saveMessage(message);
    }

    /**
     *  删除留言
     * @param id 留言id
     */
    @Override
    public void deleteMessage(Long id) {
        messageDao.deleteMessage(id);
    }

    @Override
    public int countMessage() {
        return messageDao.getCount();
    }
}

package com.blog.controller.blog;

import com.blog.entity.Message;
import com.blog.entity.User;
import com.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Ryan
 */
@Controller
public class MessageController {

    @Resource
    private MessageService messageService;

    @Value("/static/images/logo.png")
    private String avatar;

    @GetMapping("/message")
    public String message() {
        return "message";
    }

    /**
     * 查询留言
     * @param model 视图
     * @return 渲染视图
     */
    @GetMapping("/messageComment")
    public String messages(Model model) {
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message";
    }

    /**
     * 新增留言
     * @param message 留言
     * @param session 用户
     * @param model 视图
     * @return 渲染视图
     */
    @PostMapping("/message")
    public String post(Message message, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        //设置头像
        if (user != null) {
            message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
        }
        messageService.saveMessage(message);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message";
    }

    /**
     * 删除留言
     * @param id 留言id
     * @return 渲染视图
     */
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id){
        messageService.deleteMessage(id);
        return "message";
    }

}
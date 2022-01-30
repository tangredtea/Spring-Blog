package com.blog.controller.admin;

import com.blog.entity.FriendLink;
import com.blog.service.FriendLinkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author Ryan
 */
@Controller
@RequestMapping("/admin")
public class FriendLinkController {

    @Resource
    private FriendLinkService friendLinkService;

    /**
     * 查询所有友链
     * @param model 视图
     * @param pageNum 页数
     * @return 渲染视图
     */
    @GetMapping("/friendLinks")
    public String friend(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<FriendLink> listFriendLink = friendLinkService.listFriendLink();
        PageInfo<FriendLink> pageInfo = new PageInfo<>(listFriendLink);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendLinks";
    }

    /**
     * 跳转友链新增页面
     * @param model 视图
     * @return 友链新增页
     */
    @GetMapping("/friendLinks/input")
    public String input(Model model) {
        model.addAttribute("friendLink", new FriendLink());
        return "admin/friendLinks-input";
    }

    /**
     * 友链新增
     * @param friendLink 友链
     * @param result 结果
     * @param attributes 属性
     * @return 重定向到友链页
     */
    @PostMapping("/friendLinks")
    public String post(@Valid FriendLink friendLink, BindingResult result, RedirectAttributes attributes){
        FriendLink obj = friendLinkService.getFriendLinkByBlogAddress(friendLink.getBlogAddress());
        if (obj != null) {
            attributes.addFlashAttribute("msg", "不能添加相同的网址");
            return "redirect:/admin/friendLinks/input";
        }

        if(result.hasErrors()){
            return "admin/friendLinks-input";
        }
        friendLink.setCreateTime(new Date());
        int num = friendLinkService.saveFriendLink(friendLink);
        if (num == 0 ) {
            attributes.addFlashAttribute("msg", "新增失败");
        } else {
            attributes.addFlashAttribute("msg", "新增成功");
        }
        return "redirect:/admin/friendLinks";
    }

    /**
     * 跳转友链修改页面
     * @param id 友链id
     * @param model 视图
     * @return 友链修改页
     */
    @GetMapping("/friendLinks/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        model.addAttribute("friendLink", friendLinkService.getFriendLink(id));
        return "admin/friendLinks-input";
    }

    /**
     * 编辑修改友链
     * @param friendLink 友链
     * @param attributes 属性
     * @return 友链页
     */
    @PostMapping("/friendLinks/{id}")
    public String editPost(@Valid FriendLink friendLink, RedirectAttributes attributes) {
        int t = friendLinkService.updateFriendLink(friendLink);
        if (t == 0 ) {
            attributes.addFlashAttribute("msg", "编辑失败");
        } else {
            attributes.addFlashAttribute("msg", "编辑成功");
        }
        return "redirect:/admin/friendLinks";
    }

    /**
     * 删除友链
     * @param id 友链id
     * @param attributes 属性
     * @return 友链页
     */
    @GetMapping("/friendLinks/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes){
        friendLinkService.deleteFriendLink(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/friendLinks";
    }

}
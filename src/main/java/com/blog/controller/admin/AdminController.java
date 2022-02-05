package com.blog.controller.admin;

import com.blog.dao.*;
import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.util.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * 后台登录处理
 * @author Ryan
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private BlogDao blogDao;

    @Resource
    private FriendLinkDao friendLinkDao;

    @Resource
    private MessageDao messageDao;

    @Resource
    private TagDao tagDao;

    @Resource
    private TypeDao typeDao;

    @Resource
    private UserDao userDao;

    @Resource
    private UserService userService;

    @GetMapping({"","/","/index","/login"})
    public String loginPage(HttpSession session, Model model) {
        if (null != session && session.getAttribute("user") != null){
            model.addAttribute("article_nums", blogDao.getCount());
            model.addAttribute("article_views", blogDao.getViews());
            model.addAttribute("avg_views", blogDao.getAvgViews());
            model.addAttribute("friendLink_nums", friendLinkDao.getCount());
            model.addAttribute("message_nums", messageDao.getCount());
            model.addAttribute("tag_nums", tagDao.getCount());
            model.addAttribute("type_nums", typeDao.getCount());
            model.addAttribute("user_nums", userDao.getCount());
            return "admin/index";
        }
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if(user != null){
            session.setAttribute("user", user);
            return "redirect:/admin/index";
        }else {
            attributes.addFlashAttribute("msg", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @GetMapping("/users")
    public String users(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum, 5);
        List<User> allUser = userService.getUsers();
        PageInfo<User> pageInfo = new PageInfo<>(allUser);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/users";
    }

    @GetMapping("/users/input")
    public String toAddUser(Model model){
        //返回一个tag对象给前端th:object
        model.addAttribute("user", new User());
        return "admin/users-input";
    }

    @GetMapping("/users/{id}/input")
    public String toEditUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getUserInfoById(id));
        return "admin/users-input";
    }

    @PostMapping("/users")
    public String addTag(User user, RedirectAttributes attributes){
        int nums = userService.getUserInfoByUsername(user.getUsername());
        if(nums != 0){
            attributes.addFlashAttribute("msg", "不能添加已存在的用户名");
            return "redirect:/admin/users/input";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        user.setPassword(MD5Utils.code(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes){
        userService.deleteUser(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}")
    public String editTag(@PathVariable Integer id, User user, RedirectAttributes attributes){
        User beforeUser = userService.getUserInfoById(id);
        if (!Objects.equals(beforeUser.getUsername(), user.getUsername())){
            int nums = userService.getUserInfoByUsername(user.getUsername());
            if(nums != 0){
                attributes.addFlashAttribute("msg", "不能添加已存在的用户名");
                return "redirect:/admin/users/input";
            }else {
                attributes.addFlashAttribute("msg", "修改成功");
            }
        }
        if (user.getPassword() == null){
            user.setPassword(beforeUser.getPassword());
        }
        user.setPassword(MD5Utils.code(user.getPassword()));
        userService.updateUser(user);
        return "redirect:/admin/users";
    }
}
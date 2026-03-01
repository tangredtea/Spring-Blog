package com.blog.controller.admin;

import com.blog.entity.User;
import com.blog.service.*;
import com.blog.util.PasswordUtils;
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
 * @author tangredtea
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private BlogService blogService;

    @Resource
    private FriendLinkService friendLinkService;

    @Resource
    private MessageService messageService;

    @Resource
    private TagService tagService;

    @Resource
    private TypeService typeService;

    @Resource
    private UserService userService;

    @Resource
    private AIService aiService;

    @GetMapping({"","/","/index","/login"})
    public String loginPage(HttpSession session, Model model) {
        if (null != session && session.getAttribute("user") != null){
            model.addAttribute("article_nums", blogService.countBlog());
            model.addAttribute("article_views", blogService.getTotalViews());
            model.addAttribute("avg_views", blogService.getAvgViews());
            model.addAttribute("friendLink_nums", friendLinkService.countFriendLink());
            model.addAttribute("message_nums", messageService.countMessage());
            model.addAttribute("tag_nums", tagService.countTag());
            model.addAttribute("type_nums", typeService.countType());
            model.addAttribute("user_nums", userService.countUser());
            model.addAttribute("ai_enabled", aiService.isEnabled());
            // 最近发布的文章（取前5条）
            PageHelper.startPage(1, 5);
            model.addAttribute("recentBlogs", blogService.getAllBlog());
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
            // 不在 session 中存储密码
            user.setPassword(null);
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
        model.addAttribute("user", new User());
        return "admin/users-input";
    }

    @GetMapping("/users/{id}/input")
    public String toEditUser(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getUserInfoById(id));
        return "admin/users-input";
    }

    @PostMapping("/users")
    public String addUser(User user, RedirectAttributes attributes){
        int nums = userService.getUserInfoByUsername(user.getUsername());
        if(nums != 0){
            attributes.addFlashAttribute("msg", "不能添加已存在的用户名");
            return "redirect:/admin/users/input";
        }else {
            attributes.addFlashAttribute("msg", "添加成功");
        }
        user.setPassword(PasswordUtils.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes){
        userService.deleteUser(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/users";
    }

    @PostMapping("/users/{id}")
    public String editUser(@PathVariable Integer id, User user, RedirectAttributes attributes){
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
        // 如果密码为空或未修改，保留原密码；否则加密新密码
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()){
            user.setPassword(beforeUser.getPassword());
        } else {
            user.setPassword(PasswordUtils.encode(user.getPassword()));
        }
        userService.updateUser(user);
        return "redirect:/admin/users";
    }
}

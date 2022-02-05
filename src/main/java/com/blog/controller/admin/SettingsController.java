package com.blog.controller.admin;

import com.blog.config.SettingsConfig;
import com.blog.util.PropertiesUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;


/**
 * @author Ryan
 */
@Controller
@RequestMapping("/admin")
public class SettingsController {

    @Resource
    private SettingsConfig settings;

    @GetMapping("/settings")
    public String messages(Model model) throws IOException {
        model.addAttribute("settings",settings);
        return "admin/settings";
    }

    @PostMapping("/settings")
    public String message(@Valid SettingsConfig temp, RedirectAttributes attributes) throws IOException, IllegalAccessException {
        PropertiesUtil.Write(settings.getClass(),temp);
        settings = temp;
        attributes.addFlashAttribute("msg", "编辑成功");
        return "redirect:/admin/settings";
    }


}

package com.blog.service;

import com.blog.entity.Tag;

import java.util.List;

public interface TagService {
    /**
     * 保存标签
     * @param tag
     * @return
     */
    int saveTag(Tag tag);

    /**
     * 得到标签
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 通过名字得到标签
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 得到所有的标签名
     * @return
     */
    List<Tag> getAllTag();

    /**
     * 首页展示博客标签
     * @return
     */
    List<Tag> getBlogTag();

    /**
     * 从字符串中获取tag集合
     * @param text
     * @return
     */
    List<Tag> getTagByString(String text);

    /**
     * 升级标签
     * @param tag
     * @return
     */
    int updateTag(Tag tag);

    /**
     * 删除标签
     * @param id
     * @return
     */
    int deleteTag(Long id);
}

package com.blog.service;

import com.blog.entity.Tag;

import java.util.List;

/**
 * @author Ryan
 */
public interface TagService {
    /**
     * 保存标签
     * @param tag 标签
     * @return 状态值
     */
    int saveTag(Tag tag);

    /**
     * 得到标签
     * @param id 标签id
     * @return 标签
     */
    Tag getTag(Integer id);

    /**
     * 通过名字得到标签
     * @param  name 标签名
     * @return 标签
     */
    Tag getTagByName(String name);

    /**
     * 得到所有的标签名
     * @return 标签列表
     */
    List<Tag> getAllTag();

    /**
     * 首页展示博客标签
     * @return 标签列表
     */
    List<Tag> getBlogTag();

    /**
     * 从字符串中获取tag集合
     * @param text 标签名
     * @return 标签列表
     */
    List<Tag> getTagByString(String text);

    /**
     * 升级标签
     * @param tag 标签
     * @return 状态值
     */
    int updateTag(Tag tag);

    /**
     * 删除标签
     * @param id 标签id
     * @return 状态值
     */
    int deleteTag(Integer id);
}

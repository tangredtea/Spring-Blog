package com.blog.dao;

import com.blog.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ryan
 */
@Mapper
@Repository
public interface TagDao {
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
     * 通过名字获取标签
     * @param name
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 得到所有标签
     * @return
     */
    List<Tag> getAllTag();

    /**
     * 首页展示博客标签
     * @return
     */
    List<Tag> getBlogTag();

    /**
     * 更新标签
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

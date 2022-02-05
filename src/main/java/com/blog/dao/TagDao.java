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
     * @param tag 标签
     * @return 状态值
     */
    int saveTag(Tag tag);

    /**
     * 得到数量
     * @return 数量
     */
    int getCount();

    /**
     * 得到标签
     * @param id 标签id
     * @return 标签
     */
    Tag getTag(Integer id);

    /**
     * 通过名字获取标签
     * @param name 名字
     * @return 标签
     */
    Tag getTagByName(String name);

    /**
     * 得到所有标签
     * @return 标签列表
     */
    List<Tag> getAllTag();

    /**
     * 首页展示博客标签
     * @return 标签列表
     */
    List<Tag> getBlogTag();

    /**
     * 更新标签
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

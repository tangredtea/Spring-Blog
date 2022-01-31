package com.blog.service.impl;

import com.blog.dao.TagDao;
import com.blog.entity.Tag;
import com.blog.service.TagService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan
 */
@Service
public class TagServiceImpl implements TagService {

    @Resource
    TagDao tagDao;

    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    @Override
    public Tag getTag(Integer id) {
        return tagDao.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    @Override
    public List<Tag> getBlogTag() {
        return tagDao.getBlogTag();
    }

    /**
     * 从tagIds字符串中获取id，根据id获取tag集合
     * @param text 标签名
     * @return 标签列表
     */
    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Integer> nums = convertToList(text);
        for (Integer num : nums) {
            tags.add(tagDao.getTag(num));
        }
        return tags;
    }

    /**
     * 把前端的tagIds字符串转换为list集合
     * @param ids id
     * @return id列表
     */
    private List<Integer> convertToList(String ids) {
        List<Integer> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idArrays = ids.split(",");
            for (String idArray : idArrays) {
                list.add(Integer.valueOf(idArray));
            }
        }
        return list;
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public int deleteTag(Integer id) {
        return tagDao.deleteTag(id);
    }
}

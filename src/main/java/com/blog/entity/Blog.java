package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ryan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {

    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private Integer views;
    private Boolean flag;
    private Boolean appreciation;
    private Boolean shareStatement;
    private Boolean commentable;
    private Boolean published;
    private Boolean recommend;
    private Date createTime;
    private Date updateTime;

    /**
     * 这个属性用来在mybatis中进行连接查询的
     */
    private Integer typeId;
    private Integer userId;

    /**
     * 获取多个标签的id
     */
    private String tagIds;
    private String description;

    private Type type;

    private User user;

    private List<Tag> tags = new ArrayList<>();

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    /**
     * 将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
     * @param tags 标签
     * @return 标签id
     */
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuilder ids = new StringBuilder();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}

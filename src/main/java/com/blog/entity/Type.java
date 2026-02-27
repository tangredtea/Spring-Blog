package com.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tangredtea
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {

    private Integer id;
    private String name;
    private List<Blog> blogs = new ArrayList<>();
}

package com.library.modules.sys.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

/**
 * Created by sugar on 2023/6/5.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuTree {
    @Column(name = "id")
    private Integer id;
    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "parent_id")
    private Integer parentId;
    /**
     * 排序号
     */
    private Byte sort;
    private List<MenuTree> child;

}

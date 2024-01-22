package com.library.modules.sys.vo;


import com.library.modules.sys.model.SysMenu;
import lombok.Data;

import java.util.List;
@Data
public class SysMenuVo extends SysMenu {
    private List<SysMenuVo> children;
}

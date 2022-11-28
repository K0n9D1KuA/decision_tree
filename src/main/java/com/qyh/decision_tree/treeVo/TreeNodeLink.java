package com.qyh.decision_tree.treeVo;


import lombok.Data;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 树枝 用来连接树节点和做判断
 * @date 2022/11/24 23:39
 */

@Data
public class TreeNodeLink {
    //从哪里来
    private Long TreeNodeFromId;
    //到哪里去
    private Long TreeNodeToId;
    //判断类型
    //树枝判断类型  1:=  2:> 3:< 4:>= 5:<= 6:可以扩展的其他类型 枚举
    private Integer limitType;
    //树枝判断值
    private String limitValue;
    //哪颗树？
    private Long treeId;

}

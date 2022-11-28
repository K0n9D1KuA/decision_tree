package com.qyh.decision_tree.treeVo;

/*
 * @author: 空德夸
 * @description: 树节点
 * @param: null
 * @return:
 * @date: 2022/11/24 23:21
 */

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 树节点
 * @date 2022/11/24 23:40
 */

@Data
public class TreeNode {


    //属于哪颗决策树
    private Long treeId;
    //当前节点id
    private Long treeNodeId;
    //节点需要判断什么？ 假如需要判断性别
    private String ruleKey;
    //节点描述信息
    private String Description;
    @TableField(exist = false)
    //所属树枝
    private List<TreeNodeLink> treeNodeLinkList;
    //node 类型     //叶子节点：1 非叶子节点：0
    private Integer treeNodeType;
}

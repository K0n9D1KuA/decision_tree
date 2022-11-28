package com.qyh.decision_tree.treeVo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class TreeRichVo {
    //所属于哪颗树？
    private Long treeId;
    //根节点id 入口
    private Long treeRootId;
    //决策树名字
    private String TreeRichName;
    //树枝
    private List<TreeNodeLink> branches;
    //树结点
    private List<TreeNode> treeNodes;

}

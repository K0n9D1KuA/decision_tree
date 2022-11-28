package com.qyh.decision_tree.treeVo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Map;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 策略树
 * @date 2022/11/24 23:40
 */

@Data
public class TreeRich {
    //所属于哪颗树？
    @TableId
    private Long treeId;
    //根节点id 入口
    private Long treeRootId;
    //这棵树上面的所有节点
    @TableField(exist = false)
    private Map<Long, TreeNode> treeNodeMap;
    //决策树名字
    private String TreeRichName;
}

package com.qyh.decision_tree.logic;

import com.qyh.decision_tree.treeVo.TreeNode;
import com.qyh.decision_tree.treeVo.TreeRich;

import java.util.Map;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 决策树执行引擎
 * @date 2022/11/25 0:28
 */

public interface IEngine {
    //执行方法
    public TreeNode execute(TreeRich treeRich, Map<String, String> matterValueMap, Long userId);
}

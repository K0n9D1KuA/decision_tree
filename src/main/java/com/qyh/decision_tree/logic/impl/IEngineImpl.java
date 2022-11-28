package com.qyh.decision_tree.logic.impl;

import com.qyh.decision_tree.constant.DecisionTreeConstant;
import com.qyh.decision_tree.factory.TreeFactory;
import com.qyh.decision_tree.logic.IEngine;
import com.qyh.decision_tree.logic.LogicFilter;
import com.qyh.decision_tree.treeVo.TreeNode;
import com.qyh.decision_tree.treeVo.TreeNodeLink;
import com.qyh.decision_tree.treeVo.TreeRich;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 实现类 决策树的执行者
 * @date 2022/11/25 0:30
 */
@Component
public class IEngineImpl implements IEngine {
    @Override
    public TreeNode execute(TreeRich treeRich, Map<String, String> matterValueMap, Long userId) {
        //获得根节点id
        Long treeRootId = treeRich.getTreeRootId();
        //获得决策树的所有节点
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        //获得根节点
        TreeNode treeNode = treeNodeMap.get(treeRootId);
        //直到找到叶子节点
        //叶子节点：1 非叶子节点：0
        while (treeNode.getTreeNodeType() == DecisionTreeConstant.NOT_LEAF_NODE_TYPE.intValue()) {
            //当前节点的判断rule
            String ruleKey = treeNode.getRuleKey();
            //负责当前节点的filter
            LogicFilter logicFilter = TreeFactory.getLogicFilter(ruleKey);
            //获得所有树枝
            List<TreeNodeLink> treeNodeLinkList = treeNode.getTreeNodeLinkList();
            //获得用户当前应该被判断的值
            String matterValue = logicFilter.getMatterValue(matterValueMap);
            //进行filter操作 并且获得下一叶节点
            Long nextTreeNodeId = logicFilter.filter(treeNodeLinkList, matterValue);
            //更新treeNode
            treeNode = treeNodeMap.get(nextTreeNodeId);
        }
        //说明找到了叶子节点
        return treeNode;
    }
}

package com.qyh.decision_tree.config;


import com.qyh.decision_tree.factory.TreeFactory;
import com.qyh.decision_tree.service.TreeNodeLinkService;
import com.qyh.decision_tree.service.TreeNodeService;
import com.qyh.decision_tree.service.TreeRichService;
import com.qyh.decision_tree.treeVo.TreeNode;
import com.qyh.decision_tree.treeVo.TreeNodeLink;
import com.qyh.decision_tree.treeVo.TreeRich;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 用来生成策略树
 * @date 2022/11/25 0:27
 */

@Component
public class TreeRichConfig {

    @Autowired
    private TreeNodeService treeNodeService;
    @Autowired
    private TreeRichService treeRichService;
    @Autowired
    private TreeNodeLinkService treeNodeLinkService;

    @PostConstruct
    public void createTreeRich() {
        List<TreeRich> treeRiches = treeRichService.list();
        List<TreeNodeLink> treeNodeLinkList = treeNodeLinkService.list();
        List<TreeNode> treeNodeList = treeNodeService.list();
        if (treeRiches != null && treeRiches.size() > 0) {
            treeRiches.stream().forEach(
                    treeRich -> {
                        Map<Long, TreeNode> longTreeNodeMap = treeNodeList.stream()
                                //过滤
                                .filter(treeNode -> treeNode.getTreeId().equals(treeRich.getTreeId()))
                                .peek(treeNode -> {
                                    treeNode.setTreeNodeLinkList(treeNodeLinkList
                                            .stream()
                                            .filter(treeNodeLink -> treeNodeLink.getTreeNodeFromId().equals(treeNode.getTreeNodeId()))
                                            .filter(treeNodeLink -> treeNodeLink.getTreeId().equals(treeRich.getTreeId()))
                                            .collect(Collectors.toList()));
                                })
                                .collect(Collectors
                                        .toMap(TreeNode::getTreeNodeId, treeNode -> treeNode));
                        treeRich.setTreeNodeMap(longTreeNodeMap);
                        TreeFactory.registerTreeRich(treeRich.getTreeRichName(), treeRich);
                    }
            );
        }

// 原来生成决策树的方法
//        //决策树id 888
//        TreeNode treeNode11 = new TreeNode();
//        treeNode11.setTreeNodeId(11l);
//        treeNode11.setTreeNodeType(DecisionTreeConstant.NOT_LEAF_NODE_TYPE);
//        treeNode11.setDescription("用户性别是否合法");
//        treeNode11.setRuleKey(DecisionTreeConstant.SEX_DECISION);
//        treeNode11.setTreeId(888l);
//        TreeNode treeNode21 = new TreeNode();
//        treeNode21.setTreeNodeId(21l);
//        treeNode21.setTreeNodeType(DecisionTreeConstant.NOT_LEAF_NODE_TYPE);
//        treeNode21.setDescription("用户年龄是否合法");
//        treeNode21.setRuleKey(DecisionTreeConstant.AGE_DECISION);
//        treeNode21.setTreeId(888l);
//        TreeNode treeNode22 = new TreeNode();
//        treeNode22.setTreeNodeId(22l);
//        treeNode22.setTreeNodeType(DecisionTreeConstant.NOT_LEAF_NODE_TYPE);
//        treeNode22.setDescription("用户年龄是否合法");
//        treeNode22.setRuleKey(DecisionTreeConstant.AGE_DECISION);
//        treeNode22.setTreeId(888l);
//        TreeNode treeNode31 = new TreeNode();
//        treeNode31.setTreeNodeId(31l);
//        treeNode31.setTreeNodeType(DecisionTreeConstant.LEAF_NODE_TYPE);
//        treeNode31.setDescription("青年");
//        treeNode31.setTreeId(888l);
//        TreeNode treeNode32 = new TreeNode();
//        treeNode32.setTreeNodeId(32l);
//        treeNode32.setTreeNodeType(DecisionTreeConstant.LEAF_NODE_TYPE);
//        treeNode32.setDescription("少年");
//        treeNode32.setTreeId(888l);
//        TreeNode treeNode33 = new TreeNode();
//        treeNode33.setTreeNodeId(33l);
//        treeNode33.setTreeNodeType(DecisionTreeConstant.LEAF_NODE_TYPE);
//        treeNode33.setDescription("青女");
//        treeNode33.setTreeId(888l);
//        TreeNode treeNode34 = new TreeNode();
//        treeNode34.setTreeNodeId(34l);
//        treeNode34.setTreeNodeType(DecisionTreeConstant.LEAF_NODE_TYPE);
//        treeNode34.setDescription("少女");
//        treeNode34.setTreeId(888l);
//
//
//        TreeNodeLink treeNodeLink11_21 = new TreeNodeLink();
//        treeNodeLink11_21.setTreeNodeFromId(treeNode11.getTreeNodeId());
//        treeNodeLink11_21.setTreeNodeToId(treeNode21.getTreeNodeId());
//        treeNodeLink11_21.setLimitType(DecisionTreeConstant.LIMIT_TYPE_EQUAL);
//        treeNodeLink11_21.setLimitValue("男");
//        TreeNodeLink treeNodeLink11_22 = new TreeNodeLink();
//        treeNodeLink11_22.setTreeNodeFromId(treeNode11.getTreeNodeId());
//        treeNodeLink11_22.setTreeNodeToId(treeNode22.getTreeNodeId());
//        treeNodeLink11_22.setLimitType(DecisionTreeConstant.LIMIT_TYPE_EQUAL);
//        treeNodeLink11_22.setLimitValue("女");
//        TreeNodeLink treeNodeLink21_31 = new TreeNodeLink();
//        treeNodeLink21_31.setTreeNodeFromId(treeNode21.getTreeNodeId());
//        treeNodeLink21_31.setTreeNodeToId(treeNode31.getTreeNodeId());
//        treeNodeLink21_31.setLimitType(DecisionTreeConstant.LIMIT_TYPE_GREATER);
//        treeNodeLink21_31.setLimitValue("15");
//        TreeNodeLink treeNodeLink21_32 = new TreeNodeLink();
//        treeNodeLink21_32.setTreeNodeFromId(treeNode21.getTreeNodeId());
//        treeNodeLink21_32.setTreeNodeToId(treeNode32.getTreeNodeId());
//        treeNodeLink21_32.setLimitType(DecisionTreeConstant.LIMIT_TYPE_LESS);
//        treeNodeLink21_32.setLimitValue("15");
//        TreeNodeLink treeNodeLink22_33 = new TreeNodeLink();
//        treeNodeLink22_33.setTreeNodeFromId(treeNode22.getTreeNodeId());
//        treeNodeLink22_33.setTreeNodeToId(treeNode33.getTreeNodeId());
//        treeNodeLink22_33.setLimitType(DecisionTreeConstant.LIMIT_TYPE_GREATER);
//        treeNodeLink22_33.setLimitValue("15");
//        TreeNodeLink treeNodeLink22_34 = new TreeNodeLink();
//        treeNodeLink22_34.setTreeNodeFromId(treeNode22.getTreeNodeId());
//        treeNodeLink22_34.setTreeNodeToId(treeNode34.getTreeNodeId());
//        treeNodeLink22_34.setLimitType(DecisionTreeConstant.LIMIT_TYPE_LESS);
//        treeNodeLink22_34.setLimitValue("15");
//        treeNode11.setTreeNodeLinkList(Arrays.asList(treeNodeLink11_21, treeNodeLink11_22));
//        treeNode21.setTreeNodeLinkList(Arrays.asList(treeNodeLink21_31, treeNodeLink21_32));
//        treeNode22.setTreeNodeLinkList(Arrays.asList(treeNodeLink22_33, treeNodeLink22_34));
//        TreeRich treeRich = new TreeRich();
//        treeRich.setTreeId(888l);
//        treeRich.setTreeRootId(treeNode11.getTreeNodeId());
//        HashMap<Long, TreeNode> longTreeNodeHashMap = new HashMap<>();
//        longTreeNodeHashMap.put(treeNode11.getTreeNodeId(), treeNode11);
//        longTreeNodeHashMap.put(treeNode21.getTreeNodeId(), treeNode21);
//        longTreeNodeHashMap.put(treeNode22.getTreeNodeId(), treeNode22);
//        longTreeNodeHashMap.put(treeNode31.getTreeNodeId(), treeNode31);
//        longTreeNodeHashMap.put(treeNode32.getTreeNodeId(), treeNode32);
//        longTreeNodeHashMap.put(treeNode33.getTreeNodeId(), treeNode33);
//        longTreeNodeHashMap.put(treeNode34.getTreeNodeId(), treeNode34);
//        treeRich.setTreeNodeMap(longTreeNodeHashMap);
//        TreeFactory.registerTreeRich(DecisionTreeConstant.CROWD_DISTRIBUTION_DECISION_TREE, treeRich);

    }

}

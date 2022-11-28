package com.qyh.decision_tree.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qyh.decision_tree.mapper.TreeNodeMapper;
import com.qyh.decision_tree.service.TreeNodeService;
import com.qyh.decision_tree.treeVo.TreeNode;
import org.springframework.stereotype.Service;


@Service
public class TreeNodeServiceImpl extends ServiceImpl<TreeNodeMapper, TreeNode> implements TreeNodeService {
}

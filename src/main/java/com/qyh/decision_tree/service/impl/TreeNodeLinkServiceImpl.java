package com.qyh.decision_tree.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qyh.decision_tree.mapper.TreeNodeLinkMapper;
import com.qyh.decision_tree.service.TreeNodeLinkService;
import com.qyh.decision_tree.treeVo.TreeNodeLink;
import org.springframework.stereotype.Service;


@Service
public class TreeNodeLinkServiceImpl extends ServiceImpl<TreeNodeLinkMapper, TreeNodeLink> implements TreeNodeLinkService {
}

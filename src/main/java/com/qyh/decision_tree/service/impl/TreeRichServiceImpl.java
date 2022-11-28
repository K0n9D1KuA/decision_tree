package com.qyh.decision_tree.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qyh.decision_tree.mapper.TreeRichMapper;
import com.qyh.decision_tree.service.TreeRichService;
import com.qyh.decision_tree.treeVo.TreeRich;
import org.springframework.stereotype.Service;

@Service
public class TreeRichServiceImpl extends ServiceImpl<TreeRichMapper, TreeRich> implements TreeRichService {
}

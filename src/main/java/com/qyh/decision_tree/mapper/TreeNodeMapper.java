package com.qyh.decision_tree.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qyh.decision_tree.treeVo.TreeNode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TreeNodeMapper extends BaseMapper<TreeNode> {
}

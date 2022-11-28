package com.qyh.decision_tree.logic;

import com.qyh.decision_tree.treeVo.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 * @author 空德夸
 * @version 1.0
 * @description: LogicFilter 决策器接口
 * @date 2022/11/24 23:43
 */

public interface LogicFilter {

    /*
     * @author: 空德夸
     * @description: 通过树枝判断到哪个树节点
     * @param: treeNodeLinkList 某个树节点的树枝  matterValue将被决策的值
     * @return: 下一个叶子节点的id
     * @date: 2022/11/24 23:45
     */

    public Long filter(List<TreeNodeLink> treeNodeLinkList, String matterValue);
    /*
     * @author: 空德夸
     * @description: 获取决策值
     * @param: matterValueMap用户所有将被判断的决策值 格式 key = "sex" value="男“
     * @return:
     * @date: 2022/11/24 23:47
     */

    public String getMatterValue(Map<String, String> matterValueMap);
}

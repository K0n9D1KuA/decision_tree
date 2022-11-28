package com.qyh.decision_tree.logic;

import com.qyh.decision_tree.treeVo.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 抽象父类 规定大致的流程 模板方法设计模式 参考aqs
 * 子类实现特定的方法即可
 * @date 2022/11/24 23:48
 */

public abstract class BaseLogic implements LogicFilter {
    @Override
    public Long filter(List<TreeNodeLink> treeNodeLinkList, String matterValue) {
        //遍历每一条树枝
        for (TreeNodeLink treeNodeLink : treeNodeLinkList) {
            //说明满足这条树枝
            if (this.judgeMatterValue(treeNodeLink, matterValue)) {
                //返回下一个树节点的id
                System.out.println("从  " + treeNodeLink.getTreeNodeFromId() + "走到了  " + treeNodeLink.getTreeNodeToId());
                return treeNodeLink.getTreeNodeToId();
            }
        }
        return null;
    }

    //必须交给子类重写
    @Override
    public String getMatterValue(Map<String, String> matterValueMap) {
        throw new UnsupportedOperationException();
    }

    //判断方法
    private boolean judgeMatterValue(TreeNodeLink treeNodeLink, String matterValue) {
        //树枝判断类型
        Integer limitType = treeNodeLink.getLimitType();
        //数字判断决策值
        String limitValue = treeNodeLink.getLimitValue();

        //树枝判断类型  1:=  2:> 3:< 4:>= 5:<= 6:可以扩展的其他类型 枚举
        switch (limitType) {
            case 1:
                return matterValue.equals(limitValue);
            case 2:
                return Double.parseDouble(matterValue) > Double.parseDouble(limitValue);
            case 3:
                return Double.parseDouble(matterValue) < Double.parseDouble(limitValue);
            case 4:
                return Double.parseDouble(matterValue) >= Double.parseDouble(limitValue);
            case 5:
                return Double.parseDouble(matterValue) <= Double.parseDouble(limitValue);
            default:
                return false;
        }
    }
}

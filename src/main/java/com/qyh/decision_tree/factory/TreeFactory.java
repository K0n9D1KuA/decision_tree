package com.qyh.decision_tree.factory;

import com.qyh.decision_tree.logic.LogicFilter;
import com.qyh.decision_tree.treeVo.TreeRich;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 空德夸
 * @version 1.0
 * @description: 决策树工厂类 里面可以拿到决策树 决策树执行中需要的filter
 * @date 2022/11/25 0:18
 */

public class TreeFactory {
    //决策树
    public static Map<String, TreeRich> treeRichMap = new HashMap<>();
    //filter
    public static Map<String, LogicFilter> logicFilterMap = new HashMap<>();

    //注册决策树
    public static void registerTreeRich(String treeRichName, TreeRich treeRich) {
        treeRichMap.put(treeRichName, treeRich);
    }

    //注册filter
    public static void registerLogicFilter(String logicFilterType, LogicFilter logicFilter) {
        logicFilterMap.put(logicFilterType, logicFilter);
    }

    //获得filter
    public static LogicFilter getLogicFilter(String logicFilterType) {
        return logicFilterMap.get(logicFilterType);
    }

    //获得决策树
    public static TreeRich getTreeRich(String treeRichName) {
        return treeRichMap.get(treeRichName);
    }
}


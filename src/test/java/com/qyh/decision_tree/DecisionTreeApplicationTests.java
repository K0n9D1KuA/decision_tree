package com.qyh.decision_tree;

import com.qyh.decision_tree.constant.DecisionTreeConstant;
import com.qyh.decision_tree.factory.TreeFactory;
import com.qyh.decision_tree.logic.IEngine;
import com.qyh.decision_tree.treeVo.TreeNode;
import com.qyh.decision_tree.treeVo.TreeRich;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class DecisionTreeApplicationTests {
    @Autowired
    private IEngine iEngine;

    //来测试一下
    //测试完毕
    // 几乎0代码 代替大量if else！!!
    @Test
    void contextLoads() {
        HashMap<String, String> matterMap = new HashMap<>();
        matterMap.put(DecisionTreeConstant.SEX_DECISION, "未知性别");
        matterMap.put(DecisionTreeConstant.AGE_DECISION, "16");
        TreeRich treeRich = TreeFactory.getTreeRich("性别年龄测试决策树1");
        TreeNode resultTreeNode = iEngine.execute(treeRich, matterMap, 311331l);
        System.out.println(resultTreeNode.getDescription());
        //打印结果： 青女
    }

    @Test
    void test1() {

    }

}

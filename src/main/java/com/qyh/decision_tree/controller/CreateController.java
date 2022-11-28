package com.qyh.decision_tree.controller;


import com.qyh.decision_tree.service.TreeNodeLinkService;
import com.qyh.decision_tree.service.TreeNodeService;
import com.qyh.decision_tree.service.TreeRichService;
import com.qyh.decision_tree.treeVo.TreeNodeLink;
import com.qyh.decision_tree.treeVo.TreeRich;
import com.qyh.decision_tree.treeVo.TreeRichVo;
import com.qyh.decision_tree.utils.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class CreateController {

    @Autowired
    private TreeRichService treeRichService;
    @Autowired
    private TreeNodeLinkService treeNodeLinkService;
    @Autowired
    private TreeNodeService treeNodeService;

    @PostMapping("/buildDecisionTree")
    public String buildDecisionTree(@RequestBody TreeRichVo treeRichVo) {
        TreeRich treeRich = new TreeRich();
        BeanUtils.copyProperties(treeRichVo, treeRich);
        treeRichService.save(treeRich);
        Long treeId = treeRich.getTreeId();
        System.out.println(treeId);
        treeRichVo.getTreeNodes().forEach(treeNode -> treeNode.setTreeId(treeId));
        treeRichVo.getBranches().forEach(treeNodeLink -> treeNodeLink.setTreeId(treeId));
        treeNodeService.saveBatch(treeRichVo.getTreeNodes());
        treeNodeLinkService.saveBatch(treeRichVo.getBranches());
        //获得所有treeNode的 ruleKey (去重)
        List<String> filterType = treeRichVo.getTreeNodes()
                .stream()
                //转化
                .map(treeNode -> treeNode.getRuleKey())
                //去重
                .distinct()
                //去掉null
                .filter(s -> !Objects.isNull(s))
                //收集
                .collect(Collectors.toList());

        try {
            //生成不同的filter文件
            FileUtils.replace(filterType);
            //生成需要用到的常量类
            FileUtils.appendToExitingFile(filterType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "添加成功!";
    }

    @GetMapping("test")
    public void test() {
        try {
            FileUtils.appendToExitingFile(Arrays.asList("sex", "name", "phone"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}

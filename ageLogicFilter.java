package com.qyh.decision_tree.logic.concretFilter;

import com.qyh.decision_tree.constant.DecisionTreeConstant;
import com.qyh.decision_tree.factory.TreeFactory;
import com.qyh.decision_tree.logic.BaseLogic;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ageLogicFilter extends BaseLogic {
    @Override
    public String getMatterValue(Map<String, String> matterValueMap) {
        return matterValueMap.get(DecisionTreeConstant.AGE_DECISION);
    }

    @PostConstruct
    public void registerThisToFactory() {
        TreeFactory.registerLogicFilter(DecisionTreeConstant.AGE_DECISION, this);
    }
}

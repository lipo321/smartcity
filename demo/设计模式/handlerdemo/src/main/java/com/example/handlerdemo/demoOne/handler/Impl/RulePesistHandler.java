package com.example.handlerdemo.demoOne.handler.Impl;

import com.example.handlerdemo.demoOne.AlarmRule.AlarmRule;
import com.example.handlerdemo.demoOne.handler.AbstractRuleHandler;
import org.springframework.util.StringUtils;

public class RulePesistHandler extends AbstractRuleHandler {
    @Override
    public void doHandleReal(AlarmRule rule) throws Exception {
        if (StringUtils.isEmpty(rule.getName())) {
            throw new Exception("Rule name is empty.");
        }
        System.out.println("Persist rule sucess.Rule name is" + rule.getName());
    }

    @Override
    public void rollBackReal(AlarmRule rule) {
        System.out.println("Roll persist rule. Rule name is" + rule.getName());
    }
}

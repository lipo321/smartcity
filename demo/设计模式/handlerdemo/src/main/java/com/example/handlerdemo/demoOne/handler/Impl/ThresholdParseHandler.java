package com.example.handlerdemo.demoOne.handler.Impl;

import com.example.handlerdemo.demoOne.AlarmRule.AlarmRule;
import com.example.handlerdemo.demoOne.handler.AbstractRuleHandler;
import org.springframework.util.StringUtils;

/**
 * 阈值解析处理器
 */
public class ThresholdParseHandler extends AbstractRuleHandler {

    @Override
    public void doHandleReal(AlarmRule rule) throws Exception {
        if (StringUtils.isEmpty(rule.getThreshold())) {
            throw new Exception("Threshold is empty");
        }
        System.out.println("Parse threshold success. Threshold is" + rule.getThreshold());
    }

    @Override
    public void rollBackReal(AlarmRule rule) {
        System.out.println("Roll pase threshold. Threshold is" + rule.getThreshold());
    }

}

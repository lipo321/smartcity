package com.example.handlerdemo.demoOne.handler.Impl;

import com.example.handlerdemo.demoOne.AlarmRule.AlarmRule;
import com.example.handlerdemo.demoOne.handler.AbstractRuleHandler;

/**
 * 告警流规则处理器
 */
public class StreamGenerateHandler extends AbstractRuleHandler {

    @Override
    public void doHandleReal(AlarmRule rule) throws Exception {
        System.out.println("Generate stream success");
    }

    @Override
    public void rollBackReal(AlarmRule rule) {
        System.out.println("Roll Generate stream");
    }
}

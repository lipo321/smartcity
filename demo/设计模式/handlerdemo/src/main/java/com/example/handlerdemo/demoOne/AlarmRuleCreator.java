package com.example.handlerdemo.demoOne;

import com.example.handlerdemo.demoOne.AlarmRule.AlarmRule;
import com.example.handlerdemo.demoOne.handler.AbstractRuleHandler;
import com.example.handlerdemo.demoOne.handler.Impl.RulePesistHandler;
import com.example.handlerdemo.demoOne.handler.Impl.StreamGenerateHandler;
import com.example.handlerdemo.demoOne.handler.Impl.ThresholdParseHandler;

/**
 * @author lipo
 */
public class AlarmRuleCreator {

    private AbstractRuleHandler alarmRuleHandler;

    public AlarmRuleCreator() {
        alarmRuleHandler = new ThresholdParseHandler();
        alarmRuleHandler.setNextHandler(new StreamGenerateHandler()).setNextHandler(new RulePesistHandler());
    }

    public void create(AlarmRule rule) {
        alarmRuleHandler.doHandle(rule);
    }

    public static void main(String[] args) {
        AlarmRule rule = new AlarmRule();
        rule.setThreshold("cpuRate <10");
        rule.setName("Cpu Alarm");


        AlarmRuleCreator ruleCreator = new AlarmRuleCreator();
        ruleCreator.create(rule);

        System.out.println("-------------------------------------------");
        rule.setName("lipo123");
        ruleCreator.create(rule);
    }


}

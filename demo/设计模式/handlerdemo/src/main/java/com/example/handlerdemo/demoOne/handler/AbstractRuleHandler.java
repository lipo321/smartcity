package com.example.handlerdemo.demoOne.handler;

import com.example.handlerdemo.demoOne.AlarmRule.AlarmRule;

public abstract class AbstractRuleHandler {

    /**
     * 上一个处理器
     */
    private AbstractRuleHandler preHandler;

    /**
     * 下一个处理器
     */
    private AbstractRuleHandler nextHandler;


    /**
     * 业务执行
     *
     * @param rule
     */
    public void doHandle(AlarmRule rule) {
        try {
            doHandleReal(rule);
        } catch (Exception e) {
            //业务代码执行失败，主动回滚
            rollBack(rule);
            return;
        }

        if (null != nextHandler) {
            nextHandler.doHandle(rule);
        }
    }

    /**
     * 事务回滚
     *
     * @param rule
     */
    public void rollBack(AlarmRule rule) {
        rollBackReal(rule);

        //本处理器业务回滚完成，主动调用前一处理器业务回滚
        if (null != preHandler) {
            preHandler.rollBack(rule);
        }
    }

    public abstract void doHandleReal(AlarmRule rule) throws Exception;

    public abstract void rollBackReal(AlarmRule rule);

    public AbstractRuleHandler setPreHandle(AbstractRuleHandler preHandle) {
        this.preHandler = preHandle;
        return preHandle;
    }

    public AbstractRuleHandler setNextHandler(AbstractRuleHandler nextHandler) {
        this.nextHandler = nextHandler;
        nextHandler.setPreHandle(this);
        return nextHandler;
    }

}

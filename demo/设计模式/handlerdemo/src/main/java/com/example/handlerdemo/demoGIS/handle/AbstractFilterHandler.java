package com.example.handlerdemo.demoGIS.handle;

import com.example.handlerdemo.demoGIS.model.SObject;

import java.util.List;

public abstract class AbstractFilterHandler<TData, Filter> {

    protected AbstractFilterHandler nextHandler;

    /**
     * 执行过程中的临时数据
     */
    protected TData data;

    /**
     * 获取下一个执行器
     *
     * @return
     */
    public AbstractFilterHandler getNextHandler() {
        return nextHandler;
    }

    /**
     * 设置下一个执行器
     *
     * @param nextHandler
     */
    public void setNextHandler(AbstractFilterHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public TData getData() {
        return data;
    }

    public void setData(TData data) {
        this.data = data;
    }

    /**
     * 抽象过滤方法
     *
     * @param filter
     * @return
     * @throws Exception
     */
    public abstract TData filter(Filter filter) throws Exception;

    public static <Filter> Object filter(AbstractFilterHandler handler, Filter filter) throws Exception {
        AbstractFilterHandler nextHandler = handler;
        List<SObject> data = (List<SObject>) nextHandler.getData();
        do {
            nextHandler.setData(data);
            data = (List<SObject>) nextHandler.filter(filter);
            nextHandler = nextHandler.getNextHandler();
        } while (null != nextHandler);

        return data;
    }

}


package cn.smartcity.relationtran.oldModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.ArrayList;

/**
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public abstract class AObject {
    /**
     * 记录对象操作，用于两个方面
     * （1）用于查询对象历史，记录（操作）
     * （2）用于对象编辑提交，记录（操作）
     * 例如：[{id:?,operation:1}]
     * 其中id描述的该操作针对的实体ID，例如字段ID，具体哪个形态的ID，etc.
     */
    @JsonInclude(Include.NON_NULL)
    protected ArrayList<Action> actions;

    /**
     * 对象唯一标识符
     */
    protected Long id = 0L;

    /**
     * 对象名称
     */
    protected String name;

    public AObject() {
        // TODO:先生成对象ID，需制定规则
        // id不能进行初始化，需要在对象存储时外部指定
        //id = System.currentTimeMillis() + (long)(Math.random()* 100);
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AObject other = (AObject) obj;
        if (id == null && other.id != null) {
            return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}

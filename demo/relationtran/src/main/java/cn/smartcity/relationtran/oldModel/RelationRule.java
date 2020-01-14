//Source file: E:\\projects\\OneGIS\\trunk\\src\\Server\\DataStore\\onegis.core\\src\\main\\java\\onegis\\psde\\relation\\RelationRule.java

package cn.smartcity.relationtran.oldModel;

import java.util.ArrayList;

/**
 * 关系规则
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class RelationRule {

    /**
     * @since 1.0.0
     */
    private Relation relation;

    /**
     * @since 1.0.0
     */
    private ArrayList rules;

    /**
     * @roseuid 5A0B8B1400BC
     * @since 1.0.0
     */
    public RelationRule() {
        rules = new ArrayList<>();
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public ArrayList getRules() {
        return rules;
    }

    public void setRules(ArrayList rules) {
        this.rules = rules;
    }
}

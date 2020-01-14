//Source file: E:\\projects\\OneGIS\\trunk\\src\\Server\\DataStore\\onegis.core\\src\\main\\java\\onegis\\psde\\relation\\REdge.java

package cn.smartcity.relationtran.oldModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 边
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 */
public class REdge extends RObject {

    /**
     * 关系
     */
    private Relation relation;

    /**
     * 关系强度
     */
    private int intensity = 0;

    private List<ARule> rules = Collections.synchronizedList(new ArrayList<ARule>());

    /**
     * @roseuid 5A0B8B1401A6
     * @since 1.0.0
     */
    public REdge() {

    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getIntensity() {
        return intensity;
    }

    public List<ARule> getRules() {
        return rules;
    }

    public void setRules(List<ARule> rules) {
        this.rules = rules;
    }

}

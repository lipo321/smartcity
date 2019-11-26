//Source file: E:\\projects\\OneGIS\\trunk\\src\\Server\\DataStore\\onegis.core\\src\\main\\java\\onegis\\psde\\rule\\ARule.java

package cn.smartcity.relationtran.oldModel;

/**
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @date
 */
public abstract class ARule {

	private int id;
	private String name;

	public ARule() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


    /**
     * 解除规则
     */
	public abstract void fire();
}

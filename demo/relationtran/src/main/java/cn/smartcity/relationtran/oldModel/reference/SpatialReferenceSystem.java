//Source file: E:\\projects\\OneGIS\\trunk\\src\\Server\\DataStore\\onegis.core\\src\\main\\java\\onegis\\psde\\reference\\SpatialReferenceSystem.java

package cn.smartcity.relationtran.oldModel.reference;

/**
 * 空间参考
 *
 * @author 苏州中科蓝迪公司所有(c)2016-2021
 * @version 1.0.0
 * @brief 数据访问引擎核心库
 * @note 修订历史：
 * 1、giszyj@126.com于2017年10月25日设计并构建初始版本v1.0.0
 */
public class SpatialReferenceSystem {
    /**
     * 编码
     */
    private String id = "";

    private String name = "";

    /**
     * 内容
     */
    private String content = "";

    /**
     * @roseuid 59EF1E2B03E6
     */
    public SpatialReferenceSystem() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

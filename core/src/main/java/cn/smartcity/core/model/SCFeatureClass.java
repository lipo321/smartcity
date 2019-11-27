package cn.smartcity.core.model;

/**
 * @author lipoGiser
 * @date 2019/11/22 17:12
 * @Version 0.1
 * @des 同类空间要素的集合即为要素类。如：河流、道路、电缆等。要素类之间可以独立存在
 * @des 也可以具有某种关系，将不同的要素类之间存在关系时，将其组织到一个要素数据集（Feature dataset）中
 */
public class SCFeatureClass {
    private Long id;
    private String geom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }
}

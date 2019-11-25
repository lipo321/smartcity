package cn.smartcity.core.model;

/**
 * @author lipoGiser
 * @date 2019/11/22 17:12
 * @Version 0.1
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

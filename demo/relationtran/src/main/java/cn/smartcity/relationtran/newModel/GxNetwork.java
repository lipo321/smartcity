package cn.smartcity.relationtran.newModel;

import java.util.List;

/**
 * <p>GxNetwork</p>
 *
 * @author liuqingpo(snow22314 @ outlook.com)
 * @version V.1.0.1
 * @company 苏州中科蓝迪公司所有(c) 2016-2021
 * @date created on 16:24 2019/10/28
 */
public class GxNetwork {
    /**
     * 工程id
     */
    private Long            prjId;
    /**
     * 关系网
     */
    private List<GxVersion> versions;

    public GxNetwork() {
    }

    public List<GxVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<GxVersion> versions) {
        this.versions = versions;
    }

    public Long getPrjId() {
        return prjId;
    }

    public void setPrjId(Long prjId) {
        this.prjId = prjId;
    }
}

package cn.smartcity.relationtran.oldModel;

import java.util.List;

/**
 * @author lipoGiser
 * @date 2019/11/26 14:59
 * @Version 0.1
 */
public class SObject {
    private long    id;
    private Network network;
    private Long    realtime;
    private OType   oType;
    private Long    sdomainId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Long getRealtime() {
        return realtime;
    }

    public void setRealtime(Long realtime) {
        this.realtime = realtime;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public OType getoType() {
        return oType;
    }

    public void setoType(OType oType) {
        this.oType = oType;
    }

    public Long getSdomainId() {
        return sdomainId;
    }

    public void setSdomainId(Long sdomainId) {
        this.sdomainId = sdomainId;
    }
}

package com.example.demo.tools;

import onegis.common.utils.IdMakerUtils;
import onegis.dcm.DistributedIDManager;

/**
 * @author lipo@126.com
 * @date 2019年7月10日
 */
public class IdUtils {

    /**
     * 调取后台服务获取唯一id
     *
     * @param workerID     工作机器id
     * @param dataCenterId 数据中心id
     * @return
     */
    public static Long getId(Integer workerID, Integer dataCenterId) {
        IdMakerUtils idWorker = new IdMakerUtils(1, 1);
        Long oId = idWorker.nextId();
        return oId;
    }

}

package cn.smartcity.relationtran;

import cn.smartcity.relationtran.newModel.GxNetwork;
import cn.smartcity.relationtran.oldModel.SObject;
import cn.smartcity.relationtran.tran.StructNetwork;
import cn.smartcity.relationtran.tran.StuctSObject;
import cn.smartcity.relationtran.utils.JsonUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RelationtranApplication {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("=============开始进行转换================");
        System.out.println("=========================================");

        StuctSObject stuctSObject = new StuctSObject();
        List<SObject> sObjects = stuctSObject.organizeSObject();
        try {
            String sobjectString = JsonUtils.objectToJson(sObjects);
            System.out.println("=============对象Json开始================");
            System.out.println(sobjectString);
            System.out.println("=============对象Json结束================");
        } catch (Exception e) {
            e.printStackTrace();
        }

        StructNetwork structNetwork = new StructNetwork();
        GxNetwork network = structNetwork.getNetwork(sObjects);

        try {
            String networkString = JsonUtils.objectToJson(network);
            System.out.println("=============关系网络Json开始================");
            System.out.println(networkString);
            System.out.println("=============关系网络Json结束================");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=========================================");
        System.out.println("=============结束数据转换================");
        System.out.println("=========================================");
        SpringApplication.run(RelationtranApplication.class, args);
    }


    private void transform() {

    }


}

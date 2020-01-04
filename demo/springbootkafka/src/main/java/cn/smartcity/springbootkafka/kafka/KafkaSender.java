package cn.smartcity.springbootkafka.kafka;

import cn.smartcity.springbootkafka.entity.MessageEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.client.util.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author lipoGiser
 * @date 2020/1/3 13:46
 * @Version 0.1
 */
@Component
public class KafkaSender {
    private static Logger                       logger = LoggerFactory.getLogger(KafkaSender.class);
    private static KafkaProducer<String,String> producer;
    private        Gson                         gson   = new GsonBuilder().create();

    static {

        try {
            InputStream props = Resources.getResource("producer.props").openStream();
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<String, String>(properties);
        } catch (IOException e) {
            logger.error("初始化Kafka配置文件失败");
            e.printStackTrace();
        }
    }

    /**
     * 发送消息方法
     * @param topic
     * @param msg
     */
    public void sendMsg(String topic,String msg){
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMsg(msg);
        messageEntity.setSendTime(new Date());

        logger.info("sendMessage={}",gson.toJson(messageEntity));
        Future<RecordMetadata> record = producer.send(new ProducerRecord<>(topic,gson.toJson(messageEntity)));
        try {
            record.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

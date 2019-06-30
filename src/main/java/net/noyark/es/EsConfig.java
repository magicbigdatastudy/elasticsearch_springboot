package net.noyark.es;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class EsConfig implements FactoryBean<TransportClient>, InitializingBean {

    @Value("${cluster-name}")
    private String name;

    @Value("${cluster-nodes}")
    private String node;

    private TransportClient client;

    //获取由FactoryBean管理的对象
    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override //与getObject对应
    public Class<?> getObjectType() {
        return TransportClient.class;
    }

    @Override//初始化方法，在这个方法中将对象 各种参数进行赋值
    public void afterPropertiesSet() throws Exception {
        Settings settings = Settings.builder().put("cluster.name",name).build();
        //构建一个client对象，通过对node截取获取ip port，setting进行构建
        TransportClient preClient = new PreBuiltTransportClient(settings);
        String[] hostAndPoint = node.split(":");
        preClient.addTransportAddress(new TransportAddress(InetAddress.getByName(hostAndPoint[0]),Integer.parseInt(hostAndPoint[1])));
        client = preClient;
    }
}

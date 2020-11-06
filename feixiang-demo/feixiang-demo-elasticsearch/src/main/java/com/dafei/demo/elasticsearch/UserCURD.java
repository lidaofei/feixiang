package com.dafei.demo.elasticsearch;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;

public class UserCURD {

    public static void main(String[] args) throws Exception {
       Settings settings = Settings.builder()
               .put("cluster.name","elasticsearch")
               .build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));

        System.out.println(11);
        createUser(client);
        getUser(client);
        updateUser(client);
        getUser(client);

        DeleteResponse repsonse = client.prepareDelete("user","my_user","1").get();
        System.out.println("delete="+repsonse.getResult());
        getUser(client);
        client.close();
    }

    private static void updateUser(TransportClient client) throws IOException {
        UpdateResponse response = client.prepareUpdate("user", "my_user", "1")
                .setDoc(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("position", "technique manager")
                        .endObject())
                .get();
        System.out.println("update="+response.getResult());
    }

    private static void getUser(TransportClient client) {
        GetResponse response = client.prepareGet("user", "my_user", "1").get();
        System.out.println("get="+response.getSourceAsString());
    }

    private static void createUser(TransportClient client) throws IOException {
        IndexResponse response = client.prepareIndex("user", "my_user", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("id", "11")
                        .field("name", "ldf")
                        .field("age", 20)
                        .field("position", "technique")
                        .field("country", "china")
                        .field("join_date", "2017-01-01")
                        .field("salary", 5000)
                        .endObject()
                ).get();
        System.out.println("create:"+response.getResult());
    }
}

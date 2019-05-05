import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * MongoDb入门小demo
 */
public class MongoDemo3 {
    public static void main(String[] args){
        //创建连接
        MongoClient client = new MongoClient("39.96.47.108");
        //打开数据库
        MongoDatabase spitdb = client.getDatabase("spitdb");
        //获取集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");

        Map<String,Object> map = new HashMap();
        map.put("content","我要吐槽");
        map.put("suerid","9999");
        map.put("visits","123");
        map.put("publishtime",new Date());
        Document document = new Document(map);
        //插入数据
        spit.insertOne(document);
        client.close();
    }
}

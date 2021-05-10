package com.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/13 16:33
 * @package com.es
 */
@RestController
@RequestMapping("/es")
public class ESBuilderController {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public void save(
            @RequestParam Long id,
            @RequestParam String name
    ){

    }

    @RequestMapping(value = "/save/index", method = RequestMethod.GET)
    void testPutIndex() throws IOException {
        CreateIndexRequest test_index = new CreateIndexRequest("test_index");
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(test_index, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    @RequestMapping(value = "/get/index", method = RequestMethod.GET)
    void testExistIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("test_index");
        boolean exists = restHighLevelClient.indices().exists(request,
                RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @RequestMapping(value = "/delete/index", method = RequestMethod.GET)
    void testDeleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("test_index");
        // 删除
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request,
                RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    // 测试添加文档
    @RequestMapping(value = "/save/document", method = RequestMethod.GET)
    void testAddDocument() throws IOException {
        // 创建对象
        EsBlog esBlog = new EsBlog();
        esBlog.setId(1L);
        esBlog.setBuildName("小明");
        // 创建请求
        IndexRequest request = new IndexRequest("test_index");
        // 规则 put /kuang_index/_doc/1
        request.id("1");
        request.timeout(TimeValue.timeValueSeconds(1));
        request.timeout("1s");
        // 将我们的数据放入请求 json
        request.source(JSON.toJSONString(esBlog), XContentType.JSON);
        // 客户端发送请求 , 获取响应的结果
        IndexResponse indexResponse = restHighLevelClient.index(request,
                RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString()); //
        System.out.println(indexResponse.status()); // 对应我们命令返回的状态CREATED
    }

    // 获取文档，判断是否存在 get /index/doc/1
    @RequestMapping(value = "/get/document", method = RequestMethod.GET)
    void testIsExists() throws IOException {
        GetRequest getRequest = new GetRequest("test_index");
        // 不获取返回的 _source 的上下文了
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.id("1");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 获得文档的信息
    @RequestMapping(value = "/get/document1", method = RequestMethod.GET)
    void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest("test_index");
        getRequest.id("1");

        GetResponse getResponse = restHighLevelClient.get(getRequest,
                RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString()); // 打印文档的内容
        System.out.println(getResponse); // 返回的全部内容和命令式一样的
    }

    // 更新文档的信息
    @RequestMapping(value = "/update/document", method = RequestMethod.GET)
    void testUpdateRequest() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("test_index","1");
        updateRequest.timeout("1s");
        EsBlog esBlog = new EsBlog();
        esBlog.setBuildName("小明1");
        updateRequest.doc(JSON.toJSONString(esBlog), XContentType.JSON);
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest,
                RequestOptions.DEFAULT);
        System.out.println(updateResponse.status());
    }

    // 删除文档记录
    @RequestMapping(value = "/delete/document", method = RequestMethod.GET)
    void testDeleteRequest() throws IOException {
        DeleteRequest request = new DeleteRequest("test_index");
        request.timeout("1s");
        request.id("1");
        DeleteResponse deleteResponse = restHighLevelClient.delete(request,
                RequestOptions.DEFAULT);
        System.out.println(deleteResponse.status());
    }

    // 特殊的，真的项目一般都会批量插入数据！
    @RequestMapping(value = "/bulk", method = RequestMethod.GET)
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        ArrayList<EsBlog> userList = new ArrayList<>();
        userList.add(new EsBlog("kuangshen1"));
        userList.add(new EsBlog("kuangshen2"));
        userList.add(new EsBlog("kuangshen3"));
        userList.add(new EsBlog("qinjiang1"));
        userList.add(new EsBlog("qinjiang2"));
        userList.add(new EsBlog("qinjiang3"));
        // 批处理请求
        for (int i = 0; i < userList.size(); i++) {
// 批量更新和批量删除，就在这里修改对应的请求就可以了
            bulkRequest.add(
                    new IndexRequest("test_index")
                            .id("" + (i + 1))
                            .source(JSON.toJSONString(userList.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest,
                RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures()); // 是否失败，返回 false 代表成功！
    }

    // 查询
    // SearchRequest 搜索请求
    // SearchSourceBuilder 条件构造
    // HighlightBuilder 构建高亮
    // TermQueryBuilder 精确查询
    // MatchAllQueryBuilder
    // xxx QueryBuilder 对应我们刚才看到的命令！
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    void testSearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("test_index");
        // 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.highlighter();
        // 查询条件，我们可以使用 QueryBuilders 工具来实现
        // QueryBuilders.termQuery 精确
        // QueryBuilders.matchAllQuery() 匹配所有
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("buildName",
                "qinjiang1");
        // MatchAllQueryBuilder matchAllQueryBuilder =
        QueryBuilders.matchAllQuery();
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest,
                RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("=================================");
        for (SearchHit documentFields : searchResponse.getHits().getHits()) {
            System.out.println(documentFields.getSourceAsMap());
        }
    }
}

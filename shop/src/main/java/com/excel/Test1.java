package com.excel;

import com.test.Utils.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/25 3:09
 * @package com.excel
 */
@Service
@Slf4j
public class Test1 {
    /**
     *每批次处理的数据量
     */
    private static  final int LIMIT = 100000;

    @Resource
    //private CodeReceptionListDao codeReceptionListDao;

    public static Queue<Map<String, Object>> queue;//Queue是java自己的队列，具体可看API，是同步安全的

    static {
        queue = new ConcurrentLinkedQueue<Map<String, Object>>();
    }

    private String filePath = "/Users/fishfly/localFile/excel/";

    @Resource
    private AsyncTaskService asyncTaskService;

    /**
     * 初始化队列
     */
    public void initQueue() {
        // 设置数据
        //long count = codeReceptionListDao.count(new HashMap<>());
        long listCount = 5000000;
        int listCount1 = (int) listCount;
        //导出6万以上数据。。。
        int count = listCount1 / LIMIT + (listCount1 % LIMIT > 0 ? 1 : 0);//循环次数
        for (int i = 1; i <= count; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("page", i);
            map.put("limit", LIMIT);
            map.put("path", filePath);
            //添加元素
            queue.offer(map);
        }
    }

    /**
     * 多线程批量导出 excel
     * @param response 用于浏览器下载
     * @throws InterruptedException
     */
    public void threadExcel(HttpServletResponse response) throws InterruptedException {
        initQueue();
        long start = System.currentTimeMillis();
        //异步转同步，等待所有线程都执行完毕返会 主线程才会结束
        try {
            CountDownLatch cdl = new CountDownLatch(queue.size());
            while (queue.size() > 0) {
                asyncTaskService.executeAsyncTask(queue.poll(), cdl);
            }
            cdl.await();

            //压缩文件
            File zipFile = new File(filePath.substring(0, filePath.length() - 1) + ".zip");
            FileOutputStream fos1 = new FileOutputStream(zipFile);
            //压缩文件目录
            ZipUtils.toZip(filePath, fos1, true);
            //发送zip包
            ZipUtils.sendZip(response, zipFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("任务执行完毕       共消耗   ：  " + (end - start) / 1000 / 60 + "  分钟");
    }
}

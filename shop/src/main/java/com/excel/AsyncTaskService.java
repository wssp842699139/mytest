package com.excel;

import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/25 3:10
 * @package com.excel
 */
public class AsyncTaskService {
    @Async("taskExecutor")
    public void executeAsyncTask(Map<String, Object> map, CountDownLatch cdl) {
        long start = System.currentTimeMillis();
        // 导出文件路径
        List<String> list = null;
        try {
            //PageUtil.pageUtil(map);
            //查询要导出的批次数据
            //list = codeReceptionListDao.queryAllByLimit(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 写法1
        String filepath = map.get("path").toString() + map.get("page") + ".xlsx";
        //TestFileUtil.createFile(filepath);
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        //EasyExcel.write(filepath, CodeReceptionList.class).sheet("模板").doWrite(list);
        long end = System.currentTimeMillis();
        System.out.println("线程：" + Thread.currentThread().getName() + " , 导出excel   " + map.get("page") + ".xlsx   成功 , 导出数据 " + list.size() + " ,耗时 ：" + (end - start));
        list.clear();
        //执行完毕线程数减一
        cdl.countDown();
        System.out.println("剩余任务数  ===========================> " + cdl.getCount());
    }
}

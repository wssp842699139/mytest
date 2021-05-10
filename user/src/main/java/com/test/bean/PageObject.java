package com.test.bean;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageObject {
    Integer page;
    Long count;
    Integer pageSize;
    Integer pageCount;
    List list;

    public  static PageObject makePage(Integer page,Integer pageSize,Integer count){
        PageObject pageObject = new PageObject();
        pageObject.setPage(page);
        pageObject.setPageSize(pageSize);
        pageObject.setCount(count.longValue());

        Double pageCount = Math.ceil(count*1.0/pageSize*1.0);

        pageObject.setPageCount(pageCount.intValue());

        return pageObject;

    }

    public static PageObject emptyPage(Integer pageSize){
        PageObject pageObject = makePage(1, pageSize, 0);
        pageObject.setList(new ArrayList());
        return pageObject;
    }


    static public<T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();
        int page = (listSize + (pageSize - 1)) / pageSize;
        List<List<T>>listArray = new ArrayList<List<T>>();
        for (int i = 0; i<page; i++) {
            List<T>subList = new ArrayList<T>();
            for (int j = 0; j<listSize; j++) {
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
                if (pageIndex == (i + 1)) {
                    subList.add(list.get(j));
                }
                if ((j + 1) == ((j + 1) * pageSize)) {
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;
    }



    public static <T> List<T> listPaging(List<T> list, Integer pageNo, Integer pageSize){
        if(list == null){
            list = new ArrayList<T>();
        }
        if(pageNo == null){
            pageNo = 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        if(pageNo <= 0){
            pageNo = 1;
        }

        int totalitems = list.size();
        List<T> pagingList = new ArrayList<T>();

        int totalNum = ((pageNo - 1) * pageSize) + pageSize > totalitems ? totalitems : ((pageNo - 1) * pageSize) + pageSize;
        for(int i = (pageNo-1)*pageSize; i < totalNum; i++) {
            pagingList.add(list.get(i));
        }
        return pagingList;
    }

}

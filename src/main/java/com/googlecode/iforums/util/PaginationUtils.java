package com.googlecode.iforums.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;

public class PaginationUtils {
    
    /**
     * 分页计算
     * @param total
     * @param current
     * @param size
     * @return
     */
    public static Map<String, Object> pagination(int total, int current, int size){
        if(size<=0){
            size = 10;
        }
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("size", size);
        
        int pageNumber = (total+size)/size;
        resultMap.put("pageNumber", pageNumber);
        
        if(current == 0){
            current = pageNumber;
        }else if(current<1){
            current = 1;
        }
        
        if(current>pageNumber){
            current = pageNumber;
        }
        
        resultMap.put("current", current);
        //上一页
        if(current > 1){
            int previous = current -1 ;
            resultMap.put("previous", previous);
        }
        
        //当前页前后翻页显示起点位置
        int startNumber = current -4;
        
        //前后翻页终点位置计算
        int endNumber = current + 4;
        
        if(startNumber < 1 && endNumber < pageNumber){
            endNumber = NumberUtils.max(new int[]{endNumber, endNumber + (1 - startNumber)});
        }
        
        if(endNumber>pageNumber && 1<startNumber){
            startNumber = NumberUtils.min(new int[]{startNumber, startNumber + (endNumber - pageNumber)});
        }
        
        startNumber = NumberUtils.max(new int[]{1, startNumber});
        endNumber = NumberUtils.min(new int[]{endNumber, pageNumber});
        
        resultMap.put("startNumber", startNumber);
        resultMap.put("endNumber", endNumber);
        
        //下一页
        if(current < pageNumber ){
            int next = current + 1 ;
            resultMap.put("next", next);
        }
        
        return resultMap;
    }
    
    public static void main(String[] args) {
        System.out.println(pagination(99, 3, 10));
    }
}

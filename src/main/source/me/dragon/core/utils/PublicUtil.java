package me.dragon.core.utils;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PublicUtil {
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object pObj) {
        if(pObj == null){
            return true;
        }
        if(pObj == ""){
            return true;
        }
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null) {
            return false;
        }
        if (pObj == "") {
            return false;
        }
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     *
     * Title: splitList
     *
     * Description:拆分list，按500条拆分
     *
     * @param <T>
     *
     * @param list
     * @return
     */
    public static <T> List<List<T>> separateList(List<T> list) {
        List<List<T>> lists = new ArrayList<List<T>>();
        List<T> subList;
        int size = list.size();
        int sum = 100;
        int count = size / sum;
        int yu = size % sum;
        if (count == 0) {
            lists.add(list);
        } else {
            if (size % sum != 0) {
                count++;
            }
            for (int i = 0; i < count; i++) {
                if (sum * (i + 1) <= size) {
                    subList = list.subList(sum * i, sum * (i + 1));
                } else {
                    subList = list.subList(sum * i, sum * (i) + yu);
                }
                lists.add(subList);
            }
        }
        return lists;
    }

}
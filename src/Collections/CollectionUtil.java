package Collections;

import java.io.ObjectInputStream;
import java.util.*;

public class CollectionUtil {

    /**
     * 将Set转换成List
     * @param set
     * @param <T> 抽象类型
     * @return
     */
    public static <T> List<T> set2list(Set<T> set){
        List<T> resultList = new ArrayList<T>();
        Iterator<T> iterator = set.iterator();
        while(iterator.hasNext()){
            T ele = iterator.next();
            resultList.add(ele);
        }
        return resultList;
    }

    /**
     * 将Map的所有key生成List
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> List<K>  map2list(Map<K,V> map){
        List<K> resultList = new ArrayList<>();
        Set<K> entrySet = map.keySet();
        Iterator<K> it = entrySet.iterator();
        while(it.hasNext()){
            K key = it.next();
            resultList.add(key);
        }
        return resultList;
    }

    /**
     * 将List的所有成员以index为key生成Map
     * @param list
     * @param index
     * @param <E>
     *           成员是一个数组，或者可以对象，如果是对象，则可以更改为
     *           用对象的某个属性当key
     * @return
     */
    public static <E extends ArrayList>  Map<Object,List<E>>  List2Map(List<E> list,int index){
        Map<Object,List<E>> resultMap = new HashMap<>();
        Iterator<E> it = list.iterator();
        while(it.hasNext()){
            E ele = it.next();
            Object k = ele.get(index);
            List<E> values = resultMap.get(k);
            if(values==null){
                values = new ArrayList<>();
                values.add(ele);
                resultMap.put(k,values);
            }else {
                values.add(ele);
            }
        }
        return resultMap;
    }

}

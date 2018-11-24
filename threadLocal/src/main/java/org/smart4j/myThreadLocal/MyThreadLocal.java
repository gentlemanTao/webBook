package org.smart4j.myThreadLocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Gentleman
 * @Date: 2018/11/25 1:13
 * @Description:自己动手实现ThreadLocal
 **/
public class MyThreadLocal<T> {

   // private Map<Thread,T> container= Collections.synchronizedMap (new HashMap<Thread,T> ());
   private Map<Thread,T> container= new ConcurrentHashMap<> ();

   public void set(T value){
       container.put (Thread.currentThread (),value);
   }

   public T get(){
       Thread thread=Thread.currentThread ();
       T value=container.get (thread);
       if (value==null&&!container.containsKey (thread)){
           value=initialValue();
           container.put (thread,value);
       }
       return value;
   }
   public void remove(){
       container.remove (Thread.currentThread ());
   }
    protected T initialValue() {
       return null;
    }
}

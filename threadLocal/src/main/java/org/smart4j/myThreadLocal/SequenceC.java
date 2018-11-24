package org.smart4j.myThreadLocal;

import org.smart4j.ClientThread;
import org.smart4j.Sequence;

/**
 * @Author: Gentleman
 * @Date: 2018/11/25 1:01
 * @Description:
 **/
public class SequenceC implements Sequence{
    //ThreadLocal是一个容器用来存放线程局部变量
    private static MyThreadLocal<Integer> numberContainer =new MyThreadLocal<Integer> (){
        //initialValue给线程局部变量设置一个初始值
        protected Integer initialValue() {
            return 0;
        }
    };
    @Override
    public int getNumber() {
        numberContainer.set (numberContainer.get ()+1);
        return numberContainer.get ();
    }
    public static void main(String[] args) {
        Sequence sequence=new SequenceC ();
        ClientThread thread1=new ClientThread (sequence);
        ClientThread thread2=new ClientThread (sequence);
        ClientThread thread3=new ClientThread (sequence);
        thread1.start ();
        thread2.start ();
        thread3.start ();

    }
}

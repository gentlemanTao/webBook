package org.smart4j;

/**
 * @Author: Gentleman
 * @Date: 2018/11/25 1:01
 * @Description:
 **/
public class SequenceA implements Sequence{
    //线程间共享变量无法保证线程安全
    private static int number =0;
    @Override
    public int getNumber() {
        number=number+1;
        return number;
    }
    public static void main(String[] args) {
        Sequence sequence=new SequenceA ();
        ClientThread thread1=new ClientThread (sequence);
        ClientThread thread2=new ClientThread (sequence);
        ClientThread thread3=new ClientThread (sequence);
        thread1.start ();
        thread2.start ();
        thread3.start ();

    }
}

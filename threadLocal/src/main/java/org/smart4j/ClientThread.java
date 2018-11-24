package org.smart4j;

/**
 * @Author: Gentleman
 * @Date: 2018/11/25 1:02
 * @Description:
 **/
public class ClientThread extends Thread{

    private Sequence sequence;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i=0;i<3;i++ ) {
            System.out.println (Thread.currentThread ().getName ()+" => "+sequence.getNumber ());
        }
    }
}

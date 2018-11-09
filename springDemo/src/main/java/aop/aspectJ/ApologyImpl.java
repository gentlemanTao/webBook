package aop.aspectJ;

/**
 * @Author: Gentleman
 * @Date: 2018/11/10 1:14
 * @Description:
 **/
public class ApologyImpl implements Apology{

    public void saySorry(String name) {
        System.out.println ("Sorry! "+name);
    }
}

package com.myhp.basicUtil;

/**
 * Created with IntelliJ IDEA.
 * Date: 2/20/14
 * Time: 5:17 AM
 * To change this template use File | Settings | File Templates.
 *
 * Constructor of Class also have a "return", "break".
 */
public class ConstructorWithReturn  {
    private String mData;
    public ConstructorWithReturn(String string){
        if(string == null) {
            System.out.println("error in constructer");
            return;
        }
        mData = string;
        System.out.println("OK for constructer");
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode()) + mData == null ? "null": mData;
    }

    public static void main(String[] args) {
       String str = "hello word";
       /*OK for constructer
    hello word*/
//        String str=null;
/*error in constructer
null
*/
        ConstructorWithReturn obj = new ConstructorWithReturn(str);
        System.out.println(obj.toString());
    }
}

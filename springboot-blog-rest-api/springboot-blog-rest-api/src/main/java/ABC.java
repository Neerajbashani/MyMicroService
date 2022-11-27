public class ABC {

    public void abc(String k){
//        System.out.println(k);
        if(k==null){
            k="hello";
        }
        System.out.println(k);

    }

    public static void main(String[] args) {


        ABC a=new ABC();
        a.abc("efef");
    }
}

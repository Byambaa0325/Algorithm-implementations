import java.util.Scanner;

import static java.lang.Math.max;

public class E3 {
    public static int[][] data;
    public static int recursiveCallTimes=0;
    public static int dynamicCallTimes=0;

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        String X =sc.nextLine();
        String Y=sc.nextLine();
        int x = X.length();
        int y= Y.length();
        data= new int[x+1][y+1];
        for(int i=0;i<=x;i++){
            for(int j=0;j<=y;j++){
                data[i][j]=-1;
            }
        }
        System.out.println("Dynamic approach is : "+dynamic(X,Y,x,y)+" Call times: "+dynamicCallTimes);
        System.out.println("Recursive approach is : "+test(X,Y,x,y)+" Call times: "+ recursiveCallTimes);


    }
    public static int dynamic(String X, String Y, int x,int y){
        int result;
        dynamicCallTimes++;

       if(data[x][y]!=-1){
           return data[x][y];
       }
       if( x==0 || y==0){
           result= 0;
       }
       else if(X.charAt(x-1)==Y.charAt(y-1)){
           result= 1+dynamic(X,Y, x-1,y-1);
       }
       else{
           result= max(dynamic(X,Y,x-1,y), dynamic(X,Y,x,y-1));
       }
       data[x][y]=result;
       return result;
    }
    public static int test(String X, String Y, int x, int y){
        recursiveCallTimes++;
        int result;



        if( x==0 || y==0){
            result= 0;
        }
        else if(X.charAt(x-1)==Y.charAt(y-1)){
            result= 1+test(X,Y, x-1,y-1);
        }
        else{
            result= max(test(X,Y,x-1,y), test(X,Y,x,y-1));
        }

        return result;
    }
}
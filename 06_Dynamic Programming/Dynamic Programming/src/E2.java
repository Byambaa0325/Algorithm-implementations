import java.util.Scanner;

public class E2 {
    public static int[][] data;
    public static int recursiveCallTimes=0;
    public static int dynamicCallTimes=0;

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int x = sc.nextInt();
        int y= sc.nextInt();
        data= new int[x+1][y+1];
        for(int i=0;i<=x;i++){
            for(int j=0;j<=y;j++){
                data[i][j]=-1;
            }
        }
        System.out.println("Dynamic approach is : "+dynamic(x,y)+" Call times: "+dynamicCallTimes);
        System.out.println("Recursive approach is : "+test(x,y)+" Call times: "+ recursiveCallTimes);


    }
    public static int dynamic(int n, int k){
        dynamicCallTimes++;
        System.out.println("Dynamic call on : "+n+" "+ k+" on memory block = "+data[n][k]);

        if(n<k||k<0){
            throw new ArithmeticException("n is lower than k or k is lower than zero");
        }
        if(n==k||k==0){
            return 1;
        }

        if(data[n][k]==-1){
            data[n][k]=dynamic(n-1,k-1)+dynamic(n-1,k);
        }

       return data[n][k];


    }
    public static int test(int n, int k){
        System.out.println("Recursive call on : "+n+" "+ k);
        recursiveCallTimes++;
        if (n==k||(n>k&&k==0)){
            return 1;
        }
        return test(n-1,k-1)+test(n-1,k);
    }
}
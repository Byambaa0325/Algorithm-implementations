import java.util.Scanner;

public class E1 {
    public static int[][] data;
    public static int recursiveCallTimes=0;
    public static int dynamicCallTimes=0;

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        int x = sc.nextInt();
        int y= sc.nextInt();
        data= new int[x+1][y+1];
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                data[i][j]=-1;
            }
        }
        System.out.println("Dynamic approach is : "+dynamic(x,y)+" Call times: "+dynamicCallTimes);
        System.out.println("Recursive approach is : "+test(x,y)+" Call times: "+ recursiveCallTimes);


    }
    public static int dynamic(int x,int y){

        data[1][1]=1;
        data[0][1]=1;
        data[1][0]=1;
        for(int i=1;i<=x;i++){
            for(int j=1;j<=y;j++){
                dynamicCallTimes++;
                if(i==1){
                    data[i][j]=1;
                }
                else if(j==1){
                    data[i][j]=1;
                }
                else {
                    data[i][j] = data[i - 1][j] + data[i][j - 1];
                }
            }
        }
        return data[x][y];
    }
    public static int test(int x, int y){
        recursiveCallTimes++;
        if (x<=1||y<=1){
            return 1;
        }
        return test(x-1,y)+test(x,y-1);
    }
}
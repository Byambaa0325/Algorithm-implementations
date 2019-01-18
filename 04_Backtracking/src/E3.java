import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class E3 {

    public static PrintWriter out;
    public static void main ( String[] args ){
        try {
            File file = new File("input.txt");
            out = new PrintWriter(new FileWriter("output.txt"));
            Scanner sc = new Scanner(file);
            int m = sc.nextInt();
            int n = sc.nextInt();
            sc.nextLine();
            ArrayList<Integer> S = new ArrayList<>();
            ArrayList<Integer> build = new ArrayList<>();
            int input;
            for(int i = 0; i<n;i++){
                input= sc.nextInt();

                S.add(input);
            }
            select(m, S, build, 0);

            out.close();
        }catch (FileNotFoundException e) {
            System.out.println("Error during reading");
        } catch (IOException e1) {
            System.out.println("Error during writing");
        }


    }
    public static boolean select(int m, ArrayList<Integer> S, ArrayList<Integer> build,int sum){

        if(sum==m){
            print(build);
            return true;
        }
        if(sum>m){
            return false;
        }
        ArrayList<Integer> Snew= new ArrayList<>();


        for(int i=0; i<S.size();i++){
            if(sum+S.get(i)<=m){
                Snew.add(S.get(i));
            }




        }


        int temp;
        while(Snew.size()>0){

            temp=Snew.get(0);
            Snew.remove(0);
            sum+=temp;
            build.add(temp);




            select(m,Snew,build,sum);





            build.remove((Integer) temp);

            sum-=temp;

        }
        return false;


    }

    //Utility
    public static void print(ArrayList<Integer> build){


        for(int i=0; i<build.size();i++){
            out.print(build.get(i)+" ");


        }
        out.println();
    }
}

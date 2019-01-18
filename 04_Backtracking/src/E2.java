import java.util.ArrayList;



public class E2 {

    public static double min =100;

    public static void main(String[] args){
        int n=10;
        double[] stones = {4, 1, 2, 3 };
        double s =sum(stones);
        ArrayList<Double> S= new ArrayList<>();
        for(int i=0;i<stones.length;i++){

            S.add((stones[i]/s)*100);
            System.out.print(S.get(i)+" ");
        }
        System.out.println();
        select(0,S);
        System.out.println("Min is difference is "+min);



    }
    public static double sum(double[] arr){
        double s=0;
        for(int i=0;i<arr.length;i++){
            s+=arr[i];
        }
        return s;
    }

    public static void select(double build , ArrayList<Double> S){
        if(S.size()==0){
            if(min>build){
                min = 100-2*build;
            }
            System.out.println("Divided into "+build+": "+(100-build));
        }
        if(build>=50){
            if(min>100-2*build){
                min = 100-2*build;
            }
            System.out.println("Divided into "+build+": "+(100-build));
        }
        ArrayList<Double> Snew= new ArrayList<>();
        for(int i=0;i<S.size();i++){
            if(S.get(i)+build<=50){
                Snew.add(S.get(i));
            }
        }

        for(int i=0;i<Snew.size();i++){
            double temp=Snew.get(i);
            build+=temp;
            Snew.remove(i);
            select(build,Snew);
            build-=temp;
            Snew.add(temp);
        }
    }
}


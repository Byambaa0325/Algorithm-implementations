public class E1{
    public static int totalPossiblePositions=0;

    public static void main(String[] args){
        boolean[][] S = new boolean[8][8];
        int[] build= new int[8];

        //inputs
        int n=8;
        for(int i=0;i<8;i++){
            build[i]=-1;
            for(int j=0;j<8;j++){
                S[i][j]=true;
            }
        }

        for(int i=0;i<=8-n;i++) {
            select(n, i,i, build, S);
        }
    }

    //Backtracking Function
    public static int select(int n,int k,int startedPos, int[] build,boolean[][] S){

        //Termination cases
        boolean terminate = true;
        boolean printable = true;
        //If reached the end or placed all queens
        if(k==8||k-startedPos>=n){
            printBuild(build);
            return 0;
        }
        //Can we place a queen in the kth line?
        for(int i=0;i<S[k].length;i++){
            if(S[k][i]==true){
                terminate= false;
            }
            if(build[i]==-1){
                printable=false;
            }
        }
        //Looks like we cant place anymore!
        if(terminate){
            //if it is printable(all queens placed) print it!
            if(printable){
                printBuild(build);
                return 0;
            }
        }

        //Backtracking
        for(int i=0; i<S[k].length;i++){
            if(S[k][i]){
                boolean[][] Snew= new boolean[8][8];
                for(int j=0;j<8;j++){
                    for(int p=0;p<8;p++){
                        Snew[j][p]=S[j][p];
                    }

                }
                build[k]=i;
                Snew=updateBoard(Snew,k,i);
                select(n,k+1,startedPos,build,Snew);
                build[k]=-1;
            }
        }
        return 0;
    }

    //Utility Functions
    public static void printBuild(int[] build){
        totalPossiblePositions++;
        System.out.println("Variation: "+totalPossiblePositions);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(build[i]==j){
                    System.out.print("1 ");
                }
                else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
    public static boolean[][] updateBoard(boolean[][] S,int  k, int i){
        //Possibility for the left move
        int leftIterator=i;
        //Possibility for the right move
        int rightIterator=i;
        for(int j=k;j<8;j++){
            //Possibility for the vertical move
            S[j][i]=false;
            if(rightIterator<8){
                S[j][rightIterator]=false;
            }
            if(leftIterator>=0){
                S[j][leftIterator]=false;
            }
            leftIterator--;
            rightIterator++;
        }
        return S;
    }


}

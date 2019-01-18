import javafx.geometry.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


class Graph {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);

            int n = sc.nextInt();

            int[][] board = new int[n][n];
            Point2D P = new Point2D(sc.nextInt(), sc.nextInt());
            Point2D P1 = new Point2D(sc.nextInt(), sc.nextInt());

            int sectors = 0;
            sectors = BFS(board);
            int[][] pathTimes = BFS_paths(board, P);

            //board=pathVisualize(pathTimes);
            ArrayList<Point2D> path = pathExtract(pathTimes, P, P1);
            if (pathTimes[(int) P1.getX()][(int) P1.getY()] != 0) {
                System.out.println(pathTimes[(int) P1.getX()][(int) P1.getY()]);
                System.out.println(sectors);
                for (int i = path.size() - 1; i >= 0; i--) {
                    System.out.print(path.get(i).toString() + ", ");
                }
            } else {
                System.out.println(-1);
                System.out.println(sectors);
                System.out.println(-1);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error during reading");
        }
    }

    private static ArrayList pathExtract(int[][] board, Point2D P, Point2D P1) {
        ArrayList<Point2D> path = new ArrayList<>();

        Point2D temp = new Point2D(P1.getX(), P1.getY());

        while (board[(int) temp.getX()][(int) temp.getY()] != 1) {
            path.add(temp);
            if (board[(int) temp.getX()][(int) temp.getY() - 1] < board[(int) temp.getX()][(int) temp.getY()] && board[(int) temp.getX()][(int) temp.getY() - 1] != 0) {
                temp = new Point2D(temp.getX(), temp.getY() - 1);
            }
            if (board[(int) temp.getX() - 1][(int) temp.getY()] < board[(int) temp.getX()][(int) temp.getY()] && board[(int) temp.getX() - 1][(int) temp.getY()] != 0) {
                temp = new Point2D(temp.getX() - 1, temp.getY());
            }
            if (board[(int) temp.getX() + 1][(int) temp.getY()] < board[(int) temp.getX()][(int) temp.getY()] && board[(int) temp.getX() + 1][(int) temp.getY()] != 0) {
                temp = new Point2D(temp.getX() + 1, temp.getY());
            }
            if (board[(int) temp.getX()][(int) temp.getY() + 1] < board[(int) temp.getX()][(int) temp.getY()] && board[(int) temp.getX()][(int) temp.getY() + 1] != 0) {
                temp = new Point2D(temp.getX(), temp.getY() + 1);
            }
        }
        return path;
    }

    private static int[][] pathVisualize(int[][] board) {
        for (int[] ints : board) {
            System.out.println(Arrays.toString(ints));
        }
        return board;
    }

    private static int BFS(int[][] board) {
        int[][] visitedRecord = new int[board.length][board.length];
        int count = 0;
        for (int i = 0; i < visitedRecord.length; i++) {
            for (int j = 0; j < visitedRecord.length; j++) {
                visitedRecord[i][j] = 0;
            }
        }
        for (int i = 0; i < visitedRecord.length; i++) {
            for (int j = 0; j < visitedRecord.length; j++) {
                if (visitedRecord[i][j] == 0 && board[i][j] == 0) {
                    visitedRecord = BFS_paths(board, new Point2D(i, j));
                    count++;

                }
            }
        }
        return count;

    }

    private static int[][] BFS_paths(int[][] board, Point2D P) {

        int time = 0;
        int[][] visitedRecord = new int[board.length][board.length];
        for (int i = 0; i < visitedRecord.length; i++) {
            for (int j = 0; j < visitedRecord.length; j++) {
                visitedRecord[i][j] = 0;
            }
        }

        Queue<Point2D> queue = new LinkedList<>();
        Queue<Point2D> timeStampQueue = new LinkedList<>();
        queue.add(P);
        while (queue.peek() != null) {
            Point2D point = queue.remove();

            if (checkUp(board, point)) {
                if (visitedRecord[(int) point.getX()][(int) point.getY() - 1] == 0) {
                    if (((LinkedList<Point2D>) timeStampQueue).indexOf(new Point2D(point.getX(), point.getY() - 1)) == -1) {
                        timeStampQueue.add(new Point2D(point.getX(), point.getY() - 1));
                    }
                }
            }
            if (checkLeft(board, point)) {
                if (visitedRecord[(int) point.getX() - 1][(int) point.getY()] == 0) {
                    if (((LinkedList<Point2D>) timeStampQueue).indexOf(new Point2D(point.getX() - 1, point.getY())) == -1) {
                        timeStampQueue.add(new Point2D(point.getX() - 1, point.getY()));
                    }
                }
            }
            if (checkRight(board, point)) {
                if (visitedRecord[(int) point.getX() + 1][(int) point.getY()] == 0) {
                    if (((LinkedList<Point2D>) timeStampQueue).indexOf(new Point2D(point.getX() + 1, point.getY())) == -1) {
                        timeStampQueue.add(new Point2D(point.getX() + 1, point.getY()));
                    }
                }
            }
            if (checkDown(board, point)) {
                if (visitedRecord[(int) point.getX()][(int) point.getY() + 1] == 0) {
                    if (((LinkedList<Point2D>) timeStampQueue).indexOf(new Point2D(point.getX(), point.getY() + 1)) == -1) {
                        timeStampQueue.add(new Point2D(point.getX(), point.getY() + 1));
                    }
                }
            }
            if (queue.peek() == null) {
                time++;
                while (timeStampQueue.peek() != null) {
                    Point2D temp = timeStampQueue.remove();
                    visitedRecord[(int) temp.getX()][(int) temp.getY()] = time;
                    queue.add(temp);
                }
            }

        }


        return visitedRecord;


    }

    private static boolean checkUp(int[][] board, Point2D P) {
        if (P.getY() - 1 >= 0) {
            return board[(int) P.getX()][(int) P.getY() - 1] == 0;
        }
        return false;

    }

    private static boolean checkLeft(int[][] board, Point2D P) {
        if (P.getX() - 1 >= 0) {
            return board[(int) P.getX() - 1][(int) P.getY()] == 0;
        }
        return false;

    }

    private static boolean checkRight(int[][] board, Point2D P) {
        if (P.getX() + 1 < board.length) {
            return board[(int) P.getX() + 1][(int) P.getY()] == 0;
        }
        return false;

    }

    private static boolean checkDown(int[][] board, Point2D P) {
        if (P.getY() + 1 < board.length) {
            return board[(int) P.getX()][(int) P.getY() + 1] == 0;
        }
        return false;

    }
}

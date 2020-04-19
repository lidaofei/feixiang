package com.feixiang.datastructures.sparsearray;

/**
 * 稀疏数组
 * @author lidaofei
 * @date 2020/4/12 10:59
 */
public class SparseArray {
    public static void main(String[] args) {
        //一、普通二维数组转稀疏数组（推演）
        //1.定义一个11*11的二维数组
        //2.在array[1][2]=1 和 array[2][3]=1 其他值为零
        //3.定义一个3*3的二维数组
        //4.第一行：sparseArray[0][0]=11,sparseArray[0][1]=11,sparseArray[0][2]=2
        //5.第二行：sparseArray[1][0]=1,sparseArray[1][1]=2,sparseArray[1][2]=1
        //6.第三行：sparseArray[2][0]=2,sparseArray[2][1]=3,sparseArray[2][2]=1

        //普通数组转稀疏数组（推演）
        arrayToSparseArrayDeduce();

        //普通二维数组转稀疏数组（通用）
        arrayToSparseArray();

        //稀疏数组转普通二维数组(通用)
        sparseArrayToArray();
    }

    /**
     * 稀疏数组转普通二维数组(通用)
     */
    private static void sparseArrayToArray() {
        //1.初始化数组，row=sparseArray[0][0];col=sparseArray[0][1];
        //2.array[i][j] = val; sparseArray[1][0]=i;sparseArray[1][1]=j;sparseArray[1][2]=val

        int[][] sparseArray = new int[4][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = 2;
        sparseArray[1][0] = 2;
        sparseArray[1][1] = 4;
        sparseArray[1][2] = 1;
        sparseArray[2][0] = 5;
        sparseArray[2][1] = 2;
        sparseArray[2][2] = 1;
        sparseArray[3][0] = 6;
        sparseArray[3][1] = 8;
        sparseArray[3][2] = 1;

        //1.初始化数组，row=sparseArray[0][0];col=sparseArray[0][1];
        int[][] array = new int[ sparseArray[0][0] ][ sparseArray[0][1] ];

        //2.array[i][j] = val; sparseArray[1][0]=i;sparseArray[1][1]=j;sparseArray[1][2]=val

        for(int i=1; i<sparseArray.length;i++){
            array[ sparseArray[i][0] ][ sparseArray[i][1] ] = sparseArray[i][2];
        }
        printArray(array);
    }

    /**
     * 普通二维数组转稀疏数组（通用）
     */
    private static void arrayToSparseArray() {
        //二、普通二维数组转稀疏数组（通用）
        int row = 11;
        int col = 11;
        int[][] array = new int[row][col];
        //打印数组
        printArray(array);

        //2.在array[1][2]=1 和 array[2][3]=1 其他值为零
        array[1][2] = 1;
        array[2][3] = 1;
        array[4][3] = 1;
        array[5][6] = 1;

        //3.定义一个3*x的二维数组
        int arrayRow = array.length;
        int arrayCol = array[0].length;

        //算出普通二维数组有多少个不为零的项
        int haveValueSum = 0;
        for(int i=0; i<arrayRow; i++){
            for(int j=0 ; j<arrayCol; j++){
                if(array[i][j] != 0){
                    haveValueSum ++;
                }
            }
        }
        int[][] sparseArray = new int[haveValueSum+1][3];
        //4.第一行：sparseArray[0][0]=11,sparseArray[0][1]=11,sparseArray[0][2]=2

        sparseArray[0][0] = arrayRow;
        sparseArray[0][1] = arrayCol;
        sparseArray[0][2] = haveValueSum;

        //5.第二行：sparseArray[1][0]=1,sparseArray[1][1]=2,sparseArray[1][2]=1
        int count = 1;
        for(int i=0; i<arrayRow; i++){
            for(int j=0 ; j<arrayCol; j++){
                if(array[i][j] != 0){
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array[i][j];
                    count ++;
                }
            }
        }

        printArray(sparseArray);
    }

    /**
     * 普通数组转稀疏数组（推演）
     */
    private static void arrayToSparseArrayDeduce() {
        //1.定义一个11*11的二维数组
        int[][] array = new int[11][11];
        //打印数组
        printArray(array);

        //2.在array[1][2]=1 和 array[2][3]=1 其他值为零
        array[1][2] = 1;
        array[2][3] = 1;

        //3.定义一个3*3的二维数组
        int[][] sparseArray = new int[3][3];

        //4.第一行：sparseArray[0][0]=11,sparseArray[0][1]=11,sparseArray[0][2]=2
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = 2;

        //5.第二行：sparseArray[1][0]=1,sparseArray[1][1]=2,sparseArray[1][2]=1
        sparseArray[1][0] = 1;
        sparseArray[1][1] = 2;
        sparseArray[1][2] = 1;

        //6.第三行：sparseArray[2][0]=2,sparseArray[2][1]=3,sparseArray[2][2]=1
        sparseArray[2][0] = 2;
        sparseArray[2][1] = 3;
        sparseArray[2][2] = 1;

        printArray(sparseArray);
    }

    /**
     * 打印数组
     * @param array
     */
    private static void printArray(int[][] array) {
        for (int[] row : array) {
            for(int col : row){
                System.out.print(col+"\t");
            }
            System.out.println();
        }
    }
}

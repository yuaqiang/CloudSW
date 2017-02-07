package ssw;

/**
 * Created by xubo on 2016/11/25.
 */
public class SSW {
    public static int[][] scoreMatrix50 = blosum(blosum50Base());
    public static int[][] scoreMatrix60 = blosum(blosum60Base());
    public static int[][] scoreMatrix62 = blosum(blosum62Base());

    public static boolean flag = false;
    public static byte[] readNum = null;

    static {
//        System.loadLibrary("sswjni");
        try {
            System.loadLibrary("sswjni");
        } catch (UnsatisfiedLinkError e) {
            System.out.println(String.format("Cannot find libsswjni.so. Has the library been built and LD_LIBRARY_PATH or -Djava.library.path set appropriately?\n%s", e));
            throw e;
        }
    }

    public static Alignment align(String query, String ref, String scoreMatrix, int open, int extension) throws Exception {
        try {
            System.loadLibrary("sswjni");
        } catch (UnsatisfiedLinkError e) {
            System.out.println(String.format("Cannot find libsswjni.so. Has the library been built and LD_LIBRARY_PATH or -Djava.library.path set appropriately?\n%s", e));
            throw e;
        }

//        int[][] score = new int[128][128];
//        for (int i = 0; i < 128; i++) {
//            for (int j = 0; j < 128; j++) {
//                if (i == j) score[i][j] = 2;
//                else score[i][j] = -2;
//            }
//        }
//        Alignment aln = Aligner.align(query.getBytes(), ref.getBytes(), score, 3, 1, true);
//        System.out.println("Aligning nucleotides");

        int[][] SM = null;
        if (scoreMatrix.equalsIgnoreCase("BLOSUM50")) {
            SM = scoreMatrix50;
        } else if (scoreMatrix.equalsIgnoreCase("BLOSUM60")) {
            SM = scoreMatrix60;
        } else if (scoreMatrix.equalsIgnoreCase("BLOSUM62")) {
            SM = scoreMatrix62;
        } else {
            throw new Exception("score maxtrix error");
        }
        Alignment aln = Aligner.align(query.getBytes(), ref.getBytes(), SM, open, extension, true);
//        Alignment aln = Aligner.align(query.getBytes(), ref.getBytes(), scoreMatrix62, 11, 1, true);
        if (aln == null) {
            throw new RuntimeException();
        }

        return aln;
    }

    public static Alignment alignNew(String query, String ref) {

//        try {
//            System.loadLibrary("sswjni");
//        } catch (UnsatisfiedLinkError e) {
//            System.out.println(String.format("Cannot find libsswjni.so. Has the library been built and LD_LIBRARY_PATH or -Djava.library.path set appropriately?\n%s", e));
//            throw e;
//        }
//        System.out.println("flag="+flag);
        if (flag == true) {
            readNum = Aligner.profile(query.getBytes(), true);
            flag = false;
        }
        if (readNum == null) {
            System.out.println("\nRuntimeException1\n");
            throw new RuntimeException();
        }
        Alignment aln = Aligner.alignNew(readNum, ref.getBytes(), scoreMatrix50, 12, 2, true);
        if (aln == null) {
            System.out.println("\nRuntimeException2\n");
            throw new RuntimeException();
        }
        return aln;
    }

    public static int[][] blosum(String[][] strArr) {
        int[][] result = new int[128][128];

        int m = strArr.length;
        int n = strArr[0].length;

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (i == j) {
                    result[i][j] = Integer.valueOf(strArr[m - 1][n - 1]);
                } else {
                    result[i][j] = Integer.valueOf(strArr[0][n - 1]);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            char c = strArr[i][0].charAt(0);
            for (int j = 0; j < result.length; j++) {
                result[Integer.valueOf(c)][j] = Integer.valueOf(strArr[i][strArr[i].length - 1]);
                result[j][Integer.valueOf(c)] = Integer.valueOf(strArr[i][strArr[i].length - 1]);
            }
        }

        //    println(result(0)(0))
        for (int i = 0; i < m; i++) {
            char strI = strArr[i][0].charAt(0);
            //       strArr(i)(0).toCharArray

            int valueI = Integer.valueOf(strArr[i][0].charAt(0));
            //      println(strI + "=" + valueI + "=" + strArr(i)(0).charAt(0).toInt)

            for (int j = 1; j < n; j++) {

                int valueJ = Integer.valueOf(strArr[j - 1][0].charAt(0));
                int tmp = Integer.valueOf(strArr[i][j]);
                result[valueI][valueJ] = tmp;
                //        arr(value)(j)

            }
        }

        //println(result(0)(0))
        return result;
    }

    /**
     * blosum50BaseV2
     * ref:https://www.ncbi.nlm.nih.gov/IEB/ToolBox/C_DOC/lxr/source/data/BLOSUM50
     * 相对V1删除了J
     *
     * @return blosum50
     */
    public static String[][] blosum50BaseV2() {
        String[][] a = {
                {"A", "5", "-2", "-1", "-2", "-1", "-1", "-1", "0", "-2", "-1", "-2", "-1", "-1", "-3", "-1", "1", "0", "-3", "-2", "0", "-2", "-1", "-1", "-5"},
                {"R", "-2", "7", "-1", "-2", "-4", "1", "0", "-3", "0", "-4", "-3", "3", "-2", "-3", "-3", "-1", "-1", "-3", "-1", "-3", "-1", "0", "-1", "-5"},
                {"N", "-1", "-1", "7", "2", "-2", "0", "0", "0", "1", "-3", "-4", "0", "-2", "-4", "-2", "1", "0", "-4", "-2", "-3", "5", "0", "-1", "-5"},
                {"D", "-2", "-2", "2", "8", "-4", "0", "2", "-1", "-1", "-4", "-4", "-1", "-4", "-5", "-1", "0", "-1", "-5", "-3", "-4", "6", "1", "-1", "-5"},
                {"C", "-1", "-4", "-2", "-4", "13", "-3", "-3", "-3", "-3", "-2", "-2", "-3", "-2", "-2", "-4", "-1", "-1", "-5", "-3", "-1", "-3", "-3", "-1", "-5"},
                {"Q", "-1", "1", "0", "0", "-3", "7", "2", "-2", "1", "-3", "-2", "2", "0", "-4", "-1", "0", "-1", "-1", "-1", "-3", "0", "4", "-1", "-5"},
                {"E", "-1", "0", "0", "2", "-3", "2", "6", "-3", "0", "-4", "-3", "1", "-2", "-3", "-1", "-1", "-1", "-3", "-2", "-3", "1", "5", "-1", "-5"},
                {"G", "0", "-3", "0", "-1", "-3", "-2", "-3", "8", "-2", "-4", "-4", "-2", "-3", "-4", "-2", "0", "-2", "-3", "-3", "-4", "-1", "-2", "-1", "-5"},
                {"H", "-2", "0", "1", "-1", "-3", "1", "0", "-2", "10", "-4", "-3", "0", "-1", "-1", "-2", "-1", "-2", "-3", "2", "-4", "0", "0", "-1", "-5"},
                {"I", "-1", "-4", "-3", "-4", "-2", "-3", "-4", "-4", "-4", "5", "2", "-3", "2", "0", "-3", "-3", "-1", "-3", "-1", "4", "-4", "-3", "-1", "-5"},
                {"L", "-2", "-3", "-4", "-4", "-2", "-2", "-3", "-4", "-3", "2", "5", "-3", "3", "1", "-4", "-3", "-1", "-2", "-1", "1", "-4", "-3", "-1", "-5"},
                {"K", "-1", "3", "0", "-1", "-3", "2", "1", "-2", "0", "-3", "-3", "6", "-2", "-4", "-1", "0", "-1", "-3", "-2", "-3", "0", "1", "-1", "-5"},
                {"M", "-1", "-2", "-2", "-4", "-2", "0", "-2", "-3", "-1", "2", "3", "-2", "7", "0", "-3", "-2", "-1", "-1", "0", "1", "-3", "-1", "-1", "-5"},
                {"F", "-3", "-3", "-4", "-5", "-2", "-4", "-3", "-4", "-1", "0", "1", "-4", "0", "8", "-4", "-3", "-2", "1", "4", "-1", "-4", "-4", "-1", "-5"},
                {"P", "-1", "-3", "-2", "-1", "-4", "-1", "-1", "-2", "-2", "-3", "-4", "-1", "-3", "-4", "10", "-1", "-1", "-4", "-3", "-3", "-3", "-1", "-1", "-5"},
                {"S", "1", "-1", "1", "0", "-1", "0", "-1", "0", "-1", "-3", "-3", "0", "-2", "-3", "-1", "5", "2", "-4", "-2", "-2", "0", "0", "-1", "-5"},
                {"T", "0", "-1", "0", "-1", "-1", "-1", "-1", "-2", "-2", "-1", "-1", "-1", "-1", "-2", "-1", "2", "5", "-3", "-2", "0", "0", "-1", "-1", "-5"},
                {"W", "-3", "-3", "-4", "-5", "-5", "-1", "-3", "-3", "-3", "-3", "-2", "-3", "-1", "1", "-4", "-4", "-3", "15", "2", "-3", "-5", "-2", "-1", "-5"},
                {"Y", "-2", "-1", "-2", "-3", "-3", "-1", "-2", "-3", "2", "-1", "-1", "-2", "0", "4", "-3", "-2", "-2", "2", "8", "-1", "-3", "-2", "-1", "-5"},
                {"V", "0", "-3", "-3", "-4", "-1", "-3", "-3", "-4", "-4", "4", "1", "-3", "1", "-1", "-3", "-2", "0", "-3", "-1", "5", "-3", "-3", "-1", "-5"},
                {"B", "-2", "-1", "5", "6", "-3", "0", "1", "-1", "0", "-4", "-4", "0", "-3", "-4", "-2", "0", "0", "-5", "-3", "-3", "6", "1", "-1", "-5"},
                {"Z", "-1", "0", "0", "1", "-3", "4", "5", "-2", "0", "-3", "-3", "1", "-1", "-4", "-1", "0", "-1", "-2", "-2", "-3", "1", "5", "-1", "-5"},
                {"X", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-5"},
                {"*", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "1"}
        };
        return a;
    }

    /**
     * blosum50BaseV1
     * ref:https://www.ncbi.nlm.nih.gov/IEB/ToolBox/C_DOC/lxr/source/data/BLOSUM50
     * 相对于其他有J
     *
     * @return blosum50
     */
    public static String[][] blosum50Base() {
        String[][] a = {
                {"A", "5", "-2", "-1", "-2", "-1", "-1", "-1", "0", "-2", "-1", "-2", "-1", "-1", "-3", "-1", "1", "0", "-3", "-2", "0", "-2", "-2", "-1", "-1", "-5"},
                {"R", "-2", "7", "-1", "-2", "-4", "1", "0", "-3", "0", "-4", "-3", "3", "-2", "-3", "-3", "-1", "-1", "-3", "-1", "-3", "-1", "-3", "0", "-1", "-5"},
                {"N", "-1", "-1", "7", "2", "-2", "0", "0", "0", "1", "-3", "-4", "0", "-2", "-4", "-2", "1", "0", "-4", "-2", "-3", "5", "-4", "0", "-1", "-5"},
                {"D", "-2", "-2", "2", "8", "-4", "0", "2", "-1", "-1", "-4", "-4", "-1", "-4", "-5", "-1", "0", "-1", "-5", "-3", "-4", "6", "-4", "1", "-1", "-5"},
                {"C", "-1", "-4", "-2", "-4", "13", "-3", "-3", "-3", "-3", "-2", "-2", "-3", "-2", "-2", "-4", "-1", "-1", "-5", "-3", "-1", "-3", "-2", "-3", "-1", "-5"},
                {"Q", "-1", "1", "0", "0", "-3", "7", "2", "-2", "1", "-3", "-2", "2", "0", "-4", "-1", "0", "-1", "-1", "-1", "-3", "0", "-3", "4", "-1", "-5"},
                {"E", "-1", "0", "0", "2", "-3", "2", "6", "-3", "0", "-4", "-3", "1", "-2", "-3", "-1", "-1", "-1", "-3", "-2", "-3", "1", "-3", "5", "-1", "-5"},
                {"G", "0", "-3", "0", "-1", "-3", "-2", "-3", "8", "-2", "-4", "-4", "-2", "-3", "-4", "-2", "0", "-2", "-3", "-3", "-4", "-1", "-4", "-2", "-1", "-5"},
                {"H", "-2", "0", "1", "-1", "-3", "1", "0", "-2", "10", "-4", "-3", "0", "-1", "-1", "-2", "-1", "-2", "-3", "2", "-4", "0", "-3", "0", "-1", "-5"},
                {"I", "-1", "-4", "-3", "-4", "-2", "-3", "-4", "-4", "-4", "5", "2", "-3", "2", "0", "-3", "-3", "-1", "-3", "-1", "4", "-4", "4", "-3", "-1", "-5"},
                {"L", "-2", "-3", "-4", "-4", "-2", "-2", "-3", "-4", "-3", "2", "5", "-3", "3", "1", "-4", "-3", "-1", "-2", "-1", "1", "-4", "4", "-3", "-1", "-5"},
                {"K", "-1", "3", "0", "-1", "-3", "2", "1", "-2", "0", "-3", "-3", "6", "-2", "-4", "-1", "0", "-1", "-3", "-2", "-3", "0", "-3", "1", "-1", "-5"},
                {"M", "-1", "-2", "-2", "-4", "-2", "0", "-2", "-3", "-1", "2", "3", "-2", "7", "0", "-3", "-2", "-1", "-1", "0", "1", "-3", "2", "-1", "-1", "-5"},
                {"F", "-3", "-3", "-4", "-5", "-2", "-4", "-3", "-4", "-1", "0", "1", "-4", "0", "8", "-4", "-3", "-2", "1", "4", "-1", "-4", "1", "-4", "-1", "-5"},
                {"P", "-1", "-3", "-2", "-1", "-4", "-1", "-1", "-2", "-2", "-3", "-4", "-1", "-3", "-4", "10", "-1", "-1", "-4", "-3", "-3", "-2", "-3", "-1", "-1", "-5"},
                {"S", "1", "-1", "1", "0", "-1", "0", "-1", "0", "-1", "-3", "-3", "0", "-2", "-3", "-1", "5", "2", "-4", "-2", "-2", "0", "-3", "0", "-1", "-5"},
                {"T", "0", "-1", "0", "-1", "-1", "-1", "-1", "-2", "-2", "-1", "-1", "-1", "-1", "-2", "-1", "2", "5", "-3", "-2", "0", "0", "-1", "-1", "-1", "-5"},
                {"W", "-3", "-3", "-4", "-5", "-5", "-1", "-3", "-3", "-3", "-3", "-2", "-3", "-1", "1", "-4", "-4", "-3", "15", "2", "-3", "-5", "-2", "-2", "-1", "-5"},
                {"Y", "-2", "-1", "-2", "-3", "-3", "-1", "-2", "-3", "2", "-1", "-1", "-2", "0", "4", "-3", "-2", "-2", "2", "8", "-1", "-3", "-1", "-2", "-1", "-5"},
                {"V", "0", "-3", "-3", "-4", "-1", "-3", "-3", "-4", "-4", "4", "1", "-3", "1", "-1", "-3", "-2", "0", "-3", "-1", "5", "-3", "2", "-3", "-1", "-5"},
                {"B", "-2", "-1", "5", "6", "-3", "0", "1", "-1", "0", "-4", "-4", "0", "-3", "-4", "-2", "0", "0", "-5", "-3", "-3", "6", "-4", "1", "-1", "-5"},
                {"J", "-2", "-3", "-4", "-4", "-2", "-3", "-3", "-4", "-3", "4", "4", "-3", "2", "1", "-3", "-3", "-1", "-2", "-1", "2", "-4", "4", "-3", "-1", "-5"},
                {"Z", "-1", "0", "0", "1", "-3", "4", "5", "-2", "0", "-3", "-3", "1", "-1", "-4", "-1", "0", "-1", "-2", "-2", "-3", "1", "-3", "5", "-1", "-5"},
                {"X", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-5"},
                {"*", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "1"}
        };
        return a;
    }

    /**
     * blosum60
     *
     * @return blosum60
     */
    public static String[][] blosum60Base() {
        String[][] a = {
                {"A", "4", "-1", "-1", "-2", "0", "-1", "-1", "0", "-2", "-1", "-1", "-1", "-1", "-2", "-1", "1", "0", "-3", "-2", "0", "-2", "-1", "0", "-4"},
                {"R", "-1", "5", "0", "-1", "-3", "1", "0", "-2", "0", "-3", "-2", "2", "-1", "-3", "-2", "-1", "-1", "-3", "-2", "-2", "-1", "0", "-1", "-4"},
                {"N", "-1", "0", "6", "1", "-2", "0", "0", "0", "1", "-3", "-3", "0", "-2", "-3", "-2", "1", "0", "-4", "-2", "-3", "3", "0", "-1", "-4"},
                {"D", "-2", "-1", "1", "6", "-3", "0", "2", "-1", "-1", "-3", "-3", "-1", "-3", "-3", "-1", "0", "-1", "-4", "-3", "-3", "4", "1", "-1", "-4"},
                {"C", "0", "-3", "-2", "-3", "9", "-3", "-3", "-2", "-3", "-1", "-1", "-3", "-1", "-2", "-3", "-1", "-1", "-2", "-2", "-1", "-3", "-3", "-2", "-4"},
                {"Q", "-1", "1", "0", "0", "-3", "5", "2", "-2", "1", "-3", "-2", "1", "0", "-3", "-1", "0", "-1", "-2", "-1", "-2", "0", "3", "-1", "-4"},
                {"E", "-1", "0", "0", "2", "-3", "2", "5", "-2", "0", "-3", "-3", "1", "-2", "-3", "-1", "0", "-1", "-3", "-2", "-2", "1", "4", "-1", "-4"},
                {"G", "0", "-2", "0", "-1", "-2", "-2", "-2", "6", "-2", "-3", "-4", "-1", "-2", "-3", "-2", "0", "-2", "-2", "-3", "-3", "-1", "-2", "-1", "-4"},
                {"H", "-2", "0", "1", "-1", "-3", "1", "0", "-2", "7", "-3", "-3", "-1", "-1", "-1", "-2", "-1", "-2", "-2", "2", "-3", "0", "0", "-1", "-4"},
                {"I", "-1", "-3", "-3", "-3", "-1", "-3", "-3", "-3", "-3", "4", "2", "-3", "1", "0", "-3", "-2", "-1", "-2", "-1", "3", "-3", "-3", "-1", "-4"},
                {"L", "-1", "-2", "-3", "-3", "-1", "-2", "-3", "-4", "-3", "2", "4", "-2", "2", "0", "-3", "-2", "-1", "-2", "-1", "1", "-3", "-2", "-1", "-4"},
                {"K", "-1", "2", "0", "-1", "-3", "1", "1", "-1", "-1", "-3", "-2", "4", "-1", "-3", "-1", "0", "-1", "-3", "-2", "-2", "0", "1", "-1", "-4"},
                {"M", "-1", "-1", "-2", "-3", "-1", "0", "-2", "-2", "-1", "1", "2", "-1", "5", "0", "-2", "-1", "-1", "-1", "-1", "1", "-3", "-1", "-1", "-4"},
                {"F", "-2", "-3", "-3", "-3", "-2", "-3", "-3", "-3", "-1", "0", "0", "-3", "0", "6", "-4", "-2", "-2", "1", "3", "-1", "-3", "-3", "-1", "-4"},
                {"P", "-1", "-2", "-2", "-1", "-3", "-1", "-1", "-2", "-2", "-3", "-3", "-1", "-2", "-4", "7", "-1", "-1", "-4", "-3", "-2", "-2", "-1", "-2", "-4"},
                {"S", "1", "-1", "1", "0", "-1", "0", "0", "0", "-1", "-2", "-2", "0", "-1", "-2", "-1", "4", "1", "-3", "-2", "-2", "0", "0", "0", "-4"},
                {"T", "0", "-1", "0", "-1", "-1", "-1", "-1", "-2", "-2", "-1", "-1", "-1", "-1", "-2", "-1", "1", "4", "-2", "-2", "0", "0", "-1", "0", "-4"},
                {"W", "-3", "-3", "-4", "-4", "-2", "-2", "-3", "-2", "-2", "-2", "-2", "-3", "-1", "1", "-4", "-3", "-2", "10", "2", "-3", "-4", "-2", "-2", "-4"},
                {"Y", "-2", "-2", "-2", "-3", "-2", "-1", "-2", "-3", "2", "-1", "-1", "-2", "-1", "3", "-3", "-2", "-2", "2", "6", "-1", "-2", "-2", "-1", "-4"},
                {"V", "0", "-2", "-3", "-3", "-1", "-2", "-2", "-3", "-3", "3", "1", "-2", "1", "-1", "-2", "-2", "0", "-3", "-1", "4", "-3", "-2", "-1", "-4"},
                {"B", "-2", "-1", "3", "4", "-3", "0", "1", "-1", "0", "-3", "-3", "0", "-3", "-3", "-2", "0", "0", "-4", "-2", "-3", "4", "1", "-1", "-4"},
                {"Z", "-1", "0", "0", "1", "-3", "3", "4", "-2", "0", "-3", "-2", "1", "-1", "-3", "-1", "0", "-1", "-2", "-2", "-2", "1", "3", "-1", "-4"},
                {"X", "0", "-1", "-1", "-1", "-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2", "0", "0", "-2", "-1", "-1", "-1", "-1", "-1", "-4"},
                {"*", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "-4", "1"}
        };
        return a;
    }


    /**
     * blosum62
     *
     * @return blosum62
     */
    public static String[][] blosum62Base() {
        String[][] a = {
                {"A", "4", "-1", "-2", "-2", "0", "-1", "-1", "0", "-2", "-1", "-2", "-1", "-1", "-2", "-1", "1", "0", "-3", "-2", "0", "-2", "-1", "-1", "-5"},
                {"R", "-1", "6", "0", "-2", "-4", "1", "0", "-2", "0", "-3", "-2", "2", "-2", "-3", "-2", "-1", "-1", "-3", "-2", "-3", "-1", "0", "-1", "-5"},
                {"N", "-2", "0", "6", "1", "-3", "0", "0", "-1", "1", "-3", "-4", "0", "-2", "-3", "-2", "1", "0", "-4", "-2", "-3", "3", "0", "-1", "-5"},
                {"D", "-2", "-2", "1", "6", "-4", "0", "2", "-1", "-1", "-3", "-4", "-1", "-3", "-4", "-2", "0", "-1", "-5", "-3", "-3", "4", "1", "-1", "-5"},
                {"C", "0", "-4", "-3", "-4", "9", "-3", "-4", "-3", "-3", "-1", "-1", "-3", "-2", "-2", "-3", "-1", "-1", "-2", "-2", "-1", "-3", "-4", "-2", "-5"},
                {"Q", "-1", "1", "0", "0", "-3", "6", "2", "-2", "1", "-3", "-2", "1", "0", "-3", "-1", "0", "-1", "-2", "-2", "-2", "0", "3", "-1", "-5"},
                {"E", "-1", "0", "0", "2", "-4", "2", "5", "-2", "0", "-3", "-3", "1", "-2", "-3", "-1", "0", "-1", "-3", "-2", "-3", "1", "4", "-1", "-5"},
                {"G", "0", "-2", "-1", "-1", "-3", "-2", "-2", "6", "-2", "-4", "-4", "-2", "-3", "-3", "-2", "0", "-2", "-3", "-3", "-3", "-1", "-2", "-2", "-5"},
                {"H", "-2", "0", "1", "-1", "-3", "1", "0", "-2", "8", "-3", "-3", "-1", "-2", "-1", "-2", "-1", "-2", "-2", "2", "-3", "0", "0", "-1", "-5"},
                {"I", "-1", "-3", "-3", "-3", "-1", "-3", "-3", "-4", "-3", "4", "2", "-3", "1", "0", "-3", "-2", "-1", "-2", "-1", "3", "-3", "-3", "-1", "-5"},
                {"L", "-2", "-2", "-4", "-4", "-1", "-2", "-3", "-4", "-3", "2", "4", "-3", "2", "0", "-3", "-3", "-1", "-2", "-1", "1", "-4", "-3", "-1", "-5"},
                {"K", "-1", "2", "0", "-1", "-3", "1", "1", "-2", "-1", "-3", "-3", "5", "-2", "-3", "-1", "0", "-1", "-3", "-2", "-2", "0", "1", "-1", "-5"},
                {"M", "-1", "-2", "-2", "-3", "-2", "0", "-2", "-3", "-2", "1", "2", "-2", "6", "0", "-3", "-2", "-1", "-2", "-1", "1", "-3", "-2", "-1", "-5"},
                {"F", "-2", "-3", "-3", "-4", "-2", "-3", "-3", "-3", "-1", "0", "0", "-3", "0", "6", "-4", "-2", "-2", "1", "3", "-1", "-3", "-3", "-2", "-5"},
                {"P", "-1", "-2", "-2", "-2", "-3", "-1", "-1", "-2", "-2", "-3", "-3", "-1", "-3", "-4", "8", "-1", "-1", "-4", "-3", "-2", "-2", "-1", "-2", "-5"},
                {"S", "1", "-1", "1", "0", "-1", "0", "0", "0", "-1", "-2", "-3", "0", "-2", "-2", "-1", "4", "1", "-3", "-2", "-2", "0", "0", "-1", "-5"},
                {"T", "0", "-1", "0", "-1", "-1", "-1", "-1", "-2", "-2", "-1", "-1", "-1", "-1", "-2", "-1", "1", "5", "-3", "-2", "0", "-1", "-1", "-1", "-5"},
                {"W", "-3", "-3", "-4", "-5", "-2", "-2", "-3", "-3", "-2", "-2", "-2", "-3", "-2", "1", "-4", "-3", "-3", "10", "2", "-3", "-4", "-3", "-2", "-5"},
                {"Y", "-2", "-2", "-2", "-3", "-2", "-2", "-2", "-3", "2", "-1", "-1", "-2", "-1", "3", "-3", "-2", "-2", "2", "7", "-1", "-3", "-2", "-1", "-5"},
                {"V", "0", "-3", "-3", "-3", "-1", "-2", "-3", "-3", "-3", "3", "1", "-2", "1", "-1", "-2", "-2", "0", "-3", "-1", "4", "-3", "-2", "-1", "-5"},
                {"B", "-2", "-1", "3", "4", "-3", "0", "1", "-1", "0", "-3", "-4", "0", "-3", "-3", "-2", "0", "-1", "-4", "-3", "-3", "4", "1", "-1", "-5"},
                {"Z", "-1", "0", "0", "1", "-4", "3", "4", "-2", "0", "-3", "-3", "1", "-2", "-3", "-1", "0", "-1", "-3", "-2", "-2", "1", "4", "-1", "-5"},
                {"X", "-1", "-1", "-1", "-1", "-2", "-1", "-1", "-2", "-1", "-1", "-1", "-1", "-1", "-2", "-2", "-1", "-1", "-2", "-1", "-1", "-1", "-1", "-1", "-5"},
                {"*", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "1"}
        };
        return a;
    }


    /**
     * blosum50BaseV3
     * 与parasail一致，blousum50.h
     *
     * @return blosum50
     */
    public static String[][] blosum50BaseV3() {
        String[][] a = {
                {"A", "5", "-2", "-1", "-2", "-1", "-1", "-1", "0", "-2", "-1", "-2", "-1", "-1", "-3", "-1", "1", "0", "-3", "-2", "0", "-2", "-1", "-1", "-5"},
                {"R", "-2", "7", "-1", "-2", "-4", "1", "0", "-3", "0", "-4", "-3", "3", "-2", "-3", "-3", "-1", "-1", "-3", "-1", "-3", "-1", "0", "-1", "-5"},
                {"N", "-1", "-1", "7", "2", "-2", "0", "0", "0", "1", "-3", "-4", "0", "-2", "-4", "-2", "1", "0", "-4", "-2", "-3", "4", "0", "-1", "-5"},
                {"D", "-2", "-2", "2", "8", "-4", "0", "2", "-1", "-1", "-4", "-4", "-1", "-4", "-5", "-1", "0", "-1", "-5", "-3", "-4", "5", "1", "-1", "-5"},
                {"C", "-1", "-4", "-2", "-4", "13", "-3", "-3", "-3", "-3", "-2", "-2", "-3", "-2", "-2", "-4", "-1", "-1", "-5", "-3", "-1", "-3", "-3", "-2", "-5"},
                {"Q", "-1", "1", "0", "0", "-3", "7", "2", "-2", "1", "-3", "-2", "2", "0", "-4", "-1", "0", "-1", "-1", "-1", "-3", "0", "4", "-1", "-5"},
                {"E", "-1", "0", "0", "2", "-3", "2", "6", "-3", "0", "-4", "-3", "1", "-2", "-3", "-1", "-1", "-1", "-3", "-2", "-3", "1", "5", "-1", "-5"},
                {"G", "0", "-3", "0", "-1", "-3", "-2", "-3", "8", "-2", "-4", "-4", "-2", "-3", "-4", "-2", "0", "-2", "-3", "-3", "-4", "-1", "-2", "-2", "-5"},
                {"H", "-2", "0", "1", "-1", "-3", "1", "0", "-2", "10", "-4", "-3", "0", "-1", "-1", "-2", "-1", "-2", "-3", "2", "-4", "0", "0", "-1", "-5"},
                {"I", "-1", "-4", "-3", "-4", "-2", "-3", "-4", "-4", "-4", "5", "2", "-3", "2", "0", "-3", "-3", "-1", "-3", "-1", "4", "-4", "-3", "-1", "-5"},
                {"L", "-2", "-3", "-4", "-4", "-2", "-2", "-3", "-4", "-3", "2", "5", "-3", "3", "1", "-4", "-3", "-1", "-2", "-1", "1", "-4", "-3", "-1", "-5"},
                {"K", "-1", "3", "0", "-1", "-3", "2", "1", "-2", "0", "-3", "-3", "6", "-2", "-4", "-1", "0", "-1", "-3", "-2", "-3", "0", "1", "-1", "-5"},
                {"M", "-1", "-2", "-2", "-4", "-2", "0", "-2", "-3", "-1", "2", "3", "-2", "7", "0", "-3", "-2", "-1", "-1", "0", "1", "-3", "-1", "-1", "-5"},
                {"F", "-3", "-3", "-4", "-5", "-2", "-4", "-3", "-4", "-1", "0", "1", "-4", "0", "8", "-4", "-3", "-2", "1", "4", "-1", "-4", "-4", "-2", "-5"},
                {"P", "-1", "-3", "-2", "-1", "-4", "-1", "-1", "-2", "-2", "-3", "-4", "-1", "-3", "-4", "10", "-1", "-1", "-4", "-3", "-3", "-2", "-1", "-2", "-5"},
                {"S", "1", "-1", "1", "0", "-1", "0", "-1", "0", "-1", "-3", "-3", "0", "-2", "-3", "-1", "5", "2", "-4", "-2", "-2", "0", "0", "-1", "-5"},
                {"T", "0", "-1", "0", "-1", "-1", "-1", "-1", "-2", "-2", "-1", "-1", "-1", "-1", "-2", "-1", "2", "5", "-3", "-2", "0", "0", "-1", "0", "-5"},
                {"W", "-3", "-3", "-4", "-5", "-5", "-1", "-3", "-3", "-3", "-3", "-2", "-3", "-1", "1", "-4", "-4", "-3", "15", "2", "-3", "-5", "-2", "-3", "-5"},
                {"Y", "-2", "-1", "-2", "-3", "-3", "-1", "-2", "-3", "2", "-1", "-1", "-2", "0", "4", "-3", "-2", "-2", "2", "8", "-1", "-3", "-2", "-1", "-5"},
                {"V", "0", "-3", "-3", "-4", "-1", "-3", "-3", "-4", "-4", "4", "1", "-3", "1", "-1", "-3", "-2", "0", "-3", "-1", "5", "-4", "-3", "-1", "-5"},
                {"B", "-2", "-1", "4", "5", "-3", "0", "1", "-1", "0", "-4", "-4", "0", "-3", "-4", "-2", "0", "0", "-5", "-3", "-4", "5", "2", "-1", "-5"},
                {"Z", "-1", "0", "0", "1", "-3", "4", "5", "-2", "0", "-3", "-3", "1", "-1", "-4", "-1", "0", "-1", "-2", "-2", "-3", "2", "5", "-1", "-5"},
                {"X", "-1", "-1", "-1", "-1", "-2", "-1", "-1", "-2", "-1", "-1", "-1", "-1", "-1", "-2", "-2", "-1", "0", "-3", "-1", "-1", "-1", "-1", "-1", "-5"},
                {"*", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "-5", "1"}
        };
        return a;
    }

    public static int[][] blosum50V1() {
        int[][] a = {
                {5, -2, -1, -2, -1, -3, 0, -2, -1, -2, -1, -2, -1, -1, -5, -1, -1, -2, 1, 0, -5, 0, -3, -1, -2, -1},
                {-2, 6, -3, 6, 1, -4, -1, 0, -4, -4, 0, -4, -3, 5, -5, -2, 0, -1, 0, 0, -5, -3, -5, -1, -3, 1},
                {-1, -3, 13, -4, -3, -2, -3, -3, -2, -2, -3, -2, -2, -2, -5, -4, -3, -4, -1, -1, -5, -1, -5, -1, -3, -3},
                {-2, 6, -4, 8, 2, -5, -1, -1, -4, -4, -1, -4, -4, 2, -5, -1, 0, -2, 0, -1, -5, -4, -5, -1, -3, 1},
                {-1, 1, -3, 2, 6, -3, -3, 0, -4, -3, 1, -3, -2, 0, -5, -1, 2, 0, -1, -1, -5, -3, -3, -1, -2, 5},
                {-3, -4, -2, -5, -3, 8, -4, -1, 0, 1, -4, 1, 0, -4, -5, -4, -4, -3, -3, -2, -5, -1, 1, -1, 4, -4},
                {0, -1, -3, -1, -3, -4, 8, -2, -4, -4, -2, -4, -3, 0, -5, -2, -2, -3, 0, -2, -5, -4, -3, -1, -3, -2},
                {-2, 0, -3, -1, 0, -1, -2, 10, -4, -3, 0, -3, -1, 1, -5, -2, 1, 0, -1, -2, -5, -4, -3, -1, 2, 0},
                {-1, -4, -2, -4, -4, 0, -4, -4, 5, 4, -3, 2, 2, -3, -5, -3, -3, -4, -3, -1, -5, 4, -3, -1, -1, -3},
                {-2, -4, -2, -4, -3, 1, -4, -3, 4, 4, -3, 4, 2, -4, -5, -3, -3, -3, -3, -1, -5, 2, -2, -1, -1, -3},
                {-1, 0, -3, -1, 1, -4, -2, 0, -3, -3, 6, -3, -2, 0, -5, -1, 2, 3, 0, -1, -5, -3, -3, -1, -2, 1},
                {-2, -4, -2, -4, -3, 1, -4, -3, 2, 4, -3, 5, 3, -4, -5, -4, -2, -3, -3, -1, -5, 1, -2, -1, -1, -3},
                {1, -3, -2, -4, -2, 0, -3, -1, 2, 2, -2, 3, 7, -2, -5, -3, 0, -2, -2, -1, -5, 1, -1, -1, 0, -1},
                {-1, 5, -2, 2, 0, -4, 0, 1, -3, -4, 0, -4, -2, 7, -5, -2, 0, -1, 1, 0, -5, -3, -4, -1, -2, 0},
                {-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, 1, -5, -5, -5, -5, -5, -5, -5, -5, -5, 0, -5},
                {-1, -2, -4, -1, -1, -4, -2, -2, -3, -3, -1, -4, -3, -2, -5, 10, -1, -3, -1, -1, -5, -3, -4, -1, -3, -1},
                {-1, 0, -3, 0, 2, -4, -2, 1, -3, -3, 2, -2, 0, 0, -5, -1, 7, 1, 0, -1, -5, -3, -1, -1, -1, 4},
                {-2, -1, -4, -2, 0, -3, -3, 0, -4, -3, 3, -3, -2, -1, -5, -3, 1, 7, -1, -1, -5, -3, -3, -1, -1, 0},
                {1, 0, -1, 0, -1, -3, 0, -1, -3, -3, 0, -3, -2, 1, -5, -1, 0, -1, 5, 2, -5, -2, -4, -1, -2, 0},
                {0, 0, -1, -1, -1, -2, -2, -2, -1, -1, -1, -1, -1, 0, -5, -1, -1, -1, 2, 5, 1, 0, -3, -1, -2, -1},
                {-2, -1, -2, -3, -3, -1, -2, -3, 2, -1, -1, -2, 0, 4, 0, -3, -2, -2, 2, 8, 0, -1, -3, -1, -2, -1},
                {0, -3, -1, -4, -3, -1, -4, -4, 4, 2, -3, 1, 1, -3, -5, -3, -3, -3, -2, 0, -5, 5, -3, -1, -1, -3},
                {-3, -5, -5, -5, -3, 1, -3, -3, -3, -2, -3, -2, -1, -4, -5, -4, -1, -3, -4, -3, -5, -3, 15, -1, 2, -2},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -5, -1, -1, -1, -1, -1, -5, -1, -1, -1, -1, -1},
                {-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, 8, -5},
                {-1, 1, -3, 1, 5, -4, -2, 0, -3, -3, 1, -3, -1, 0, -5, -1, 4, 0, 0, -1, -5, -3, -2, -1, -2, 5}
        };
        return a;
    }

    public static int[][] blosum50V2() {
        int[][] a = {
                {5, -2, -1, -2, -1, -1, -1, 0, -2, -1, -2, -1, -1, -3, -1, 1, 0, -3, -2, 0, -2, -2, -1, -1, -5},
                {-2, 7, -1, -2, -4, 1, 0, -3, 0, -4, -3, 3, -2, -3, -3, -1, -1, -3, -1, -3, -1, -3, 0, -1, -5},
                {-1, -1, 7, 2, -2, 0, 0, 0, 1, -3, -4, 0, -2, -4, -2, 1, 0, -4, -2, -3, 5, -4, 0, -1, -5},
                {-2, -2, 2, 8, -4, 0, 2, -1, -1, -4, -4, -1, -4, -5, -1, 0, -1, -5, -3, -4, 6, -4, 1, -1, -5},
                {-1, -4, -2, -4, 13, -3, -3, -3, -3, -2, -2, -3, -2, -2, -4, -1, -1, -5, -3, -1, -3, -2, -3, -1, -5},
                {-1, 1, 0, 0, -3, 7, 2, -2, 1, -3, -2, 2, 0, -4, -1, 0, -1, -1, -1, -3, 0, -3, 4, -1, -5},
                {-1, 0, 0, 2, -3, 2, 6, -3, 0, -4, -3, 1, -2, -3, -1, -1, -1, -3, -2, -3, 1, -3, 5, -1, -5},
                {0, -3, 0, -1, -3, -2, -3, 8, -2, -4, -4, -2, -3, -4, -2, 0, -2, -3, -3, -4, -1, -4, -2, -1, -5},
                {-2, 0, 1, -1, -3, 1, 0, -2, 10, -4, -3, 0, -1, -1, -2, -1, -2, -3, 2, -4, 0, -3, 0, -1, -5},
                {-1, -4, -3, -4, -2, -3, -4, -4, -4, 5, 2, -3, 2, 0, -3, -3, -1, -3, -1, 4, -4, 4, -3, -1, -5},
                {-2, -3, -4, -4, -2, -2, -3, -4, -3, 2, 5, -3, 3, 1, -4, -3, -1, -2, -1, 1, -4, 4, -3, -1, -5},
                {-1, 3, 0, -1, -3, 2, 1, -2, 0, -3, -3, 6, -2, -4, -1, 0, -1, -3, -2, -3, 0, -3, 1, -1, -5},
                {-1, -2, -2, -4, -2, 0, -2, -3, -1, 2, 3, -2, 7, 0, -3, -2, -1, -1, 0, 1, -3, 2, -1, -1, -5},
                {-3, -3, -4, -5, -2, -4, -3, -4, -1, 0, 1, -4, 0, 8, -4, -3, -2, 1, 4, -1, -4, 1, -4, -1, -5},
                {-1, -3, -2, -1, -4, -1, -1, -2, -2, -3, -4, -1, -3, -4, 10, -1, -1, -4, -3, -3, -2, -3, -1, -1, -5},
                {1, -1, 1, 0, -1, 0, -1, 0, -1, -3, -3, 0, -2, -3, -1, 5, 2, -4, -2, -2, 0, -3, 0, -1, -5},
                {0, -1, 0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1, 2, 5, -3, -2, 0, 0, -1, -1, -1, -5},
                {-3, -3, -4, -5, -5, -1, -3, -3, -3, -3, -2, -3, -1, 1, -4, -4, -3, 15, 2, -3, -5, -2, -2, -1, -5},
                {-2, -1, -2, -3, -3, -1, -2, -3, 2, -1, -1, -2, 0, 4, -3, -2, -2, 2, 8, -1, -3, -1, -2, -1, -5},
                {0, -3, -3, -4, -1, -3, -3, -4, -4, 4, 1, -3, 1, -1, -3, -2, 0, -3, -1, 5, -3, 2, -3, -1, -5},
                {-2, -1, 5, 6, -3, 0, 1, -1, 0, -4, -4, 0, -3, -4, -2, 0, 0, -5, -3, -3, 6, -4, 1, -1, -5},
                {-2, -3, -4, -4, -2, -3, -3, -4, -3, 4, 4, -3, 2, 1, -3, -3, -1, -2, -1, 2, -4, 4, -3, -1, -5},
                {-1, 0, 0, 1, -3, 4, 5, -2, 0, -3, -3, 1, -1, -4, -1, 0, -1, -2, -2, -3, 1, -3, 5, -1, -5},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -5},
                {-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, 1}
        };
        return a;
    }
}

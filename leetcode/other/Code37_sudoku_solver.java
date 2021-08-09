package leetcode.other;

import java.util.Stack;

public class Code37_sudoku_solver {

    int m_count_in_a_round = -1;
    int m_count_all = 0;

    int cal[][] = new int[9][9];

//    int cal[][]={
//        {0,0,0,9,0,0,1,0,2},
//        {4,0,0,1,0,6,0,0,0},
//        {0,8,0,0,2,0,0,0,5},
//        {6,3,0,0,0,0,5,0,0},
//        {0,0,4,5,0,7,9,0,0},
//        {0,0,5,0,0,0,0,3,1},
//        {9,0,0,0,6,0,0,1,0},
//        {0,0,0,8,0,9,0,0,7},
//        {7,0,6,0,0,2,0,0,0},
//    };

    class BlockLimit {
        int min_j;
        int max_j;
        int min_i;
        int max_i;
    }

    BlockLimit get_block_limit(int block_id) {
        BlockLimit bl = new BlockLimit();
        switch (block_id) {
            case 1:
                bl.min_j = 0;
                bl.max_j = 2;
                bl.min_i = 0;
                bl.max_i = 2;
                break;
            case 2:
                bl.min_j = 0;
                bl.max_j = 2;
                bl.min_i = 3;
                bl.max_i = 5;
                break;
            case 3:
                bl.min_j = 0;
                bl.max_j = 2;
                bl.min_i = 6;
                bl.max_i = 8;
                break;
            case 4:
                bl.min_j = 3;
                bl.max_j = 5;
                bl.min_i = 0;
                bl.max_i = 2;
                break;
            case 5:
                bl.min_j = 3;
                bl.max_j = 5;
                bl.min_i = 3;
                bl.max_i = 5;
                break;
            case 6:
                bl.min_j = 3;
                bl.max_j = 5;
                bl.min_i = 6;
                bl.max_i = 8;
                break;
            case 7:
                bl.min_j = 6;
                bl.max_j = 8;
                bl.min_i = 0;
                bl.max_i = 2;
                break;
            case 8:
                bl.min_j = 6;
                bl.max_j = 8;
                bl.min_i = 3;
                bl.max_i = 5;
                break;
            case 9:
                bl.min_j = 6;
                bl.max_j = 8;
                bl.min_i = 6;
                bl.max_i = 8;
                break;
            default:
                break;
        }
        return bl;
    }

    int findblock(int j, int i) {
        if ((j >= 0) && (j <= 2)) {
            if ((i >= 0) && (i <= 2)) return 1;
            if ((i >= 3) && (i <= 5)) return 2;
            if ((i >= 6) && (i <= 8)) return 3;
        }

        if ((j >= 3) && (j <= 5)) {
            if ((i >= 0) && (i <= 2)) return 4;
            if ((i >= 3) && (i <= 5)) return 5;
            if ((i >= 6) && (i <= 8)) return 6;
        }

        if ((j >= 6) && (j <= 8)) {
            if ((i >= 0) && (i <= 2)) return 7;
            if ((i >= 3) && (i <= 5)) return 8;
            if ((i >= 6) && (i <= 8)) return 9;
        }

        return 0;
    }


    int findAnswer_unique(int jj, int ii) {
        int i, j;
        int count = 0;
        int ret = 0;
        int temp[] = new int[10];

        for (i = 0; i < 9; i++) {
            if (cal[jj][i] != 0) {
                temp[cal[jj][i]] = 1;
            }
        }

        for (i = 0; i < 9; i++) {
            if (cal[i][ii] != 0) {
                temp[cal[i][ii]] = 1;
            }
        }

        int block_id = findblock(jj, ii);
        BlockLimit bl = get_block_limit(block_id);
        for (j = bl.min_j; j <= bl.max_j; j++) {
            for (i = bl.min_i; i <= bl.max_i; i++) {
                if (cal[j][i] != 0) {
                    temp[cal[j][i]] = 1;
                }
            }
        }

        for (i = 1; i < 10; i++) {
            if (temp[i] == 0) {
                count++;
                ret = i;
            }
        }

        if (count > 1) {
            ret = 0;
        }

        return ret;
    }

    int is_possible_in_row(int jj, int ii, int value) {
        int i, j;

        for (j = 0; j < 9; j++) {
            if (cal[j][ii] == value)
                return 0;
        }

        int block_id = findblock(jj, ii);
        BlockLimit bl = get_block_limit(block_id);
        for (j = bl.min_j; j <= bl.max_j; j++) {
            for (i = bl.min_i; i <= bl.max_i; i++) {
                if (cal[j][i] == value)
                    return 0;
            }
        }

        return 1;
    }

    void findAnswer_in_row(int jj) {
        int i;
        int count = 0;
        int ret = 0;
        int temp[] = new int[10];
        int blank[] = new int[10];

        for (i = 0; i < 9; i++) {
            if (cal[jj][i] != 0) {
                temp[cal[jj][i]] = 1;
            }
        }

        for (i = 1; i < 10; i++) {
            if (temp[i] == 0) {
                blank[++count] = i;
            }
        }

        blank[0] = count;

        int jjj = 0, iii = 0;
        for (int k = 1; k < blank[0] + 1; k++) {
            count = 0;
            for (i = 0; i < 9; i++) {
                if (cal[jj][i] == 0) {
                    if (is_possible_in_row(jj, i, blank[k]) > 0) {
                        count++;
                        jjj = jj;
                        iii = i;
                    }
                }
            }
            if (count == 1) {
                cal[jjj][iii] = blank[k];
//                printf("a[%d][%d] is %d\n", jjj + 1, iii + 1, blank[k]);
                m_count_in_a_round++;
            }
        }
    }

    int is_possible_in_column(int jj, int ii, int value) {
        int i, j;

        for (i = 0; i < 9; i++) {
            if (cal[jj][i] == value)
                return 0;
        }

        int block_id = findblock(jj, ii);
        BlockLimit bl = get_block_limit(block_id);
        for (j = bl.min_j; j <= bl.max_j; j++) {
            for (i = bl.min_i; i <= bl.max_i; i++) {
                if (cal[j][i] == value)
                    return 0;
            }
        }

        return 1;
    }


    void findAnswer_in_column(int ii) {
        int i, j;
        int count = 0;
        int ret = 0;
        int temp[] = new int[10];
        int blank[] = new int[10];

        for (j = 0; j < 9; j++) {
            if (cal[j][ii] != 0) {
                temp[cal[j][ii]] = 1;
            }
        }

        for (i = 1; i < 10; i++) {
            if (temp[i] == 0) {
                blank[++count] = i;
            }
        }

        blank[0] = count;

        int jjj = 0, iii = 0;
        for (int k = 1; k < blank[0] + 1; k++) {
            count = 0;
            for (j = 0; j < 9; j++) {
                if (cal[j][ii] == 0) {
                    if (is_possible_in_column(j, ii, blank[k]) > 0) {
                        count++;
                        jjj = j;
                        iii = ii;
                    }
                }
            }
            if (count == 1) {
                cal[jjj][iii] = blank[k];
//                printf("a[%d][%d] is %d\n", jjj + 1, iii + 1, blank[k]);
                m_count_in_a_round++;
            }
        }
    }

    int is_possible_in_block(int jj, int ii, int value) {
        int i, j;
        for (i = 0; i < 9; i++) {
            if (cal[jj][i] == value)
                return 0;
        }

        for (j = 0; j < 9; j++) {
            if (cal[j][ii] == value)
                return 0;
        }

        return 1;
    }

    void findAnswer_in_block(int block_id) {
        int i, j;
        int count = 0;
        int temp[] = new int[10];
        int blank[] = new int[10];

        BlockLimit bl = get_block_limit(block_id);
        for (j = bl.min_j; j <= bl.max_j; j++) {
            for (i = bl.min_i; i <= bl.max_i; i++) {
                if (cal[j][i] > 0) {
                    temp[cal[j][i]] = 1;
                }
            }
        }

        for (i = 1; i < 10; i++) {
            if (temp[i] == 0) {
                blank[++count] = i;
            }
        }
        blank[0] = count;

        int jjj = 0, iii = 0;
        for (int k = 1; k < blank[0] + 1; k++) {
            count = 0;
            for (j = bl.min_j; j <= bl.max_j; j++) {
                for (i = bl.min_i; i <= bl.max_i; i++) {
                    if (cal[j][i] == 0) {
                        if (is_possible_in_block(j, i, blank[k]) > 0) {
                            count++;
                            jjj = j;
                            iii = i;
                        }
                    }
                }
            }
            if (count == 1) {
                cal[jjj][iii] = blank[k];
//                printf("a[%d][%d] is %d\n", jjj + 1, iii + 1, blank[k]);
                m_count_in_a_round++;
            }
        }
    }

    int this_num_is_not_in_this_block(int block_id, int num) {
        int i, j;
        BlockLimit bl = get_block_limit(block_id);
        for (j = bl.min_j; j <= bl.max_j; j++) {
            for (i = bl.min_i; i <= bl.max_i; i++) {
                if (cal[j][i] == num) {
                    return 0;
                }
            }
        }
        return 1;
    }

    void findAnswer_with_specified_num(int num) {
        int i, j;
        int ii = 0, jj = 0;
        int block_id;
        int count = 0;

        for (block_id = 1; block_id <= 9; block_id++) {
            if (this_num_is_not_in_this_block(block_id, num) > 0) {
                BlockLimit bl = get_block_limit(block_id);
                for (j = bl.min_j; j <= bl.max_j; j++) {
                    for (i = bl.min_i; i <= bl.max_i; i++) {
                        if (cal[j][i] == 0) {
                            if (is_possible_in_block(j, i, num) > 0) {
                                count++;
                                jj = j;
                                ii = i;
                            }
                        }
                    }
                }
                if (count == 1) {
                    cal[jj][ii] = num;
//                    printf("a[%d][%d] is %d\n",jj+1,ii+1,num);
                    m_count_in_a_round++;
                }
            }
        }
    }

    void findAnswer() {
        int ret = 0;
        int i, j;

//        printf("findAnswer_unique:\n");
        for (j = 0; j < 9; j++) {
            for (i = 0; i < 9; i++) {
                if (cal[j][i] == 0) {
                    int answer = findAnswer_unique(j, i);
                    if (answer > 0) {
                        cal[j][i] = answer;
//                        printf("a[%d][%d] is %d\n", j + 1, i + 1, answer);
                        m_count_in_a_round++;
                    }
                }
            }
        }

//        System.out.println("\nfindAnswer_in_row:\n");
        for (j = 0; j < 9; j++)
            findAnswer_in_row(j);

//        System.out.println("\nfindAnswer_in_column:\n");
        for (i = 0; i < 9; i++)
            findAnswer_in_column(i);

//        System.out.println("\nfindAnswer_in_block:\n");
        for (i = 1; i < 10; i++)
            findAnswer_in_block(i);

//        printf("\nfindAnswer_with_specified_num:\n");
        for (i = 1; i < 10; i++)
            findAnswer_with_specified_num(i);
    }

    boolean weAreDone() {
        int count = 0;

        m_count_in_a_round = -1;
        while (m_count_in_a_round != 0) {
            m_count_in_a_round = 0;
            findAnswer();
//            printf("*****round %d we find %d answer*****\n\n",++count,m_count_in_a_round);
            m_count_all += m_count_in_a_round;
        }

        if (notFinish())
            return false;
        else
            return true;
    }

    int getPossibleNum(int jj, int ii, int[] possibleArray) {
        int i, j;
        int count = 0;
        int temp[] = new int[10];

        //Í¬Ò»ÐÐ
        for (i = 0; i < 9; i++) {
            if (cal[jj][i] > 0) {
                temp[cal[jj][i]] = 1;
            }
        }

        //Í¬Ò»ÁÐ
        for (i = 0; i < 9; i++) {
            if (cal[i][ii] > 0) {
                temp[cal[i][ii]] = 1;
            }
        }

        //Í¬Ò»·½Õó
        int block_id = findblock(jj, ii);
        BlockLimit bl = get_block_limit(block_id);
        for (j = bl.min_j; j <= bl.max_j; j++) {
            for (i = bl.min_i; i <= bl.max_i; i++) {
                if (cal[j][i] > 0) {
                    temp[cal[j][i]] = 1;
                }
            }
        }

        for (i = 1; i < 10; i++) {
            if (temp[i] == 0) {
                possibleArray[count] = i;
                count++;
            }
        }

        return count;
    }

    class assume_data {
        int i;
        int j;
        int possible_num;
        int assume_id;
        int possibleArray[] = new int[9];
        int dat[][] = new int[9][9];
    }

    ;

    boolean isNotLegal(int jj, int ii, int value) {
        int i, j;

        // 同一行是否有相同的元素
        for (i = 0; i < 9; i++) {
            if (i == ii)
                continue;

            if (cal[jj][i] == value)
                return true;
        }

        // 同一列是否有相同的元素
        for (j = 0; j < 9; j++) {
            if (j == jj)
                continue;

            if (cal[j][ii] == value)
                return true;
        }

        // 同一块是否有相同的元素
        int block_id = findblock(jj, ii);
        BlockLimit bl = get_block_limit(block_id);
        for (j = bl.min_j; j <= bl.max_j; j++) {
            for (i = bl.min_i; i <= bl.max_i; i++) {
                if (i == ii && j == jj)
                    continue;

                if (cal[j][i] == value)
                    return true;
            }
        }

        return false;
    }

    boolean allIsLegal() {
        int i, j;
        int PossibleNum;
        int possibleArray[] = new int[9];

        for (j = 0; j < 9; j++) {
            for (i = 0; i < 9; i++) {
                if (cal[j][i] == 0) {
                    PossibleNum = getPossibleNum(j, i, possibleArray);
                    if (PossibleNum == 0) {
                        //there is an error
                        return false;
                    }
                } else if (isNotLegal(j, i, cal[j][i]))
                    return false;
            }
        }

        return true;
    }

    boolean done_flag = false;
    Stack<assume_data> s = new Stack<>();

    void setAll(int[][] src, int[][] dst) {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                dst[j][i] = src[j][i];
            }
        }
    }

    void recover() {
//        printAll();
        //there is an error, we shoule pop the stack!
        System.out.println("-----------------there is an error, we shoule pop the stack!\n");
        assume_data redo_dat = s.pop();
//        memcpy(cal,redo_dat.dat,sizeof(cal));
//        for (int j=0;j<9;j++) {
//            for (int i = 0; i < 9; i++) {
//                cal[j][i] = redo_dat.dat[j][i];
//            }
//        }
        setAll(redo_dat.dat, cal);
//        System.arraycopy(redo_dat.dat, 0, cal, 0, 81);

//        if (!s.empty())
//            s.pop();
    }

    void assume_run() {
        int i, j, k;
        int PossibleNum;
        int possibleArray[] = new int[9];
        int minPossibleArray[] = new int[9];
        int minPossibleNum = 10;
        int minPossible_I = 0, minPossible_J = 0;

        if (done_flag)
            return;

        System.out.println("\n_+_+_+_+_+ assume _+_+_+_+_+\n\n");

        for (j = 0; j < 9; j++) {
            for (i = 0; i < 9; i++) {
                if (cal[j][i] == 0) {
                    PossibleNum = getPossibleNum(j, i, possibleArray);
                    if (PossibleNum < minPossibleNum) {
                        minPossibleNum = PossibleNum;
//                        memcpy(minPossibleArray,possibleArray,sizeof(possibleArray));
                        System.arraycopy(possibleArray, 0, minPossibleArray, 0, 9);
                        minPossible_I = i;
                        minPossible_J = j;
//#if 0
//                        printf("a[%d][%d] PossibleNum is %d\n",j+1,i+1,PossibleNum);
//                        for (k=0;k<PossibleNum;k++)
//                        {
//                            printf("PossibleNum %d is %d ",k,possibleArray[k]);
//                        }
//                        printf("\n");
//#endif
                    }
                }
            }
        }

//        printf("a[%d][%d] minPossibleNum is %d\n",minPossible_J+1,minPossible_I+1,minPossibleNum);
//        for (k=0;k<minPossibleNum;k++)
//        {
//            printf("PossibleNum %d is %d\n",k+1,minPossibleArray[k]);
//        }
//        printf("\n");

        for (k = 0; k < minPossibleNum; k++) {
//            printf(" now we assume a[%d][%d] is %d\n",minPossible_J+1,minPossible_I+1,minPossibleArray[k]);
            assume_data dat = new assume_data();
            dat.i = minPossible_I;
            dat.j = minPossible_J;
            dat.possible_num = minPossibleNum;
            dat.assume_id = k;
//            memcpy(dat.possibleArray,minPossibleArray,sizeof(minPossibleArray));
            System.arraycopy(minPossibleArray, 0, dat.possibleArray, 0, 9);
//            memcpy(dat.dat,cal,sizeof(cal));
            setAll(cal, dat.dat);
            s.push(dat);  //back up!

            cal[minPossible_J][minPossible_I] = minPossibleArray[k];

            if (weAreDone()) {
                if (allIsLegal()) {
                    //we are really done!
                    done_flag = true;
                    return;
                } else {
                    recover();
                    continue;  //assume another num for cal[minPossible_J][minPossible_I]
                }
            } else {
                if (!allIsLegal()) {
                    recover();
                    continue;  //assume another num for cal[minPossible_J][minPossible_I]
                } else {
//                    printAll();
//                    printf("-----------------a[%d][%d] is %d legal, but it is not completed, continue assume!\n",minPossible_J+1,minPossible_I+1,minPossibleArray[k]);
                    assume_run();  //assume again!
                    if (done_flag)
                        return;
                }
            }
        }
    }

    private void init(char[][] board) {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                if (board[j][i] != '.') {
                    cal[j][i] = Integer.valueOf(board[j][i] - '0');
                } else {
                    cal[j][i] = 0;
                }
            }
        }
    }

    private void fill(char[][] board) {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                board[j][i] = (char) (cal[j][i] + '0');
            }
        }
    }


    boolean notFinish() {
        int i, j;

        for (j = 0; j < 9; j++) {
            for (i = 0; i < 9; i++) {
                if (cal[j][i] == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        init(board);

        if (!weAreDone())
            assume_run();

        if (notFinish())
            System.out.println("not complete\n");
        else
            System.out.println("complete\n");

        fill(board);
    }

    public static void main(String[] args) {
        Code37_sudoku_solver c = new Code37_sudoku_solver();
        char[][] board =
        {{'.','.','.','2','.','.','.','6','3'},
            {'3','.','.','.','.','5','4','.','1'},
            {'.','.','1','.','.','3','9','8','.'},
            {'.','.','.','.','.','.','.','9','.'},
            {'.','.','.','5','3','8','.','.','.'},
            {'.','3','.','.','.','.','.','.','.'},
            {'.','2','6','3','.','.','5','.','.'},
            {'5','.','3','7','.','.','.','.','8'},
            {'4','7','.','.','.','1','.','.','.'}};
        c.solveSudoku(board);

        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                System.out.print(board[j][i] + " ");
            }
            System.out.println();
        }
    }
}

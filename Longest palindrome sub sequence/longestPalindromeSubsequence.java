
public class Test{

    public static void main(String[] args) {
        String string = "abbna";//input string
        Palindrome p = new Palindrome(string);
    }
}
class Palindrome {
    String string;
    char[] x = string.toCharArray();
    int[][] L;//matrix to compute the length
    char[] a1;
    char[] b1;
    result[][] c;

    public Pallindrome(String string) {
        this.string = string;
        System.out.println("Input string:"+string);
        L = new int[string.length()][string.length()];
        System.out.println("Length of the longest palindrome:" + check());
        System.out.println("Longest palindrome");
        lcs(string, new StringBuffer(string).reverse().toString());
    }

    int check() {
        /*initialization of the matrix*/
        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < string.length(); j++) {
                if (i == j) {
                    L[i][j] = 1;
                }
            }
        }
        int j = 0;
        for (int i = 0; i < string.length(); i++) {
            for (int s = 0; s < string.length() - i; s++) {
                j = s + i;
                L[s][j] = computecost(s, j);
                L[j][s] = computecost(j, s);

            }
        }
        for (int i = 0; i < string.length(); i++) {
            for (j = 0; j < string.length(); j++) {
                System.out.print(L[i][j] + " ");
            }
            System.out.println(" ");
        }
        return L[0][string.length() - 1];
    }

    int computecost(int i, int j) {
        if (i == j) {         
            return L[i][j];
        } else if (x[i] != x[j]) {
            if (i < j) {
                return max(L[i + 1][j], L[i][j - 1]);
            } else {
                return max(L[i][j + 1], L[i - 1][j]);
            }
        } else {
            if (i < j) {

                return (L[i + 1][j - 1] + 2);
            } else {

                return (L[i - 1][j + 1] + 2);
            }
        }
    }

    int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }
/*This method uses longest common sub sequence to print the pallindrome sub sequence*/
    void lcs(String a, String b) {                          
        a1 = a.toCharArray();
        b1 = b.toCharArray();
        c = new result[a.length() + 1][b.length() + 1];
        int i, j = 0;
        for (i = 0; i <= a.length(); i++) {
            for (j = 0; j <= b.length(); j++) {
                c[i][j] = new result();
                if (i == 0 || j == 0) {
                    c[i][j].v = 0;
                    c[i][j].d = 'h';
                } else if (a1[i - 1] != b1[j - 1]) {
                    c[i][j].v = Math.max(c[i - 1][j].v, c[i][j - 1].v);
                    if (c[i - 1][j].v >= c[i][j - 1].v) {
                        c[i][j].d = 'u';
                    } else {
                        c[i][j].d = 's';
                    }

                } else {
                    c[i][j].v = c[i - 1][j - 1].v + 1;
                    c[i][j].d = 'd';
                }
            }
        }
        print_lcs(i - 1, j - 1);
        System.out.println(" ");
    }

    void print_lcs(int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (c[i][j].d == 'd') {
            print_lcs(i - 1, j - 1);
            System.out.print(a1[i - 1]);
        } else if (c[i][j].d == 'u') {
            print_lcs(i - 1, j);
        } else {
            print_lcs(i, j - 1);
        }
    }
}

class result {
    int v;    //value
    char d;   //direction
}

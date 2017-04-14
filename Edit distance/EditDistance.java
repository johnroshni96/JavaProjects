public class Test {

    public static void main(String[] args) {
        String a = "execution";
        String b = "intention";
        Analyse ana = new Analyse(a, b);
        ana.match();
    }
}

class Analyse {

    String a;
    String b;
    dist[][] D;
    char[] s;

    public Analyse(String a, String b) {
        this.a = a;
        System.out.println("String a:" + a);
        this.b = b;
        System.out.println("String b:" + b);
        D = new dist[b.length() + 1][a.length() + 1];
        s = a.toCharArray();
    }

    void match() {

        //intialization
        D[0][0] = new dist(0, "halt");

        for (int i = 0; i <= b.length(); i++) {
            for (int j = 0; j <= a.length(); j++) {
                if (!(i == 0 && j == 0)) {
                    D[i][0] = new dist(i, "down");
                    D[0][j] = new dist(j, "left");
                }
            }
        }
        //checking
        for (int i = 1; i <= b.length(); i++) {
            for (int j = 1; j <= a.length(); j++) {
                min(D[i - 1][j].value + 1, D[i][j - 1].value + 1, D[i - 1][j - 1].value + check(i - 1, j - 1), i, j);
            }
        }
        for (int i = 0; i <= b.length(); i++) {
            for (int j = 0; j <= a.length(); j++) {
                System.out.print(D[i][j].value + D[i][j].dir + "  ");
            }
            System.out.println("");
        }

        backtrack();
    }

    int check(int i, int j) {

        if (a.charAt(j) == b.charAt(i)) {
            D[i + 1][j + 1] = new dist(0, "");
            return 0;
        } else {
            D[i + 1][j + 1] = new dist(0, "diag");
            return 2;
        }
    }

    int min(int a, int b, int c, int i, int j) {
        if (c <= b && c <= a) {
            D[i][j].value = c;
            return c;

        } else if (a <= b && a <= c) {
            D[i][j].value = a;
            D[i][j].dir = "down";
            return a;

        } else {
            D[i][j] = new dist(b, "left");
            D[i][j].value = b;
            D[i][j].dir = "left";
            return b;
        }
    }

    void backtrack() {
        int i = b.length();
        int j = a.length();
        System.out.println("Backtracing");
        while (!(i == 0 && j == 0)) {
            if (D[i][j].dir.equals("diag")) {
                System.out.println("substitution");
                i = i - 1;
                j = j - 1;
            } else if (D[i][j].dir.equals("")) {
                System.out.println("no change");
                i = i - 1;
                j = j - 1;
            } else if (D[i][j].dir.equals("down")) {
                System.out.println("deletion");
                i = i - 1;
            } else if (D[i][j].dir.equals("left")) {
                System.out.println("insertion");
                j = j - 1;
            } else {
                break;
            }
        }

    }
}

class dist {

    int value;
    String dir;

    public dist(int value, String dir) {
        this.value = value;
        this.dir = dir;
    }

}

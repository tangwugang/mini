/**
 * Created by twg on 17/2/14.
 */
public class test {
    public static void main(String[] args) {
        String t = "rou-key";

        char[] chars = t.toCharArray();
        char[] result = new char[chars.length -1]; // not completely accurate but good guess
        int currPos = 0;
        boolean upperCaseNext = false;
        for (char c : chars) {
            if (c == '-') {
                upperCaseNext = true;
            }
            else if (upperCaseNext) {
                result[currPos++] = Character.toUpperCase(c);
                upperCaseNext = false;
            }
            else {
                result[currPos++] = c;
            }
        }

        System.out.println("new String(result) = " + new String(result,0,currPos));
    }
}

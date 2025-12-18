public class Encrption {
    public static void main(String[] args){
        String message = "hloimrjel a az";
        char[] arr = message.toCharArray();
        int n = arr.length;

        char[] decryptedArr = new char[n];

        for (int i = 0; i < n / 2; i++) {
            decryptedArr[2 * i] = arr[i];
            decryptedArr[2 * i + 1] = arr[i + n / 2];
        }

        String result = new String(decryptedArr);

        if (result.endsWith("z")) {
            result = result.substring(0, result.length() - 1);
        }

      System.out.println(result);
    }
}

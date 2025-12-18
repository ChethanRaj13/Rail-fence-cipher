import java.net.*;

public class Client {

    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 4000;

        try {
            DatagramSocket socket = new DatagramSocket();

            byte[] initData = "connect".getBytes();
            DatagramPacket initPacket =
                    new DatagramPacket(
                            initData,
                            initData.length,
                            InetAddress.getByName(serverAddress),
                            serverPort
                    );
            socket.send(initPacket);

            System.out.println("Connected to server.");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(receivePacket);

                String encryptedMessage = new String(
                        receivePacket.getData(),
                        0,
                        receivePacket.getLength()
                );

                String decryptedMessage = decryption(encryptedMessage);
                System.out.println("Server says: " + decryptedMessage);

                if (decryptedMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Client stopped.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decryption(String message) {
        if (message.length() % 2 != 0) {
            return message;
        }

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

        return result;
    }
}

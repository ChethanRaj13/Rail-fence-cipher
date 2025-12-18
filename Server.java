import java.net.*;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        int port = 4000;

        try {
            DatagramSocket socket = new DatagramSocket(port);
            Scanner scanner = new Scanner(System.in);

            System.out.println("UDP Server started. Waiting for client...");

            byte[] buffer = new byte[1024];
            DatagramPacket receivePacket =
                    new DatagramPacket(buffer, buffer.length);
            socket.receive(receivePacket);

            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            System.out.println("Client connected.");

            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                String encryptedMessage = encryption(message);

                byte[] sendData = encryptedMessage.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(
                                sendData,
                                sendData.length,
                                clientAddress,
                                clientPort
                        );

                socket.send(sendPacket);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            scanner.close();
            System.out.println("Server stopped.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryption(String message) {
        if (message.length() % 2 != 0) {
            message = message + "z";
        }

        char[] arr = message.toCharArray();
        int n = arr.length;

        char[] upperHalf = new char[n / 2];
        char[] lowerHalf = new char[n / 2];

        for (int i = 0; i < n / 2; i++) {
            upperHalf[i] = arr[2 * i];
            lowerHalf[i] = arr[2 * i + 1];
        }

        return new String(upperHalf) + new String(lowerHalf);
    }
}

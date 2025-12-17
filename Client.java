import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 4000;

        try {
            DatagramSocket socket = new DatagramSocket();

            // Send initial packet to server
            byte[] initData = "connect".getBytes();
            DatagramPacket initPacket =
                    new DatagramPacket(initData, initData.length,
                            InetAddress.getByName(serverAddress), serverPort);
            socket.send(initPacket);

            System.out.println("Connected to server.");

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(receivePacket);

                String message = new String(
                        receivePacket.getData(), 0,
                        receivePacket.getLength()
                );

                System.out.println("Server says: " + message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Client stopped.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

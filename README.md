# UDP Client–Server Encryption Project

This project demonstrates a simple UDP-based client–server communication system in Java with a custom encryption and decryption algorithm.

The server encrypts messages before sending them, and the client decrypts them upon receiving. The goal is to show how basic networking and data transformation can work together.

---

## Features

- UDP client–server communication using `DatagramSocket.`
- Custom symmetric encryption and decryption logic
- Handles even and odd-length messages
- Graceful termination using the `exit` command
- Simple console-based interaction

---

## How the Encryption Works

1. If the message length is odd, a padding character `z` is added.
2. Characters at even indices are stored in the first half.
3. Characters at odd indices are stored in the second half.
4. The encrypted message is formed by concatenating both halves.

### Decryption

- The encrypted message is split into two halves.
- Characters are interleaved to reconstruct the original message.
- The padding `z` is removed if present.

---
# Project Structure
.
├── Server.java
├── Client.java
├── Encryption.java
└── README.md
---

## How to Run

### 1. Compile the files
```bash
javac Server.java Client.java
```

2. Start the server
```bash
java Server
```
3. Start the client (in a new terminal)
```bash
java Client
```
---
## Usage

Type messages in the server terminal.
The client receives and decrypts them.
Type exit on the server to stop both the server and the client.

---
## Example

Server input
```bash
hello iam raj
```

Encrypted message sent
```bash
hloimrjel a az
```

Client output
```bash
hello iam raj
```

---

## Notes

Spaces are treated as normal characters during encryption.
This project is for educational purposes and not meant for secure communication.

---
## License

This project is open-source and free to use for learning and experimentation.

# todo backend

1. definire una lista di endpoint che servono al frontend

## Authentication Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/auth/login` | Authenticate any user | Public |
| POST | `/api/auth/register/client` | Register as client | Public |
| POST | `/api/auth/register/professional` | Register as professional | Public |

## Client Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/clients/profile` | Get current client profile | Client |
| PUT | `/api/clients/profile` | Update current client profile | Client |
| PUT | `/api/clients/profile/image` | Upload/update profile image | Client |
| GET | `/api/clients/{id}` | Get client by ID | Admin |
| GET | `/api/clients` | Get all clients | Admin |
| DELETE | `/api/clients/{id}` | Delete client | Admin |

## Professional Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/professionals` | Get all professionals | Public/Client |
| GET | `/api/professionals/{id}` | Get professional details | Public/Client |
| GET | `/api/professionals/search` | Search professionals by criteria | Public/Client |
| GET | `/api/professionals/profile` | Get current professional profile | Professional |
| PUT | `/api/professionals/profile` | Update professional profile | Professional |
| PUT | `/api/professionals/profile/image` | Upload/update profile image | Professional |
| POST | `/api/professionals/availability` | Add availability slots | Professional |
| GET | `/api/professionals/availability` | Get own availability | Professional |
| DELETE | `/api/professionals/availability/{id}` | Remove availability slot | Professional |

## Meeting Request Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| POST | `/api/requests` | Create meeting request | Client |
| GET | `/api/requests/sent` | Get requests sent by client | Client |
| GET | `/api/requests/received` | Get requests received by professional | Professional |
| GET | `/api/requests/{id}` | Get specific request details | Client/Professional |
| PUT | `/api/requests/{id}/accept` | Accept request | Professional |
| PUT | `/api/requests/{id}/reject` | Reject request | Professional |
| PUT | `/api/requests/{id}/cancel` | Cancel request | Client |

## Admin Endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/admin/clients` | Get all clients | Admin |
| GET | `/api/admin/professionals` | Get all professionals | Admin |
| GET | `/api/admin/requests` | Get all requests | Admin |
| DELETE | `/api/admin/users/{id}` | Delete any user | Admin |
| GET | `/api/admin/stats` | Get system statistics | Admin |

1. nascondere chiave per generazione, file .env configurare spring per leggere file .env
2. area chat? mi servono i socket

## create new entity Chat

'''
@Entity
@Data
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column
    private LocalDateTime lastActivity;
}
'''

## create new entity chatMessage

'''
@Entity
@Data
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String encryptedContent;
    
    @Column(nullable = false)
    private Long senderId;
    
    @Column(nullable = false)
    private String senderType;  // "CLIENT" or "PROFESSIONAL"
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @Enumerated(EnumType.STRING)
    private MessageStatus status = MessageStatus.SENT;
}
enum MessageStatus {
    SENT, DELIVERED, READ
}
'''

## new entity for storing public keys

'''
@Entity
@Data
@Table(name = "user_encryption_keys")
public class UserEncryptionKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private String userType;  // "CLIENT" or "PROFESSIONAL"
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String publicKey;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
'''

## chat endpoints

| Method | Endpoint | Description | Access |
|--------|----------|-------------|--------|
| GET | `/api/chats` | Get all user chats | Client/Professional |
| GET | `/api/chats/{chatId}` | Get specific chat details | Client/Professional |
| GET | `/api/chats/{chatId}/messages` | Get encrypted messages | Client/Professional |
| POST | `/api/chats` | Create a new chat | Client |
| PUT | `/api/chats/{chatId}/read` | Mark messages as read | Client/Professional |
| POST | `/api/keys` | Register public key | Client/Professional |
| GET | `/api/keys/{userId}` | Get user's public key | Client/Professional |

## web socket configuration

'''
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-chat").withSockJS();
    }
}
'''

## web socket destinations

| Destination | Purpose | Direction |
|--------|----------|-------------|--------|
| /app/chat.sendMessage/{chatId} | Send encrypted message | Client → Server |
| /user/queue/chat.messages | Receive encrypted messages | Server → Client |
| /user/queue/chat.typing | Typing indicator | Bidirectional |
| /user/queue/chat.status | Message status updates | Server → Client |

## client side

'''
async function generateKeyPair() {
  const keyPair = await window.crypto.subtle.generateKey(
    {
      name: "RSA-OAEP",
      modulusLength: 2048,
      publicExponent: new Uint8Array([1, 0, 1]),
      hash: "SHA-256",
    },
    true,
    ["encrypt", "decrypt"]
  );
  
  // Export public key for sharing
  const publicKeyExported = await window.crypto.subtle.exportKey(
    "spki", 
    keyPair.publicKey
  );
  
  // Convert to base64 for storage/transmission
  const publicKeyBase64 = btoa(
    String.fromCharCode(...new Uint8Array(publicKeyExported))
  );
  
  // Store private key securely in localStorage or IndexedDB
  // Warning: for production, consider more secure storage options
  
  return { keyPair, publicKeyBase64 };
}
'''

## criptazione

'''
async function encryptMessage(publicKeyBase64, message) {
  // Import recipient's public key
  const publicKeyData = Uint8Array.from(
    atob(publicKeyBase64), c => c.charCodeAt(0)
  );
  
  const publicKey = await window.crypto.subtle.importKey(
    "spki",
    publicKeyData,
    { name: "RSA-OAEP", hash: "SHA-256" },
    false,
    ["encrypt"]
  );
  
  // Encrypt the message
  const encoder = new TextEncoder();
  const data = encoder.encode(message);
  const encryptedData = await window.crypto.subtle.encrypt(
    { name: "RSA-OAEP" },
    publicKey,
    data
  );
  
  // Convert to base64 for transmission
  return btoa(String.fromCharCode(...new Uint8Array(encryptedData)));
}
'''

## decriptazione

'''
async function decryptMessage(privateKey, encryptedBase64) {
  // Convert base64 to array buffer
  const encryptedData = Uint8Array.from(
    atob(encryptedBase64), c => c.charCodeAt(0)
  );
  
  // Decrypt using private key
  const decryptedData = await window.crypto.subtle.decrypt(
    { name: "RSA-OAEP" },
    privateKey,
    encryptedData
  );
  
  // Convert to text
  const decoder = new TextDecoder();
  return decoder.decode(decryptedData);
}
'''

## alcune considerazioni sulle immagini del profilo

1. non dovrebbero essere pesanti percio fare una resize prima di inviarle al server
2. forse è meglio spostare la logica di criptazione e decriptazione lato client

## considerazioni sulle istant chat

1. i messaggi dovrebbero avere un size massimo lato client, non mi posso accorgere dei tagli dal lato backend?

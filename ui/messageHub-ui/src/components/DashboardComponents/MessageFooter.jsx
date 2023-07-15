// import React, { useEffect } from "react";
// import axios from "axios";
// import { useState, useRef} from "react";
// import SockJS from "sockjs-client";
// import { Stomp } from "@stomp/stompjs";

// function MessageFooter({ senderId, receiverId, flagToConnect, setMessageResponse}) {
//   const [messageBody, setMessageBody] = useState("");
//   const inputRef = useRef(null);
//   const stompClientRef = useRef(null);
//   const handleInputChange = (e) => {
//     setMessageBody(e.target.value);
//   };
//   useEffect(() => {
//     if (!flagToConnect) {
//       connect();
//     }
//   }, [flagToConnect]);
//   const handleKeyDown = (event) => {
//     if (event.key === 'Enter') {
//       event.preventDefault();
//       sendMessage();
//     }
//   };
//   async function sendMessage() {
//       // const messageRequest = {
//       //   senderId,
//       //   receiverId,
//       // };
//       console.log("flag", flagToConnect);
//       console.log("stompClient", stompClient);
//       stompClient.current.send("/app/hello", {}, JSON.stringify(message));
//       //await showMessages(messageRequest);
//       const message = {
//         senderId,
//         receiverId,
//         messageBody,
//       };
//       try {
//         await axios.post("/messagehub/rest/messages/send", message).then(() => {
//           setMessageBody("");
//           inputRef.current.focus();
//         });

//     } catch (error) {
//       console.error(error);
//     }
//   }

//   function connect() {
//     const socket = new SockJS("/ws");
//     const stompClient = Stomp.over(socket);
//     stompClient.connect({}, function (frame) {
//       console.log("Connected: " + frame);
//       stompClient.subscribe("/topic/chats", function (chatMessage) {
//         console.log(chatMessage);
//         setMessageResponse(prevMessageResponse => [
//           ...prevMessageResponse, JSON.parse(chatMessage.body)]
//         );
//       });
//     });
//     stompClientRef.current = stompClient;
//   }

//   return (
//     <>
//       <input
//         type="text"
//         className="input-box"
//         onChange={handleInputChange}
//         placeholder="Type your message..."
//         value={messageBody}
//         onKeyDown={handleKeyDown}
//         ref={inputRef}
//       />
//       <button className="send-button" onClick={() => sendMessage()} >
//         Send
//       </button>
//     </>
//   );
// }

// export default MessageFooter;

import React, { useState, useRef, useEffect } from "react";
import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";

function MessageFooter({
  senderId,
  receiverId,
  flagToConnect,
  setMessageResponse,
}) {
  const [messageBody, setMessageBody] = useState("");
  const inputRef = useRef(null);
  const stompClientRef= useRef(null);

  useEffect(() => {
    if (!flagToConnect) {
      connect();
    }
  }, [flagToConnect]);

  const handleInputChange = (e) => {
    setMessageBody(e.target.value);
  };

  const handleKeyDown = (event) => {
    if (event.key === "Enter") {
      event.preventDefault();
      sendMessage();
    }
  };

  const sendMessage = () => {
    const message = {
      senderId,
      receiverId,
      messageBody,
    };
    stompClientRef.current.send("/app/send", {}, JSON.stringify(message));
    setMessageBody("");
    inputRef.current.focus();
  };

  const connect = () => {
    const socket = new SockJS("/ws");
    const stompClient = Stomp.over(socket);

    stompClient.connect({}, (frame) => {
      console.log("Connected: " + frame);
      stompClient.subscribe("/topic/chats", (chatMessage) => {
        var receivedMessage = JSON.parse(chatMessage.body);
        var messageObject = receivedMessage.body;
        setMessageResponse((prevMessageResponse) => [
          ...prevMessageResponse,
          messageObject
        ]);
      });
    });

    stompClientRef.current = stompClient;
  };

  return (
    <>
      <input
        type="text"
        className="input-box"
        onChange={handleInputChange}
        placeholder="Type your message..."
        value={messageBody}
        onKeyDown={handleKeyDown}
        ref={inputRef}
      />
      <button className="send-button" onClick={sendMessage}>
        Send
      </button>
    </>
  );
}

export default MessageFooter;

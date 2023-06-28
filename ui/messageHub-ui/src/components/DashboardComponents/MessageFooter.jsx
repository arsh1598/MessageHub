import React from "react";
import axios from "axios";
import { useState, useRef} from "react";

function MessageFooter({ senderId, receiverId, showMessages }) {
  const [messageBody, setMessageBody] = useState("");
  const inputRef = useRef(null);
  const handleInputChange = (e) => {
    setMessageBody(e.target.value);
  };
  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      event.preventDefault();
      sendMessage();
    }
  };
  async function sendMessage() {
    const message = {
      senderId,
      receiverId,
      messageBody,
    };
    try {
      await axios.post("/messagehub/rest/messages/send", message).then(() => {
        setMessageBody("");
        inputRef.current.focus();
      });
      const messageRequest = {
        senderId,
        receiverId,
      };
      await showMessages(messageRequest);

    } catch (error) {
      console.error(error);
    }
  }

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
      <button className="send-button" onClick={() => sendMessage()} >
        Send
      </button>
    </>
  );
}

export default MessageFooter;

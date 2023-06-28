import React from "react";

const AddContact = ({ contact, setContact }) => {
  
  const handleInputChange = (e) => {
    setContact({ ...contact, phone: e.target.value });
  };

  return (
    <form >
      <div className="form-group">
        <label>Phone</label>
        <input
          type="text"
          name="phone"
          pattern="[0-9]{10}"
          title="Enter 10 digit phone number"
          className="form-control"
          placeholder="Phone"
          value={contact.phone}
          onChange={handleInputChange}
          required
        />
      </div>
    </form>
  );
};

export default AddContact;

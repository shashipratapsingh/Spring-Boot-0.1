export class Employee {
  id : number;
  firstName : string;
  lastName : string ;
  email : string;


  constructor(id : number,firstName : string,lastName : string,email : string) {
    this.id = id;                // assigning a value to the 'id' property in the constructor
    this.firstName = firstName; // assigning a value to the 'firstName' property in the constructor
    this.lastName = lastName;  // assigning a value to the 'lastName' property in the constructor
    this.email = email;       // assigning a value to the 'email' property in the constructor
  }
}

import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { validateHorizontalPosition, validateVerticalPosition } from '@angular/cdk/overlay';



@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit{
  constructor(private userService:UserService,private snack:MatSnackBar){}

public user={
  username:'',
  password:'',
  firstName:'',
  lastName:'',
  email:'',
  phone:'',

};


  ngOnInit(): void {}
  formSubmit(){
    console.log(this.user)
    if(this.user.username=='' || this.user.username==null)
    {
     // alert('User is required !!')
     this.snack.open("Username is required !!","ok",{
      duration:3000,

     });
     return;
    }

    //addUser: userservice

    this.userService.addUser(this.user).subscribe(
      (data)=>{
          //sucess
          console.log(data)
         alert("successfull Registration")
       },
      (error)=>{
        //error
        console.log(error);
        // alert("Something went wrong")
        this.snack.open("Something went wrong","ok",{
          duration:3000,
          });
      }
    )
  }
}

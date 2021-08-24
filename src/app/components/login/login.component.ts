import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  msg:any;
  name:any;
 userCredendials={
   username: '',
   password: ''
 }
  constructor(private userService: UserService, private router: Router) { }
  data: any;
  ngOnInit(): void {

  }
  ngSubmit(){
    if(this.userCredendials.username!='' && this.userCredendials.password!='' && this.userCredendials.username!=null && this.userCredendials.password!=null){
      this.userService.generateToken(this.userCredendials)
      .subscribe(
        (res:any)=>{
          console.log(res.token);
          this.userService.loginUser(res.token);
          //this.router.navigate(['/community'],{ queryParams: { id: this.userCredendials.username }})
          window.location.href="/community?id="+this.userCredendials.username;
        },
        error=>{
          console.log(error);
          this.msg="User doesn't exist!!!";
        }
      )
    }
    else{
      this.msg="Please enter details";
    }
  }
  reload(){
    location.reload();
  }

}

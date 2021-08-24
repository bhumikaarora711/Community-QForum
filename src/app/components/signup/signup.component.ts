import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  user: User = new User();
  constructor(private service: UserService, private router: Router) { }

  ngOnInit(): void {
  }
  data: any;
  msg: any;

  public signUp() {
    console.log(this.user.firstname)
    if (this.user.email == null || this.user.password == null || this.user.firstname == null || this.user.lastname == null || this.user.email == '' || this.user.password == '' || this.user.firstname == '' || this.user.lastname == '') {
      this.msg = "*Please enter details";
    }
    else {
      this.msg = "*Account already exist with this mail id!";
    }
    this.service.signUp(this.user)
      .subscribe(res => {
        this.data = res;
        this.router.navigate(['/login'])
      },
        error => {
          console.log("error occured");
        })
  }


}

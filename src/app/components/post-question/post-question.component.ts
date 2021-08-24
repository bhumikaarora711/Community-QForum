import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Question } from 'src/app/models/question';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.css']
})
export class PostQuestionComponent implements OnInit {
  data: any = [];
  params: any;
  msg: any;
  break = true;
  users: any;
  question: Question = new Question();
  category: Category = new Category();
  categories: any;
  postCat = false;
  constructor(private userService: UserService, private _router: Router, private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.router.queryParams.subscribe(
      params => { this.username(params), console.log(params.id) },
    );

    this.userService.getCategory()
      .subscribe(
        res => { this.categories = res; console.log(res) },
        error => { console.log(error) }
      )

    this.userService.getUsers()
      .subscribe(
        users => { console.log(users); this.users = users; }
      )

  }
  username(params: any) {
    this.params = params
  }

  postQue() {
    this.users.forEach((element: any) => {
      if (element.email == this.params.id) {
        console.log(element.firstname + " " + element.lastname);
        this.question.author = element.firstname + " " + element.lastname;
      }
    });
    console.log(this.question)
    if (this.question.category == '' || this.question.category == null) {
      this.question.category = "technology";
    }
    this.categories.forEach((element: any) => {
      if (element.name == this.question.category) {
        this.question.categoryCode = element.categoryCode;
      }
    });
    this.userService.postQuestion(this.question)
      .subscribe(res => {
        this.data = res;
        this._router.navigate(['/community'], { queryParams: { id: this.params.id } });
      },
        error => {
          console.log("error occured");
          if (this.question.title == null || this.question.body == null || this.question.category == null) {
            this.msg = "*Please enter details";
          }
          else {
            this.msg = "*Something went wrong!!! Try again later.";
          }
        })
  }

  showCategory() {
    if (this.postCat == false)
      this.postCat = true;
    else
      this.postCat = false;
  }
  postCategory() {
    this.userService.postCategory(this.category)
      .subscribe(
        data => { console.log(data), location.reload() },
        error => { console.log(error) }
      )
    this.postCat = false;
  }

}

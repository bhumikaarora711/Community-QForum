import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http: HttpClient) {}

  baseUrl="http://localhost:8080";
  // User related services

  generateToken(userCredendials: any) {
    return this._http.post(`${this.baseUrl}/token`, userCredendials);
  }

  loginUser(token: any) {
    localStorage.setItem("token", token);
    return true;
  }

  isLoggedIn() {
    let token = localStorage.getItem("token");
    if (token == undefined || token === '' || token == null) {
      return false;
    }
    else {
      return true;
    }
  }
  logout() {
    localStorage.removeItem("token");
    return true;
  }

  getToken() {
    return localStorage.getItem("token");
  }


  signUp(user: User) {
    return this._http.post(`${this.baseUrl}/signup`, user, { responseType: 'text' as 'json' })
  }

  getUsers() {
    return this._http.get(`${this.baseUrl}/getusers`);
  }

  getUserCount() {
    return this._http.get(`${this.baseUrl}/getuserscount`);
  }

  //Questions related services

  getQue() {
    return this._http.get(`${this.baseUrl}/getque`);
  }

  postQuestion(question: any) {
    return this._http.post(`${this.baseUrl}/postque`, question, { responseType: 'text' as 'json' })
  }

  getQueById(id: any) {
    console.log(id);
    return this._http.get(`${this.baseUrl}/getquebyid/${id}`, { responseType: 'text' as 'json' });
  }

  findQueByTitle(title: any) {
    return this._http.get(`${this.baseUrl}/searchque?title=${title}`);
  }

  closedQue(id: any) {
    return this._http.put(`${this.baseUrl}/closedque/${id}`, { responseType: 'text' as 'json' });
  }

  getQuestionCount() {
    return this._http.get(`${this.baseUrl}/getquecount`);
  }

  //Answers related services

  getAns() {
    return this._http.get(`${this.baseUrl}/getans`);
  }

  postAns(answer: any) {
    return this._http.post(`${this.baseUrl}/postans`, answer, { responseType: 'text' as 'json' });
  }

  markAnsCorrect(id: any) {
    return this._http.put(`${this.baseUrl}/markans/${id}`, { responseType: 'text' as 'json' });
  }

  //Category related services

  postCategory(category: any) {
    return this._http.post(`${this.baseUrl}/postcategory`, category, { responseType: 'text' as 'json' });
  }

  getCategory() {
    return this._http.get(`${this.baseUrl}/getcategory`);
  }

  postComment(comment: any) {
    return this._http.post(`${this.baseUrl}/postcomment`, comment, { responseType: 'text' as 'json' });
  }


}

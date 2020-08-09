import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Cookie} from "ng2-cookies";
import {Observable} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";

@Injectable()
export class AppService {
  public clientId = 'newClient';
  public redirectUri = 'http://localhost:8089/';
  private router: Router;

  constructor(
    private _http: HttpClient,private _snackBar:MatSnackBar) {
  }


  retrieveToken(username,password){
    let params = new URLSearchParams();
    params.append('grant_type','authorization_code');
    params.append('client_id', this.clientId);
    params.append('client_secret', 'newClientSecret');
    params.append('redirect_uri', this.redirectUri);
    // params.append("username","admin")
    // params.append("password","password");
    // params.append("email","yomefisseha18@gmail.com");
    // console.log("params",params);

    let headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'});
    this._http.post<any>('http://localhost:8080/authenticate', {
      "password":password,
      "username":username,
      "email":"ymengit2u@outlook.com"
    })
      .subscribe(
        data => this.saveToken(data),
        err => alert("unable to login!z")
      );

    // this._http.post('http://localhost:8080/authenticate', params.toString(), { headers: headers })
    //   .subscribe(
    //     data => this.saveToken(data),
    //     err => alert('Invalid Credentials')
    //   );
  }
  saveToken(token) {
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
    Cookie.set("id_token", token.id_token, expireDate);
    this.router.navigate(['/']);
    this._snackBar.open("Successfully logged in", '×', { panelClass: [status], verticalPosition: 'top', duration: 3000 });

  }

  getResource(resourceUrl){
    // var headers = new HttpHeaders({
    //   'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
    //   'Authorization': 'Bearer ' + Cookie.get('access_token')
    // });
    // return this._http.get(resourceUrl, {headers: headers})
    //   .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  checkCredentials() {
    return Cookie.check('access_token');
  }

  logout() {
    let token = Cookie.get('id_token');
    Cookie.delete('access_token');
    Cookie.delete('id_token');
    this.router.navigate(['/']);
    this._snackBar.open("logged out successfully", '×', { panelClass: [status], verticalPosition: 'top', duration: 3000 });

  }
}

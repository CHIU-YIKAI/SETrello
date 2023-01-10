import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import { CreateTrelloService } from './create-trello.service';

@Component({
  selector: 'app-add-trello',
  templateUrl: './add-trello.component.html',
  styleUrls: ['./add-trello.component.css']
})
export class AddTrelloComponent implements OnInit {
  projectImportMsg = '';
  InputGitRepoUrl = '';
  badGitImportMsg = '';
  badSonarImportMsg = '';
  emptyProjectNameMsg = '';

  datas: any;
  NameofProject = '';
  DesciptionOfProject= '';
  UserID = '';
  IDofProject:'';
  ProjectOverviewpageurl = "choose-project";

  InputSonarHost = '';
  InputSonarProjectKey = '';
  InputSonarToken = '';

  isGitUrlValid: boolean;
  isSonarUrlValid: boolean;

  constructor(private router: Router, private createtrelloservice: CreateTrelloService ,private activerouter:ActivatedRoute ) {

   }
  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.UserID = window.sessionStorage.getItem('UserID');
    this.isGitUrlValid = false;
    this.isSonarUrlValid = false;
  }

  CreateProjectCheckRepoUrlAndSonarUrl(){
    this.isGitUrlValid = false;
    if (this.NameofProject == '') {
      this.emptyProjectNameMsg = "Project Name不得為空";
    }
    else {
      this.emptyProjectNameMsg = '';
      this.CheckGitHubRepoUrlValid();
    }
  }

  CheckGitHubRepoUrlValid() {
    this.badGitImportMsg = '';
    console.log(this.InputGitRepoUrl);
    if (this.InputGitRepoUrl != '') {
      const GitRepoUrlData = {
        githubUrl:undefined
      };
      GitRepoUrlData.githubUrl  = this.InputGitRepoUrl;
      const data = JSON.stringify(GitRepoUrlData);
    }
    else {
      this.InputGitRepoUrl = '';
      this.badGitImportMsg = "GitHub RepoUrl不得為空";
      this.CheckSonarUrlValid();
    }
  }

  CheckSonarUrlValid(){
    this.badSonarImportMsg = '';
    if (this.InputSonarHost != '' && this.InputSonarProjectKey != '' && this.InputSonarToken != '') {
      const SonarUrlData = {
        sonarHost:undefined,
        sonarProjectKey:undefined,
        sonarToken:undefined
      };
      SonarUrlData.sonarHost  = this.InputSonarHost;
      SonarUrlData.sonarProjectKey  = this.InputSonarProjectKey;
      SonarUrlData.sonarToken  = this.InputSonarToken;

      const data = JSON.stringify(SonarUrlData);
      console.log(data);

    }
    else {
      this.ResetSonarInput();
      this.badSonarImportMsg = "Sonar Input不得為空";
    }
  }

  ResetSonarInput() {
    this.InputSonarHost = '';
    this.InputSonarProjectKey = '';
    this.InputSonarToken = '';
  }

  CreateProject() {
    const CreateUserProjectData = {
      userId:undefined,
      projectName:undefined,
      projectDescription:undefined,
      githubUrl:undefined,
      sonarHost:undefined,
      sonarProjectKey:undefined,
      sonarToken:undefined
    };
    CreateUserProjectData.userId  =  this.UserID.toString();
    CreateUserProjectData.projectName  =  this.NameofProject.toString();
    CreateUserProjectData.projectDescription = this.DesciptionOfProject.toString();
    CreateUserProjectData.githubUrl  =  this.InputGitRepoUrl.toString();
    CreateUserProjectData.sonarHost  = this.InputSonarHost.toString();
    CreateUserProjectData.sonarProjectKey  = this.InputSonarProjectKey.toString();
    CreateUserProjectData.sonarToken  = this.InputSonarToken.toString();

    this.InputSonarHost = '';
    this.InputSonarProjectKey = '';
    this.InputSonarToken = '';

    const data = JSON.stringify(CreateUserProjectData);

  }
}

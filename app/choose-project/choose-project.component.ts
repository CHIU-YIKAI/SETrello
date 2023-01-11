import { Component, OnInit } from '@angular/core';
import {Router,ActivatedRoute} from '@angular/router';
import {GetProjectInfoService} from './get-project-info.service';
import {GetTrelloInfoService} from './get-trello-info.service';
import {DeleteProjectService} from './delete-project.service';
import {GetRepoInfoOfChosenProjectService} from "../choose-repository/get-repo-info-of-chosen-project.service";
import {DeleteTrelloBoardProjectService} from "./delete-TrelloBoardproject.service";

@Component({
  selector: 'app-choose-project',
  templateUrl: './choose-project.component.html',
  styleUrls: ['./choose-project.component.css']
})

export class ChooseProjectComponent implements OnInit {
  projectNames = new Array();
  projectIntroduction = new Array();
  projectRepoNumbers = new Array();
  ProjectStartTime = new Array();
  datas: any;
  trellodatas : any;
  responsedata: any;
  item:any;
  totalProject:any;
  UserID = '';

  BoardID = '';
  ChosenProjectID = '';

  ProjectID = '';
  repo_datas: any;
  owner = new Array();
  repoNames = new Array();
  totalData = new Array();

  githubUrl = new Array();
  githubUrlDatas: any;

  constructor(private router: Router, private getrepoinfoofchosenproject: GetRepoInfoOfChosenProjectService, private getProjectInfoService: GetProjectInfoService, private getTrelloInfoService: GetTrelloInfoService, private delProjectService: DeleteProjectService, private activerouter:ActivatedRoute, private delTrelloBoardProjectProjectService: DeleteTrelloBoardProjectService, private GetTrelloInfoService: GetTrelloInfoService) {}

  ngOnInit(): void {
    window.scrollTo(0, 0);
    this.UserID = window.sessionStorage.getItem('UserID');
    this.getTotalProjectInfo();
    this.getTrelloProject();
  }

  getTotalProjectInfo() {
    for (let i = 0; i<this.githubUrl.length; i++) {
      this.githubUrl.pop();
    }
    const UserProjectData = {
      userId:undefined,
    };
    UserProjectData.userId  = this.UserID;
    const data = JSON.stringify(UserProjectData);
    this.getProjectInfoService.getUserProjectData(data).subscribe(
      request => {
        this.datas = request;
        console.log(this.datas);

        for (let item of this.datas) {
          const UserData = {
            projectId:undefined,
          };
          UserData.projectId  = item.projectId;
          const data = JSON.stringify(UserData);
          this.getrepoinfoofchosenproject.getRepoDataOfProject(data).subscribe(
            request => {
              this.githubUrlDatas = request;
              for(let item of this.githubUrlDatas) {
                console.log(item);
                this.githubUrl.push("https://github.com/" + item.ownerName + "/" + item.repoName);
              }
            }
          );
        }
      }
    );
  }

  getTrelloProject(){
    //TODO
    const UserTrelloData = {
      userId:undefined,
    };
    UserTrelloData.userId  = this.UserID;
    const data = JSON.stringify(UserTrelloData);
    this.getTrelloInfoService.getUserTrelloData(data).subscribe(
      request => {
        this.trellodatas = request;
        for (let item of this.trellodatas) {
          // this.trellodatas = "trello boards"
          console.log(item);
        }
      }
    );
  }

  choose_repo(projectid, projectName, projectDescription) {
    console.log(projectid);
    sessionStorage.setItem('ChosenProjectID', projectid);
    sessionStorage.setItem('projectName', projectName);
    sessionStorage.setItem('projectIntroduction', projectDescription);
    console.log("chosenid:",projectid)

    this.ProjectID = window.sessionStorage.getItem('ChosenProjectID');

    const UserRepoData = {
      projectId:undefined,
    };
    UserRepoData.projectId  = this.ProjectID;
    const data = JSON.stringify(UserRepoData);
    this.getrepoinfoofchosenproject.getRepoDataOfProject(data).subscribe(
      request => {
        this.repo_datas = request;
        console.log(this.repo_datas);
        for(let item of this.repo_datas){
          this.repoNames.push(item.repoName);
          this.owner.push(item.ownerName);

          console.log(this.repoNames);
          console.log(this.owner);

          sessionStorage.setItem('repoName', this.repoNames[0]);
          sessionStorage.setItem('owner', this.owner[0]);
        }
        this.router.navigate(['project-analysis']);
      }
    );
  }
  choose_trello_board(projectid, projectName, projectDescription, boardId) {
    console.log(projectid);
    sessionStorage.setItem('ChosenProjectID', projectid);
    sessionStorage.setItem('projectName', projectName);
    sessionStorage.setItem('projectIntroduction', projectDescription);
    sessionStorage.setItem('boardId', boardId);
    console.log("chosen board:",projectName)

    this.ProjectID = window.sessionStorage.getItem('ChosenProjectID');
    this.BoardID = window.sessionStorage.getItem('boardId');
    const UserBoardData = {
      userId:undefined,
      boardId:undefined,
    };
    UserBoardData.userId  = this.UserID;
    UserBoardData.boardId  = this.BoardID;

    const Boarddata = JSON.stringify(UserBoardData);
    console.log(Boarddata);
    this.GetTrelloInfoService.getUserTrelloDetail(Boarddata).subscribe(
      request => {
        this.repo_datas = request;
        console.log(this.repo_datas);
        // for(let item of this.repo_datas){
        //   this.repoNames.push(item.repoName);
        //   this.owner.push(item.ownerName);
        //
        //   console.log(this.repoNames);
        //   console.log(this.owner);
        //
        // }
        this.router.navigate(['trelloBoard']);
      }
    );
  }


  delete_repo(delId) {
    const deletedprojectId: string = delId.toString();
    console.log("choose to delete id:",deletedprojectId);
    if(confirm("確認要刪除此專案嗎?"))
    {
      const DeleteProject = {
            userId:undefined,
            projectId:undefined
          };
      DeleteProject.userId  = this.UserID;
      DeleteProject.projectId = deletedprojectId;
      const deldata = JSON.stringify(DeleteProject);
      this.delProjectService.deleteChosenProject(DeleteProject).subscribe(
        request => {
        this.responsedata = request;
        console.log(this.responsedata);
          if(this.responsedata.isSuccess == "true"){
            alert("刪除專案成功")
            //this.router.navigate(['choose-project']);
          }
          else{
            alert("刪除專案失敗")
          }

        }
      );
    }
    window.location.reload();
  }
  delete_TrelloBoard(delId) {
    const deletedprojectId: string = delId.toString();
    console.log("choose to delete id:",deletedprojectId);
    if(confirm("確認要刪除此專案嗎?"))
    {
      const DeleteProject = {
        userId:undefined,
        projectId:undefined
      };
      DeleteProject.userId  = this.UserID;
      DeleteProject.projectId = deletedprojectId;
      const deldata = JSON.stringify(DeleteProject);
      this.delTrelloBoardProjectProjectService.deleteChosenProject(DeleteProject).subscribe(
        request => {
          this.responsedata = request;
          console.log(this.responsedata);
          if(this.responsedata.isSuccess == "true"){
            alert("刪除專案成功")
            //this.router.navigate(['choose-project']);
          }
          else{
            alert("刪除專案失敗")
          }

        }
      );
    }
    window.location.reload();
  }

  goToAddProjectPage() {
    this.router.navigate(['createproject']);
  }
  goToAddTrelloPage() {
    this.router.navigate(['addTrello']);
  }

}

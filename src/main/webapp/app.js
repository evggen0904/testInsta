var startFrom = 0;
var maxRows = 24;
var inProgress = false;

var app = angular.module("myApp", ['ngDialog']);
app.controller("PictureCtrl", PictureCtrl);

function PictureCtrl($window, PictureDataSvc, $scope, ngDialog) {
    $scope.pictures =[];
    $scope.currentPicture = {};

    $scope.pictureDialog = function () {
        $scope.popUpDialogContent = 'Здесь будет картинка';
        $scope.showPopupDialog = true;
        // $scope.showPopupDialog = true;
    }

 /*   $scope.clickToOpen = function () {
        ngDialog.open({ template: 'popupTmpl.html', className: 'ngdialog-theme-default' });
    };*/

    $scope.openDefault = function (selectedPicture) {
        $scope.currentPicture = selectedPicture;
        ngDialog.open({
            scope: $scope,
            template: 'firstDialogId',
            closeByDocument: false,
            controller: 'InsideCtrl',
            className: 'ngdialog-theme-default'
        });
    };

    PictureDataSvc.getPictures()
        .then(function success(data) {
            $scope.pictures = createPicturesArray(data, $scope.pictures);
        });

    var scrollPos = 0;
    $window.onscroll = function () {
        scrollPos = document.body.scrollTop || document.documentElement.scrollTop || 0;
        var maxHeight = document.body.scrollHeight;
        var clientHeight = document.body.clientHeight;
        console.log(scrollPos);

        if ((maxHeight - scrollPos) === clientHeight) {
            console.log("end");
            PictureDataSvc.getPictures()
                .then(function (data) {
                    $scope.pictures = createPicturesArray(data, $scope.pictures)
                });
        }
    };
 /*   this.selectContact = function (index) {
        self.selectedContact = this.contacts(index);
    }*/
}

app.service("PictureDataSvc", function ($http) {

    this.getPictures = function(){
        if (inProgress)
            return;

        inProgress = true;
        var config = {
            headers: {
                'startFrom': startFrom,
                'maxRows': maxRows
            }
        };
        var promise1 = $http.get(document.location.href +  "rest/pictures/some", config);
        var promise2 = promise1.then(function success(response) {
                startFrom += maxRows +1;
                inProgress = false;
                return response.data;
            },
            function error(response) {
                console.log("Возникла ошибка");
                console.log("код ответа: " + response.status);
            }
        );

        return promise2;
    }
});

function createPicturesArray(data, picturesArray) {
    var tmpData = data;
    var newArr = [];

    for (var i =0; i< tmpData.length; i++){
        newArr.push(tmpData[i]);
        if (((i+1) % 4 ==0) || i == tmpData.length-1){
            picturesArray.push(newArr);
            newArr = [];
        }
    }
    return picturesArray;
}

/*
app.directive('popUpDialog', function () {
    return{
        restrict: 'EA',
        scope: false,
        // templateUrl: 'popUpDialog.html',
        template: '<div > \
                        <input type="text" value={{popUpDialogContent}} >\
                   </div>',
        controller: function ($scope) {
            $scope.showPopupDialog = true;
            $scope.closePopUpDialog = function() {
                $scope.showPopUpDialog = false;
            }
        }
            
        
    }
});*/

app.controller('InsideCtrl', function ($scope, ngDialog, CommentService) {

    $scope.newComment = '';
    CommentService.getComments($scope.currentPicture.id)
        .then(function (data) {
            $scope.comments = data;
        })
    $scope.addComment = function () {
        if ($scope.newComment ==='')
            return;
        var date = new Date();
        var dataObj = {
            "author_id": 3,
            "comment_date": "2010-10-10",
            "comment": $scope.newComment,
            "picture_id": $scope.currentPicture.id
        };
        CommentService.addComment($scope.currentPicture.id, dataObj)
            .then(function (data) {
                $scope.newComment = '';
                CommentService.getComments($scope.currentPicture.id)
                    .then(function (data) {
                        $scope.comments = data;
                    });
            });
    }
});

app.service("CommentService", function ($http) {

    this.getComments = function(pictureId){

        var promise1 = $http.get(document.location.href +  "rest/pictures/" + pictureId +"/comments");
        var promise2 = promise1.then(function success(response) {
                return response.data;
            },
            function error(response) {
                console.log("Возникла ошибка");
                console.log("код ответа: " + response.status);
            }
        );

        return promise2;
    }

    this.addComment = function (pictureId, comment) {
        var parameter = JSON.stringify(comment);
        var promise1 = $http.post(document.location.href +  "rest/pictures/" + pictureId +"/comments", parameter);
        var promise2 = promise1.then(function success(data, status, headers, config) {
                return data;
            },
            function error(data, status, headers, config) {
                console.log( "failure message: " + JSON.stringify({data: data}));
            }
        );
        return promise2;
    }
});









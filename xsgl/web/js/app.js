 /**
 * Created by silent on 2017-05-28/028.
 */

function httpConfig(cofig, data) {

    return {
        method: cofig.method,
        url: cofig.url,
        data: data
    }
}
//$http(req).then(function(){...}, function(){...});

var app = angular.module("app", ['ngAnimate', 'ui.router', 'angularFileUpload']);

app.run(['$rootScope', function ($rootScope) {




}]);

//路由设置
app.config(["$locationProvider", "$stateProvider", "$urlRouterProvider", function ($locationProvider, $stateProvider, $urlRouterProvider) {
    $locationProvider.hashPrefix('');

    $stateProvider
        .state('stu', {
            url: '/studentList/:pageNo/:keyword',
            views: {
                "list": {
                    templateUrl: 'student_listTemplate.action',
                    controller: 'stuList'
                }

            }
            //指明控制器
        })
        .state('stu.page', {
        url: '/page',
        views: {
            "page":{
                templateUrl: 'pageUi.action',
                controller: 'page'
            }

        }
    })
        .state('stu.add', {
            url: '/add',
            views: {
                "stuAdd": {
                    templateUrl: 'student_addUi.action',
                    controller: 'addController'
                }

            }
        })
        .state('stu.edit', {
            url: '/edit/:studentId',
            views: {
                "stuAdd": {
                    templateUrl: 'student_addUi.action',
                    controller: 'addController'
                }

            }
        })
        .state('stu.upload', {
            url: '/upload',
            views: {
                "stuUpload": {
                    templateUrl: 'uploadUi.action',
                    controller: 'upload'
                }

            }
        })
        .state('user', {
        url: '/user/:pageNo/:keyword',
        views: {
            "list": {
                templateUrl: 'user_listTemplate.action',
                controller: 'userCtrl'
            }

        }
        //指明控制器
    })
        .state('user.add', {
            url: '/add',
            views: {
                "addUser": {
                    templateUrl: 'user_addUi.action',
                    controller: 'addUserCtrl'
                }

            }
            //指明控制器
        })
        .state('user.page', {
            url: '/page',
            views: {
                "page":{
                    templateUrl: 'pageUi.action',
                    controller: 'page'
                }

            }
        })
        .state('user.edit', {
            url: '/edit/:userId',
            views: {
                "addUser": {
                    templateUrl: 'user_addUi.action',
                    controller: 'addUserCtrl'
                }

            }
        }).state('user.upload', {
            url: '/upload',
            views: {
                "userUpload": {
                    templateUrl: 'uploadUi.action',
                    controller: 'upload'
                }

            }
        })
        .state('class', {
        url: '/class',
        views: {
            "list": {
                templateUrl: 'class_listTemplate.action',
                controller: 'classCtrl'
            }

        }
        //指明控制器
    })
        .state('class.add', {
            url: '/add',
            views: {
                "classAdd": {
                    templateUrl: 'class_addUi.action',
                    controller: 'addClassCtrl'
                }

            }
        })
        .state('userInfo', {
            url: '/userInfo',
            views: {
                "list": {
                    templateUrl: 'user_userInfo.action',
                    controller: 'userInfo'
                }
            }
            //指明控制器
        })
        .state('index', {
        url: '/index',
        views: {
            "list": {
                templateUrl: 'class_listTemplate.action',
                controller: 'classCtrl'
            }

        }
        //指明控制器
    })

    //user_listTemplate.action
    $urlRouterProvider.otherwise('/index');     //匹配所有不在上面的路由


}]);

//control之间的数据传递
app.factory("pageData",function(){
    return {};
})




 app.controller('main', function ($scope, $http, $location,$rootScope) {
    $scope.activeTab=1;
    $rootScope.$on('$viewContentLoaded', function (event, current) {

         if($location.$$path.indexOf("/index")>-1){
             $scope.activeTab=1;
         }
        else if( $location.$$path.indexOf("/studen")>-1){
             $scope.activeTab=3;
        }
        else if($location.$$path.indexOf("/class")>-1){
             $scope.activeTab=4;
        }
        else if($location.$$path.indexOf("/userInf")>-1){
             $scope.activeTab=5;
        }else if($location.$$path.indexOf("/user")>-1){
             $scope.activeTab=2;
         }
    });
    
    $scope.logout=function () {

        document.getElementById("logout").click();
    }
     $scope.user={};
     $http({
         method:"post",
         url:'user_user.action'
     }).then(function (res) {
         if(res.data.status==1){

             $scope.user=res.data.user;

         }
     },function (error) {

     });


})







//获取图片对象 闯入 input
function getPhoto(node) {
    var imgURL = window.URL.createObjectURL(node.files[0]);
    return imgURL;
}



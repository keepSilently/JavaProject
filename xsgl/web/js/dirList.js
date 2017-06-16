/**
 * Created by silent on 2017-05-28/028.
 */
var app = angular.module("app", ['ngRoute']);
app.run(['$rootScope', function ($rootScope) {

    $rootScope.$on('$routeChangeStart', function (event, current, pre) {
        console.log(event);
        // console.log(current);
        // console.log(pre);
    });


}]);
app.config(['$routeProvider', "$locationProvider", function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider
        .when('/add', {
            templateUrl: 'component/add.html',
            controller: 'addController'
        })
        .when('/div2', {
            template: '<p>这是div2{{text}}</p>',
            controller: 'div2Controller'
        })
        .when('/div3', {
            template: '<p>这是div3{{text}}</p>',
            controller: 'div3Controller'
        })
        .when('/content/:id/:cateid', {
            template: '<p>这是content{{id}}</p>',
            controller: 'div4Controller'
        })
    // .otherwise({
    //     redirectTo : '/div2'
    // });

}]);

app.controller('addController', function ($scope) {

    $scope.closeWindow = function () {
        //返回上一页
        window.history.go(-1)
    }


});
app.controller('main', function ($scope) {
    //定义数据接收学生列表
    $scope.studentList = [];
    $scope.studentList.push({
        name: "我的151",
        id: "12345615615",
        checked: true,
        bj: "我262",
        zy: "软件技术263",
        gender: "女",
        mobile: "13068519323"
    })
    $scope.studentList.push({
        name: "我的",
        id: "123456",
        checked: false,
        bj: "我",
        zy: "软件技术",
        gender: "女",
        mobile: "13068519323"
    });

    //默认列表不全选
    $scope.check = false;
    //点击全选
    $scope.allCheck = function () {

        if (!$scope.check) {
            $scope.check = true;
        }
        else {
            $scope.check = false;
        }

        angular.forEach($scope.studentList, function (stu) {
            stu.checked = $scope.check;
        })
    }
    //点击选手列表
    $scope.selectStu = function (stu) {
        if (stu.checked) {
            stu.checked = false;
        } else {
            stu.checked = true;
        }
    }
    //删除选择的学生
    $scope.deleteStu = function () {

        angular.forEach($scope.studentList, function (stu) {
            //从数组中找出选择的学生
            if (stu.checked) {
                //获取学生在数组的索引
                var index = $scope.studentList.indexOf(stu);
                if (index > -1) {
                    //通过索引删除学生   1代表从该索引开始删除1个
                    $scope.studentList.splice(index, 1);
                }

            }
        })
    }
});

app.controller('div2Controller', function ($scope) {
    $scope.text = 'div2Controller';
});
app.controller('div3Controller', function ($scope) {
    $scope.text = 'div3Controller';
});

app.controller('div4Controller', ['$scope', '$routeParams', function ($scope, $routeParams) {
    console.log($routeParams);
    $scope.id = $routeParams.num;

    $scope.text = 'div4Controller';
}]);

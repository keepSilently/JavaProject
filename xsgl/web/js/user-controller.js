/**
 * Created by silent on 2017-06-04/004.
 */

app.controller('userCtrl', function ($scope, $http, $state , pageData, $stateParams, $location) {
    console.log($stateParams)
   $scope.pageData=pageData;
    $scope.keyword = $stateParams.keyword;
    $scope.pageData.pageNo=$stateParams.pageNo;


    $scope.userList = [];
    // private String name;
    // private String img;
    // private String password;
    // private String permission;
    // private String userId;
    $scope.userList.push({name: "用户名", password: "密码", permission: true, img: "img/tx.jpg"})
    console.log()

    $scope.add = function () {

    }
    $scope.findUser = function () {

        $state.go("user.page", {pageNo: 1, keyword: $scope.keyword}, {reload: true});
        $scope.pageData.keyword=$scope.keyword;
        // $location.path("/stu/1/"+$scope.keyword+"/page");
        console.log("点击搜索$location：：" + $state.keyword);
    }
    $scope.refresh = function () {
        $scope.sreach();
    }
    $scope.sreach = function () {
        var keyword=$scope.keyword;

        if ($scope.keyword == undefined || $scope.keyword == null) {

            keyword = "";
        }

        var cofig = httpConfig({method: "POST", url: "user_jsonList.action"}, {
            keyword: keyword,
            pageNo: $scope.pageData.pageNo
        })
        $http(cofig).then(function (res) {
            $scope.userList = res.data.pageResult.items;
            $scope.pageData.totalPageCount=res.data.pageResult.totalPageCount;
            $scope.pageData.totalCount=res.data.pageResult.totalCount;
            if (res.data.pageResult.pageNo > 0) {
                $scope.pageData.changePageNo(res.data.pageResult.pageNo)
            }
        }, function () {
            alert("查询失败")

        });

    };
    $scope.sreach();


    //定义数据接收学生列表


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

        angular.forEach($scope.userList, function (user) {
            user.checked = $scope.check;
        })
    }
    //点击列表
    $scope.selectUser = function (user) {
        if (user.checked) {
            user.checked = false;
        } else {
            user.checked = true;
        }
    }
    function getSelectArr() {
        var arr = [];

        angular.forEach($scope.userList, function (user) {
            //从数组中找出选择的学生
            if (user.checked) {
                arr.push(user);
            }
        })
        return arr;

    }


    //删除选择的学生
    $scope.deleteUser = function () {
        var del = getSelectArr();

        angular.forEach($scope.userList, function (user) {
            //从数组中找出选择的学生
            if (user.checked) {
                del.push(user);
            }
        })

        angular.forEach(del, function (user) {
            if (user.checked) {
                var cofig = httpConfig({method: "POST", url: "user_delete.action"}, {selectedRow: user.userId})
                $http(cofig).then(function (res) {
                    //判断服务是否删除成功
                    if (res.data.status == 1) {
                        //selectedRow
                        //服务器删除成功，页面删除
                        var index = $scope.userList.indexOf(user);
                        console.log(index)
                        if (index > -1) {
                            // 通过索引删除学生   1代表从该索引开始删除1个
                            $scope.userList.splice(index, 1);
                        }
                    }


                }, function () {
                    alert("删除失败")

                });

            }
        })
    }

    //导出xml
    $scope.exportExcel = function () {
        var selectad = getSelectArr();
        var href = "user_exportExcel.action";
        
            for (var i = 0; i < selectad.length; i++) {
                if (i == 0) {
                    href += "?selectedRows=" + selectad[i].userId;

                    // =2&selectedRows=4&selectedRows=6
                }
                else {
                    href += "&selectedRows=" + selectad[i].userId;
                }
            }
            var a = document.createElement("a");
            a.href = href;
            console.log(href)
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);





    }
    //编辑学生
    $scope.edit = function () {
        var arr = getSelectArr();
        if (arr.length == 1) {
            //$location.path("/edit/" + arr[0].studentId);
            $state.go("user.edit", {userId: arr[0].userId});
        } else {
            alert("请选择一个学生")
        }


    }

    //修改权限
    $scope.changePermission=function ($event,user) {
        var check=$event.target.checked;
        if(check){
            user.permission="admin";
            //changePermission
        }else {
            user.permission="user";
        }

        var cofig = httpConfig({method: "POST", url: "user_changePermission.action"}, {'user':user})
        $http(cofig).then(function (res) {
            //判断服务是否删除成功
            if (res.data.status == 1) {

            }else {
                if(check==true){
                    user.permission="user";
                }else {
                    user.permission="admin";
                }


            }


        }, function () {
            alert("修改权限失败")
            if(check==true){
                user.permission="user";
            }else {
                user.permission="admin";
            }
        });


    }


});


//添加学生  编辑学生的控制器
app.controller('addUserCtrl', function ($scope, FileUploader, $http, $state, pageData, $stateParams) {


    $scope.user = {};
    $scope.img;

    if ($stateParams.userId == undefined || location.href.lastIndexOf("add") > 0) {

        $scope.title = "添加用户";
        $scope.method = "user_add.action";

    }
    else {
        console.log("addUserCtrl编辑==" + $stateParams)
        $scope.title = "编辑用户";
        var keyword = $stateParams.userId;
        var cofig = httpConfig({method: "POST", url: "user_findUser.action"}, {keyword: keyword})
        $http(cofig).then(function (res) {
            if (res.data.status == 1) {
                console.log(res.data.user)
                $scope.user = res.data.user;
            }
            $scope.method = "user_update.action";
        }, function () {
            alert("查询失败")

        });


    }


    $scope.windown = {};
    $scope.windown.isClose = true;


    $scope.selectClose = function () {

        if ($scope.windown.isClose) {
            $scope.windown.isClose = false;
        } else {
            $scope.windown.isClose = true;
        }
    }


//设置日期组件

    //关闭窗口
    $scope.closeWindow = function () {
        //返回上一页
        window.history.go(-1)
    }
    //显示选择文件的窗口
    $scope.showFile = function () {
        document.getElementById("img").click()
    }
    //监听文件改变
    $scope.fileNameChanged = function (file) {
        document.getElementById("txImg").src = getPhoto(file);

    }
    //上传头像
    $scope.uploader = new FileUploader({
        url: 'user_addImg.action',
        queueLimit: 1,     //文件个数
        removeAfterUpload: true,   //上传后删除文件
        alias: "headImg", //设置表单的名称

    });

    $scope.uploader.filters.push({
        fn: function (item, options) {
            console.log('syncFilter');
            return this.queue.length < 10;
        }
    });

    $scope.clearItems = function () {    //重新选择文件时，清空队列，达到覆盖文件的效果
        $scope.uploader.clearQueue();
    }
    //上传成功
    $scope.uploader.onSuccessItem = function (fileItem, response, status, headers) {

        if (response.status == 1) {
            console.log("response.headImgFileName" + response.headImgFileName)
            $scope.user.img = response.headImgFileName;
            sendData();
        }
        else {
            alert("提交失败")
        }

    };


    //上传失败
    $scope.uploader.onErrorItem = function (fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };


    //点击确定按钮
    $scope.addUser = function () {

        if ($scope.uploader.queue.length == 0) {
            sendData();
        } else {
            $scope.uploader.uploadAll();
            $scope.clearItems;
        }
    }

    //发送学生数据
    function sendData() {
        if($scope.checkbox){
            $scope.user.permission="admin";
            //changePermission
        }else {
            $scope.user.permission="user";
        }

        var config = {
            method: "POST",
            url: $scope.method,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            data: $scope.user,
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    console.log(p + "===" + obj[p]);
                    if (obj[p] != null || obj[p] != undefined) {


                        str.push("user." + encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));

                    }
                }
                return str.join("&");
            }
        };

        $http(config).then(function (res) {
            if (res.data.status == 1 && $scope.windown.isClose) {
                $state.go("user.page", $stateParams, {reload: true})
                // window.history.go(-1);

            }

        }, function () {
            alert("查询失败")

        });

    }

});

app.controller('userInfo', function ($scope ,$http, $state, $stateParams) {
    $scope.user={};
    $scope.closeWindow=function () {
        window.history.go(-1);
    }

    $http({
        method:"post",
        url:'user_user.action'
    }).then(function (res) {
        if(res.data.status==1){
            console.log(res.data.user)
            $scope.user=res.data.user;
        }
    },function (error) {

    });



});
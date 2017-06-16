/**
 * Created by silent on 2017-06-04/004.
 */

app.controller('stuList', function ($scope, $http, $state, pageData, $stateParams, $location) {


    $scope.pageData = pageData;
    $scope.keyword = $stateParams.keyword;
    $scope.pageData.pageNo = $stateParams.pageNo;


    $http({
        method:"post",
        url:'user_user.action'
    }).then(function (res) {
        if(res.data.status==1){

            $scope.permission=res.data.user.permission;
        }
    },function (error) {

    });


    $scope.$watch("pageData.pageNo", function (a) {
        //  $location.path("/stu/"+a+"/"+$scope.keyword+"/page");
        //console.log("页数发生改变111"+a);
        // $scope.sreach();
    }, true);


    $scope.add = function () {

    }
    $scope.find = function () {
        $state.go("stu.page", {pageNo: 1, keyword: $scope.keyword}, {reload: true});
        $scope.pageData.keyword = $scope.keyword;
        // $location.path("/stu/1/"+$scope.keyword+"/page");

    }
    $scope.refresh = function () {
        $scope.sreach();
    }
    $scope.sreach = function () {
        var keyword;

        if ($scope.keyword == undefined || $scope.keyword == null) {

            keyword = "";
        }
        else {
            keyword = $scope.keyword;

        }
        $scope.rekeyword = keyword;
        var cofig = httpConfig({method: "POST", url: "student_jsonList.action"}, {
            keyword: keyword,
            pageNo: $scope.pageData.pageNo
        })
        $http(cofig).then(function (res) {
            $scope.studentList = res.data.pageResult.items;
            $scope.pageData.totalPageCount = res.data.pageResult.totalPageCount;
            $scope.pageData.totalCount = res.data.pageResult.totalCount;
            if (res.data.pageResult.pageNo > 0) {
                $scope.pageData.changePageNo(res.data.pageResult.pageNo)
            }
        }, function () {
            alert("查询失败")

        });


    };
    $scope.sreach();


    //定义数据接收学生列表
    $scope.studentList = [];


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
    //点击列表
    $scope.selectStu = function (stu) {
        if (stu.checked) {
            stu.checked = false;
        } else {
            stu.checked = true;
        }
    }
    function getSelectArr() {
        var arr = [];

        angular.forEach($scope.studentList, function (stu) {
            //从数组中找出选择的学生
            if (stu.checked) {
                arr.push(stu);
            }
        })
        return arr;

    }


    //删除选择的学生
    $scope.deleteStudent = function () {

        var del = getSelectArr();

        angular.forEach($scope.studentList, function (stu) {
            //从数组中找出选择的学生
            if (stu.checked) {
                del.push(stu);
            }
        })

        angular.forEach(del, function (stu) {
            if (stu.checked) {
                var cofig = httpConfig({method: "POST", url: "student_delete.action"}, {selectedRow: stu.studentId})
                $http(cofig).then(function (res) {

                    //判断服务是否删除成功
                    if (res.data.status == 1) {
                        //selectedRow
                        //服务器删除成功，页面删除
                        var index = $scope.studentList.indexOf(stu);

                        if (index > -1) {
                            // 通过索引删除学生   1代表从该索引开始删除1个
                            $scope.studentList.splice(index, 1);
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
        var href = "student_exportExcel.action";

        for (var i = 0; i < selectad.length; i++) {
            if (i == 0) {
                href += "?selectedRows=" + selectad[i].studentId;

                // =2&selectedRows=4&selectedRows=6
            }
            else {
                href += "&selectedRows=" + selectad[i].studentId;
            }
        }
        var a = document.createElement("a");
        a.href = href;

        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);


    }
    //编辑学生
    $scope.edit = function () {
        var arr = getSelectArr();
        if (arr.length == 1) {
            //$location.path("/edit/" + arr[0].studentId);
            $state.go("stu.edit", {studentId: arr[0].studentId});
        } else {
            alert("请选择一个学生")
        }


    }


});


//添加学生  编辑学生的控制器
app.controller('addController', function ($scope, FileUploader, $http, $state, pageData, $stateParams) {


    $scope.student = {};
    $scope.img;

    if ($stateParams.studentId == undefined || location.href.lastIndexOf("add") > 0) {
        $scope.title = "添加学生";
        $scope.method = "student_add.action";
    }
    else {
        $scope.title = "编辑学生";
        var keyword;

        keyword = $stateParams.studentId;


        var cofig = httpConfig({method: "POST", url: "student_findStudent.action"}, {keyword: keyword})
        $http(cofig).then(function (res) {
            if (res.data.status == 1) {

                $scope.student = res.data.student;

                if ($scope.student.birthday != undefined) {
                    var date = new Date($scope.student.birthday)
                    var m = (date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : ("0" + (date.getMonth() + 1));
                    var d = (date.getDate() < 10) ? ("0" + date.getDate()) : date.getDate();
                    $scope.student.birthday = date.getFullYear() + "-" + m + "-" + d;

                }
            }
            $scope.method = "student_update.action";
        }, function () {
            alert("查询失败")

        });


    }


    $scope.windown = {};
    $scope.windown.isClose = true;


    $scope.genderY = function () {
        if ($scope.student.gender == true) {
            $scope.student.gender = false;
        } else {
            $scope.student.gender = true;
        }

    }
    $scope.genderX = function () {

        if ($scope.student.gender == true) {
            $scope.student.gender = false;
        } else {
            $scope.student.gender = true;
        }

    }

    $scope.selectClose = function () {

        if ($scope.windown.isClose) {
            $scope.windown.isClose = false;
        } else {
            $scope.windown.isClose = true;
        }
    }


//设置日期组件
    $('.myDate').datepicker({
        format: "yyyy-mm-dd"

    });
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
        url: 'student_addImg.action',
        queueLimit: 1,     //文件个数
        removeAfterUpload: true,   //上传后删除文件
        alias: "headImg", //设置表单的名称

    });

    $scope.uploader.filters.push({
        fn: function (item, options) {

            return this.queue.length < 10;
        }
    });

    $scope.clearItems = function () {    //重新选择文件时，清空队列，达到覆盖文件的效果
        $scope.uploader.clearQueue();
    }
    //上传成功
    $scope.uploader.onSuccessItem = function (fileItem, response, status, headers) {

        if (response.status == 1) {

            $scope.student.img = response.headImgFileName;
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
    $scope.addStudent = function () {

     //   if (!$scope.error.isError) {


            $scope.student.birthday = $("#birthday").val();


            if ($scope.uploader.queue.length == 0) {
                sendData();

            } else {
                $scope.uploader.uploadAll();
                $scope.clearItems;
            }
      //  }
    }

    //发送学生数据
    function sendData() {
        if ($scope.selected3 != null && $scope.selected3 != undefined) {
            $scope.student.classes = $scope.selected3.classesId;
        }

        var config = {
            method: "POST",
            url: $scope.method,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            data: $scope.student,
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {

                    if (obj[p] != null || obj[p] != undefined) {

                        if (p != 'classes') {
                            str.push("student." + encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                        } else {
                            str.push("student.classes.classesId" + "=" + encodeURIComponent(obj[p]));
                        }

                    }
                }
                return str.join("&");
            }
        };

        $http(config).then(function (res) {
            if (res.data.status == 1 && $scope.windown.isClose) {
                $state.go("stu.page", $stateParams, {reload: true})
                // window.history.go(-1);

            }

        }, function () {
            alert("查询失败")

        });

    }

    //-------------------错误------------

    $scope.error = {};

    // $scope.$watch("student.schoolId", function (value) {
    //
    //     if (value == "" || value == undefined) {
    //         $scope.error.schoolId = "学号为空";
    //         $scope.error.isError = true;
    //     }
    //     else if (value.length < 4) {
    //         $scope.error.schoolId = "学号小于4位";
    //         $scope.error.isError = true;
    //     } else {
    //
    //
    //         var cofig = httpConfig({
    //             method: "POST",
    //             url: "student_checkSchoolId.action"
    //         }, {student: {"schoolId": $scope.student.schoolId, "studentId": $scope.student.studentId}})
    //         $http(cofig).then(function (res) {
    //             if (res.data.status == -1) {
    //                 $scope.error.schoolId = "学号已存在";
    //                 $scope.error.isError = true;
    //             }
    //             else if (res.data.status == 1) {
    //                 $scope.error.schoolId = "";
    //                 $scope.error.isError = false;
    //             }
    //         }, function () {
    //             alert("查询失败")
    //
    //         });
    //     }
    //
    // }, true);


    //
    $scope.$watch("selected1", function (item) {
            $scope.departmentList = [];
            if (item != undefined && item != null && item.department != undefined && item.department != null && item.department.length > 0) {
                $scope.departmentList = item.department;
                $scope.selected2 = item.department[0];

                if ($scope.student != null && $scope.student != undefined && $scope.department != undefined && $scope.department != null) {
                    angular.forEach($scope.departmentList, function (v) {

                        if (v.departmentId == $scope.department.departmentId) {
                            console.log("department===" + v.name)
                            $scope.selected2 = v;
                            return;
                        }
                    });
                }
            }
        }
    )

    $scope.$watch("selected2", function (item) {
        $scope.classList = [];
        if (item != undefined && item != null && item.classesSet.length > 0) {
            $scope.classList = item.classesSet;
            $scope.selected3 = item.classesSet[0];

            if ($scope.student != null && $scope.student != undefined && $scope.classes != undefined && $scope.classes != null) {
                angular.forEach($scope.classList, function (v) {
                    if (v.classesId == $scope.classes.classesId) {
                        console.log("classes==" + v.classesName)
                        $scope.selected3 = v;
                        return;
                    }
                });

            }
        }
    })


    $http({method: 'post', url: 'class_findProfessions.action'}).then(function (res) {
        if (res.data.status == 1) {
            $scope.professionList = res.data.professionList;
            //设置中
            if ($scope.professionList.length > 0) {
                console.log($scope.student)
                $scope.selected1 = $scope.professionList[0];

                if ($scope.student != null && $scope.student.classes != null) {
                    $scope.department = $scope.student.classes.department
                    $scope.profession = $scope.department.profession;
                    $scope.classes = $scope.student.classes;
                    angular.forEach($scope.professionList, function (v) {


                        if (v.professionId == $scope.profession.professionId) {
                            console.log("profession===" + v.professionName)
                            $scope.selected1 = v;
                            return;
                        }


                    });


                }
            }

        } else {
            //TODO 获取失败
        }
    }, function (error) {

        //TODO 发送失败

    });


});
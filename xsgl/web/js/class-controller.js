/**
 * Created by silent on 2017-06-04/004.
 */

app.controller('classCtrl', function ($scope, $http, $state, $stateParams, $location, $echartsConfig) {


    $http({
        method:"post",
        url:'user_user.action'
    }).then(function (res) {
        if(res.data.status==1){

            $scope.permission=res.data.user.permission;
        }
    },function (error) {

    });

    $echartsConfig.series[0].links=[];
    $echartsConfig.series[0].data.push({
        name: '广州工商学院',
        symbolSize: 100,
        draggable: true,
    });
    $scope.refresh=function () {
        $echartsConfig.series[0].links=[];
        $echartsConfig.series[0].data=[];
        $scope.getData();
    }


$scope.getData=function () {
    

    $http({method: 'post', url: 'class_findProfessions.action'}).then(function (res) {
        if (res.data.status == 1) {
            $scope.professionList = res.data.professionList;
            if ($scope.professionList.length > 0) {
                angular.forEach($scope.professionList, function (v) {
                    var pro={
                        name: v.professionName,
                        draggable: true,
                        symbolSize: 60,
                    }
                    $echartsConfig.series[0].data.push(pro);
                  var indx1=   $echartsConfig.series[0].data.indexOf(pro);
                  if(indx1>-1){
                      $echartsConfig.series[0].links.push({
                          source: indx1,
                          target: 0,
                          category: 0,
                          value: '院系'
                      });
                  }

                    if (v.department.length > 0) {
                        angular.forEach(v.department, function (v1) {

                            var dep={
                                name: v1.name,
                                draggable: true,
                                symbolSize: 40,
                            };
                            $echartsConfig.series[0].data.push(dep)


                            var indx2=  $echartsConfig.series[0].data.indexOf(dep);
                            if(indx2>-1){
                                $echartsConfig.series[0].links.push({
                                    source: indx2,
                                    target: indx1,
                                    category: 0,
                                    value: '专业'
                                });
                            }


                            if (v1.classesSet.length > 0) {
                                angular.forEach(v1.classesSet, function (v2) {

                                    var cla={
                                        name: v2.classesName,
                                        draggable: true,
                                        symbolSize: 20,
                                    };
                                    console.log(cla)
                                    $echartsConfig.series[0].data.push(cla);

                                    var indx3=  $echartsConfig.series[0].data.indexOf(cla);
                                    if(indx3>-1){
                                        $echartsConfig.series[0].links.push({
                                            source: indx3,
                                            target: indx2,
                                            category: 0,
                                            value: '班级'
                                        });
                                    }

                                });


                            }

                        })
                    }


                })
            }


            $scope.onload();


        } else {
            //TODO 获取失败
        }
    }, function (error) {

        //TODO 发送失败

    });
}
    $scope.getData();


});


app.controller('addClassCtrl', function ($scope, $http, $state, $stateParams) {
        $scope.professionList = [];
        $scope.departmentList = [];
        //关闭窗口
        $scope.closeWindow = function () {
            window.history.go(-1);
        }
        //添加院系
        $scope.addProfession = function () {
            $http({
                method: 'post',
                url: 'class_addProfession.action',
                data: {profession: {professionName: $scope.addProfessionName}}
            })
                .then(function (res) {
                    if (res.data.status == 1) {
                        //TODO  保存成功
                        $scope.addProfessionName = "";
                    }
                    else {
                        //TODO 保存失败
                    }

                }, function () {

                })
        }
        //监听选项卡变化
        $scope.$watch("activeTab", function (i) {
            if (i == 2 || i == 3) {
                $http({method: 'post', url: 'class_findProfessions.action'}).then(function (res) {
                    if (res.data.status == 1) {
                        $scope.professionList = res.data.professionList;
                        //设置中
                        if ($scope.professionList.length > 0) {
                            $scope.selected1 = $scope.professionList[0];
                            $scope.selected2 = $scope.professionList[0];
                        }

                    } else {
                        //TODO 获取失败
                    }
                }, function (error) {

                    //TODO 发送失败

                });

            }
        });
        //添加专业
        $scope.addDepartment = function () {

            //departmentName
            if ($scope.selected1 != null && $scope.selected1 != undefined) {
                $http({
                    method: "post",
                    url: 'class_addDepartment.action',
                    data: {department: {name: $scope.departmentName, profession: $scope.selected1}}
                })
                    .then(function (res) {
                        if (res.data.status == 1) {
                            $scope.departmentName = "";
                        } else {
                            //todo 保存失败
                        }

                    }, function (error) {
                        //todo 发送失败
                    })
            } else {

                //todo 没有选择数据
            }
        }
        $scope.$watch("selected2", function (item) {
            $scope.departmentList = [];
            console.log(item)
            if (item != undefined && item != null && item.department != undefined && item.department != null && item.department.length > 0) {
                $scope.departmentList = item.department;
                $scope.selected3 = item.department[0];
            }

        })
        //addClass
        $scope.addClass = function () {
            if ($scope.selected3 != null && $scope.selected3 != undefined) {
                $http({
                    method: 'post',
                    url: 'class_addClass.action',
                    data: {classes: {classesName: $scope.classesName, department: $scope.selected3}}
                }).then(function (res) {
                    if (res.data.status == 1) {
                        $scope.classesName = "";
                    } else {
                        //todo 保存失败
                    }

                }, function (error) {
                    //todo 发送失败
                })
            } else {
                //todo 没有选择数据
            }
        }

    }
);


app.factory('$echartsConfig', function () {
    return  {
        title: {
            text: ''
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        label: {
            normal: {
                show: true,
                textStyle: {
                    fontSize: 12
                },
            }
        },
        title: {
            text: "广州工商学院数据分析",
            subtext: "各学院专业关系",
            top: "top",
            left: "center"
        },
        legend: {
            x: "center",
            show: false,
            data: ["院系", "专业", '班级']
        },
        series: [

            {
                type: 'graph',
                layout: 'force',
                symbolSize: 45,
                focusNodeAdjacency: true,
                roam: true,
                categories: [{
                    name: '院系',
                    itemStyle: {
                        normal: {
                            color: "#009800",
                        }
                    }
                }, {
                    name: '专业',
                    itemStyle: {
                        normal: {
                            color: "#4592FF",
                        }
                    }
                }, {
                    name: '班级',
                    itemStyle: {
                        normal: {
                            color: "#3592F",
                        }
                    }
                }],
                label: {
                    normal: {
                        show: true,
                        textStyle: {
                            fontSize: 12
                        },
                    }
                },
                force: {
                    repulsion: 1000
                },
                edgeSymbolSize: [4, 50],
                edgeLabel: {
                    normal: {
                        show: true,
                        textStyle: {
                            fontSize: 10
                        },
                        formatter: "{c}"
                    }
                },
                data: [],
                links:[]
                ,
                lineStyle: {
                    normal: {
                        opacity: 0.9,
                        width: 1,
                        curveness: 0
                    }
                }
            }
        ]
    };
})
//echarts directive
    .directive('echarts', ['$echartsConfig', function ($echartsConfig) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {

                if (!scope.$echartsInstance)
                    scope.$echartsInstance = {};
                scope.$watch(attrs.echarts, function () {
                    var option=angular.extend({},$echartsConfig,scope.$eval(attrs.echarts));
                    if (option.id) {
                        scope.$echartsInstance[option.id] = echarts.init(element[0]);
                        scope.$echartsInstance[option.id].setOption(option);
                    } else {
                        scope.$echartsInstance = echarts.init(element[0]);
                        scope.$echartsInstance.setOption(option);
                    }
                });

                scope.onload=function () {
                    var option=angular.extend({},$echartsConfig,scope.$eval(attrs.echarts));
                    if (option.id) {
                        scope.$echartsInstance[option.id] = echarts.init(element[0]);
                        scope.$echartsInstance[option.id].setOption(option);
                    } else {
                        scope.$echartsInstance = echarts.init(element[0]);
                        scope.$echartsInstance.setOption(option);
                    }
                }

            }
        };
    }])
/**
 * Created by silent on 2017-06-04/004.
 *
 * 这是分页控制器
 */
app.controller("page", function($scope, $http,pageData,$state,$stateParams,$location) {

    $scope.pageData=pageData;

    $scope.$watch("pageData.totalPageCount",function () {
        $scope.pages=pageData.totalPageCount;
        $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
        $scope.pageList = [];
        for(var i = 0; i < $scope.newPages; i++) {
            $scope.pageList.push(i + 1);
        }

    },true);


    $scope.pageData.changePageNo=function(pageNo){

        $scope.selectPage(pageNo);
    }




    //数据源
    //分页总数
    $scope.pages = 20; //分页数
    $scope.newPages = $scope.pages > 5 ? 5 : $scope.pages;
    $scope.pageList = [];


    $scope.selPage = 1;


    //   $scope.items = $scope.data.slice(0, $scope.pageSize);
    //分页要repeat的数组
    for(var i = 0; i < $scope.newPages; i++) {
        $scope.pageList.push(i + 1);
    }

    //打印当前选中页索引
    $scope.selectPage = function(page) {
        var temp=false;
       if( page<0){
           page=-page;
           temp=true;
       }



        //不能小于1大于最大
        if(page < 1 || page > $scope.pages) return;
        //最多显示分页数5
        if(page > 2) {
            //因为只显示5个页数，大于2页开始分页转换
            var newpageList = [];
            for(var i = (page - 3); i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)); i++) {
                newpageList.push(i + 1);
            }
            $scope.pageList = newpageList;
        }
        $scope.selPage = page;

        $scope.isActivePage(page);

        $scope.pageData.pageNo=page;

        if(temp){
            if($location.$$path.indexOf("/user")==0){
                $state.go("user.page", {pageNo:page,keyword:$scope.pageData.keyword},{reload:false});
            }else {
                $state.go("stu.page", {pageNo:page,keyword:$scope.pageData.keyword},{reload:false});
            }

        }
       // $state.reload("stu.page", {page:page,keyword:$scope.pageData.keyword});
       // $state.go("stu.page", {page:page,keyword:$scope.pageData.keyword},{reload:true});


    };
    //设置当前选中页样式
    $scope.isActivePage = function(page) {
        return $scope.selPage == page;
    };
    //上一页
    $scope.Previous = function() {
        $scope.selectPage(-($scope.selPage - 1));
    }
    //下一页
    $scope.Next = function() {
        $scope.selectPage(-($scope.selPage + 1));
    };

    if($stateParams.pageNo>1&& $scope.selPage==1){

        $scope.selectPage( $stateParams.pageNo);
    }

})
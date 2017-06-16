/**
 * Created by silent on 2017-06-06/006.
 *
 * 这是上传excel文件的控制器
 */
app.controller('upload', function ($scope, $http , $location,FileUploader) {

    $scope.uploadUri="";
    //importExcel
    if($location.$$path.indexOf("/user")==0){
        $scope.exportExcelUri="user_exportExcel.action?temp=1";
        $scope.uploadUri="user_importExcel.action";
    }else {
        $scope.exportExcelUri="student_exportExcel.action?temp=1";
        $scope.uploadUri="student_importExcel.action";
    }

    $scope.openFile=function () {
        document.getElementById("xmlFile").click();
        
    }

     $scope.uploader = new FileUploader({
        url:  $scope.uploadUri,
        queueLimit: 1,     //文件个数
        removeAfterUpload: true,   //上传后删除文件
        alias:"excel"  //设置表单的名称

    });


    $scope.closeWindow=function () {
        window.history.go(-1);
        
    }

    // FILTERS

    // a sync filter
    $scope.uploader.filters.push({
        fn: function(item , options) {
            console.log('syncFilter');
            return this.queue.length < 10;
        }
    });

    $scope.clearItems = function(){    //重新选择文件时，清空队列，达到覆盖文件的效果
        $scope.uploader.clearQueue();
    }

    $scope.uploader.onAfterAddingFile = function(fileItem) {
        $scope.fileItem = fileItem._file;    //添加文件之后，把文件信息赋给scope
    };

    $scope.uploader.onSuccessItem = function(fileItem, response, status, headers) {
            $scope.message= $scope.fileName+"--->"+response.message;
            $scope.fileName=undefined;
    };
    //进度
    $scope.uploader.onProgressItem = function(fileItem, progress) {

        console.info('onProgressItem', fileItem, progress);
    };

    //上传失败
    $scope.uploader.onErrorItem = function(fileItem, response, status, headers) {
        console.info('onErrorItem', fileItem, response, status, headers);
    };

    $scope.fileNameChanged = function(file){
     $scope.fileName= file.value;


    }
    $scope.start=function () {
        $scope.uploader.uploadAll();
    }


});
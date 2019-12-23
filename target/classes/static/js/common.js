//增加空tr
function mapTR(ele,colNum,len){
    var str = '';
    for (var i=0;i<len;i++) {
        str += '<tr>'
        for (var j=0;j<colNum;j++){
            str += '<td></td>'
        }
        str += '</tr>'
    }
    $('.'+ele).html(str)
}
// 获取数据公共方法
function getData(obj){
    var result = {};
    $.ajax({
        url:obj.url,
        dataType:'json',
        method:obj.method,
        data:obj.data,
        // contentType:obj.contentType,
        success:function(req){
            if(req.success){
                result = req
            }
        }
    })
    return result
}
function postData(obj){
    var result = {};
    $.ajax({
        url:obj.url,
        dataType:'json',
        method:obj.method,
        data:JSON.stringify(obj.data),
        contentType:obj.contentType,
        success:function(req){
            if(req.success){
                result = req
            }
        }
    })
    return result
}

//增加空tr
function mapTR(ele,len){
    var str = '';
    for (var i=0;i<len;i++) {
        str += '<tr><td></td><td></td><td></td></tr>'
    }
    $('.'+ele).html(str)
}
mapTR()
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

//获取url传值
layui.define(function (exports) {
    var $ = layui.jquery
        , fun = {
            //ajax的再封装
            AjaxData: function (url, type='get', data = {}, callback, error) {
                $.ajax({
                    url: layui.setter.base + url
                    , type: type
                    , data: data
                    , dataType: 'json'
                    , success: callback
                    , error: error
                })
            }
            ,GetRequest: function () {
                var url = location.search; //获取url中"?"符后的字串  
                var theRequest = new Object();
                if (url.indexOf("?") != -1) {
                    var str = url.substr(1);
                    strs = str.split("&");
                    for (var i = 0; i < strs.length; i++) {
                        theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
                    }
                }
                return theRequest;
            }
        }

    //接口输出
    exports('fun', fun);
});
var pathName = window.document.location.pathname;//获取主机地址之后的目录如：/stuWork/index.jsp
var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);//获取带"/"的项目名，如：/stuWork
document.write("<script language=javascript src='"+ projectName +"/static/js/jquery-3.3.1.min.js'></script>");
/**
 * 获取指定的URL参数值
 * URL:http://www.quwan.com/index?name=tyler
 * 参数：paramName URL参数
 * 调用方法:getParam("name")
 * 返回值:tyler
 */
function getParam(paramName) {
    paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
    }
    return paramValue == "" && (paramValue = null), paramValue
}

/**
 *获取整个form的值（json形式的值）
 * @param form 传递表单参数
 * @returns {{}}
 */
function getEntity(form) {
    var result = {};
    $(form).find("[name]").each(function() {
        var field = $(this).attr("name");
        var val;
        if ($(this).attr('type') == 'checkbox') {
            val = $(this).prop('checked');
        } else if ($(this).attr('type') == 'radio') {
            val = $(this).prop('checked');
        } else {
            val = $(this).val();
        }

        // 获取单个属性的值,并扩展到result对象里面
        getField(field.split('.'), val, result);
    });
    return result;
}

/**
 *
 * @param fieldNames
 * @param value
 * @param result
 */
function getField(fieldNames, value, result) {
    if (fieldNames.length > 1) {
        for (var i = 0; i < fieldNames.length - 1; i++) {
            if (result[fieldNames[i]] == undefined) {
                result[fieldNames[i]] = {}
            }
            result = result[fieldNames[i]];
        }
        result[fieldNames[fieldNames.length - 1]] = value;
    } else {
        result[fieldNames[0]] = value;
    }
}

/**
 *传入对应参数将form整个表单赋值
 * @param form
 * @param entity
 */
function setEntity(form, entity) {

    $(form).find("[name]").each(function() {

        var field = $(this).attr("name");
        fieldNames = field.split('.');
        var value = JSON.parse(JSON.stringify(entity));
        for (var index = 0; index < fieldNames.length; index++) {
            value = value[fieldNames[index]];
            if (!value) {
                break;
            }
        }

        if ($(this).attr("type") === "checkbox" || $(this).attr("type") === "radio") {
            $('input[value='+value+']').attr('checked', true);
        } else if($(this).attr("type") === "text" || $(this).attr("type") === "hidden"){
            if (value) {
                $('input[name='+field+']').val(value);
            } else {
                $('input[name='+field+']').val("");
            }
        }else {
            $('select[name='+field+']').val(value);
        }
    })
}


/*定义年级相关的参数和方法，start */
var t = {}
    ,grade = ""
    ,date=new Date;
var nowYear = date.getFullYear()
    ,pastYear1 = nowYear-1
    ,pastYear2 = nowYear-2
    ,pastYear3 = nowYear-3;

// /**
//  * 生成年级选项
//  */
// function getSelectGrade() {
//     var select = $("#grade");
//     select.prepend("<option value=''>请选择</option>");
// //select.append("<option value='' selected='selected'>全部</option>");
//     select.append("<option value='"+nowYear+"'>"+nowYear+"</option>");
//     select.append("<option value='"+(pastYear1)+"'>"+(pastYear1)+"</option>");
//     select.append("<option value='"+(pastYear2)+"'>"+(pastYear2)+"</option>");
//     select.append("<option value='"+(pastYear3)+"'>"+(pastYear3)+"</option>");
// }
// /*和年级相关的参数和方法，end*/
// /*公共tree、table业务，start */
// /*定义tree、table相关的参数和方法，start */
// var classCode,className,orgId,orgName,commTreeUrl,commTableUrl,elem,cols;
// /**
//  * 加载tree
//  * @param url
//  * @param grade1
//  */
// function getCommTree(url,grade1) {
//     layui.$.ajax({
//         url: url
//         ,data:{grade:grade1}
//         ,type: 'POST'
//         ,dataType: 'json'
//         ,beforeSend:function () {
//             this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'] });//未加载时显示loading样式
//         }
//         ,complete: function () {
//             layer.close(this.layerIndex);//加载完成关闭loading样式
//         }
//         ,success: function (res) {
// //加载成功给页面layui.tree赋值
//             layui.$("#left_tree").children(":first").empty();//清空tree
//             layui.tree({
//                 elem: '#tree'
//                 , target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
//                 , nodes: [res] //需要[{}]形式数据，而此处res格式为{}形式，因此外面加[]
//                 , click: function (node) {
//                     commTreeOnClick(node);
//
//                 }
//             });
//         }
//     })
// }
//
// function getCommTable(elem,url,cols) {
//     layui.table.render({
//         elem: elem
//         , url: url
//         , toolbar: '#tableToolbar'
//         ,id:'tableDataID'
//         ,contentType: 'application/json'
//         ,method: 'post'
//         ,request: {
//             pageName: 'currentPage',
//             limitName: 'pageSize'
//         }
//         ,parseData: function(result){ //result 即为原始返回的数据
//             return {
//                 "code":result.code, //解析接口状态
//                 "msg": result.msg, //解析提示文本
//                 "count": result.extend.PageInfo.total, //解析数据长度
//                 "data": result.extend.PageInfo.list //解析数据列表
//             };
//         },
//         response: {
//             statusCode: 200 //重新规定成功的状态码为 200，layui.table 组件默认为 0
//         }
//         , page: {
//             layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
//             , 'prev': '上一页'
//             , 'next': '下一页'
//             , 'limits': [10, 20, 30, 40, 50]
//             , jump: function (obj) {
//             }
//         }
//         , loading: true
//         , cellMinWidth: 120
//         , defaultToolbar: ['filter']
//         , cols: cols
//     });
// }
//
// /**
//  * tree点击事件
//  * @param node
//  */
// function commTreeOnClick(node) {
//     if('2' == node.level){
//         classCode = node.id
//         ;className = node.name
//         ;orgName = null
//         ;layui.$("input[name='orgName']").val("")
//         ;orgId = null;
//     }else{
//         orgId = node.id
//         ;orgName = node.name
//         ;className = null
//         ;layui.$("input[name='className']").val("")
//         ;classCode = null;
//     }
//
//     reloadCommTable();
// }
// /**
//  * 重载table
//  */
// function reloadCommTable() {
//     t = getParamOfWhere();
//     layui.table.reload('tableDataID', { //table的id
//         where: t
//     });
// //搜索框数据继续显示
//     for (var k in t){
//         layui.$("input[name='"+k+"']").val(t[k]);
//     }
// }
//
// /**
//  * 获取table中where属性的参数，用于条件查询，根据具体页面重写该方法
//  * @returns {{}}
//  */
// function getParamOfWhere() {
//     grade = isEmpty(layui.$("#grade option:selected").text()) ? "" : layui.$("#grade option:selected").text()
//     ;classCode = layui.$("input[name='classCode']").val()
//     ;className = layui.$("input[name='className']").val()
//     ;orgName = isEmpty(orgId)? layui.$("input[name='orgName']").val(): orgName;
//     var re = {
//         id: classCode = isEmpty(classCode) ? '' : classCode
//         , orgId: orgId = isEmpty(orgId) || 0 == orgId ? '' : orgId
//         , className: className = isEmpty(className) ? '' : className
//         , orgName: orgName = isEmpty(orgName) ? '' : orgName
//         , grade: grade = isEmpty(grade) ? '' : grade
//     };
//     return re;
// }
// /*定义tree、table相关的参数和方法，end */
/**
 * 判断参数是否为空
 * @param p
 * @returns {boolean}
 */
function isEmpty(p) {
    return null == p || '' == p || undefined == p;
}
//
// /**
//  * search按钮点击触发
//  */
// function search(){
//     reloadCommTable();
// }
/*公共tree、table业务，end */

window.fromIframeData = null;
function getIframeData(obj) {
    window.fromIframeData = obj;
}
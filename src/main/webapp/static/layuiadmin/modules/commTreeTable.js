layui.define(['layer','tree', 'table'],function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var $ = layui.$
        ,layer = layui.layer
        ,table = layui.table
        ,tree = layui.tree
        ,form = layui.form
        ,date=new Date;
        window.clickNode = {id:null,name:null,level:null};

    var commTreeTable = {
        /**
         * 此方法初始化页面tree上面年级的select下拉框
         * @param selectId 此参数为下拉框的标签ID
         */
        getSelectGrade: function (selectId) {
            var nowYear = date.getFullYear()
                ,pastYear1 = nowYear - 1
                ,pastYear2 = nowYear - 2
                ,pastYear3 = nowYear - 3
                ,select = $('#'+selectId);
            select.append("<option value='' selected='selected'>请选择</option>");
            select.append("<option value='" + nowYear + "' >" + nowYear + "</option>");
            select.append("<option value='" + (pastYear1) + "'>" + (pastYear1) + "</option>");
            select.append("<option value='" + (pastYear2) + "'>" + (pastYear2) + "</option>");
            select.append("<option value='" + (pastYear3) + "'>" + (pastYear3) + "</option>");
            form.render('select'); //刷新select选择框渲染
        },

/****************************************加载tree，包括tree中的点击时间以及后续的table的刷新方法****************************************/
        /**
         * 此方法为加载tree的方法
         * @param treeElement 页面tree的标签ID
         * @param treeUrl tree的数据请求url
         * @param data tree的参数传递（有的话）
         * @param tableId 此参数为table中定义的ID（非标签ID）
         * @param toolbarForm 为工具栏中form的标签ID
         * @param tableUrl table数据的加载url
         */
    getCommTree: function (treeElement,treeUrl,data,tableId,toolbarForm,tableUrl) {
            treeElement = "#" + treeElement;
        $.ajax({
            url: treeUrl,
            data:JSON.stringify(data),
            type: 'POST',
            dataType: "json",
            contentType: 'application/json;charset=utf-8',
            //未加载时显示loading样式
            beforeSend: function () {this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'] });},
            //加载完成关闭loading样式
            complete: function () {layer.close(this.layerIndex);},
            success: function(result){commTreeTable.treeFunction(result,treeElement,tableUrl,tableId,toolbarForm)}
        })
    },
        /**
         * 此方法为tree处理的核心方法，包括了定义click事件中的参数
         * @param result tree请求后得到的后台数据
         * @param treeElement tree的标签ID
         * @param tableUrl table请求的url
         * @param tableId  此参数为table中定义的ID（非标签ID）
         * @param toolbarForm 为工具栏中form的标签ID
         */
    treeFunction: function (result,treeElement,tableUrl,tableId,toolbarForm) {
        tree({
            elem: treeElement,
            target: '_blank',  //是否新选项卡打开（比如节点返回href才有效）
            nodes: result,  //需要[{}]形式数据，而此处res格式为{}形式，因此外面加[]
            click: function (node) {
                window.clickNode.id = commTreeTable.isEmpty(node.id) ? "" :node.id;
                window.clickNode.name = commTreeTable.isEmpty(node.name) ? "" :node.name;
                window.clickNode.level = commTreeTable.isEmpty(node.level) ? "" :node.level;
                commTreeTable.commTreeOnClick(tableUrl,tableId,toolbarForm);
            }
        });
    },
        /**
         * 此方法为tree点击的事件的核心方法
         * @param tableUrl table请求的url
         * @param tableId 此参数为table中定义的ID（非标签ID）
         * @param toolbarForm 为工具栏中form的标签ID
         */
        commTreeOnClick:function (tableUrl,tableId,toolbarForm) {
            toolbarForm = "#" + toolbarForm;
            var toolForm = $(toolbarForm);
            var parameter = getEntity(toolForm);
            commTreeTable.reloadCommTable(tableId,tableUrl,parameter);//加载table对象初始化方法；
            setEntity(toolForm,parameter);
        },

        /**
         * 此方法为table的重新加载方法
         * @param tableId 此参数为table中定义的ID（非标签ID）
         * @param tableUrl table请求的url
         * @param parameter 传入后台的方法
         */
        reloadCommTable:function (tableId,tableUrl,parameter) {
            table.reload(tableId, { url: tableUrl,contentType: "application/json",where: parameter});
        },

/**************************************************END**********************************************************************************/


/**************************************************table初始化**********************************************************************************/
        /**
         * 此方法为table的初始化方法
         * @param tableElement table的标签ID
         * @param tableUrl table请求的url
         * @param tableId 此参数为table中定义的ID（非标签ID）
         * @param cols 自定义列的传入
         * @param tableToolbar 工具栏ID
         */
        getCommTable:function(tableElement, tableUrl,tableId,cols,tableToolbar){
            tableElement = "#" + tableElement;
            tableToolbar = "#" + tableToolbar;
            table.render({
                elem: tableElement,
                url: tableUrl,
                toolbar: tableToolbar,
                id:tableId,
                contentType: 'application/json',
                method: 'post',
                request: {pageName: 'currentPage',limitName: 'pageSize'},
                parseData: function (result) { return {"code": result.code, "msg": result.msg, "count": result.extend.PageInfo.total,"data": result.extend.PageInfo.list};},
                response: {statusCode: 200},  //重新规定成功的状态码为 200，table 组件默认为 0
                page: {layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'], 'prev': '上一页', 'next': '下一页', 'limits': [10, 20, 30, 40, 50] , jump: function (obj) { } },
                loading: true,
                cellMinWidth: 120,
                defaultToolbar: ['filter', 'print', 'exports'],
                cols: cols,
                // done: function(res, curr, count){
                //     this.where={};
                // }
        });
        },
/**************************************************END**********************************************************************************/

/**************************************************table工具栏监听事件**********************************************************************************/
        /**
         *
         * @param table 页面的table对象
         * @param tableElement table的标签ID
         * @param tableId 此参数为table中定义的ID（非标签ID）
         * @param tableUrl table请求的url
         * @param toolbarForm 工具栏form的标签ID
         * @param delTableUrl s删除方法的url
         * @param formUrl 标签的路径地址，绝对路径
         */
        listenToolbar:function (table,tableElement,tableId,tableUrl,toolbarForm,delTableUrl,formUrl) {
            table.on('toolbar('+tableElement+')', function (obj) {
                var data;
                if(obj.event == 'update' || obj.event == 'delete') {data = table.checkStatus(obj.config.id).data; }
                switch(obj.event){
                    case 'add':
                        commTreeTable.formLayer('新增', obj.event,data,formUrl,tableUrl,tableId,toolbarForm);
                        break;
                    case 'delete':
                        commTreeTable.deleteLayer(data,delTableUrl,toolbarForm,tableId,tableUrl);
                        break;
                    case 'update':
                        if(data.length != 1){ layer.open({title: '操作错误',content: '请选中一条数据进行编辑！' }); }else{commTreeTable.formLayer('编辑', obj.event,data,formUrl,tableUrl,tableId,toolbarForm);}
                        break;
                    case 'search':
                        commTreeTable.Search(toolbarForm,tableId,tableUrl,toolbarForm);
                        break;
                    case 'multiSearch':
                        commTreeTable.formLayer('自定义搜索', obj.event,data,formUrl,tableUrl,tableId,toolbarForm);
                        break;
                };
            });

        },
        /**
         * 此方法为删除的判断框
         * @param data 删除请求的参数
         * @param delTableUrl 删除的url
         * @param toolbarForm 工具栏form的标签ID
         * @param tableId table的定义ID
         * @param tableUrl table请求参数url
         */
        deleteLayer:function (data,delTableUrl,toolbarForm,tableId,tableUrl) {
            var deleteText = "";
            var deleteArray = new Array();
            deleteText += "<div style=\"padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;\">";
            for(var i = 0 ; i < data.length;i++){deleteText += "编号:" + data[i].id + "<br>";deleteArray.push(data[i].id);}
            deleteText += "</div>";
            if(data.length > 0){
                layer.open({type: 1,title: '确认删除？',closeBtn: false,shade: 0.8,id: 'deleteLayer', btn: ['确定删除', '取消'],btnAlign: 'c',moveType: 1 ,content: deleteText,
                    yes: function(){commTreeTable.deleteSuccess(deleteArray,delTableUrl,tableUrl,toolbarForm,tableId)}});
                }else {layer.open({title: '操作错误',content: '请至少选中一条数据进行删除！'}); }
            },
        /**
         *
         * @param deleteArray 删除方法的参数
         * @param delTableUrl 删除方法请求的url
         * @param tableUrl table请求的url
         * @param toolbarForm 工具栏form的标签ID
         * @param tableId table定义ID
         */
        deleteSuccess:function(deleteArray,delTableUrl,tableUrl,toolbarForm,tableId){
            var flag = commTreeTable.deleteElement(delTableUrl,deleteArray);
            if(flag){
                toolbarForm = "#" + toolbarForm;
                var toolForm = $(toolbarForm);
                var result = getEntity(toolForm);
                commTreeTable.reloadCommTable(tableId,tableUrl,result);
                setEntity(toolForm,result);
                layer.closeAll();
            }else{layer.msg("警告", {time: 3000,content:"删除失败"}) }
        },
        /**
         * 此方法为删除对象的后台请求方法
         * @param deleteUrl 删除的URL
         * @param data 删除对象的ID的集合
         * @returns {boolean} 是否删除成功，返回Boolean值
         */
        deleteElement:function (deleteUrl, data) {
            var result = false;
            $.ajax({
                url: deleteUrl,
                data: JSON.stringify(data),
                async: false,
                type: 'post',
                contentType: 'application/json;charset=utf-8',
                success: function(res){
                    result = res.code == 200 ? true : false;
                }
            })
            return result;
        },
        /**
         * 此方法为search的核心方法
         * @param toolbarForm 工具栏的标签ID
         * @param tableId 此参数为table中定义的ID（非标签ID）
         * @param tableUrl table请求的url
         * @constructor
         */
        Search:function (toolbarForm,tableId,tableUrl) {
            toolbarForm = "#" + toolbarForm;
            var toolForm = $(toolbarForm);
            commTreeTable.reloadCommTable(tableId,tableUrl,getEntity(toolForm));
            setEntity(toolForm,getEntity(toolForm));
        },
        /**
         * 此方法为layer弹框的核心方法
         * @param title form的标题
         * @param type 请求的类型
         * @param data 请求所带的参数，一般情况为table的主键
         * @param formUrl form的路径
         * @param tableUrl table的数据请求url
         * @param tableId table的定义ID
         * @param toolbarForm 工具栏的标签ID
         */
        formLayer:function (title,type,data,formUrl,tableUrl,tableId,toolbarForm) {
                var requestUrl = "";
                if (type == 'update'){requestUrl = formUrl + '?type='+ type +'&id= '+ data[0].id ;}else {requestUrl = formUrl + '?type='+ type ; }
                parent.layer.open({
                    type: 2, //Page层类型
                    area: [document.body.offsetWidth*0.7,document.body.offsetHeight],
                    title: title,
                    shade: 0.6,  //遮罩透明度
                    maxmin: true,  //允许全屏最小化
                    anim: 2,  //0-6的动画形式，-1不开启
                    content: requestUrl,
                    end: function(index, layero){
                        toolbarForm = "#" + toolbarForm;
                        var toolForm = $(toolbarForm);
                        var result = getEntity(toolForm);
                        if(type == "multiSearch"){
                            commTreeTable.reloadCommTable(tableId,tableUrl,top.parent.fromIframeData);
                            setEntity(toolForm,result);
                        }else{
                            commTreeTable.reloadCommTable(tableId,tableUrl,result);
                            setEntity(toolForm,result);
                        }
                    }
                });
        },
/**************************************************END**********************************************************************************/

        /**
         * 此方法判断对象是否为空
         * @param parameter 所判断的对象
         * @returns {boolean} 返回是否为空对象
         */
        isEmpty:function isEmpty(parameter){
            return null == parameter || '' == parameter || undefined == parameter;
        },
/****************************************form页面相关方法****************************************/

/***********************************************END**********************************************/

    };

//输出test接口
    exports('commTreeTable', commTreeTable);
});
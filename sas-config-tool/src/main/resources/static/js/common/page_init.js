
function PageObject(_data) {


    this.data = {
        "title":"数据源配置",
        "object":"数据源",
        "addURL":"/sas/datasource/add",
        "updateURL":"/sas/datasource/update",
        "deleteURL":"/sas/datasource/delete",
        "queryURL":"/sas/datasource/list",
        "fields":[
            {"name":"数据源名", "code":"dataSource", "type":"text", "showInTable":true,"width":80},
            {"name":"数据源类型", "code":"dataSourceType", "type":"select","showInTable":true, "width":30, "selectType":"lookup","selectObject":"b7ae904e-8392-11e9-ace8-cbe2a0556a26"},
            {"name":"加载器", "code":"loader", "type":"text", "showInTable":true,"width":30},
            {"name":"配置", "code":"config", "type":"text","showInTable":true, "width":300}
    ]};

    var pageData = _data;

    this.initBody = function initBody() {

        var form = "<div id=\"bidderDiv\" style=\"display: none; \">\n"
                   + "    <div style=\"margin:20px\">\n"
                   + "    <form role=\"form\">";
        var fields = pageData["fields"];
        for (var i = 0; i < fields.length; i ++) {
            form += "<div class=\"form-group\">\n"
                    + " <label for=\"dataSource\">"+fields[i]["name"]+"</label>";
            if (fields[i]["type"] === "text") {
                form += "<input type=\"text\" class=\"form-control\" id=\""+fields[i]["code"]+"\" placeholder=\"请输入"+fields[i]["name"]+"\">";
            } else if (fields[i]["type"] === "select") {
                form += "<select class=\"selectpicker form-control\" id=\""+fields[i]["code"]+"\" name=\""+fields[i]["code"]+"\"></select>";
            } else if (fields[i]["type"] === "textarea") {
                form += "<textarea class=\"form-control\" id=\""+fields[i]["code"]+"\" placeholder=\"请输入"+fields[i]["name"]+"\"/>";
            }
            form += "</div>";
        }
        form += "        <div align=\"center\" >\n"
                + "            <button type=\"submit\" id=\"dilivery\" style=\"width: 200px;\" class=\"btn btn-primary btn-block\">提交</button>\n"
                + "        </div>";
        form += "    </form>\n"
                + "    </div>\n"
                + "</div>\n";

        var html = form
               + "<div class=\"container\">\n"
               + "\n"
               + "    <h3 align=\"center\">"+pageData["title"]+"</h3>\n"
               + "    <div class=\"btn-group\">\n"
               + "        <button id=\"home\" type=\"button\" class=\"btn btn-sm btn-primary\">首页</button>\n"
               + "        <button id=\"add\" type=\"button\" class=\"btn btn-sm btn-primary\">添加"+pageData["object"]+"</button>\n"
               + "        <button id=\"searchByid\" type=\"button\" class=\"btn btn-sm btn-primary\">按名称查找</button>\n"
               + "    </div>\n"
               + "\n"
               + "    <div id=\"ccT\">\n"
               + "        <table id=\"ArbetTable\" class=\"table  table-striped\" style=\"word-break:break-all; word-wrap:break-all; table-layout: fixed;\">\n"
               + "        </table>\n"
               + "    </div>\n"
               + "\n"
               + "</div>";
        return html;
   };

   this.onAdd = function onAdd() {
       layer.open({
          type : 1,
          skin : 'layui-layer-lan',
          area : [ '800px', '500px' ],
          shadeClose : true, //点击遮罩关闭
          title : '添加'+pageData["object"],
          content : $('#bidderDiv')
      });

       var fields = pageData["fields"];
       for (var i = 0; i < fields.length; i ++) {
           var field = fields[i];
           if (field["type"] === "select") {
               if (field["selectType"] === "lookup") {
                   initLookupSelect(field["code"], field["selectObject"]);
               } else if (field["selectType"] === "reference") {
                   initReferenceSelect(field["code"], field["selectObject"], field["selectField"]);
               }
           } else {
               $("#"+field["code"]).val("");
           }
       }

       $("#dilivery").off("click").on("click", function() {
           var record = {};
           var fields = pageData["fields"];
           for (var i = 0; i < fields.length; i ++) {
               var field = fields[i];
               record[field["code"]] = $("#"+field["code"]).val();
           }
           $.ajax({
              type : "POST",
              url : pageData["addURL"],
              datatype : "text",
              contentType : "application/json",
              data : JSON.stringify(record),
              async : true,
              success : function(result) {
                  layer.alert('添加成功', {
                      icon : 6
                  });
              },
              error : function(result) {
                  layer.alert('添加失败', {
                      icon : 5
                  });
              }
          })
       });
   };

   function onUpdate(data) {
       pageData = data;
       this.update = function (e, value, row, index) {
        layer.open({
               type : 1,
               area : [ '900px', '540px' ],
               shadeClose : true, //点击遮罩关闭
               title : '修改'+pageData["object"],
               content : $('#bidderDiv')
           });
       var fields = pageData["fields"];
       $("#id").val(row["id"]);
       for (var i = 0; i < fields.length; i ++) {
           var field = fields[i];
           if (field["type"] === "select") {
               if (field["selectType"] === "lookup") {
                   initLookupSelect(field["code"], field["selectObject"]);
               } else if (field["selectType"] === "reference") {
                   initReferenceSelect(field["code"], field["selectObject"], field["selectField"]);
               }
           }
           $("#"+field["code"]).val(row[field["code"]]);
       }

        $("#dilivery").off("click").on("click", function() {
            var record = {
                id : row["id"]
            }
            var fields = pageData["fields"];
            for (var i = 0; i < fields.length; i ++) {
                var field = fields[i];
                record[field["code"]] = $("#"+field["code"]).val();
            }

            //Ajax调用处理
            $.ajax({
               type : "POST",
               url : pageData["updateURL"],
               datatype : "text",
               contentType : "application/json",
               data : JSON.stringify(record),
               async : true,
               success : function(result) {
                   layer.alert('修改成功', {
                       icon : 6
                   });
               },
               error : function(result) {
                   layer.alert('修改失败', {
                       icon : 5
                   });
               }
           })
        });
       };
   }

   function onDelete(data) {
       pageData = data;
       this.delete = function (e, value, row, index) {
       layer.msg("确认删除?", {
           time : 0,
           icon : 7,
           btn : [ "是", "否" ],
           yes : function(index) {
               layer.close(index);
               $.ajax({
                  type : "POST",
                  url : pageData["deleteURL"],
                  data : "id=" + row["id"],
                  contentType : "application/x-www-form-urlencoded",
                  async : false,
                  success : function(result) {
                      location.reload();
                  },
                  error : function(result) {
                      layer.alert('删除失败', {
                          icon : 5
                      });
                  }
              });

           }
       });
       };
   }

   this.initEvent = function () {
       var oEventInit = new Object();
       oEventInit.data = pageData;
       oEventInit.onAdd = this.onAdd;
       oEventInit.Init = function() {
           $("#add").on("click", this.onAdd);
       }
       return oEventInit;
   }

   this.initTable = function initTable() {
       var oTableInit = new Object();
       oTableInit.data = pageData;
       oTableInit.Init = function() {
           var columns = [];
           var i = 0;
           var fields = pageData["fields"];
           for (i = 0; i < fields.length; i ++) {
               var field = fields[i];
               if (field["showInTable"] === false) {
                   continue;
               }
               var column = {};
               column["field"] = field["code"];
               column["title"] = field["name"];
               column["colspan"] = 1;
               column["width"] = field["width"];
               columns[columns.length] = column;
           }
           var operate = {
               field : 'operate',
               title : '操作',
               colspan: 1,
               width: 80,
               events : operateEvents,
               formatter : operateFormatter
           };
           columns[columns.length] = operate;

           $('#ArbetTable').bootstrapTable({
               url : pageData["queryURL"],
               method : 'get',
               toolbar: '#toolbar',
               striped : true,
               cache : false,
               pagination : true,
               sortable : false,
               queryParams : oTableInit.queryParams,
               showToggle : true,
               sidePagination : "server",
               pageList : [ 4, 10 ],
               pageNumber : 1,
               pageSize : 4,
               showColunms : true,
               clickToSelect : true,
               showRefresh : true,
               search : false,
               uniqueId : "id",
               contentType : "application/x-www-form-urlencoded",
               columns : columns
           });
       };
       oTableInit.queryParams = function(params) {
           var temp = {
               limit : params.limit,
               offset : params.offset
           };
           return temp;
       };
       return oTableInit;
   };

    function operateFormatter(value, row, index) {
        return [ '<button class="btn btn-sm btn-warning" id="edit">编辑</button>',
                 '<button class="btn btn-sm btn-danger" id="delete">删除</button>' ]
            .join(" ");
    }

    var operateEvents = {
        "click #delete" : function(e, value, row, index) {
            var deletor = new onDelete(operateEvents.data);
            deletor.delete(e,value,row,index);
        },
        "click #edit" : function(e, value, row, index) {
            var updator = new onUpdate(operateEvents.data);
            updator.update(e,value,row,index);
        },
        data : pageData
    };
    window.operateEvents = operateEvents;
}
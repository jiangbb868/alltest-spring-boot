
function PageObject(data) {

    this.data = {
        "title":"数据源配置",
        "object":"数据源",
        "addURL":"/sas/datasource/add",
        "updateURL":"/sas/datasource/update",
        "deleteURL":"/sas/datasource/delete",
        "queryURL":"/sas/datasource/list",
        "fields":[
            {"name":"数据源名", "code":"dataSource", "type":"text", "width":80, "param":""},
            {"name":"数据源类型", "code":"dataSourceType", "type":"select", "width":30, "selectType":"b7ae904e-8392-11e9-ace8-cbe2a0556a26"},
            {"name":"加载器", "code":"loader", "type":"text", "width":30, "param":""},
            {"name":"配置", "code":"config", "type":"text", "width":300, "param":""}
    ]};

    this.initBody = function initBody() {

        var form = "<div id=\"bidderDiv\" style=\"display: none; \">\n"
                   + "    <div style=\"margin:20px\">\n"
                   + "    <form role=\"form\">";
        var fields = this.data["fields"];
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
        form += "    </form>\n"
                + "    </div>\n"
                + "</div>\n";

        var html = form
               + "<div class=\"container\">\n"
               + "\n"
               + "    <h3 align=\"center\">"+this.data["title"]+"</h3>\n"
               + "    <div class=\"btn-group\">\n"
               + "        <button id=\"home\" type=\"button\" class=\"btn btn-sm btn-primary\">首页</button>\n"
               + "        <button id=\"add\" type=\"button\" class=\"btn btn-sm btn-primary\">添加"+this.data["object"]+"</button>\n"
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
          title : '添加'+this.data["object"],
          content : $('#bidderDiv')
      });

       var fields = this.data["fields"];
       for (var i = 0; i < fields.length; i ++) {
           var field = fields[i];
           if (field["type"] === "select") {
               initLookupSelect(field["code"], field["selectType"]);
           } else {
               $("#"+field["code"]).val("");
           }
       }

       $("#dilivery").off("click").on("click", function() {
           var record = {};
           var fields = this.data["fields"];
           for (var i = 0; i < fields.length; i ++) {
               var field = fields[i];
               record[field["code"]] = $("#"+field["code"]).val();
           }
           $.ajax({
              type : "POST",
              url : this.data["addURL"],
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

   this.onUpdate = function onUpdate(e, value, row, index) {
        layer.open({
               type : 1,
               area : [ '900px', '540px' ],
               shadeClose : true, //点击遮罩关闭
               title : '修改'+this.data["object"],
               content : $('#bidderDiv')
           });
       var fields = this.data["fields"];
       $("#id").val(row["id"]);
       for (var i = 0; i < fields.length; i ++) {
           var field = fields[i];
           if (field["type"] === "select") {
               initLookupSelect(field["code"], field["selectType"]);
           }
           $("#"+field["code"]).val(row[field["code"]]);
       }

        $("#dilivery").off("click").on("click", function() {
            var record = {
                id : row["id"]
            }
            var fields = this.data["fields"];
            for (var i = 0; i < fields.length; i ++) {
                var field = fields[i];
                record[field["code"]] = $("#"+field["code"]).val();
            }

            //Ajax调用处理
            $.ajax({
               type : "POST",
               url : this.data["updateURL"],
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

   this.onDelete = function onDelete(e, value, row, index) {
       layer.msg("确认删除?", {
           time : 0,
           icon : 7,
           btn : [ "是", "否" ],
           yes : function(index) {
               layer.close(index);
               $.ajax({
                  type : "POST",
                  url : this.data["deleteURL"],
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

   this.initTable = function initTable() {
       var oTableInit = new Object();
       oTableInit.data = this.data;
       oTableInit.Init = function() {
           var columns = [];
           var i = 0;
           var fields = this.data["fields"];
           for (i = 0; i < fields.length; i ++) {
               var field = fields[i];
               var column = {};
               column["field"] = field["code"];
               column["title"] = field["name"];
               column["colspan"] = 1;
               column["width"] = field["width"];
               columns[i] = column;
           }
           var operate = {
               field : 'operate',
               title : '操作',
               colspan: 1,
               width: 80,
               events : operateEvents,
               formatter : operateFormatter
           };
           columns[i] = operate;

           $('#ArbetTable').bootstrapTable({
               url : this.data["queryURL"],
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

    window.operateEvents = {
        "click #delete" : function(e, value, row, index) {
            return new this.onDelete(e,value,row,index);
        },
        "click #edit" : function(e, value, row, index) {
            alert(this.data);
            layer.open({
                           type : 1,
                           area : [ '900px', '540px' ],
                           shadeClose : true, //点击遮罩关闭
                           title : '修改'+this.data["object"],
                           content : $('#bidderDiv')
                       });
            var fields = this.data["fields"];
            $("#id").val(row["id"]);
            for (var i = 0; i < fields.length; i ++) {
                var field = fields[i];
                if (field["type"] === "select") {
                    initLookupSelect(field["code"], field["selectType"]);
                }
                $("#"+field["code"]).val(row[field["code"]]);
            }

            $("#dilivery").off("click").on("click", function() {
                var record = {
                    id : row["id"]
                }
                var fields = this.data["fields"];
                for (var i = 0; i < fields.length; i ++) {
                    var field = fields[i];
                    record[field["code"]] = $("#"+field["code"]).val();
                }

                //Ajax调用处理
                $.ajax({
                           type : "POST",
                           url : this.data["updateURL"],
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
        }
    }
}
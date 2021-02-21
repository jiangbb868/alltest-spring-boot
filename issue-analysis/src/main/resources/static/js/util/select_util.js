    var ciType = [
        ["d81a4a90-839b-11e9-b846-23d922fea2ec","/sas/datasource/list"],
        ["4eec2496-8391-11e9-9efc-73158419bc0c","/sas/object/list"],
        ["0c8e2cea-8394-11e9-a2a2-bbce877b27f0","/sas/indicator/list"],
        ["sasDimRelation","/sas/dimRelation/list"]
    ];

    function findURL(ciTypeId) {
        for (var i = 0; i < ciType.length; i ++) {
            if (ciTypeId === ciType[i][0]) {
                return ciType[i][1];
            }
        }
        return null;
    }
    /**
     * 动态生成select选项
     * @param selectId
     * @param lookupType
     * @returns
     */
    function initLookupSelect(selectId, lookupType) {
        var selectObj = $("#" + selectId);
        $.ajax({
           url : "/sas/lookup/list",
           async : false,
           type : "GET",
           data : "lookupType=" + lookupType,
           contentType : "application/x-www-form-urlencoded",
           success : function(result) {
               if (result.success) {
                   var lookups = result.data;
                   selectObj.find("option").remove();
                   for (var i in lookups) {
                       var lookup = lookups[i];
                       var optionValue = lookup.name;
                       var optionText = lookup.displayName;
                       selectObj.append(new Option(optionText, optionValue));
                   }

                   // 刷新select
                   selectObj.selectpicker('refresh');
               } else {
                   toastr.error('获取['+ lookupType + ']信息失败，原因：' + result.errorMessage);
               }
           },
           error : function(result) {
               toastr.error('获取['+ lookupType + ']信息失败，原因：' + result.errorMessage);
           }
       });// ajax
    }

    /**
     * 动态生成引用选项
     * @param selectId
     * @param referenceType
     * @returns
     */
    function initReferenceSelect(selectId, referenceType, field) {
        var selectObj = $("#" + selectId);
        var URL = findURL(referenceType);
        if (URL == null) {
            return;
        }
        $.ajax({
           url : URL,
           async : false,
           type : "GET",
           contentType : "application/x-www-form-urlencoded",
           success : function(result) {
               if (result != null) {
                   var jsonResult = JSON.parse(result);
                   var objects = jsonResult.rows;
                   selectObj.find("option").remove();
                   for (var i in objects) {
                       var object = objects[i];
                       var optionValue = object[field];
                       var optionText = object.displayName;
                       selectObj.append(new Option(optionText, optionValue));
                   }

                   // 刷新select
                   selectObj.selectpicker('refresh');
               } else {
                   toastr.error('获取['+ referenceType + ']信息失败，原因：' + result.errorMessage);
               }
           },
           error : function(result) {
               toastr.error('获取['+ referenceType + ']信息失败，原因：' + result.errorMessage);
           }
       });// ajax
    }

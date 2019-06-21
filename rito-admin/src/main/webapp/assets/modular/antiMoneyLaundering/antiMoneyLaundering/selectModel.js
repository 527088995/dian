(function () {
    // 指定图表的配置项和数据
    var options = [{
        "value": ""
        ,"alias": "请选择"
    }
        ,{
            "value": "居民身份证或临时居民身份证"
            ,"alias": "居民身份证或临时居民身份证"
        }
        ,{
            "value": "港澳居民来往大陆通行证"
            ,"alias": "港澳居民来往大陆通行证"
        }
        ,{
            "value": "台湾居民来往大陆通行证"
            ,"alias": "台湾居民来往大陆通行证"
        }
        ,{
            "value": "港澳居民居住证"
            ,"alias": "港澳居民居住证"
        }
        ,{
            "value": "台湾居民居住证"
            ,"alias": "台湾居民居住证"
        }
        ,{
            "value": "外国护照"
            ,"alias": "外国护照"
        }
        ,{
            "value": "中华人民共和国因公护照"
            ,"alias": "中华人民共和国因公护照"
        }
        ,{
            "value": "中华人民共和国因私护照"
            ,"alias": "中华人民共和国因私护照"
        }
        ,{
            "value": "统一社会信用代码"
            ,"alias": "统一社会信用代码"
        }
        ,{
            "value": "组织机构代码"
            ,"alias": "组织机构代码"
        },{
            "value": "N"
            ,"alias": "@N"
        }];

    var model_html = "{{#  layui.each(d, function(index, item){ }}<option value={{item.value}}>{{item.alias}}</option>{{#  }); }}";

    //使用拓展模块
    layui.use(['laytpl'], function() {
        var laytpl = layui.laytpl;
        var dom = document.getElementsByClassName("model_select");
        laytpl(model_html).render(options, function (options) {
            for (var i = dom.length - 1; i >= 0; i--) {
                dom[i].innerHTML = options;
            }
        })
    });
    })()
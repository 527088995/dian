layui.use(['form'], function (){

    var form = layui.form;


//地址编码
var cityCode={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",
    34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",
    52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
//校验
form.verify({
    //身份证详细校验
    IDNumber:function(value, item){ //value：表单的值、item：表单的DOM对象
        if(!value || !/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(value)){
            return '身份证号格式错误';
        }
        else if(!cityCode[value.substr(0,2)]){
            return '地址编码错误';
        }
        else {
            //18位身份证需要验证最后一位校验位
            if(value.length == 18){
                value = value.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = value[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != value[17]){
                    return  "校验位错误";
                }
            }
        }

    },

    //验证证件类型是否含有全角字符
    card:function (value, item) {
        for(var i=0;i<value.length;i++){
            strCode=value.charCodeAt(i);
            if((strCode>65248)||(strCode==12288)){
                return '有全角字符';
            }
        }
    },

    //证件号码长度
    cardLength:function (value, item) {
        if(value.length < 6){
            return '证件号码必须大于或等于6位';
        }
    },

    //账号验证
    account:function (value, item) {
        if(!/^[a-zA-Z0-9(\-)]$/.test(value)){
            return '账号中不得含有除数字、字母、“-”之外的字符';
        }
        if(/^(\d)\1+$/.test(value)&&/^\d/.test(value)){
            return '账号字段不得全部为连续相同的数字';
        }
    },

    //日期验证
    // dateFormat:function (value, item) {
    //     if(){
    //
    //     }
    // }

    //验证证件号码括号是否对称
    bracket:function (value, item) {
        var arr = value.split('') // 将传入字符串转化为数组
        var stack = new Stack() // 新建栈
        var aString = '({[]})'  // 用来匹配的字符串
        var index = -1          //初始化下标

        for (var i = 0; i < arr.length; i++) {
            var item = arr[i]
            if ((index = aString.indexOf(item)) < 3) {   // 将每一项都用aString匹配，下标小于3就是左括号
                // 左括号，入栈
                stack.into(item)
            } else {
                //  右括号，栈列表出栈一个元素与之匹配
                var target = stack.out()
                //  如果出栈元素不存在，则这个右括号没有响应匹配的左括号，验证失败
                if (!target) {
                    return '证件号码括号不对称'
                }
                // 取出与 item 对应的左括号与出栈元素匹配，不相等则验证失败
                if (target !== aString.charAt(5-index)) {
                    return '证件号码括号不对称'
                }
            }
        }
        //  循环匹配完成后，如果栈列表还有元素，则缺少与之匹配的右括号，验证失败
        if (stack.size()) {
            return false
        }
    }

});

})
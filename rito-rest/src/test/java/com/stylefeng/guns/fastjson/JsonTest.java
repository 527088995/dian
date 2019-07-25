package com.stylefeng.guns.fastjson;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.SimpleObject;
import com.stylefeng.guns.rest.modular.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.rest.modular.auth.security.impl.Base64SecurityAction;

/**
 * json测试
 *
 * @author ...
 * @date 2017-08-25 16:11
 */


public class JsonTest {

    public static void main(String[] args) {
        String randomKey = "0h7bsj";

        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setUser("fsn");
        simpleObject.setAge(11);
        simpleObject.setName("zzzz");
        simpleObject.setTips("code");

        String json = JSON.toJSONString(simpleObject);

        String encode = new Base64SecurityAction().doAction(json);
        //md5签名
        String md5 = MD5Util.encrypt(encode + randomKey);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));
    }
}

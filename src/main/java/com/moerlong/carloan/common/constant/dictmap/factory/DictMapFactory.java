package com.moerlong.carloan.common.constant.dictmap.factory;

import com.moerlong.carloan.common.constant.dictmap.base.AbstractDictMap;
import com.moerlong.carloan.common.constant.dictmap.base.SystemDict;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;

/**
 * 字典的创建工厂
 *
 * @author hwl
 * @date 2017-05-06 15:12
 */
public class DictMapFactory {

    private static final String basePath = "com.moerlong.carloan.common.constant.dictmap.";

    /**
     * 通过类名创建具体的字典类
     */
    public static AbstractDictMap createDictMap(String className) {
        if("SystemDict".equals(className)){
            return new SystemDict();
        }else{
            try {
                Class<AbstractDictMap> clazz = (Class<AbstractDictMap>) Class.forName(basePath + className);
                return clazz.newInstance();
            } catch (Exception e) {
                throw new BussinessException(BizExceptionEnum.ERROR_CREATE_DICT);
            }
        }
    }
}

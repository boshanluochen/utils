package cn.sycamore.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;

import cn.sycamore.util.valid;

/**
 * �ṩһЩBeanUti��Ҫ�ķ���
 */
public class Factory {

    /**
     * ���BeanUtil��������JavaBean����
     * ֻ��ȡ�򵥵������ֶ�
     */
    public static Map<String,Map<String,BeanStruct>> BEAN_SIMPLE_PROPERTIES = new Hashtable<>();


    /**
     * ���BeanUtil��������JavaBean����
     * ֻ��ȡ�򵥵������ֶ�(�����ֶ����ֵĴ�Сд)
     */
    public static Map<String,Map<String,BeanStruct>> BEAN_SIMPLE_PROPERTIESIGNORE = new Hashtable<>();


    static {
        //����ʵ��ʵ����ȷ��JavaBean������
    }

    public static boolean isDeclaredField(String className, String pro) throws ClassNotFoundException {
        Class   classz = Class.forName(className);
        Field[] fields = classz.getFields();
        if (valid.valid(fields)) {
            for (Field f : fields) {
                if (f.getName().equals(pro)) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * ��JavaBean���н�����������static������
     *
     * @param obj
     */
    public static void add(Object obj) throws IntrospectionException, ClassNotFoundException {
        add(obj.getClass());
    }


    public static void add(Class clazz) throws IntrospectionException, ClassNotFoundException {
        String className = clazz.getName();
        if (!valid.valid(BEAN_SIMPLE_PROPERTIES.get(className))) {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
            if (proDescrtptors != null && proDescrtptors.length > 0) {
                Map<String,BeanStruct> simpleProperties = new Hashtable<>();
                Map<String,BeanStruct> simplePropertiesIgnore = new Hashtable<>();
                for (PropertyDescriptor propDesc : proDescrtptors) {
                    String fieldName = propDesc.getName();
                    if (!"class".equals(fieldName)) {
                        Object type = propDesc.getPropertyType();
                        Method readMethod = propDesc.getReadMethod();
                        Method writeMethod = propDesc.getWriteMethod();
                        boolean isDeclared = isDeclaredField(className, fieldName);
                        simpleProperties.put(fieldName, new BeanStruct(fieldName, type, readMethod, writeMethod, isDeclared));
                        simplePropertiesIgnore.put(fieldName.toLowerCase(), new BeanStruct(fieldName, type, readMethod, writeMethod, isDeclared));
                    }
                }
                BEAN_SIMPLE_PROPERTIES.put(className, simpleProperties);
                BEAN_SIMPLE_PROPERTIESIGNORE.put(className, simplePropertiesIgnore);
            }
        }
    }

}

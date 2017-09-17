package cn.sycamore.bean;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.sycamore.util.valid;

/**
 * JavaBean��ص�һЩ����
 */
public final class BeanUtil {
	   private final static Map<String,BeanStruct> simpleProperties(Object obj) {
	        return Factory.BEAN_SIMPLE_PROPERTIES.get(obj.getClass().getName());
	    }

	    private final static Map<String,BeanStruct> simplePropertiesIgnore(Object obj) {
	        return Factory.BEAN_SIMPLE_PROPERTIESIGNORE.get(obj.getClass().getName());
	    }

	    private final static Method getReadMethod(Object obj, String pro) {
	        BeanStruct st = simpleProperties(obj).get(pro);
	        return st.getReadMethod();
	    }

	    private final static Method getWriteMethod(Object obj, String pro) {
	        BeanStruct st = simpleProperties(obj).get(pro);
	        return st.getWriteMethod();
	    }

	    private final static Method getReadMethodIgnore(Object obj, String pro) {
	        BeanStruct st = simplePropertiesIgnore(obj).get(pro);
	        return st.getReadMethod();
	    }

	    private final static Method getWriteMethodIgnore(Object obj, String pro) {
	        BeanStruct st = simplePropertiesIgnore(obj).get(pro);
	        return st.getWriteMethod();
	    }

	    private final static Object readMethod(Object bean,
	            Method readMethod) throws InvocationTargetException, IllegalAccessException {
	        return readMethod.invoke(bean);
	    }

	    private final static void writeMethod(Object bean, Method writeMethod,
	            Object value) throws InvocationTargetException, IllegalAccessException {
	        writeMethod.invoke(bean, value);
	    }


	    /**
	     * ���Bean��BeanFactory�Ľ�����Χ��
	     *
	     * @param obj ��Ŀ��obj���뵽BeanFactory�Ľ�����Χ��
	     */
	    public final static void add(Object obj) {
	        try {
	            Factory.add(obj);
	        } catch (IntrospectionException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * ���Bean��BeanFactory�Ľ�����Χ��
	     *
	     * @param clazz ��Ŀ��clazz���뵽BeanFactory�Ľ�����Χ��
	     */
	    public final static void add(Class clazz) {
	        try {
	            Factory.add(clazz);
	        } catch (IntrospectionException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }


	    /**
	     * �ж������Ƿ����
	     *
	     * @param bean �жϵ�Ŀ��bean
	     * @param pro  �жϵ�����
	     * @return �Ƿ����
	     */
	    public final static boolean hasProperty(Object bean, String pro) {
	        add(bean);
	        Map map = simpleProperties(bean);
	        return map.containsKey(pro);
	    }


	    /**
	     * �ж��Լ�����Ķ��Ǽ̳е�����pro�Ƿ����
	     *
	     * @param bean �жϵ�Ŀ��bean
	     * @param pro  �жϵ�����
	     * @return �Ƿ����
	     */
	    public final static boolean hasDeclaredProperty(Object bean, String pro) {
	        add(bean);
	        Map        map = simpleProperties(bean);
	        BeanStruct st  = (BeanStruct) map.get(pro);
	        return valid.valid(st) && st.isDeclared();
	    }

	    /**
	     * �ж������Ƿ���ں��Դ�Сд
	     *
	     * @param bean �жϵ�Ŀ��bean
	     * @param pro  �жϵ�����
	     * @return �Ƿ����
	     */
	    public final static boolean hasPropertyIgnoreCase(Object bean, String pro) {
	        add(bean);
	        Map map = simplePropertiesIgnore(bean);
	        return map.containsKey(pro.toLowerCase());
	    }


	    /**
	     * ʹ���Զ���Ĺ�����
	     *
	     * @param bean   �жϵ�Ŀ��bean
	     * @param pro    �жϵ�����
	     * @param filter �Զ�������Թ��˺���
	     * @return �Ƿ����
	     */
	    public final static boolean hasPropertyFilter(Object bean, String pro, PropertyFilter filter) {
	        add(bean);
	        pro = filter.Properties(pro);
	        Map<String,BeanStruct> map = simpleProperties(bean);
	        if (valid.valid(map)) {
	            Set<String> set = map.keySet();
	            for (String s : set) {
	                if (pro.equals(filter.Properties(s))) {
	                    return true;
	                }
	            }
	        }
	        return false;
	    }

	    /**
	     * ��ȡ���������
	     *
	     * @param bean �жϵ�Ŀ��bean
	     * @param pro  �жϵ�����
	     * @return ���Զ�Ӧ��ֵ
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static Object getProperty(Object bean, String pro) throws InvocationTargetException, IllegalAccessException {
	        add(bean);
	        return readMethod(bean, getReadMethod(bean, pro));
	    }

	    /**
	     * ��ȡ���������
	     *
	     * @param bean ������Bean
	     * @param pro  ��������
	     * @return �������Ե�ֵ��������쳣���ؿ�
	     */
	    public final static Object getPropertyPeaceful(Object bean, String pro) {
	        add(bean);
	        Object result = null;
	        try {
	            result = readMethod(bean, getReadMethod(bean, pro));
	        } catch (InvocationTargetException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }




	    /**
	     * ��ȡ���������(�����������ִ�Сд)
	     *
	     * @param bean ������Bean
	     * @param pro  ��������
	     * @return �������Ե�ֵ��������쳣���ؿ�
	     */
	    public final static Object getPropertyIgnoreCase(Object bean,
	            String pro) throws InvocationTargetException, IllegalAccessException {
	        add(bean);
	        return readMethod(bean, getReadMethodIgnore(bean, pro));
	    }

	    /**
	     * ��ȡ���������(�����������ִ�Сд)
	     *
	     * @param bean ������Bean
	     * @param pro  ��������
	     * @return �������Ե�ֵ��������쳣���ؿ�
	     */
	    public final static Object getPropertyIgnoreCasePeaceful(Object bean, String pro) {
	        add(bean);
	        Object result = null;
	        try {
	            result = readMethod(bean, getReadMethodIgnore(bean, pro));
	        } catch (InvocationTargetException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

	    /**
	     * ʹ���Զ���Ĺ�������ȡ��������Ի�ȡ���������
	     *
	     * @param bean   ������Bean
	     * @param pro    ��������
	     * @param filter �Զ���Ĺ��˺���
	     * @return �������Ե�ֵ��������쳣���ؿ�
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static Object getPropertyFilter(Object bean, String pro,
	            PropertyFilter filter) throws InvocationTargetException, IllegalAccessException {
	        add(bean);
	        Object result = null;
	        pro = filter.Properties(pro);
	        Map<String,BeanStruct> map = simpleProperties(bean);
	        if (valid.valid(map)) {
	            Set<String> set = map.keySet();
	            for (String s : set) {
	                if (pro.equals(filter.Properties(s))) {
	                    result = readMethod(bean, getReadMethod(bean, s));
	                }
	            }
	        }
	        return result;
	    }

	    /**
	     * ʹ���Զ���Ĺ�������ȡ���������
	     *
	     * @param bean   ������Bean
	     * @param pro    ��������
	     * @param filter �Զ���Ĺ��˺���
	     * @return �������Ե�ֵ��������쳣���ؿ�
	     */
	    public final static Object getPropertyFilterPeaceful(Object bean, String pro, PropertyFilter filter) {
	        add(bean);
	        Object result = null;
	        pro = filter.Properties(pro);
	        Map<String,BeanStruct> map = simpleProperties(bean);
	        if (valid.valid(map)) {
	            Set<String> set = map.keySet();
	            try {
	                for (String s : set) {
	                    if (pro.equals(filter.Properties(s))) {
	                        result = readMethod(bean, getReadMethod(bean, s));
	                    }
	                }
	            } catch (InvocationTargetException | IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	        return result;
	    }


	    /**
	     * ���ö��������
	     *
	     * @param bean  ������Bean
	     * @param pro   ��������
	     * @param value �������Ե�ֵ
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static void setProperty(Object bean, String pro,
	            Object value) throws InvocationTargetException, IllegalAccessException {
	        add(bean);
	        writeMethod(bean, getWriteMethod(bean, pro), value);
	    }

	    /**
	     * ���ö��������
	     *
	     * @param bean  ������Bean
	     * @param pro   ��������
	     * @param value �������Ե�ֵ
	     */
	    public final static void setPropertyPeaceful(Object bean, String pro, Object value) {
	        add(bean);
	        try {
	            writeMethod(bean, getWriteMethod(bean, pro), value);
	        } catch (InvocationTargetException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }





	    /**
	     * ���ö�������Ժ��Դ�Сд
	     *
	     * @param bean  ������Bean
	     * @param pro   ��������
	     * @param value �������Ե�ֵ
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static void setPropertyIgnoreCase(Object bean, String pro,
	            Object value) throws InvocationTargetException, IllegalAccessException {
	        add(bean);
	        writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
	    }

	    /**
	     * ���ö�������Ժ��Դ�Сд
	     *
	     * @param bean  ������Bean
	     * @param pro   ��������
	     * @param value �������Ե�ֵ
	     */
	    public final static void setPropertyIgnoreCasePeaceful(Object bean, String pro, Object value) {
	        add(bean);
	        try {
	            writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
	        } catch (InvocationTargetException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	    }


	    /**
	     * ʹ���Զ����filter����������ֵ
	     *
	     * @param bean   ������Bean
	     * @param pro    ��������
	     * @param value  �������Ե�ֵ
	     * @param filter �Զ���ĺ���
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static void setPropertyFilter(Object bean, String pro, Object value,
	            PropertyFilter filter) throws InvocationTargetException, IllegalAccessException {
	        add(bean);
	        pro = filter.Properties(pro);
	        Map<String,BeanStruct> map = simpleProperties(bean);
	        if (valid.valid(map)) {
	            Set<String> set = map.keySet();
	            for (String s : set) {
	                if (pro.equals(filter.Properties(s))) {
	                    writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
	                }
	            }

	        }
	    }

	    /**
	     * ʹ���Զ����filter����������ֵ
	     *
	     * @param bean   ������Bean
	     * @param pro    ��������
	     * @param value  �������Ե�ֵ
	     * @param filter �Զ���ĺ���
	     */
	    public final static void setPropertyFilterPeaceful(Object bean, String pro, Object value, PropertyFilter filter) {
	        add(bean);
	        pro = filter.Properties(pro);
	        Map<String,BeanStruct> map = simpleProperties(bean);
	        if (valid.valid(map)) {
	            Set<String> set = map.keySet();
	            try {
	                for (String s : set) {
	                    if (pro.equals(filter.Properties(s))) {
	                        writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
	                    }
	                }
	            } catch (InvocationTargetException | IllegalAccessException e) {
	                e.printStackTrace();
	            }

	        }
	    }


	    /**
	     * ��������ָ��������
	     *
	     * @param srcBean  ԴBean
	     * @param destBean Ŀ��Bean
	     * @param pros     copy������
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static void copyProperty(Object srcBean, Object destBean,
	            String[] pros) throws InvocationTargetException, IllegalAccessException {
	        add(srcBean);
	        add(destBean);
	        if (valid.valid(pros)) {
	            for (String s : pros) {
	                Object value = readMethod(srcBean, getReadMethod(srcBean, s));
	                writeMethod(destBean, getWriteMethod(destBean, s), value);
	            }
	        }
	    }

	    /**
	     * ��������ָ��������
	     *
	     * @param srcBean  ԴBean
	     * @param destBean Ŀ��Bean
	     * @param pros     copy������
	     */
	    public final static void copyPropertyPeaceful(Object srcBean, Object destBean, String[] pros) {
	        add(srcBean);
	        add(destBean);
	        if (valid.valid(pros)) {
	            try {
	                for (String s : pros) {
	                    Object value =readMethod(srcBean, getReadMethod(srcBean, s));
	                    writeMethod(destBean, getWriteMethod(destBean, s),value );
	                }
	            } catch (InvocationTargetException | IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    /**
	     * ����ͬ������
	     *
	     * @param srcBean  ԴBean
	     * @param destBean Ŀ��Bean
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static void copyProperties(Object srcBean,
	            Object destBean) throws InvocationTargetException, IllegalAccessException {
	        add(srcBean);
	        add(destBean);
	        Map<String,BeanStruct> srcMap       = simpleProperties(srcBean);
	        Map<String,BeanStruct> dstMap       = simpleProperties(destBean);
	        Map<String,BeanStruct> intersection = CollectionUtil.intersection(srcMap, dstMap);
	        for (Map.Entry<String,BeanStruct> entry : intersection.entrySet()) {
	            String key = entry.getKey();
	            Object value = readMethod(srcBean, getReadMethod(srcBean, key));
	            writeMethod(destBean, getWriteMethod(destBean, key), value);
	        }
	    }

	    /**
	     * ����ͬ������
	     *
	     * @param srcBean  ԴBean
	     * @param destBean Ŀ��Bean
	     */
	    public final static void copyPropertiesPeaceful(Object srcBean, Object destBean) {
	        add(srcBean);
	        add(destBean);
	        Map<String,BeanStruct> srcMap       = simpleProperties(srcBean);
	        Map<String,BeanStruct> dstMap       = simpleProperties(destBean);
	        Map<String,BeanStruct> intersection = CollectionUtil.intersection(srcMap, dstMap);
	        for (Map.Entry<String,BeanStruct> entry : intersection.entrySet()) {
	            String key = entry.getKey();
	            try{
	                //Ϊʲô�Ὣtryд�����������foreach�����棿
	                //������뾡���ܶ�ĸ������ԵĻ������
	                Object value = readMethod(srcBean, getReadMethod(srcBean, key));
	                writeMethod(destBean, getWriteMethod(destBean, key), value);
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    /**
	     * ����ͬ������(���Դ�Сд)
	     *
	     * @param srcBean  ԭBean
	     * @param destBean Ŀ��Bean
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static void copyPropertiesIgnoreCase(Object srcBean,
	            Object destBean) throws InvocationTargetException, IllegalAccessException {
	        add(srcBean);
	        add(destBean);
	        Map<String,BeanStruct> srcMap       = simplePropertiesIgnore(srcBean);
	        Map<String,BeanStruct> dstMap       = simplePropertiesIgnore(destBean);
	        Map<String,BeanStruct> intersection = CollectionUtil.intersection(srcMap, dstMap);
	        for (Map.Entry entry : intersection.entrySet()) {
	            String key = (String) entry.getKey();
	            Object value = readMethod(srcBean, getReadMethodIgnore(srcBean, key));
	            writeMethod(destBean, getWriteMethodIgnore(destBean, key), value);
	        }
	    }

	    /**
	     * ����ͬ������(���Դ�Сд)
	     *
	     * @param srcBean  ԭBean
	     * @param destBean Ŀ��Bean
	     */
	    public final static void copyPropertiesIgnoreCasePeaceful(Object srcBean, Object destBean)  {
	        add(srcBean);
	        add(destBean);
	        Map<String,BeanStruct> srcMap       = simplePropertiesIgnore(srcBean);
	        Map<String,BeanStruct> dstMap       = simplePropertiesIgnore(destBean);
	        Map<String,BeanStruct> intersection = CollectionUtil.intersection(srcMap, dstMap);
	        for (Map.Entry entry : intersection.entrySet()) {
	            String key = (String) entry.getKey();
	            Object value = null;
	            try {
	                value = readMethod(srcBean, getReadMethodIgnore(srcBean, key));
	                writeMethod(destBean, getWriteMethodIgnore(destBean, key), value);
	            } catch (InvocationTargetException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }

	        }
	    }


	    /**
	     * ʹ���Զ�������Թ��˺���
	     *
	     * @param srcBean  ԭBean
	     * @param destBean Ŀ��bean
	     * @param filter   �Զ���Ĺ��˺���
	     * @throws InvocationTargetException
	     * @throws IllegalAccessException
	     */
	    public final static void copyProperties(Object srcBean, Object destBean,
	            PropertyFilter filter) throws InvocationTargetException, IllegalAccessException {
	        add(srcBean);
	        add(destBean);
	        Map<String,BeanStruct> srcMap = simpleProperties(srcBean);
	        Map<String,BeanStruct> dstMap = simpleProperties(destBean);
	        if (valid.valid(srcMap, dstMap)) {
	            Map<String,String> srcMapFilter = new HashMap<>();
	            Map<String,String> dstMapFilter = new HashMap<>();
	            for (Map.Entry<String,BeanStruct> entry : srcMap.entrySet()) {
	                srcMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
	            }
	            for (Map.Entry<String,BeanStruct> entry : dstMap.entrySet()) {
	                dstMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
	            }
	            Map<String,String> intersection = CollectionUtil.intersection(srcMapFilter, dstMapFilter);
	            if (valid.valid(intersection)) {
	                for (Map.Entry<String,String> entry : intersection.entrySet()) {
	                    String key = entry.getKey();
	                    String srcKey = srcMapFilter.get(key);
	                    String dstKey = dstMapFilter.get(key);
	                    Object value = readMethod(srcBean, getReadMethod(srcBean, srcKey));
	                    writeMethod(destBean, getWriteMethod(destBean, dstKey), value);
	                }
	            }
	        }
	    }

	    /**
	     * ʹ���Զ�������Թ��˺���
	     *
	     * @param srcBean  ԭBean
	     * @param destBean Ŀ��bean
	     * @param filter   �Զ���Ĺ��˺���
	     */
	    public final static void copyPropertiesPeaceful(Object srcBean, Object destBean, PropertyFilter filter)  {
	        add(srcBean);
	        add(destBean);
	        Map<String,BeanStruct> srcMap = simpleProperties(srcBean);
	        Map<String,BeanStruct> dstMap = simpleProperties(destBean);
	        if (valid.valid(srcMap, dstMap)) {
	            Map<String,String> srcMapFilter = new HashMap<>();
	            Map<String,String> dstMapFilter = new HashMap<>();
	            for (Map.Entry<String,BeanStruct> entry : srcMap.entrySet()) {
	                srcMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
	            }
	            for (Map.Entry<String,BeanStruct> entry : dstMap.entrySet()) {
	                dstMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
	            }
	            Map<String,String> intersection = CollectionUtil.intersection(srcMapFilter, dstMapFilter);
	            if (valid.valid(intersection)) {
	                for (Map.Entry<String,String> entry : intersection.entrySet()) {
	                    String key = entry.getKey();
	                    String srcKey = srcMapFilter.get(key);
	                    String dstKey = dstMapFilter.get(key);
	                    try {
	                        Object value = readMethod(srcBean, getReadMethod(srcBean, srcKey));
	                        writeMethod(destBean, getWriteMethod(destBean, dstKey), value);
	                    } catch (InvocationTargetException e) {
	                        e.printStackTrace();
	                    } catch (IllegalAccessException e) {
	                        e.printStackTrace();
	                    }

	                }
	            }
	        }
	    }
}

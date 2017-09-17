package cn.sycamore.bean;

import java.lang.reflect.Method;

/**
 * ����ֶ�������Ϣ
 */
public class BeanStruct {


    //�ֶε�����
    private String  fieldName;
    //�ֶε�����
    private Object  fieldType;
    //�ֶ�ֵ������
    private Method  readMethod;
    //�ֶ�ֵд����
    private Method  writeMethod;
    private boolean isDeclared;

    public BeanStruct(String fieldName, Object fieldType, Method readMethod, Method writeMethod, boolean isDeclared) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.readMethod = readMethod;
        this.writeMethod = writeMethod;
    }

    public boolean isDeclared() {
        return isDeclared;
    }

    public void setDeclared(boolean isDeclared) {
        this.isDeclared = isDeclared;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldType() {
        return fieldType;
    }

    public void setFieldType(Object fieldType) {
        this.fieldType = fieldType;
    }

    public Method getReadMethod() {
        return readMethod;
    }

    public void setReadMethod(Method readMethod) {
        this.readMethod = readMethod;
    }

    public Method getWriteMethod() {
        return writeMethod;
    }

    public void setWriteMethod(Method writeMethod) {
        this.writeMethod = writeMethod;
    }
}
